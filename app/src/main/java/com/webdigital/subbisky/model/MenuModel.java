package com.webdigital.subbisky.model;


import androidx.fragment.app.Fragment;

public class MenuModel {

    public String menuName;
    public Fragment url;
    public boolean hasChildren, isGroup;
    public int image;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, Fragment url, int image) {

        this.menuName = menuName;
        this.url = url;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.image = image;
    }
}