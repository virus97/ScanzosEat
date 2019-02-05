package com.simonescanzani.scanzoseat.datamodels;

import java.util.List;

public class Shop {

    private String Title;
    private String Street ;
    private float minPrice;
    private int Thumbnail ;

    private List<Product> listProduct;

    public Shop() {
    }

    public Shop(String title, String street, float minPrice, int thumbnail, List<Product> listProduct) {
        Title = title;
        Street = street;
        this.minPrice = minPrice;
        Thumbnail = thumbnail;
        this.listProduct=listProduct;
    }


    public String getTitle() {
        return Title;
    }

    public String getStreet() {
        return Street;
    }

    public String getMinPrice() {
        return "Spesa Minima: "+minPrice+ "€";
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

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
