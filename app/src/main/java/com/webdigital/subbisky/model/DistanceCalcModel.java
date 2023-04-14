package com.webdigital.subbisky.model;

import java.util.List;

public class DistanceCalcModel {
    private  String from,to,distanceKm,success;
    private Integer statusCode,distanceMeter;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(String distanceKm) {
        this.distanceKm = distanceKm;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getDistanceMeter() {
        return distanceMeter;
    }

    public void setDistanceMeter(Integer distanceMeter) {
        this.distanceMeter = distanceMeter;
    }
}
