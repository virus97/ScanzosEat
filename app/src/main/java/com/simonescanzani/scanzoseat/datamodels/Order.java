package com.simonescanzani.scanzoseat.datamodels;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "Order")
public class Order {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;


    @Embedded
    private Shop shop;


    @TypeConverters(ProductConverter.class)
    @ColumnInfo(name = "products")
    private List<Product> products;

    @ColumnInfo(name = "prezzo")
    private float prezzo;

    public Order(int id, Shop shop, List<Product> products, float prezzo) {
        this.id = id;
        this.shop = shop;
        this.products = products;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
}
