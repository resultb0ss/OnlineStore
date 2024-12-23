package com.example.onlinestore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestore.R
import com.example.onlinestore.Utilits.loadImage
import com.example.onlinestore.databinding.ViewholderPictureBinding

class PictureAdapter(
    val items: MutableList<String>,
    private val onImageSelected: (String) -> Unit
) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PictureViewHolder {
        val binding =
            ViewholderPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PictureViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.binding.viewHolderMainImage.loadImage(item)
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            onImageSelected(item)
        }

        if (selectedPosition == position) {
            holder.binding.viewHolderMainLayout.setBackgroundResource(R.drawable.green_bg_selected)
        } else {
            holder.binding.viewHolderMainLayout.setBackgroundResource(R.drawable.gray_bg)
        }
    }

    override fun getItemCount(): Int = items.size


    class PictureViewHolder(val binding: ViewholderPictureBinding) :
        RecyclerView.ViewHolder(binding.root)
}