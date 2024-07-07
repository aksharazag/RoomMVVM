package com.example.roommvvm.model


import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("products.php")
    suspend fun getProducts(): Response<Product>

  companion object{
      fun create(): ApiService{
          val retrofit = Retrofit.Builder()
              .baseUrl("https://apps.clickastro.com/test/")
              .addConverterFactory(GsonConverterFactory.create())
              .build()

          return retrofit.create(ApiService::class.java)

      }
  }
}


