package com.example.tapasrestaurant.entity;

public class Receipt {
    private String name;
    private double price;
    private double totalAmount;

    private int quantity;

    public Receipt(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

