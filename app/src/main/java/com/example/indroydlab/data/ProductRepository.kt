package com.example.indroydlab.data

import com.example.indroydlab.model.ProductModel

object ProductRepository {

    fun getProducts(): List<ProductModel> {
        return listOf(
            ProductModel(1, " wireless Headphone", 2500.0, 1999.0, true, 18.0),
            ProductModel(2, "Bluetooth Speaker", 1500.0, null, false, 18.0),
            ProductModel(3, "Smart Watch", 3000.0, 2499.0, true, 18.0),
            ProductModel(4, "USB Cable", 400.0, null, false, 5.0),
            ProductModel(5, "Power Bank", 1200.0, null, false, 5.0),
            ProductModel(6, " wireless Headphone", 2500.0, 1999.0, true, 18.0)
        )
    }
}
