package com.webdigital.subbisky.model;

public class BookingModel {
    private int id;
    private int date;
    private String status;
    private int total;
    private String view;

    public BookingModel(int id, int date, String status, int total, String view) {
        this.id = id;
        this.date=date;
        this.status=status;
        this.total=total;
        this.view=view;
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public String getView() {
        return view;
    }
}
