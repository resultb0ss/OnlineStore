package com.example.onlinestore.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlinestore.Adapter.ListItemsAdapter
import com.example.onlinestore.R
import com.example.onlinestore.ViewModel.MainViewModel
import com.example.onlinestore.databinding.FragmentListItemsBinding


class ListItemsFragment : BaseFragment<FragmentListItemsBinding>() {

    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListItemsBinding {
        return FragmentListItemsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            fragmentDetailBackButton.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_listItemsFragment_to_mainFragment)
            }
            fragmentListItemsProgressBarList.visibility = View.VISIBLE
            viewModel.recommended.observe(requireActivity(), Observer {
                fragmentListItemsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                fragmentListItemsRecyclerView.adapter = ListItemsAdapter(it) { item ->
                    val action =
                        ListItemsFragmentDirections.Companion.actionListItemsFragmentToDetailFragment(
                            item
                        )
                    findNavController().navigate(action)
                }
                fragmentListItemsProgressBarList.visibility = View.GONE
            })
            viewModel.loadFiltered(id)
            Log.d("@@@","Load filtered id$id")
        }
    }

    private fun getBundle() {

        id = ListItemsFragmentArgs.Companion.fromBundle(requireArguments()).id
        title = ListItemsFragmentArgs.Companion.fromBundle(requireArguments()).title
        binding.categoryText.text = title
    }


}