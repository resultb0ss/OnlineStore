package com.example.onlinestore.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.Utilits.getJsonValues
import com.example.onlinestore.databinding.ViewholderRecommendedBinding
import kotlinx.serialization.json.jsonArray

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

            val jsonElem = item.picUrl.getJsonValues()

            Glide.with(holder.itemView.context).load(jsonElem[0])
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