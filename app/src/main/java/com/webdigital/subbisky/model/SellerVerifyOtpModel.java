package com.webdigital.subbisky.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerVerifyOtpModel {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("role_id")
        @Expose
        private Integer roleId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("email_verified_at")
        @Expose
        private Object emailVerifiedAt;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("city_id")
        @Expose
        private String cityId;
        @SerializedName("pincode")
        @Expose
        private Object pincode;
        @SerializedName("landmark")
        @Expose
        private Object landmark;
        @SerializedName("address")
        @Expose
        private Object address;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("verify")
        @Expose
        private String verify;
        @SerializedName("verifyOtp")
        @Expose
        private String verifyOtp;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("seller")
        @Expose
        private Seller seller;
        @SerializedName("type")
        @Expose
        private String type;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
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


        public Object getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(Object emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public Object getPincode() {
            return pincode;
        }

        public void setPincode(Object pincode) {
            this.pincode = pincode;
        }

        public Object getLandmark() {
            return landmark;
        }

        public void setLandmark(Object landmark) {
            this.landmark = landmark;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Seller getSeller() {
            return seller;
        }

        public void setSeller(Seller seller) {
            this.seller = seller;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
    public class Seller {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("shop_name")
        @Expose
        private Object shopName;
        @SerializedName("shop_address")
        @Expose
        private Object shopAddress;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("show_mobile_email")
        @Expose
        private String showMobileEmail;
        @SerializedName("shop_image")
        @Expose
        private Object shopImage;
        @SerializedName("service_id")
        @Expose
        private Integer serviceId;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("service_parent_id")
        @Expose
        private Integer serviceParentId;
        @SerializedName("iframe")
        @Expose
        private Object iframe;
        @SerializedName("open_close_time")
        @Expose
        private Object openCloseTime;
        @SerializedName("open_time")
        @Expose
        private Object openTime;
        @SerializedName("close_time")
        @Expose
        private Object closeTime;
        @SerializedName("deliveryStatus")
        @Expose
        private String deliveryStatus;
        @SerializedName("deliveryCharge")
        @Expose
        private String deliveryCharge;
        @SerializedName("room_capacity")
        @Expose
        private String roomCapacity;
        @SerializedName("cod")
        @Expose
        private String cod;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Object getShopName() {
            return shopName;
        }

        public void setShopName(Object shopName) {
            this.shopName = shopName;
        }

        public Object getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(Object shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShowMobileEmail() {
            return showMobileEmail;
        }

        public void setShowMobileEmail(String showMobileEmail) {
            this.showMobileEmail = showMobileEmail;
        }

        public Object getShopImage() {
            return shopImage;
        }

        public void setShopImage(Object shopImage) {
            this.shopImage = shopImage;
        }

        public Integer getServiceId() {
            return serviceId;
        }

        public void setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Integer getServiceParentId() {
            return serviceParentId;
        }

        public void setServiceParentId(Integer serviceParentId) {
            this.serviceParentId = serviceParentId;
        }

        public Object getIframe() {
            return iframe;
        }

        public void setIframe(Object iframe) {
            this.iframe = iframe;
        }

        public Object getOpenCloseTime() {
            return openCloseTime;
        }

        public void setOpenCloseTime(Object openCloseTime) {
            this.openCloseTime = openCloseTime;
        }

        public Object getOpenTime() {
            return openTime;
        }

        public void setOpenTime(Object openTime) {
            this.openTime = openTime;
        }

        public Object getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(Object closeTime) {
            this.closeTime = closeTime;
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

        public String getRoomCapacity() {
            return roomCapacity;
        }

        public void setRoomCapacity(String roomCapacity) {
            this.roomCapacity = roomCapacity;
        }

        public String getCod() {
            return cod;
        }

        public void setCod(String cod) {
            this.cod = cod;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}