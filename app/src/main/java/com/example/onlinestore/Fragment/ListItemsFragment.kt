package com.example.onlinestore.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlinestore.databinding.FragmentListItemsBinding


class ListItemsFragment : BaseFragment<FragmentListItemsBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListItemsBinding {
        return FragmentListItemsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = ListItemsFragmentArgs.Companion.fromBundle(requireArguments()).id
        val title = ListItemsFragmentArgs.Companion.fromBundle(requireArguments()).title

        Log.d("@@@","List Fragment $id, $title")
    }

}