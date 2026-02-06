package com.example.figma_appium.ui.product

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.figma_appium.data.AppData
import com.example.figma_appium.databinding.ActivityProductDetailsBinding
import com.example.figma_appium.models.Product
import com.example.figma_appium.ui.cart.CartActivity

class ProductDetailsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityProductDetailsBinding
    private var product: Product? = null
    private var selectedSize: String = "L"
    private var selectedColor: Int? = null
    private var quantity: Int = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        product = AppData.getProductById(productId)
        
        product?.let { setupProductDetails(it) }
        setupClickListeners()
    }
    
    private fun setupProductDetails(product: Product) {
        binding.apply {
            tvProductBrand.text = product.brand
            tvProductName.text = product.name
            tvDescription.text = product.description
            tvPrice.text = String.format("$%.2f", product.price * quantity)
            tvReviews.text = String.format("(%d Review)", product.reviewCount)
            ivProductImage.setImageResource(product.imageResId)
            
            if (!product.inStock) {
                tvStockStatus.text = "Out of Stock"
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.apply {
            btnBack.setOnClickListener { finish() }
            
            btnIncrease.setOnClickListener {
                quantity++
                updateQuantity()
            }
            
            btnDecrease.setOnClickListener {
                if (quantity > 1) {
                    quantity--
                    updateQuantity()
                }
            }
            
            // Size selection
            btnSizeS.setOnClickListener { selectSize("S") }
            btnSizeM.setOnClickListener { selectSize("M") }
            btnSizeL.setOnClickListener { selectSize("L") }
            btnSizeXL.setOnClickListener { selectSize("XL") }
            btnSizeXXL.setOnClickListener { selectSize("XXL") }
            
            btnAddToCart.setOnClickListener {
                product?.let { prod ->
                    AppData.addToCart(prod, selectedSize, selectedColor)
                    Toast.makeText(
                        this@ProductDetailsActivity,
                        "Added to cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            
            ivCart.setOnClickListener {
                startActivity(Intent(this@ProductDetailsActivity, CartActivity::class.java))
            }
        }
    }
    
    private fun selectSize(size: String) {
        selectedSize = size
        // Reset all sizes to default
        binding.apply {
            btnSizeS.setBackgroundResource(
                if (size == "S") com.example.figma_appium.R.drawable.bg_circle_black
                else com.example.figma_appium.R.drawable.bg_circle_stroke
            )
            btnSizeM.setBackgroundResource(
                if (size == "M") com.example.figma_appium.R.drawable.bg_circle_black
                else com.example.figma_appium.R.drawable.bg_circle_stroke
            )
            btnSizeL.setBackgroundResource(
                if (size == "L") com.example.figma_appium.R.drawable.bg_circle_black
                else com.example.figma_appium.R.drawable.bg_circle_stroke
            )
            btnSizeXL.setBackgroundResource(
                if (size == "XL") com.example.figma_appium.R.drawable.bg_circle_black
                else com.example.figma_appium.R.drawable.bg_circle_stroke
            )
            btnSizeXXL.setBackgroundResource(
                if (size == "XXL") com.example.figma_appium.R.drawable.bg_circle_black
                else com.example.figma_appium.R.drawable.bg_circle_stroke
            )
            
            // Update text color
            val whiteColor = getColor(com.example.figma_appium.R.color.white)
            val darkColor = getColor(com.example.figma_appium.R.color.text_dark)
            
            btnSizeS.setTextColor(if (size == "S") whiteColor else darkColor)
            btnSizeM.setTextColor(if (size == "M") whiteColor else darkColor)
            btnSizeL.setTextColor(if (size == "L") whiteColor else darkColor)
            btnSizeXL.setTextColor(if (size == "XL") whiteColor else darkColor)
            btnSizeXXL.setTextColor(if (size == "XXL") whiteColor else darkColor)
        }
    }
    
    private fun updateQuantity() {
        binding.tvQuantity.text = quantity.toString()
        product?.let {
            binding.tvPrice.text = String.format("$%.2f", it.price * quantity)
        }
    }
}
