package com.example.onlinestore.Utilits

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

fun JsonElement.getJsonValues(): List<String> {

    val list = Json.decodeFromString<Map<String, List<String>>>(this.toString()).values.toList()[0]
    return list
}

