package com.example.indroydlab.model;

public class Cart_item {

        private final Product product;
        private int quantity;

        public Cart_item(Product product) {
            this.product = product;
            this.quantity = 1;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
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

        public double getItemTotal() {
            double price = product.isDiscounted()
                    ? product.getDiscountedPrice()
                    : product.getOriginalPrice();
            return price * quantity;
        }

        public double getTaxAmount() {
            return getItemTotal() * product.getTaxPercent() / 100;
        }
    }

