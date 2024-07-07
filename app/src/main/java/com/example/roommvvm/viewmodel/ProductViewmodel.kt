package com.example.roommvvm.viewmodel

import com.example.roommvvm.model.Product
import com.example.roommvvm.model.ProductRepository


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommvvm.model.ApiService
import com.example.taskastro.model.ProductDatabase

import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ProductRepository
    val allProducts: LiveData<List<Product>>

    // MutableLiveData to track the API call status
    private val _apiCallStatus = MutableLiveData<String>()
    val apiCallStatus: LiveData<String> get() = _apiCallStatus

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        val productService = ApiService.create()
        repository = ProductRepository(productService,productDao)
        allProducts = repository.allProducts
        refreshProducts()
    }

    fun refreshProducts() = viewModelScope.launch {

        _apiCallStatus.value = "Loading"
        try {
            val response = repository.refreshProducts()
            Log.d("main check", "API response: $response")
            _apiCallStatus.value = "Success"
        } catch (e: Exception) {
            Log.e("main check", "API call failed: ${e.message}", e)
            _apiCallStatus.value = "Failed: ${e.message}"
        }
        Log.d("main check", "refreshProducts: ${ repository.refreshProducts()}")
        repository.refreshProducts()
    }
}
