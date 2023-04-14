package com.webdigital.subbisky.model;

import java.util.List;

public class SellerHotelListEditModel {
    private Boolean success;
    private Integer statusCode;
    private String message;
    private List<SellerHotelListEditModel.SellerHotelEdit> hotels;

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

    public List<SellerHotelEdit> getHotels() {
        return hotels;
    }

    public void setHotels(List<SellerHotelEdit> hotels) {
        this.hotels = hotels;
    }

    public class SellerHotelEdit {
        private Integer id,seller_id,available_rooms;
        private String name,description,checkin_time,checkout_time,price,
                image,google_location,status,amenities,
                bed_size,room_square_feet,created_at,updated_at,imageTwo,imageThree,imageFour,childrenExtra,
                adultExtra,imageFive,from_block,to_block,roomCapacity,priceDescription;
        private List<SellerHotelListEditModel.SellerHotelEdit.Amenity_idEditList> amenity_id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(Integer seller_id) {
            this.seller_id = seller_id;
        }

        public Integer getAvailable_rooms() {
            return available_rooms;
        }

        public void setAvailable_rooms(Integer available_rooms) {
            this.available_rooms = available_rooms;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public List<Amenity_idEditList> getAmenity_id() {
            return amenity_id;
        }

        public void setAmenity_id(List<Amenity_idEditList> amenity_id) {
            this.amenity_id = amenity_id;
        }

        public class Amenity_idEditList {
            private Integer id;
            private String name,image,created_at,updated_at;

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
}
