package com.example.indroydlab.model

data class ProductModel (
    val id: Int,
    val name: String,
    val originalPrice: Double,
    val discountedPrice: Double?,
    val isDiscounted: Boolean,
    val taxPercent: Double
)

