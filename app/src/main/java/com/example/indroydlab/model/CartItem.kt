package com.example.indroydlab.model

class CartItem (
    val  product: ProductModel,
    var quantity: Int = 1
) {
    fun incrementQuantity() = quantity++
    fun getItemPrice(): Double {
        return if (product.isDiscounted && product.discountedPrice != null) {
            product.discountedPrice * quantity
        } else{ product.originalPrice * quantity }
    }
    fun getItemTotal(): Double {
        return if (product.isDiscounted && product.discountedPrice != null) {
            product.discountedPrice * quantity
        } else {
            product.originalPrice * quantity
        }
    }

    fun getTaxAmount(): Double{ return getItemPrice() * product.taxPercent / 100 }
}