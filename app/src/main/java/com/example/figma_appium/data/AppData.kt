package com.example.figma_appium.data

import com.example.figma_appium.R
import com.example.figma_appium.models.CartItem
import com.example.figma_appium.models.Offer
import com.example.figma_appium.models.Product

object AppData {
    
    private val _cartItems = mutableListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems
    
    fun addToCart(product: Product, size: String? = null, color: Int? = null) {
        val existingItem = _cartItems.find { 
            it.product.id == product.id && 
            it.selectedSize == size && 
            it.selectedColor == color 
        }
        
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            _cartItems.add(CartItem(product, 1, size, color))
        }
    }
    
    fun removeFromCart(cartItem: CartItem) {
        _cartItems.remove(cartItem)
    }
    
    fun updateQuantity(cartItem: CartItem, quantity: Int) {
        if (quantity <= 0) {
            removeFromCart(cartItem)
        } else {
            cartItem.quantity = quantity
        }
    }
    
    fun getCartTotal(): Double {
        return _cartItems.sumOf { it.getTotalPrice() }
    }
    
    fun clearCart() {
        _cartItems.clear()
    }
    
    val products = listOf(
        Product(
            id = 1,
            name = "Traveler Tote",
            brand = "The Marc Jacobs",
            description = "The perfect tote for everyday use. Made with premium materials and designed for durability. Features multiple compartments for organization.",
            price = 195.00,
            imageResId = R.drawable.product_bag,
            availableColors = listOf(
                android.graphics.Color.parseColor("#000000"),
                android.graphics.Color.parseColor("#8B4513")
            )
        ),
        Product(
            id = 2,
            name = "Clean 90 Triple Sneakers",
            brand = "Axel Arigato",
            description = "Modern sneakers with a minimalist design. Comfortable for all-day wear with premium cushioning and breathable materials.",
            price = 245.00,
            imageResId = R.drawable.product_shoes,
            availableColors = listOf(
                android.graphics.Color.parseColor("#2C3E50"),
                android.graphics.Color.parseColor("#FFFFFF"),
                android.graphics.Color.parseColor("#34495E")
            )
        ),
        Product(
            id = 3,
            name = "Vado Odelle Dress",
            brand = "Roller Rabbit",
            description = "Get a little lift from these Sam Edelman sandals featuring ruched straps and leather lace-up ties, while a braided jute sole makes a fresh statement for summer.",
            price = 198.00,
            imageResId = R.drawable.product_dress,
            availableColors = listOf(
                android.graphics.Color.parseColor("#000000"),
                android.graphics.Color.parseColor("#CADCA7"),
                android.graphics.Color.parseColor("#FFA500")
            )
        ),
        Product(
            id = 4,
            name = "Daypack Backpack",
            brand = "Herschel Supply Co.",
            description = "Classic backpack with modern features. Multiple pockets for organization and padded laptop sleeve. Perfect for school or work.",
            price = 40.00,
            imageResId = R.drawable.product_backpack,
            availableColors = listOf(
                android.graphics.Color.parseColor("#D3D3D3"),
                android.graphics.Color.parseColor("#000000")
            )
        )
    )
    
    val offers = listOf(
        Offer(
            id = 1,
            title = "50% Off",
            discount = "50%",
            description = "On everything today",
            code = "FSCREATION",
            imageResId = R.drawable.offer_bag,
            backgroundColor = android.graphics.Color.parseColor("#E8E8E8")
        ),
        Offer(
            id = 2,
            title = "70% Off",
            discount = "70%",
            description = "On everything today",
            code = "FASHION70",
            imageResId = R.drawable.offer_shoes,
            backgroundColor = android.graphics.Color.parseColor("#D3D3D3")
        )
    )
    
    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }
}
