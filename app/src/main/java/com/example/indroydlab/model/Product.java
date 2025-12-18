package com.example.indroydlab.model;

public class Product {

    private int id;
    private String name;
    private double originalPrice;
    private Double discountedPrice;
    private boolean isDiscounted;
    private int taxPercent;

    public Product(int id, String name, double originalPrice,
                   Double discountedPrice, boolean isDiscounted, int taxPercent) {
        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.isDiscounted = isDiscounted;
        this.taxPercent = taxPercent;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public int getTaxPercent() {
        return taxPercent;
    }
}
