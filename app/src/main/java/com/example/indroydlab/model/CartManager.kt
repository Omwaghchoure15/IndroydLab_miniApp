package com.example.indroydlab.model

import androidx.compose.runtime.mutableStateListOf

object CartManager {
    val cartItems = mutableStateListOf<CartItem>()

    fun addToCart( product: ProductModel ) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            val index = cartItems.indexOf(existingItem)
            cartItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)

        } else { cartItems.add(CartItem(product)) }
    }
    fun getSubTotal(): Double = cartItems.sumOf { it.getItemPrice() }
    fun getTaxTotal(): Double = cartItems.sumOf { it.getTaxAmount() }
    fun getCouponDiscount(): Double {
        val eligibleAmount = cartItems
            .filter { it.product.discountedPrice == null }
            .sumOf { it.getItemPrice() }
        if (eligibleAmount < 1000) return 0.0
        return minOf(eligibleAmount * 0.20,300.0)
    }
    fun getFinalAmount(): Double = getSubTotal() + getTaxTotal() - getCouponDiscount()
    fun increaseQuantity(product: ProductModel) {
        val index = cartItems.indexOfFirst { it.product.id == product.id }
        if (index != -1) {
            val item = cartItems[index]
            cartItems[index] = item.copy(quantity = item.quantity + 1)
        }
    }
    fun decreaseQuantity(product: ProductModel) {
        val item = cartItems.find { it.product.id == product.id }
        item?.let {
            if (it.quantity > 1){
                it.quantity--
            }else{
                cartItems.remove(it)
            }
        }
    }
    fun removeFromCart(product: ProductModel) =
        cartItems.removeAll { it.product.id == product.id }
    fun clearCart() = cartItems.clear()
}