package com.example.onlinestore.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.databinding.ViewholderRecommendedBinding

class ListItemsAdapter(
    val items: MutableList<ItemsModel>,
    val function: (item: ItemsModel) -> Unit
) :
    RecyclerView.Adapter<ListItemsAdapter.ListItemsHolderAdapter>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListItemsHolderAdapter {
        val binding =
            ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemsHolderAdapter(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ListItemsHolderAdapter,
        position: Int
    ) {
        val item = items[position]

        with(holder.binding) {
            recommendedItemTitleText.text = item.title
            recommendedItemPriceText.text = "$${item.price}"
            recommendedItemRatingText.text = item.rating.toString()
            Glide.with(holder.itemView.context).load(item.picUrl[0]).into(recommendedItemImage)

            root.setOnClickListener {
                function(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    class ListItemsHolderAdapter(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}