package com.example.tapasrestaurant.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Gerecht implements Parcelable {
    private int productid;
    private String naam;
    private String prijs;
    private int tafel;
    private int quantity;

    public Gerecht() {
        this.productid = productid;
        this.naam = naam;
        this.prijs = prijs;
        this.tafel = tafel;
        this.quantity = quantity;
    }

    protected Gerecht(Parcel in) {
        productid = in.readInt();
        naam = in.readString();
        prijs = in.readString();
        tafel = in.readInt();



    }

    public static final Creator<Gerecht> CREATOR = new Creator<Gerecht>() {
        @Override
        public Gerecht createFromParcel(Parcel in) {
            return new Gerecht(in);
        }

        @Override
        public Gerecht[] newArray(int size) {
            return new Gerecht[size];
        }
    };

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }

    public int getTafel() {
        return tafel;
    }

    public void setTafel(int tafel) {
        this.tafel = tafel;
    }


    public int getQuantity() {
        return quantity;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productid);
        dest.writeString(naam);
        dest.writeString(prijs);
        dest.writeInt(tafel);
    }




    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public class Gerecht2 {
        private String name;

        public Gerecht2(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
