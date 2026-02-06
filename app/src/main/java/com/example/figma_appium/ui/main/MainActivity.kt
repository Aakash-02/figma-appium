package com.example.figma_appium.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.figma_appium.adapters.OfferAdapter
import com.example.figma_appium.adapters.ProductAdapter
import com.example.figma_appium.data.AppData
import com.example.figma_appium.databinding.ActivityMainBinding
import com.example.figma_appium.ui.cart.CartActivity
import com.example.figma_appium.ui.product.ProductDetailsActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var offerAdapter: OfferAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupOffers()
        setupProducts()
        setupNavigation()
    }
    
    private fun setupOffers() {
        offerAdapter = OfferAdapter(AppData.offers)
        binding.rvOffers.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = offerAdapter
        }
    }
    
    private fun setupProducts() {
        productAdapter = ProductAdapter(AppData.products) { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("PRODUCT_ID", product.id)
            startActivity(intent)
        }
        
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = productAdapter
        }
    }
    
    private fun setupNavigation() {
        binding.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        
        binding.navHome.setOnClickListener {
            // Already on home
        }
    }
}
