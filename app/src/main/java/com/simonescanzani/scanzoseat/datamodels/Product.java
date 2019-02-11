package com.simonescanzani.scanzoseat.datamodels;

import java.io.Serializable;

public class Product implements Serializable {
    private float prezzo;
    private String nome;
    private String ingredienti;
    private int Thumbnail ;
    private int quantity = 0;

    public Product(String nome, String ingredienti, float prezzo, int Thumbnail){
        this.nome=nome;
        this.ingredienti=ingredienti;
        this.prezzo=prezzo;
        this.Thumbnail=Thumbnail;
    }

    public String getPrezzo() {
        return "Prezzo: " + prezzo + " €";
    }

    public float getPrezzoNumber(){
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        if(this.quantity!=0)
            this.quantity--;
    }
}
