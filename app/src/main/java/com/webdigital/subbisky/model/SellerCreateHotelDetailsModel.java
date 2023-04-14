package com.webdigital.subbisky.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class SellerCreateHotelDetailsModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("hotels")
    @Expose
    private List<Hotel> hotels = null;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
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
        @SerializedName("amenity_id")
        @Expose
        private List<AmenityId> amenityId = null;

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

        public List<AmenityId> getAmenityId() {
            return amenityId;
        }

        public void setAmenityId(List<AmenityId> amenityId) {
            this.amenityId = amenityId;
        }

    }
    public class AmenityId {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("created_at")
        @Expose
        private Object createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}