package com.example.tapasrestaurant.entity;

public class GerechtQuantity {
    private Gerecht gerecht;
    private int quantity;

    public GerechtQuantity(Gerecht gerecht, int quantity) {
        this.gerecht = gerecht;
        this.quantity = quantity;
    }

    public Gerecht getGerecht() {
        return gerecht;
    }

    public int getQuantity() {
        return quantity;
    }
}
