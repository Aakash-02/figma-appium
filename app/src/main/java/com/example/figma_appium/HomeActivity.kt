package com.example.figma_appium

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.figma_appium.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var productAdapter: ProductAdapter
    private val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProducts()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupProducts() {
        products.addAll(
            listOf(
                Product(
                    1,
                    "The Marc Jacobs",
                    "Traveler Tote",
                    "$195.00",
                    R.drawable.img_1_114_rectangle_image_90
                ),
                Product(
                    2,
                    "Axel Arigato",
                    "Clean 90 Triple Sneakers",
                    "$245.00",
                    R.drawable.img_1_126_rectangle_image_90
                )
            )
        )
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(products) { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("product_id", product.id)
            startActivity(intent)
        }
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = productAdapter
        }
    }

    private fun setupClickListeners() {
        binding.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.navHome.setOnClickListener {
            // Already on home
        }
    }
}
