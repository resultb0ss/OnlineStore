package com.example.onlinestore.Fragment

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinestore.Adapter.CartAdapter
import com.example.onlinestore.Helper.ChangeNumberItemsListener
import com.example.onlinestore.Helper.ManagmentCart
import com.example.onlinestore.R
import com.example.onlinestore.databinding.FragmentCartBinding


class CartFragment : BaseFragment<FragmentCartBinding>() {

    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(inflater, container, false)
    }

    override fun onResume() {
        super.onResume()
        managmentCart = ManagmentCart(requireContext())
        setVariable()
        initCartList()
        calculatorCart()
    }

    private fun initCartList() {
        binding.fragmentCartRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        Log.d("@@@","Cart Get list()")
        binding.fragmentCartRecyclerView.adapter = CartAdapter(
            managmentCart.getListCart(),
            requireContext(),
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculatorCart()
                }
            })
        with(binding) {
            fragmentCartEmptyText.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setVariable() {
        binding.apply {
            fragmentCartBackButton.setOnClickListener {
                Toast.makeText(requireContext(), "Надо сделать переход назад", Toast.LENGTH_SHORT)
                    .show()
            }

            fragmentCartPaymentMethod1.setOnClickListener {
                fragmentCartPaymentMethod1.setBackgroundResource(R.drawable.green_bg_selected)
                methodIc1.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.green))
                methodTitle1.setTextColor(resources.getColor(R.color.green))
                methodSubtitle1.setTextColor(resources.getColor(R.color.green))

                fragmentCartPaymentMethod2.setBackgroundResource(R.drawable.grey_bg_selected)
                methodIc2.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                methodTitle2.setTextColor(resources.getColor(R.color.black))
                methodSubtitle2.setTextColor(resources.getColor(R.color.grey))
            }

            fragmentCartPaymentMethod2.setOnClickListener {
                fragmentCartPaymentMethod2.setBackgroundResource(R.drawable.green_bg_selected)
                methodIc2.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.green))
                methodTitle2.setTextColor(resources.getColor(R.color.green))
                methodSubtitle2.setTextColor(resources.getColor(R.color.green))

                fragmentCartPaymentMethod1.setBackgroundResource(R.drawable.grey_bg_selected)
                methodIc1.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                methodTitle1.setTextColor(resources.getColor(R.color.black))
                methodSubtitle1.setTextColor(resources.getColor(R.color.grey))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatorCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() * tax * delivery) * 100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        with(binding) {
            fragmentCartTotalFeeText.text = "$${itemTotal}"
            fragmentCarTaxText.text = "$${tax}"
            fragmentCartDeliveryText.text = "$${delivery}"
            fragmentCartTotalText.text = "$${total}"
        }
    }


}