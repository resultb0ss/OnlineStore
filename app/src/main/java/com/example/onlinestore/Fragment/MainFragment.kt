package com.example.onlinestore.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlinestore.Model.SliderModel
import com.example.onlinestore.R
import com.example.onlinestore.ViewModel.MainViewModel
import com.example.onlinestore.databinding.FragmentMainBinding


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel = MainViewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onResume() {
        super.onResume()
//        initBanner()
        initCategory()
        initRecommended()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
    }

    private fun initRecommended() {
        binding.mainFragmentProgressBarRecommendation.visibility = View.VISIBLE
        viewModel.recommended.observe(requireActivity(), Observer {
            binding.mainFragmentRecyclerViewRecommendation.layoutManager =
                GridLayoutManager(requireActivity(), 2)
//            binding.mainFragmentRecyclerViewRecommendation.adapter = RecommendedAdapter(it)
            binding.mainFragmentProgressBarRecommendation.visibility = View.GONE
        })
//        viewModel.loadRecommended()

    }

    private fun initCategory() {
        binding.mainFragmentProgressBarCategory.visibility = View.VISIBLE
        viewModel.categories.observe(requireActivity(), Observer {
            binding.mainFragmentRecyclerViewCategory.layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL, false
            )
//            binding.mainFragmentRecyclerViewCategory.adapter = CategoryAdapter(it)
            binding.mainFragmentProgressBarCategory.visibility = View.GONE
        })
//        viewModel.loadCategory()
    }

    private fun banners(image: List<SliderModel>) {
//        binding.mainFragmentViewPager2.adapter = SliderAdapter(image,binding.mainFragmentViewPager2)
        binding.mainFragmentViewPager2.clipToPadding = false
        binding.mainFragmentViewPager2.clipChildren = false
        binding.mainFragmentViewPager2.offscreenPageLimit = 3
        binding.mainFragmentViewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.mainFragmentViewPager2.setPageTransformer(compositePageTransformer)

        if (image.size > 1) {
            binding.apply {
                mainFragmentDotIndicator.visibility = View.VISIBLE
                mainFragmentDotIndicator.attachTo(binding.mainFragmentViewPager2)
            }
        }
    }

    private fun initBanner() {
        binding.mainFragmentProgressBarSlider.visibility = View.VISIBLE
        viewModel.banners.observe(requireActivity(), Observer {
            banners(it)
            binding.mainFragmentProgressBarSlider.visibility = View.GONE
        })
//        viewModel.loadBanners()
    }


}