package com.webdigital.subbisky.model;

public class PickAndDropModel {
    private int id;
    private int image;
    private String title;
    private String shortdesc;
    private String Call;
    private String Email;
    private double rating;;



    public PickAndDropModel(int id, int image, String title, String shortdesc, String Call, String Email, double rating) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.shortdesc = shortdesc;
        this.Call=Call;
        this.Email=Email;
        this.rating = rating;


    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getCall() {
        return Call;
    }

    public String getEmail() {
        return Email;
    }

    public double getRating() {
        return rating;
    }
}
