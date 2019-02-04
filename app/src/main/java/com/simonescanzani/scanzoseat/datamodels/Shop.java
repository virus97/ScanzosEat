package com.simonescanzani.scanzoseat.datamodels;

public class Shop {

    private String Title;
    private String Street ;
    private float minPrice;
    private int Thumbnail ;

    public Shop() {
    }

    public Shop(String title, String street, float minPrice, int thumbnail) {
        Title = title;
        Street = street;
        this.minPrice = minPrice;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }

    public String getStreet() {
        return Street;
    }

    public String getMinPrice() {
        return "Prezzo Minimo: "+minPrice+ "€";
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setStreet(String category) {
        Street = category;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
