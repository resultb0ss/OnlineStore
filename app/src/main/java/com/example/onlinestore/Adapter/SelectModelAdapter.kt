package com.example.onlinestore.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestore.R
import com.example.onlinestore.databinding.ViewholderModelBinding

class SelectModelAdapter(val items: MutableList<String>) :
    RecyclerView.Adapter<SelectModelAdapter.SelectModelViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectModelViewHolder {
        val binding =
            ViewholderModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectModelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SelectModelViewHolder,
        position: Int
    ) {
        holder.binding.modelText.text = items[position]
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            if (selectedPosition == position) {
                holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_selected)
                holder.binding.modelText.setTextColor(context.resources.getColor(R.color.green))
            } else {
                holder.binding.modelLayout.setBackgroundResource(R.drawable.gray_bg)
                holder.binding.modelText.setTextColor(context.resources.getColor(R.color.black))
            }
        }
    }

    override fun getItemCount(): Int = items.size


    class SelectModelViewHolder(val binding: ViewholderModelBinding) :
        RecyclerView.ViewHolder(binding.root)

}