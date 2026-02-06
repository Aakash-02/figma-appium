package com.example.figma_appium

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.figma_appium.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    private val cartItems = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCartItems()
        setupRecyclerView()
        setupClickListeners()
        updateTotal()
    }

    private fun setupCartItems() {
        // Sample cart items
        cartItems.addAll(
            listOf(
                CartItem(
                    Product(1, "Roller Rabbit", "Vado Odelle Dress", "$198.00", R.drawable.img_1_269_rectangle_image_24_1),
                    1
                ),
                CartItem(
                    Product(2, "Axel Arigato", "Clean 90 Triole Snakers", "$245.00", R.drawable.img_1_258_rectangle_untitled_1_1),
                    1
                ),
                CartItem(
                    Product(3, "Herschel Supply Co.", "Daypack Backpack", "$40.00", R.drawable.img_1_244_rectangle_untitled_1_3),
                    1
                )
            )
        )
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(
            cartItems,
            onQuantityChanged = { updateTotal() },
            onItemDeleted = { position ->
                cartAdapter.removeItem(position)
                updateTotal()
            }
        )
        binding.rvCartItems.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }

    private fun setupClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnApply.setOnClickListener {
            val promoCode = binding.etPromoCode.text.toString()
            if (promoCode.isNotEmpty()) {
                Toast.makeText(this, "Promo code applied", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCheckout.setOnClickListener {
            Toast.makeText(this, "Proceeding to checkout", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTotal() {
        val itemCount = cartItems.sumOf { it.quantity }
        // Calculate total (simplified - using static value for demo)
        binding.tvTotal.text = "Total ($itemCount item) : $500"
        binding.tvCartCount.text = itemCount.toString()
    }
}
