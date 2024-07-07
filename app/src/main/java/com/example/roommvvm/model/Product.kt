package com.example.roommvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String
)
