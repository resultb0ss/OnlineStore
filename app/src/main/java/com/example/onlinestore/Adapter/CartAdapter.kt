package com.example.onlinestore.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.Helper.ChangeNumberItemsListener
import com.example.onlinestore.Helper.ManagmentCart
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        val item = listItemSelected[position]

        holder.binding.itemCartViewItemTitleText.text = item.title
        holder.binding.itemCartViewItemFeeEachTime.text = "$" + item.price
        holder.binding.itemCartViewTotalEachItem.text =
            "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.itemCartViewNumberItemText.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0])
            .into(holder.binding.itemCartViewItemImage)

        holder.binding.itemCartViewPlusCartButton.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }
            })
        }

        holder.binding.itemCartViewMinusCartButton.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }
            })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size


    class CartViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)
}