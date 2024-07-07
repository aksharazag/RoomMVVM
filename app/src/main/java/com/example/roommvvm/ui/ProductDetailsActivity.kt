package com.example.roommvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roommvvm.databinding.ActivityProductDetailsBinding
import com.example.roommvvm.viewmodel.ProductViewModel


class ProductDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_ID = "com.example.roommvvm.PRODUCT_ID"
    }

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra(EXTRA_PRODUCT_ID, 0)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(this, Observer { products ->
            val product = products.find { it.id == productId }
            product?.let { binding.product = it }
        })
    }
}

