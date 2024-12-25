package com.example.onlinestore.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onlinestore.Data.AUTHFIREBASE
import com.example.onlinestore.Utilits.myToast
import com.example.onlinestore.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                AUTHFIREBASE.signInWithCredential(p0).addOnCompleteListener {
                    if (it.isSuccessful) {
                        myToast("Вы успешно авторизованы")
                    } else {
                        myToast(it.exception?.message.toString())
                    }
                }
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                val phone = binding.loginFragmentPhoneField.editText?.text.toString()
                val id = p0
                val action =
                    LoginFragmentDirections.Companion.actionLoginFragmentToEnterSmsFragment(
                        phone,
                        id
                    )
                findNavController().navigate(action)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                myToast(p0.message.toString())
            }
        }

        binding.loginFragmentFloatingActionButton.setOnClickListener{
            val phone = binding.loginFragmentPhoneField.editText?.text.toString()
            if (phone.isEmpty()){
                myToast("Необходимо ввести номер телефона")
            } else {
                login()
            }
        }

        binding.fragmentLoginBackButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun login(){
        val phone = binding.loginFragmentPhoneField.editText?.text.toString()
        val options = PhoneAuthOptions.newBuilder(AUTHFIREBASE)
            .setPhoneNumber(phone)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


}