package com.example.indroydlab.model

data class ProductModel (
    val id: Int,
    val name: String,
    val originalPrice: Double,
    val discountedPrice: Double?, // nullable
    val taxPercent: Double
){
    val finalPrice: Double
        get() = discountedPrice ?: originalPrice
}

