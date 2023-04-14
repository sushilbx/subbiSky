package com.webdigital.subbisky.model;

public class AddToCartModel {
    private int id;
    private String title;
    private String rup;
    private String rup1;
    private int image;

    public AddToCartModel(int id, String title, String rup, String rup1, int image) {
        this.id = id;
        this.title = title;
        this.rup=rup;
        this.rup1=rup1;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRup() {
        return rup;
    }

    public String getRup1() {
        return rup1;
    }

    public int getImage() {
        return image;
    }
}
