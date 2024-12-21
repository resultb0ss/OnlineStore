package com.example.onlinestore.Model

import java.io.Serializable

@kotlinx.serialization.Serializable
data class CategoryModel(
    val title: String,
    val id: Int,
    val picUrl: String
): Serializable
