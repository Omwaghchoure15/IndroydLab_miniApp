package com.example.indroydlab.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.indroydlab.model.CartItem
import com.example.indroydlab.model.CartManager
import com.example.indroydlab.model.ProductModel

class CartViewModel: ViewModel() {
    private val cartManager = CartManager()
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItem: List<CartItem> = _cartItems

    fun addToCart(product: ProductModel){
        cartManager.addToCart(product)
        refreshCart()
    }
    fun increaseQuantity(product: ProductModel){
        cartManager.increaseQuantity(product)
        refreshCart()
    }
    fun decreaseQuantity(product: ProductModel){
        cartManager.decreaseQuantity(product)
        refreshCart()
    }
    fun removeFromCart(product: ProductModel){
        cartManager.removeFromCart(product)
        refreshCart()
    }
    fun clearCart(){
        cartManager.clearCart()
        refreshCart()
    }
    fun getSubTotal() = cartManager.getSubTotal()
    fun getTaxTotal() = cartManager.getTaxTotal()
    fun getCouponDiscount() = cartManager.getCouponDiscount()
    fun getFinalAmount() = cartManager.getFinalAmount()
    private fun refreshCart(){
        _cartItems.clear()
        _cartItems.addAll(cartManager.getCartItem())
    }
}