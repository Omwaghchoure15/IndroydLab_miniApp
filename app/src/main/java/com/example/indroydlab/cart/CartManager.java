package com.example.indroydlab.cart;

import com.example.indroydlab.model.CartItem;
import com.example.indroydlab.model.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }
    private int cartVersion = 0;

    public int getCartVersion() {
        return cartVersion;
    }

    public void addToCart(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.incrementQuantity();
                cartVersion++;
                return;
            }
        }
        cartItems.add(new CartItem(product));
    }

    public double getSubTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public double getTaxTotal() {
        double tax = 0;
        for (CartItem item : cartItems) {
            tax += item.getTaxAmount();
        }
        return tax;
    }

    public double getCouponDiscount() {
        double eligibleAmount = 0;

        for (CartItem item : cartItems) {
            if (!item.getProduct().isDiscounted()) {
                eligibleAmount += item.getTotalPrice();
            }
        }

        if (eligibleAmount < 1000) {
            return 0;
        }

        double discount = eligibleAmount * 0.20;

        return Math.min(discount, 300);
    }

    public double getFinalAmount() {
        return getSubTotal() + getTaxTotal() - getCouponDiscount();
    }

    public void clearCart() {
        cartItems.clear();
        cartVersion++;
    }

    public void removeFromCart(Product product) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getId() == product.getId()) {
                cartItems.remove(i);
                cartVersion++;
                return;
            }
        }
    }

}
