package com.example.tapasrestaurant.entity;

public class Gerecht {
    private Integer productid; // private = restricted access
    private String Naam;

    private double Price;
    // Getter
    public Integer getProduct_Id() {
        return productid;
    }

    public String getNaam() {
        return Naam;
    }

    public double getPrice() {
        return Price;
    }

    // Setter
    public void setProductid(Integer newId) {
        this.productid = newId;
    }

    public void setNaam(String newNaam) {
        this.Naam = newNaam;
    }

    public void setPrice(double price) {
        Price = price;
    }
}