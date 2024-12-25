package com.example.onlinestore.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onlinestore.Data.AUTHFIREBASE
import com.example.onlinestore.Data.CHILD_ID
import com.example.onlinestore.Data.CHILD_PHONE
import com.example.onlinestore.Data.NODE_PHONES
import com.example.onlinestore.Data.NODE_USERS
import com.example.onlinestore.Data.REF_DATABASE_ROOT
import com.example.onlinestore.Data.UID
import com.example.onlinestore.R
import com.example.onlinestore.Utilits.AppTextWatcher
import com.example.onlinestore.Utilits.myToast
import com.example.onlinestore.databinding.FragmentEnterSmsBinding
import com.google.firebase.auth.PhoneAuthProvider

class EnterSmsFragment : BaseFragment<FragmentEnterSmsBinding>() {

    private lateinit var idForCredential: String
    private lateinit var phone: String

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEnterSmsBinding {
        return FragmentEnterSmsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phone = EnterSmsFragmentArgs.Companion.fromBundle(requireArguments()).number
        idForCredential = EnterSmsFragmentArgs.Companion.fromBundle(requireArguments()).id

        binding.smsEnterFragmentSubtitleText.text =
            "Мы направили сообщение с смс кодом на номер $phone"

        binding.fragmentSmsEnterBackButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.enterSmsFragmentSmsField.editText?.addTextChangedListener(AppTextWatcher { inputText ->
            if (inputText?.length == 6) {
                toMainFragment()
            }
        })
    }

    private fun toMainFragment() {
        val smsCode = binding.enterSmsFragmentSmsField.editText?.text.toString()
        val credential = PhoneAuthProvider.getCredential(idForCredential, smsCode)

        AUTHFIREBASE.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                var dataMap: MutableMap<String, Any> = mutableMapOf<String, Any>()
                dataMap[CHILD_ID] = UID
                dataMap[CHILD_PHONE] = phone
                REF_DATABASE_ROOT.child(NODE_PHONES).child(phone).setValue(UID)
                    .addOnFailureListener {
                        myToast(it.message.toString())
                    }.addOnSuccessListener {
                    REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(dataMap)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                myToast("Вы успешно авторизованы")

                                findNavController()
                                    .navigate(R.id.action_enterSmsFragment_to_mainFragment)

                            } else myToast(it.exception?.message.toString())
                        }
                }
            } else {
                myToast(it.exception?.message.toString())
            }
        }
    }


}