package com.example.tapasrestaurant.entity;

public class Receipt {
    private String name;
    private String price;

    public Receipt(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
