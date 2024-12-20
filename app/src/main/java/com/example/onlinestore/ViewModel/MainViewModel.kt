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
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.Column
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _recommended = MutableLiveData<MutableList<ItemsModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val categories: LiveData<MutableList<CategoryModel>> = _category
    val recommended: LiveData<MutableList<ItemsModel>> = _recommended


    fun loadRecommended(){
        viewModelScope.launch{
            val list = mutableListOf<SliderModel>()
            val data = clientDatabase.from("Items").select{
                filter {
                    eq("showRecommended", true)
                }
            }.decodeList<SliderModel>()
            Log.d("@@@","View Model list urls $data")
            list.addAll(data)
            _banner.value = list
        }
    }


    fun loadBanners(){
        viewModelScope.launch{
            val list = mutableListOf<SliderModel>()
            val data = clientDatabase.from("Banner").select().decodeList<SliderModel>()
            list.addAll(data)
            _banner.value = list
        }
    }

    fun loadCategory(){
        viewModelScope.launch{
            val list = mutableListOf<CategoryModel>()
            val data = clientDatabase.from("Category").select().decodeList<CategoryModel>()
            list.addAll(data)
            _category.value = list
        }
    }
}