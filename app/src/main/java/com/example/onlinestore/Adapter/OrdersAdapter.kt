package com.example.onlinestore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestore.Model.Order
import com.example.onlinestore.databinding.ViewholderOrderBinding

class OrdersAdapter(val orders: MutableList<Order>) :
    RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrdersViewHolder {
        val binding =
            ViewholderOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OrdersViewHolder,
        position: Int
    ) {
        val order = orders[position]
        val customId = (100..999).random()
            .toString() + " - " + order.timestamp.toString() + "-" + (10..99).random().toString()
        holder.binding.orderItemIdTextView.text = "Заказ № $customId "
        holder.binding.orderItemTimeStampTextView.text = order.timestamp
        holder.binding.orderItemStatusTextView.text = order.status
        holder.binding.orderItemCoastTextView.text = "${order.coast} руб."
    }

    override fun getItemCount(): Int = orders.size


    class OrdersViewHolder(val binding: ViewholderOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}