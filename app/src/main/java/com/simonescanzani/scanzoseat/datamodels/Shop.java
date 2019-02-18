package com.simonescanzani.scanzoseat.datamodels;


import org.json.JSONException;
import org.json.JSONObject;

public class Shop {

    private String Title;
    private String Street ;
    private float minPrice;
    private int Thumbnail ;
    private String image_url;
    private String id;
    public final static String ENDPOINT = "/restaurants";


    public Shop() {
    }

    public Shop(String title, String street, float minPrice, int thumbnail) {
        Title = title;
        Street = street;
        this.minPrice = minPrice;
        Thumbnail = thumbnail;
    }


    public Shop(JSONObject jsonShop){
        try {
            this.Title = jsonShop.getString("name");
            this.Street = jsonShop.getString("address");
            this.minPrice = (float)jsonShop.getDouble("min_order");
            //this.minPrice = Float.valueOf(jsonShop.getString("min_order"));
            this.image_url = jsonShop.getString("image_url");
            this.id = jsonShop.getString("id");
        }catch (JSONException ex){
            ex.getStackTrace();
        }
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

    public float getMinPriceNumber(){
        return minPrice;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public String getImage_url(){
        return image_url;
    }

    public void setImage_url(String img){
        this.image_url=img;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
