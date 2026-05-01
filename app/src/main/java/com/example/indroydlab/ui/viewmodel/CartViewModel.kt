package com.example.indroydlab.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.indroydlab.model.CartItem
import com.example.indroydlab.model.CartManager
import com.example.indroydlab.model.ProductModel

class CartViewModel: ViewModel() {
    val cartItem: List<CartItem> = CartManager.cartItems

    fun addToCart(product: ProductModel) = CartManager.addToCart(product)
    fun increaseQuantity(product: ProductModel) = CartManager.increaseQuantity(product)
    fun decreaseQuantity(product: ProductModel) = CartManager.decreaseQuantity(product)
    fun removeFromCart(product: ProductModel) = CartManager.removeFromCart(product)
    fun clearCart() = CartManager.clearCart()

    fun getSubTotal() = CartManager.getSubTotal()
    fun getTaxTotal() = CartManager.getTaxTotal()
    fun getCouponDiscount() = CartManager.getCouponDiscount()
    fun getFinalAmount() = CartManager.getFinalAmount()

}