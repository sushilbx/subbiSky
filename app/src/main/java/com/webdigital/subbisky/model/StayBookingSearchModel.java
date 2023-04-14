package com.webdigital.subbisky.model;

import java.util.List;

public class StayBookingSearchModel  {
    private Integer statusCode;
    private Boolean success;
    private String message;
    private StayBookingShop shop;
    private List<StayBookingReview> reviews;
    private List<StayBookingHotels> hotels;
    private List<StayBookingAmenities> amenities;
    private StayBookingBookingDetails bookingDetails;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StayBookingShop getShop() {
        return shop;
    }

    public void setShop(StayBookingShop shop) {
        this.shop = shop;
    }

    public List<StayBookingReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<StayBookingReview> reviews) {
        this.reviews = reviews;
    }

    public List<StayBookingHotels> getHotels() {
        return hotels;
    }

    public void setHotels(List<StayBookingHotels> hotels) {
        this.hotels = hotels;
    }

    public List<StayBookingAmenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<StayBookingAmenities> amenities) {
        this.amenities = amenities;
    }

    public StayBookingBookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(StayBookingBookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }


    public class StayBookingShop {
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

    public class StayBookingReview {
    }

    public static class StayBookingHotels {
        private int id,seller_id;
        private String  name,description,price,checkin_time,checkout_time,image,google_location,status,amenities,bed_size,room_square_feet,available_rooms,created_at,updated_at,imageTwo,imageThree,imageFour,childrenExtra,adultExtra,imageFive,from_block,to_block,roomCapacity,priceDescription;
        private List<StayBookingHotelsAmenities> amenity_id;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCheckin_time() {
            return checkin_time;
        }

        public void setCheckin_time(String checkin_time) {
            this.checkin_time = checkin_time;
        }

        public String getCheckout_time() {
            return checkout_time;
        }

        public void setCheckout_time(String checkout_time) {
            this.checkout_time = checkout_time;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGoogle_location() {
            return google_location;
        }

        public void setGoogle_location(String google_location) {
            this.google_location = google_location;
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

        public String getBed_size() {
            return bed_size;
        }

        public void setBed_size(String bed_size) {
            this.bed_size = bed_size;
        }

        public String getRoom_square_feet() {
            return room_square_feet;
        }

        public void setRoom_square_feet(String room_square_feet) {
            this.room_square_feet = room_square_feet;
        }

        public String getAvailable_rooms() {
            return available_rooms;
        }

        public void setAvailable_rooms(String available_rooms) {
            this.available_rooms = available_rooms;
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

        public String getFrom_block() {
            return from_block;
        }

        public void setFrom_block(String from_block) {
            this.from_block = from_block;
        }

        public String getTo_block() {
            return to_block;
        }

        public void setTo_block(String to_block) {
            this.to_block = to_block;
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

        public List<StayBookingHotelsAmenities> getAmenity_id() {
            return amenity_id;
        }

        public void setAmenity_id(List<StayBookingHotelsAmenities> amenity_id) {
            this.amenity_id = amenity_id;
        }

        public class StayBookingHotelsAmenities {
            private int id;
            private String name,image,created_at,updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

    public class StayBookingAmenities {
        private int id;
        private String name,image,created_at,updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

    public class StayBookingBookingDetails {
        private String  from,to,adult,totalRoom,totalChildren,adultExtra,childrenExtra;

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

        public String getAdult() {
            return adult;
        }

        public void setAdult(String adult) {
            this.adult = adult;
        }

        public String getTotalRoom() {
            return totalRoom;
        }

        public void setTotalRoom(String totalRoom) {
            this.totalRoom = totalRoom;
        }

        public String getTotalChildren() {
            return totalChildren;
        }

        public void setTotalChildren(String totalChildren) {
            this.totalChildren = totalChildren;
        }

        public String getAdultExtra() {
            return adultExtra;
        }

        public void setAdultExtra(String adultExtra) {
            this.adultExtra = adultExtra;
        }

        public String getChildrenExtra() {
            return childrenExtra;
        }

        public void setChildrenExtra(String childrenExtra) {
            this.childrenExtra = childrenExtra;
        }
    }
}
