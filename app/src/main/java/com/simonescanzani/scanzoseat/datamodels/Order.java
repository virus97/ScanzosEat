package com.simonescanzani.scanzoseat.datamodels;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> list;
    private Shop shop;
    private float prezzo;


    public Order(Shop shop){
         this.shop = shop;
    }

    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }


    public String getPrezzo() {
        return "Prezzo: " + prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Shop getShop() {
        return shop;
    }
}
