package com.example.figma_appium.models

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    val rating: Float = 5.0f,
    val reviewCount: Int = 320,
    val availableSizes: List<String> = listOf("S", "M", "L", "XL", "XXL"),
    val availableColors: List<Int> = emptyList(),
    val inStock: Boolean = true
)
