package com.example.indroydlab.model

data class CartItem (
    val  product: ProductModel,
    var quantity: Int = 1
) {
    fun getItemPrice(): Double {
        return  (product.discountedPrice ?: product.originalPrice) * quantity
    }

    fun getTaxAmount(): Double {
        return getItemPrice() * product.taxPercent / 100
    }

    fun getItemTotal(): Double = getItemPrice()
}