package com.example.figma_appium

data class Product(
    val id: Int,
    val name: String,
    val subtitle: String,
    val price: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)
