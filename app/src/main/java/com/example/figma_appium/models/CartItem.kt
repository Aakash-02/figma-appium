package com.example.figma_appium.models

data class CartItem(
    val product: Product,
    var quantity: Int = 1,
    var selectedSize: String? = null,
    var selectedColor: Int? = null
) {
    fun getTotalPrice(): Double {
        return product.price * quantity
    }
}
