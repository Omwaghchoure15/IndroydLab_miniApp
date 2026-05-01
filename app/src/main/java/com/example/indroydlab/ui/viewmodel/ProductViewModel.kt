package com.example.indroydlab.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.indroydlab.data.ProductRepository
import com.example.indroydlab.model.ProductModel

class ProductViewModel: ViewModel(){
    private val _products = mutableStateListOf<ProductModel>()
    val products: List<ProductModel> = _products

    init { loadProducts() }
    private fun loadProducts(){
        _products.clear()
        _products.addAll(ProductRepository.getProducts())
    }

    fun getProductById(id: String): ProductModel?{
        return _products.find { it.id.toString() == id }
    }
}