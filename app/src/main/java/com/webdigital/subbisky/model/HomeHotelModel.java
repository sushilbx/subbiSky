package com.webdigital.subbisky.model;

public class HomeHotelModel {

    private int id;
    private String title;
    private String title2;
    private String shortdesc;
    private double rating;
    private int image;

    public HomeHotelModel(int id, String title, String title2, String shortdesc, double rating, int image) {
        this.id = id;
        this.title = title;
        this.title2 = title2;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle2() {
        return title2;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public int getImage() {
        return image;
    }
}