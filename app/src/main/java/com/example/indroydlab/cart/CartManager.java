package com.example.indroydlab.cart;

import com.example.indroydlab.model.Cart_item;
import com.example.indroydlab.model.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private final List<Cart_item> cartItems = new ArrayList<>();

    public List<Cart_item> getCartItems() {
        return new ArrayList<>(cartItems);
    }
    public void addToCart(Product product) {
        for (Cart_item item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.incrementQuantity();
                return;
            }
        }
        cartItems.add(new Cart_item(product));
    }

    public double getSubTotal() {
        double total = 0;
        for (Cart_item item : cartItems) {
            total += item.getItemTotal();
        }
        return total;
    }

    public double getTaxTotal() {
        double tax = 0;
        for (Cart_item item : cartItems) {
            tax += item.getItemTotal() * item.getProduct().getTaxPercent() / 100;
        }
        return tax;
    }


    public double getFinalAmount() {
        double total = getSubTotal() + getTaxTotal() - getCouponDiscount();
        return total;
    }


    public double getCouponDiscount() {
        double eligibleAmount = 0;

        for (Cart_item item : cartItems) {
            if (!item.getProduct().isDiscounted()) {
                eligibleAmount += item.getItemTotal();
            }
        }

        if (eligibleAmount < 1000) {
            return 0;
        }

        double discount = eligibleAmount * 0.20;

        return Math.min(discount, 300);
    }

    public void increaseQuantity(Product product) {
        for (Cart_item item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                System.out.println("Qty = " + item.getQuantity());
                return;
            }
        }
    }

    public void decreaseQuantity(Product product) {
        for (int i = 0; i < cartItems.size(); i++) {
            Cart_item item = cartItems.get(i);
            if (item.getProduct().getId() == product.getId()) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    cartItems.remove(i);
                }
                return;
            }
        }
    }

    public void clearCart() {
        cartItems.clear();

    }
    public void removeFromCart(Product product) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getId() == product.getId()) {
                cartItems.remove(i);
                return;
            }
        }
    }



}
