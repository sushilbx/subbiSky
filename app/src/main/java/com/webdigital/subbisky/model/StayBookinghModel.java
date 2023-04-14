package com.webdigital.subbisky.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class StayBookinghModel {

    @SerializedName("shop")
    @Expose
    private Shop shop;
    @SerializedName("hotels")
    @Expose
    private List<Hotel> hotels = null;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("totalRoom")
    @Expose
    private Integer totalRoom;
    @SerializedName("totalChildren")
    @Expose
    private Integer totalChildren;
    @SerializedName("totalAdult")
    @Expose
    private Integer totalAdult;
    @SerializedName("adultExtra")
    @Expose
    private Integer adultExtra;
    @SerializedName("childrenExtra")
    @Expose
    private Integer childrenExtra;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private Boolean message;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public Integer getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(Integer totalRoom) {
        this.totalRoom = totalRoom;
    }

    public Integer getTotalChildren() {
        return totalChildren;
    }

    public void setTotalChildren(Integer totalChildren) {
        this.totalChildren = totalChildren;
    }

    public Integer getTotalAdult() {
        return totalAdult;
    }

    public void setTotalAdult(Integer totalAdult) {
        this.totalAdult = totalAdult;
    }

    public Integer getAdultExtra() {
        return adultExtra;
    }

    public void setAdultExtra(Integer adultExtra) {
        this.adultExtra = adultExtra;
    }

    public Integer getChildrenExtra() {
        return childrenExtra;
    }

    public void setChildrenExtra(Integer childrenExtra) {
        this.childrenExtra = childrenExtra;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public class Shop {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
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
        @SerializedName("ratingCount")
        @Expose
        private Integer ratingCount;
        @SerializedName("ratingAverage")
        @Expose
        private Integer ratingAverage;

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

        public Integer getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
        }

        public Integer getRatingAverage() {
            return ratingAverage;
        }

        public void setRatingAverage(Integer ratingAverage) {
            this.ratingAverage = ratingAverage;
        }

    }
    public class Hotel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("seller_id")
        @Expose
        private Integer sellerId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("checkin_time")
        @Expose
        private String checkinTime;
        @SerializedName("checkout_time")
        @Expose
        private String checkoutTime;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("google_location")
        @Expose
        private String googleLocation;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("amenities")
        @Expose
        private String amenities;
        @SerializedName("bed_size")
        @Expose
        private String bedSize;
        @SerializedName("room_square_feet")
        @Expose
        private String roomSquareFeet;
        @SerializedName("available_rooms")
        @Expose
        private Integer availableRooms;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("imageTwo")
        @Expose
        private String imageTwo;
        @SerializedName("imageThree")
        @Expose
        private String imageThree;
        @SerializedName("imageFour")
        @Expose
        private String imageFour;
        @SerializedName("childrenExtra")
        @Expose
        private String childrenExtra;
        @SerializedName("adultExtra")
        @Expose
        private String adultExtra;
        @SerializedName("imageFive")
        @Expose
        private String imageFive;
        @SerializedName("from_block")
        @Expose
        private Object fromBlock;
        @SerializedName("to_block")
        @Expose
        private Object toBlock;
        @SerializedName("roomCapacity")
        @Expose
        private String roomCapacity;
        @SerializedName("priceDescription")
        @Expose
        private String priceDescription;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSellerId() {
            return sellerId;
        }

        public void setSellerId(Integer sellerId) {
            this.sellerId = sellerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getCheckinTime() {
            return checkinTime;
        }

        public void setCheckinTime(String checkinTime) {
            this.checkinTime = checkinTime;
        }

        public String getCheckoutTime() {
            return checkoutTime;
        }

        public void setCheckoutTime(String checkoutTime) {
            this.checkoutTime = checkoutTime;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGoogleLocation() {
            return googleLocation;
        }

        public void setGoogleLocation(String googleLocation) {
            this.googleLocation = googleLocation;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAmenities() {
            return amenities;
        }

        public void setAmenities(String amenities) {
            this.amenities = amenities;
        }

        public String getBedSize() {
            return bedSize;
        }

        public void setBedSize(String bedSize) {
            this.bedSize = bedSize;
        }

        public String getRoomSquareFeet() {
            return roomSquareFeet;
        }

        public void setRoomSquareFeet(String roomSquareFeet) {
            this.roomSquareFeet = roomSquareFeet;
        }

        public Integer getAvailableRooms() {
            return availableRooms;
        }

        public void setAvailableRooms(Integer availableRooms) {
            this.availableRooms = availableRooms;
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

        public String getImageTwo() {
            return imageTwo;
        }

        public void setImageTwo(String imageTwo) {
            this.imageTwo = imageTwo;
        }

        public String getImageThree() {
            return imageThree;
        }

        public void setImageThree(String imageThree) {
            this.imageThree = imageThree;
        }

        public String getImageFour() {
            return imageFour;
        }

        public void setImageFour(String imageFour) {
            this.imageFour = imageFour;
        }

        public String getChildrenExtra() {
            return childrenExtra;
        }

        public void setChildrenExtra(String childrenExtra) {
            this.childrenExtra = childrenExtra;
        }

        public String getAdultExtra() {
            return adultExtra;
        }

        public void setAdultExtra(String adultExtra) {
            this.adultExtra = adultExtra;
        }

        public String getImageFive() {
            return imageFive;
        }

        public void setImageFive(String imageFive) {
            this.imageFive = imageFive;
        }

        public Object getFromBlock() {
            return fromBlock;
        }

        public void setFromBlock(Object fromBlock) {
            this.fromBlock = fromBlock;
        }

        public Object getToBlock() {
            return toBlock;
        }

        public void setToBlock(Object toBlock) {
            this.toBlock = toBlock;
        }

        public String getRoomCapacity() {
            return roomCapacity;
        }

        public void setRoomCapacity(String roomCapacity) {
            this.roomCapacity = roomCapacity;
        }

        public String getPriceDescription() {
            return priceDescription;
        }

        public void setPriceDescription(String priceDescription) {
            this.priceDescription = priceDescription;
        }

    }
    public class Data {

        @SerializedName("room")
        @Expose
        private String room;
        @SerializedName("adult")
        @Expose
        private String adult;
        @SerializedName("children")
        @Expose
        private String children;
        @SerializedName("from_date")
        @Expose
        private String fromDate;
        @SerializedName("to_date")
        @Expose
        private String toDate;

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getAdult() {
            return adult;
        }

        public void setAdult(String adult) {
            this.adult = adult;
        }

        public String getChildren() {
            return children;
        }

        public void setChildren(String children) {
            this.children = children;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

    }
}