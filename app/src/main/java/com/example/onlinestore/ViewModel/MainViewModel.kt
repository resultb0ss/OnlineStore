package com.example.onlinestore.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestore.Data.SupabaseClient.clientDatabase
import com.example.onlinestore.Model.CategoryModel
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.Model.SliderModel
import com.example.onlinestore.Utilits.getJsonValues
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


class MainViewModel : ViewModel() {

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _recommended = MutableLiveData<MutableList<ItemsModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val categories: LiveData<MutableList<CategoryModel>> = _category
    val recommended: LiveData<MutableList<ItemsModel>> = _recommended

    fun loadFiltered(id: String) {
        viewModelScope.launch {
            val list = mutableListOf<ItemsModel>()
            val data = clientDatabase.from("Items").select() {
                filter {
                    eq("categoryId", id)
                }
            }.decodeList<ItemsModel>()
            list.addAll(data)
            Log.d("@@@","Main View Model list id $list")
            _recommended.value = list
        }
    }

    fun loadRecommended() {
        viewModelScope.launch {
            val list = mutableListOf<ItemsModel>()
            val data = clientDatabase.from("Items").select() {
                filter {
                    eq("showRecommended", true)
                }
            }.decodeList<ItemsModel>()
            list.addAll(data)
            Log.d("@@@","Recommended $list")
            _recommended.value = list
        }
    }


    fun loadBanners() {
        viewModelScope.launch {
            val list = mutableListOf<SliderModel>()
            val data = clientDatabase.from("Banner").select().decodeList<SliderModel>()
            list.addAll(data)
            _banner.value = list
        }
    }

    fun loadCategory() {
        viewModelScope.launch {
            val list = mutableListOf<CategoryModel>()
            val data = clientDatabase.from("Category").select().decodeList<CategoryModel>()
            list.addAll(data)
            _category.value = list
        }
    }
}