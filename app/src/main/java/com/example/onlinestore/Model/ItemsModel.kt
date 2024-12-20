package com.example.onlinestore.Model

import com.google.gson.JsonParser
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import java.io.Serializable

data class ItemsModel(
    var id: Int,
    var title: String,
    var description: String,
    var picUrl: ArrayList<Json>,
    var model: ArrayList<Json>,
    var price: Double,
    var rating: Double,
    var numberInCart: Int,
    var showRecommended: Boolean = false,
    var categoryId: Int
) : Serializable
