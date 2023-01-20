package com.example.tapasrestaurant.entity;

import java.util.ArrayList;




public class SelectedItems {
    public static final SelectedItems instance = new SelectedItems();
    private ArrayList<Gerecht> gerechtenGeselecteerdArray;

    private SelectedItems() {
        gerechtenGeselecteerdArray = new ArrayList<>();
    }

    public static SelectedItems getInstance() {
        return instance;
    }

    public ArrayList<Gerecht> getGerechtenGeselecteerdArray() {
        return gerechtenGeselecteerdArray;
    }

    public void setGerechtenGeselecteerdArray(ArrayList<Gerecht> gerechtenGeselecteerdArray) {
        this.gerechtenGeselecteerdArray = gerechtenGeselecteerdArray;
    }
}