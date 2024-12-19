package com.example.onlinestore.Model

import java.io.Serializable

data class ItemsModel(
    var id: Int,
    var title: String,
    var description: String,
    var picUrl: ArrayList<String> = ArrayList(),
    var model: ArrayList<String> = ArrayList(),
    var price: Double,
    var rating: Double,
    var numberInCart: Int,
    var showRecommended: Boolean = false,
    var categoryId: Int
) : Serializable
