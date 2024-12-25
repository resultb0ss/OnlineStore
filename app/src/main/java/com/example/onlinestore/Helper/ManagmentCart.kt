package com.example.onlinestore.Helper

import android.content.Context
import android.widget.Toast
import com.example.onlinestore.Model.ItemsModel


class ManagmentCart(val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertItem(item: ItemsModel) {
        var items = getListCart()
        val existAlready = items.any { it.title == item.title }
        val index = items.indexOfFirst { it.title == item.title }

        if (existAlready) {
            items[index].numberInCart = item.numberInCart
        } else {
            items.add(item)
        }
        tinyDB.putListObject("CartList", items)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

    fun minusItem(items: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (items[position].numberInCart == 1) {
            items.removeAt(position)
        } else {
            items[position].numberInCart--
        }
        tinyDB.putListObject("CartList", items)
        listener.onChanged()
    }

    fun plusItem(items: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        items[position].numberInCart++
        tinyDB.putListObject("CartList", items)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val items = getListCart()
        var fee = 0.0
        for (item in items) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}