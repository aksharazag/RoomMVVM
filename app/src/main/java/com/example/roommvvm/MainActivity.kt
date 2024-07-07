package com.example.roommvvm



import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roommvvm.databinding.ActivityMainBinding
import com.example.roommvvm.model.Product
import com.example.roommvvm.ui.ProductDetailsActivity
import com.example.roommvvm.ui.ProductListAdapter
import com.example.roommvvm.viewmodel.ProductViewModel
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this

        productViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ProductViewModel::class.java]
        binding.myViewmodel = productViewModel



        val adapter = ProductListAdapter { product -> onProductClick(product) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        productViewModel.allProducts.observe(this, Observer { products ->
            products?.let { adapter.submitList(it)
                Log.d("main check", "onCreate: $it")
            }
        })

        // Set default uncaught exception handler for additional debugging
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("UncaughtException", "Uncaught exception in thread: " + thread.name, throwable)
        }

    }

    private fun onProductClick(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java).apply {
            putExtra(ProductDetailsActivity.EXTRA_PRODUCT_ID, product.id)
        }
        startActivity(intent)
    }
}
