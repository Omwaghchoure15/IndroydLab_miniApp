package com.example.indroydlab.model;

import com.example.indroydlab.model.Product;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public double getItemPrice() {
        if (product.isDiscounted() && product.getDiscountedPrice() != null) {
            return product.getDiscountedPrice();
        }
        return product.getOriginalPrice();
    }

    public double getTotalPrice() {
        return getItemPrice() * quantity;
    }

    public double getTaxAmount() {
        return getTotalPrice() * product.getTaxPercent() / 100;
    }
}
