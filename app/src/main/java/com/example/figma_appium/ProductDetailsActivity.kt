package com.example.figma_appium

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.figma_appium.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.ivCartTop.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.tvMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantity()
            }
        }

        binding.tvPlus.setOnClickListener {
            quantity++
            updateQuantity()
        }

        binding.btnAddToCart.setOnClickListener {
            // Add to cart logic
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.ivLove.setOnClickListener {
            // Toggle favorite
        }
    }

    private fun updateQuantity() {
        binding.tvQuantity.text = quantity.toString()
        // Update total price based on quantity
    }
}
