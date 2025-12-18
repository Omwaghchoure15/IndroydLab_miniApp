package com.example.indroydlab.Data;

import com.example.indroydlab.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "Wireless Headphones", 2500, 1999.0, true, 18));
        products.add(new Product(2, "Bluetooth Speaker", 1500, null, false, 18));
        products.add(new Product(3, "Smart Watch", 3000, 2499.0, true, 18));
        products.add(new Product(4, "USB Cable", 400, null, false, 5));
        products.add(new Product(5, "Power Bank", 1200, null, false, 5));

        return products;
    }
}
