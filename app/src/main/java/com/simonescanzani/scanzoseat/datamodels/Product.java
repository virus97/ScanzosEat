package com.simonescanzani.scanzoseat.datamodels;

import java.io.Serializable;

public class Product implements Serializable {
    private float prezzo;
    private String nome;
    private String ingredienti;
    private int Thumbnail ;

    public Product(String nome, String ingredienti, float prezzo, int Thumbnail){
        this.nome=nome;
        this.ingredienti=ingredienti;
        this.prezzo=prezzo;
        this.Thumbnail=Thumbnail;
    }

    public String getPrezzo() {
        return "Prezzo: " + prezzo + " €";
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
}
