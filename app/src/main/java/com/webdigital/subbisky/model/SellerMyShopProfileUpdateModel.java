package com.webdigital.subbisky.model;

public class SellerMyShopProfileUpdateModel {
    private String message;
    private Boolean success;
    private Integer statusCode;
    private SeelerMyShopProfile shop;
    private SellerMyShopProfileUser user;

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

    public SeelerMyShopProfile getShop() {
        return shop;
    }

    public void setShop(SeelerMyShopProfile shop) {
        this.shop = shop;
    }

    public SellerMyShopProfileUser getUser() {
        return user;
    }

    public void setUser(SellerMyShopProfileUser user) {
        this.user = user;
    }

    public class SeelerMyShopProfile {
        private Integer id,user_id,service_id,service_parent_id;
        private String shop_name,shop_address,status,show_mobile_email,
                shop_image,description,iframe,open_close_time,deliveryStatus,
                deliveryCharge,room_capacity,cod,created_at,updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public Integer getService_id() {
            return service_id;
        }

        public void setService_id(Integer service_id) {
            this.service_id = service_id;
        }

        public Integer getService_parent_id() {
            return service_parent_id;
        }

        public void setService_parent_id(Integer service_parent_id) {
            this.service_parent_id = service_parent_id;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
    }

    public class SellerMyShopProfileUser {
        private Integer id,role_id;
        private String name,email,phone,email_verified_at,status,
                city_id,pincode,landmark,address,otp,verify,verifyOtp,
                created_at,updated_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRole_id() {
            return role_id;
        }

        public void setRole_id(Integer role_id) {
            this.role_id = role_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getVerify() {
            return verify;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }

        public String getVerifyOtp() {
            return verifyOtp;
        }

        public void setVerifyOtp(String verifyOtp) {
            this.verifyOtp = verifyOtp;
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
}
