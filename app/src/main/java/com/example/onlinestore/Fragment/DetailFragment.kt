package com.example.onlinestore.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinestore.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = DetailFragmentArgs.Companion.fromBundle(requireArguments()).Item
        Log.d("@@@","Detail Fragment item = $item")
    }


}