package com.webdigital.subbisky.model;

import java.util.List;

public class ServiceDetailsModel {
    private String message;
    private Boolean success;
    private Integer statusCode;
    private ServiceSeller seller;
    private List<ServiceSellerServices> services;
    private List<ServiceReviews> reviews;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public ServiceSeller getSeller() {
        return seller;
    }

    public void setSeller(ServiceSeller seller) {
        this.seller = seller;
    }

    public List<ServiceSellerServices> getServices() {
        return services;
    }

    public void setServices(List<ServiceSellerServices> services) {
        this.services = services;
    }

    public List<ServiceReviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<ServiceReviews> reviews) {
        this.reviews = reviews;
    }

    public class ServiceSeller {
        private int id,user_id;
        private String shop_name,shop_address,status,show_mobile_email,shop_image,service_id,description,service_parent_id,iframe,open_close_time,deliveryStatus,deliveryCharge,room_capacity,cod,created_at,updated_at,ratingCount,ratingAverage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShow_mobile_email() {
            return show_mobile_email;
        }

        public void setShow_mobile_email(String show_mobile_email) {
            this.show_mobile_email = show_mobile_email;
        }

        public String getShop_image() {
            return shop_image;
        }

        public void setShop_image(String shop_image) {
            this.shop_image = shop_image;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getService_parent_id() {
            return service_parent_id;
        }

        public void setService_parent_id(String service_parent_id) {
            this.service_parent_id = service_parent_id;
        }

        public String getIframe() {
            return iframe;
        }

        public void setIframe(String iframe) {
            this.iframe = iframe;
        }

        public String getOpen_close_time() {
            return open_close_time;
        }

        public void setOpen_close_time(String open_close_time) {
            this.open_close_time = open_close_time;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getDeliveryCharge() {
            return deliveryCharge;
        }

        public void setDeliveryCharge(String deliveryCharge) {
            this.deliveryCharge = deliveryCharge;
        }

        public String getRoom_capacity() {
            return room_capacity;
        }

        public void setRoom_capacity(String room_capacity) {
            this.room_capacity = room_capacity;
        }

        public String getCod() {
            return cod;
        }

        public void setCod(String cod) {
            this.cod = cod;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(String ratingCount) {
            this.ratingCount = ratingCount;
        }

        public String getRatingAverage() {
            return ratingAverage;
        }

        public void setRatingAverage(String ratingAverage) {
            this.ratingAverage = ratingAverage;
        }

    }

    public class ServiceSellerServices {
        private int id,seller_id;
        private String name,price,description,image,status,created_at,updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

          }

    public class ServiceReviews {
    }
}
