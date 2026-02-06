package com.example.figma_appium.ui.cart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.figma_appium.adapters.CartAdapter
import com.example.figma_appium.data.AppData
import com.example.figma_appium.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupCart()
        setupClickListeners()
        updateTotals()
    }
    
    private fun setupCart() {
        cartAdapter = CartAdapter(
            AppData.cartItems.toMutableList(),
            onQuantityChanged = { updateTotals() },
            onItemDeleted = { cartItem ->
                AppData.removeFromCart(cartItem)
                updateTotals()
                Toast.makeText(this, "Item removed from cart", Toast.LENGTH_SHORT).show()
            }
        )
        
        binding.rvCartItems.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.apply {
            btnBack.setOnClickListener { finish() }
            
            btnApplyPromo.setOnClickListener {
                val promoCode = etPromoCode.text.toString()
                if (promoCode.isNotEmpty()) {
                    Toast.makeText(
                        this@CartActivity,
                        "Promo code applied: $promoCode",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            
            btnCheckout.setOnClickListener {
                if (AppData.cartItems.isNotEmpty()) {
                    Toast.makeText(
                        this@CartActivity,
                        "Proceeding to checkout...",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@CartActivity,
                        "Cart is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    
    private fun updateTotals() {
        val itemCount = AppData.cartItems.size
        val total = AppData.getCartTotal()
        
        binding.apply {
            tvCartBadge.text = itemCount.toString()
            tvTotalItems.text = String.format("Total (%d item) :", itemCount)
            tvTotalPrice.text = String.format("$%.0f", total)
        }
    }
    
    override fun onResume() {
        super.onResume()
        updateTotals()
    }
}
