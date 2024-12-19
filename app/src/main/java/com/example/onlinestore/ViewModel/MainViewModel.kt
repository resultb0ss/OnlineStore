package com.example.onlinestore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinestore.Model.CategoryModel
import com.example.onlinestore.Model.ItemsModel
import com.example.onlinestore.Model.SliderModel

class MainViewModel: ViewModel() {

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _recommended = MutableLiveData<MutableList<ItemsModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val categories: LiveData<MutableList<CategoryModel>> = _category
    val recommended: LiveData<MutableList<ItemsModel>> = _recommended


}