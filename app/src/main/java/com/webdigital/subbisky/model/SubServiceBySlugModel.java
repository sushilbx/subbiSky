package com.webdigital.subbisky.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class SubServiceBySlugModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("nearby_shop")
    @Expose
    private List<NearbyShop> nearbyShop = null;

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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<NearbyShop> getNearbyShop() {
        return nearbyShop;
    }

    public void setNearbyShop(List<NearbyShop> nearbyShop) {
        this.nearbyShop = nearbyShop;
    }
    public class UserId {

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
        private Object otp;
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

        public Object getOtp() {
            return otp;
        }

        public void setOtp(Object otp) {
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

    }
    public class Service {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("parent_id")
        @Expose
        private Integer parentId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("image")
        @Expose
        private Object image;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("type")
        @Expose
        private Object type;
        @SerializedName("rank")
        @Expose
        private Object rank;
        @SerializedName("commission")
        @Expose
        private Integer commission;
        @SerializedName("meta_title")
        @Expose
        private Object metaTitle;
        @SerializedName("meta_description")
        @Expose
        private Object metaDescription;
        @SerializedName("meta_keywords")
        @Expose
        private Object metaKeywords;
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

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getRank() {
            return rank;
        }

        public void setRank(Object rank) {
            this.rank = rank;
        }

        public Integer getCommission() {
            return commission;
        }

        public void setCommission(Integer commission) {
            this.commission = commission;
        }

        public Object getMetaTitle() {
            return metaTitle;
        }

        public void setMetaTitle(Object metaTitle) {
            this.metaTitle = metaTitle;
        }

        public Object getMetaDescription() {
            return metaDescription;
        }

        public void setMetaDescription(Object metaDescription) {
            this.metaDescription = metaDescription;
        }

        public Object getMetaKeywords() {
            return metaKeywords;
        }

        public void setMetaKeywords(Object metaKeywords) {
            this.metaKeywords = metaKeywords;
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
    public class NearbyShop {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private UserId userId;
        @SerializedName("shop_name")
        @Expose
        private String shopName;
        @SerializedName("shop_address")
        @Expose
        private String shopAddress;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("show_mobile_email")
        @Expose
        private String showMobileEmail;
        @SerializedName("shop_image")
        @Expose
        private String shopImage;
        @SerializedName("service_id")
        @Expose
        private Integer serviceId;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("service_parent_id")
        @Expose
        private Integer serviceParentId;
        @SerializedName("iframe")
        @Expose
        private String iframe;
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
        @SerializedName("available_rooms")
        @Expose
        private String availableRooms;
        @SerializedName("close_status")
        @Expose
        private Integer closeStatus;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public UserId getUserId() {
            return userId;
        }

        public void setUserId(UserId userId) {
            this.userId = userId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
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

        public String getShopImage() {
            return shopImage;
        }

        public void setShopImage(String shopImage) {
            this.shopImage = shopImage;
        }

        public Integer getServiceId() {
            return serviceId;
        }

        public void setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getServiceParentId() {
            return serviceParentId;
        }

        public void setServiceParentId(Integer serviceParentId) {
            this.serviceParentId = serviceParentId;
        }

        public String getIframe() {
            return iframe;
        }

        public void setIframe(String iframe) {
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

        public String getAvailableRooms() {
            return availableRooms;
        }

        public void setAvailableRooms(String availableRooms) {
            this.availableRooms = availableRooms;
        }

        public Integer getCloseStatus() {
            return closeStatus;
        }

        public void setCloseStatus(Integer closeStatus) {
            this.closeStatus = closeStatus;
        }

    }
}