package com.webdigital.subbisky.model;

public class HomeImageModel {


    private int image;
    private String name,listing;
    public HomeImageModel(int image,String name,String listing) {
        this.image = image;
        this.name = name;
        this.listing = listing;


    }

    public int getImage() {
        return image;
    }
    public String getname() {
        return name;
    }
    public String getlisting() {
        return listing;
    }
}