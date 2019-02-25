package com.simonescanzani.scanzoseat.datamodels;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "Shop")
public class Shop {

    @ColumnInfo(name = "Title")
    private String Title;

    @ColumnInfo(name = "Street")
    private String Street ;

    @ColumnInfo(name = "minPrice")
    private float minPrice;

    private int Thumbnail ;

    @ColumnInfo(name = "image_url")
    private String image_url;

    @ColumnInfo(name = "shop_id")
    private String id;

    @ColumnInfo(name = "rating")
    private float rating;

    public final static String ENDPOINT = "/restaurants";

    @Ignore
    private ArrayList<Product> products;


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
            this.rating=(float)(jsonShop.getDouble("rating")/10);
            Log.i("rating", String.valueOf(this.rating));
            JSONArray prodotti = jsonShop.getJSONArray("products");
            products = new ArrayList<>();
            for(int i=0; i<prodotti.length(); i++){
                products.add(new Product(prodotti.getJSONObject(i)));
            }
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

    public String getMinPriceString() {
        return "Spesa Minima: "+minPrice+ "€";
    }

    public float getMinPrice(){
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts(){
        return this.products;
    }
}
