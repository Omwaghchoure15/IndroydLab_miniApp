package com.example.indroydlab.model

class CartManager {
    val cartItems = mutableListOf<CartItem>()
    fun getCartItem(): List<CartItem> = cartItems.toList()
    fun addToCart(product: ProductModel){
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.incrementQuantity()
        }
        else{
            cartItems.add(CartItem(product))
        }
    }
    fun getSubTotal(): Double = cartItems.sumOf { it.getItemTotal() }
    fun getTaxTotal(): Double = cartItems.sumOf { it.getItemTotal() * it.product.taxPercent / 100 }
    fun getCouponDiscount(): Double {
        val eligibleAmount = cartItems
            .filter { it.product.discountedPrice == null }
            .sumOf { it.getItemTotal() }
        if (eligibleAmount < 1000) return 0.0
        val discount = eligibleAmount * 0.20
        return minOf(discount,300.0)
    }
    fun getFinalAmount(): Double = getSubTotal() + getTaxTotal() - getCouponDiscount()
    fun increaseQuantity(product: ProductModel){
        cartItems.find { it.product.id == product.id }
            ?.let {
                it.quantity++
            println("Oty = ${it.quantity}")
            }
    }
    fun decreaseQuantity(product: ProductModel){
        val item = cartItems.find { it.product.id == product.id }
        item?.let {
            if (it.quantity > 1){
                it.quantity--
            }else{
                cartItems.remove(it)
            }
        }
    }
    fun removeFromCart(product: ProductModel) {
        cartItems.removeAll { it.product.id == product.id }
    }
    fun clearCart() {
        cartItems.clear()
    }
}