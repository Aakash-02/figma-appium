package com.example.figma_appium.models

data class Offer(
    val id: Int,
    val title: String,
    val discount: String,
    val description: String,
    val code: String,
    val imageResId: Int,
    val backgroundColor: Int
)
