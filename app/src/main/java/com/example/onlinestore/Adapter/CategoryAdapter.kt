package com.example.onlinestore.Adapter

import android.content.res.ColorStateList
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.Model.CategoryModel
import com.example.onlinestore.R
import com.example.onlinestore.databinding.ViewholderCategoryBinding


class CategoryAdapter(
    val items: MutableList<CategoryModel>,
    val function: (id: String, title: String) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CategoryViewHolder {
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder, position: Int
    ) {
        val item = items[position]
        holder.binding.itemCartViewItemTitleText.text = item.title

        Glide.with(holder.itemView.context).load(item.picUrl)
            .into(holder.binding.itemCategoryMainImage)

        if (selectedPosition == position) {
            holder.binding.itemCategoryMainImage.setBackgroundResource(0)
            holder.binding.itemCategoryMainLayout.setBackgroundResource(R.drawable.green_button_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.itemCategoryMainImage,
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.white)
                )
            )
            holder.binding.itemCartViewItemTitleText.visibility = View.VISIBLE
            holder.binding.itemCartViewItemTitleText.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        } else {

            holder.binding.itemCategoryMainImage.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.itemCategoryMainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.itemCategoryMainImage,
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.black)
                )
            )
            holder.binding.itemCartViewItemTitleText.visibility = View.GONE
            holder.binding.itemCartViewItemTitleText.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )

        }

        holder.binding.root.setOnClickListener {
            var position = position
            if (position != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                function(item.id.toString(),item.title)
            }, 1000)
        }
    }

    override fun getItemCount(): Int = items.size


    class CategoryViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}