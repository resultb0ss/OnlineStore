package com.example.onlinestore.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinestore.Adapter.CategoryAdapter
import com.example.onlinestore.Adapter.OrdersAdapter
import com.example.onlinestore.ViewModel.MainViewModel
import com.example.onlinestore.databinding.FragmentOrdersBinding

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private val viewModel = MainViewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrdersBinding {
        return FragmentOrdersBinding.inflate(inflater, container, false)
    }


    override fun onResume() {
        super.onResume()
        initOrders()
    }

    private fun initOrders() {
        viewModel.orders.observe(requireActivity(), Observer {
            binding.ordersFragmentRecyclerView.layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL, false
            )
            binding.ordersFragmentRecyclerView.adapter = OrdersAdapter(it)
        })
        viewModel.loadCategory()
    }


}