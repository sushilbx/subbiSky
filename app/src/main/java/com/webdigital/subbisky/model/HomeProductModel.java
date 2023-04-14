package com.webdigital.subbisky.model;

public class HomeProductModel {

    private String title;
    private String showall;
    private int image;

    public HomeProductModel(int image, String title, String showall) {
        this.image = image;
        this.title = title;
        this.showall=showall;

    }

    public String getTitle() {
        return title;
    }

    public String getShowall() {
        return showall;
    }

    public int getImage() {
        return image;
    }
}
