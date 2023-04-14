package com.webdigital.subbisky.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StayBookingNewModel {
    @SerializedName("shop") @Expose public Shop shop;
    @SerializedName("hotels") @Expose public ArrayList<Hotels> hotels;
    @SerializedName("reviews") @Expose public ArrayList<String> reviews;
    @SerializedName("totalRoom") @Expose public int totalRoom;
    @SerializedName("totalChildren") @Expose public int totalChildren;
    @SerializedName("totalAdult") @Expose public int totalAdult;
    @SerializedName("adultExtra") @Expose public int adultExtra;
    @SerializedName("childrenExtra") @Expose public int childrenExtra;
    @SerializedName("data") @Expose public Data data;
    @SerializedName("message") @Expose public Boolean message;
    @SerializedName("status") @Expose public int status;


    public class Shop{
        @SerializedName("id") @Expose public int id;
        @SerializedName("user_id") @Expose public int user_id;
        @SerializedName("shop_name") @Expose public String shop_name;
        @SerializedName("shop_address") @Expose public String shop_address;
        @SerializedName("status") @Expose public String status;
        @SerializedName("show_mobile_email") @Expose public String show_mobile_email;
        @SerializedName("shop_image") @Expose public String shop_image;
        @SerializedName("service_id") @Expose public int service_id;
        @SerializedName("description") @Expose public String description;
        @SerializedName("service_parent_id") @Expose public int service_parent_id;
        @SerializedName("iframe") @Expose public String iframe = null;
        @SerializedName("open_close_time") @Expose public String open_close_time = null;
        @SerializedName("open_time") @Expose public String open_time = null;
        @SerializedName("close_time") @Expose public String close_time = null;
        @SerializedName("deliveryStatus") @Expose public String deliveryStatus = null;
        @SerializedName("deliveryCharge") @Expose public String deliveryCharge = null;
        @SerializedName("room_capacity") @Expose public String room_capacity = null;
        @SerializedName("cod") @Expose public String cod = null;
        @SerializedName("created_at") @Expose public String created_at = null;
        @SerializedName("updated_at") @Expose public String updated_at = null;
        @SerializedName("ratingCount") @Expose public int ratingCount;
        @SerializedName("ratingAverage") @Expose public int ratingAverage;
    }

    public class Hotels{
        @SerializedName("id") @Expose public int id;
        @SerializedName("seller_id") @Expose public int seller_id;
        @SerializedName("name") @Expose public String name;
        @SerializedName("description") @Expose public String description;
        @SerializedName("price") @Expose public int price;
        @SerializedName("checkin_time") @Expose public String checkin_time;
        @SerializedName("checkout_time") @Expose public String checkout_time;
        @SerializedName("image") @Expose public String image;
        @SerializedName("google_location") @Expose public String google_location;
        @SerializedName("status") @Expose public String status;
        @SerializedName("amenities") @Expose public String amenities;
        @SerializedName("bed_size") @Expose public String bed_size;
        @SerializedName("room_square_feet") @Expose public String room_square_feet;
        @SerializedName("available_rooms") @Expose public String available_rooms;
        @SerializedName("created_at") @Expose public String created_at;
        @SerializedName("updated_at") @Expose public String updated_at;
        @SerializedName("imageTwo") @Expose public String imageTwo;
        @SerializedName("imageThree") @Expose public String imageThree;
        @SerializedName("imageFour") @Expose public String imageFour;
        @SerializedName("childrenExtra") @Expose public String childrenExtra;
        @SerializedName("adultExtra") @Expose public String adultExtra;
        @SerializedName("imageFive") @Expose public String imageFive;
        @SerializedName("from_block") @Expose public String from_block;
        @SerializedName("to_block") @Expose public String to_block;
        @SerializedName("roomCapacity") @Expose public String roomCapacity;
        @SerializedName("priceDescription") @Expose public String priceDescription;
    }

    public class Data{
        @SerializedName("children") @Expose public ArrayList<String> children;
        @SerializedName("adult") @Expose public ArrayList<String> adult;
        @SerializedName("rooms") @Expose public ArrayList<String> rooms;
    }
}