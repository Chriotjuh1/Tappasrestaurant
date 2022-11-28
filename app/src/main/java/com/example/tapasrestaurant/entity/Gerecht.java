package com.example.tapasrestaurant.entity;

public class Gerecht {
    private Integer Product_Id; // private = restricted access
    private String Naam;

    private double Price;
    // Getter
    public Integer getProduct_Id() {
        return Product_Id;
    }

    public String getNaam() {
        return Naam;
    }

    public double getPrice() {
        return Price;
    }

    // Setter
    public void setProduct_Id(Integer newId) {
        this.Product_Id = newId;
    }

    public void setNaam(String newNaam) {
        this.Naam = newNaam;
    }

    public void setPrice(double price) {
        Price = price;
    }
}