package com.example.roommvvm.model

import android.util.Log
import androidx.lifecycle.LiveData
import java.io.IOException

class ProductRepository(private val productService: ApiService,private val productDao: ProductDao) {
   // private val apiService: ApiService


    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun refreshProducts() {
        try {
            val response = productService.getProducts()
            if (response.isSuccessful) {
                Log.d("main repo", "refreshProducts: ${response.body()}")
                val products = response.body()
                if (products != null) {
                    productDao.insertAll(products)
                }
            } else {
                throw IOException("API call failed with status code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw IOException("Error refreshing products", e)
        }
    }





//    init {
//        //Retrofit Builder class for creating and executing http request
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://apps.clickastro.com/test/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)
//    }
//
//    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
//
//    suspend fun refreshProducts() {
//        val products = apiService.getProducts()
//        Log.d("product", "refreshProducts: $products")
//        productDao.insertAll(products)
//    }

}