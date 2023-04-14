package com.webdigital.subbisky.model;

public class SellerDashboardModel {
    private String message,type;
    private Boolean success;
    private int statusCode;
    private String  totalCategories,totalProduct,activeProduct,inactiveProduct,totalOrders,totalService,activeService,inactiveService;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTotalCategories() {
        return totalCategories;
    }

    public void setTotalCategories(String totalCategories) {
        this.totalCategories = totalCategories;
    }

    public String getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(String totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getActiveProduct() {
        return activeProduct;
    }

    public void setActiveProduct(String activeProduct) {
        this.activeProduct = activeProduct;
    }

    public String getInactiveProduct() {
        return inactiveProduct;
    }

    public void setInactiveProduct(String inactiveProduct) {
        this.inactiveProduct = inactiveProduct;
    }

    public String getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(String totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getTotalService() {
        return totalService;
    }

    public void setTotalService(String totalService) {
        this.totalService = totalService;
    }

    public String getActiveService() {
        return activeService;
    }

    public void setActiveService(String activeService) {
        this.activeService = activeService;
    }

    public String getInactiveService() {
        return inactiveService;
    }

    public void setInactiveService(String inactiveService) {
        this.inactiveService = inactiveService;
    }
}
