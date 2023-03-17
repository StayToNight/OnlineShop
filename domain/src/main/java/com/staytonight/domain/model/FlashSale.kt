package com.staytonight.domain.model

data class FlashSale(
    val category: String,
    val discount: Int,
    val imageUrl: String,
    val name: String,
    val price: Double
)