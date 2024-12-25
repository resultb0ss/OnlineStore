package com.example.onlinestore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.databinding.ViewholderRecommendedBinding

class RecommendedAdapter(
    val items: MutableList<ItemsModel>,
    val function: (item: ItemsModel) -> Unit
) :
    RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedViewHolder {
        val binding =
            ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecommendedViewHolder,
        position: Int
    ) {
        val item = items[position]

        with(holder.binding) {
            recommendedItemTitleText.text = item.title
            recommendedItemPriceText.text = "$${item.price}"
            recommendedItemRatingText.text = item.rating.toString()

            Glide.with(holder.itemView.context).load(item.picUrl[0])
                .into(recommendedItemImage)

            root.setOnClickListener {
                function(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    class RecommendedViewHolder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}