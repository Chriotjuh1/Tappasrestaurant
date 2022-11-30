package com.example.tapasrestaurant.entity;

public class Gerecht {
    private Integer productid; // private = restricted access
    private String Naam;
    private String Prijs;

    // Getter
    public Integer getProduct_Id() {
        return productid;
    }

    public String getNaam() {
        return Naam;
    }

    public String getPrijs() {return Prijs;}


    // Setter
    public void setProductid(Integer newId) {
        this.productid = newId;
    }

    public void setNaam(String newNaam) {
        this.Naam = newNaam;
    }

    public void setPrijs (String newPrijs) {
        this.Prijs = newPrijs;
    }


}