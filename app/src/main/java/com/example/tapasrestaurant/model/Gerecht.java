package com.example.tapasrestaurant.model;

public class Gerecht {
    private Integer Product_Id; // private = restricted access
    private String Naam;
    // Getter
    public Integer getProduct_Id() {
        return Product_Id;
    }

    public String getNaam() {
        return Naam;
    }

    // Setter
    public void setProduct_Id(Integer newId) {
        this.Product_Id = newId;
    }

    public void setNaam(String newNaam) {
        this.Naam = newNaam;
    }
}
