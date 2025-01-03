package com.example.onlinestore.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onlinestore.R
import com.example.onlinestore.databinding.FragmentIntroBinding

class IntroFragment : BaseFragment<FragmentIntroBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIntroBinding {
        return FragmentIntroBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_mainFragment)
        }

        binding.introFragmentSignInText.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_loginFragment)
        }

        binding.introFragmentRegistrationText.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_registrationFragment)
        }
    }


}