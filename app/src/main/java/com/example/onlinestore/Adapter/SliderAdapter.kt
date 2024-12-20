package com.example.onlinestore.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.onlinestore.Model.SliderModel
import com.example.onlinestore.databinding.SliderItemContainerBinding

class SliderAdapter(
    private var sliderItems: List<SliderModel>, private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context
    private val runnable = Runnable {
        sliderItems = sliderItems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SliderViewHolder {
        context = parent.context
        val binding =
            SliderItemContainerBinding.inflate(LayoutInflater.from(context), parent, false)

        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SliderViewHolder, position: Int
    ) {
        holder.setImage(sliderItems[position], context)
        if (position == sliderItems.lastIndex - 1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int = sliderItems.size


    class SliderViewHolder(private val binding: SliderItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("CheckResult")
        fun setImage(sliderItems: SliderModel, context: Context) {

            Glide.with(context).load(sliderItems.url).apply {
                RequestOptions().transform(CenterInside())
            }.into(binding.imageSlide)
        }
    }
}