package com.example.onlinestore.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinestore.Adapter.PictureAdapter
import com.example.onlinestore.Adapter.SelectModelAdapter
import com.example.onlinestore.Helper.ManagmentCart
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.R
import com.example.onlinestore.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        managmentCart = ManagmentCart(requireContext())
        item = DetailFragmentArgs.Companion.fromBundle(requireArguments()).Item

        getBundle()
        initList()
    }

    private fun initList() {
        val modelList = ArrayList<String>()

        item.model.forEach { modelList.add(it) }

        binding.fragmentDetailMainItemModelsRecyclerView.adapter = SelectModelAdapter(modelList)
        binding.fragmentDetailMainItemModelsRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )

        val picList = ArrayList<String>()
        item.picUrl.forEach { picList.add(it) }


        Glide.with(requireContext()).load(picList[0]).into(binding.fragmentDetailMainItemImage)
        binding.fragmentDetailMainItemsRecyclerView.adapter =
            PictureAdapter(picList) { selectedImageUrl ->
                Glide.with(requireContext()).load(selectedImageUrl)
                    .into(binding.fragmentDetailMainItemImage)
            }
        binding.fragmentDetailMainItemsRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle() {

        binding.apply {
            fragmentDetailMainItemTitleText.text = item.title
            fragmentDetailMainItemDescription.text = item.description
            fragmentDetailMainItemPriceText.text = "$" + item.price
            fragmentDetailRatingText.text = "${item.rating} Rating"
            fragmentDetailAddToCartButton.setOnClickListener {
                item.numberInCart = numberOrder
                managmentCart.insertItem(item)
            }
            fragmentDetailBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
            fragmentDetailCartButton.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
            }
        }

    }


}