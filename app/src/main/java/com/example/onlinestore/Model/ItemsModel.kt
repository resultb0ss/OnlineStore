package com.example.onlinestore.Model


import kotlinx.serialization.json.JsonElement
import java.io.Serializable

@kotlinx.serialization.Serializable
data class ItemsModel(
    var id: Int,
    var title: String,
    var description: String,
    var picUrl: JsonElement,
    var model: JsonElement,
    var price: Double,
    var rating: Double,
    var numberInCart: Int = 0,
    var showRecommended: Boolean = false,
    var categoryId: Int
) : Serializable
