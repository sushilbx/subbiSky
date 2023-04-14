package com.webdigital.subbisky.model;

import java.util.List;

public class CustomerOrderListModel {
    private String message;
    private Boolean success;
    private Integer statusCode;
    private OrderedProductDetail order;

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

    public OrderedProductDetail getOrder() {
        return order;
    }

    public void setOrder(OrderedProductDetail order) {
        this.order = order;
    }

    public class OrderedProductDetail {
        private int id,seller_id,user_id;
        private String order_id,name,phone,email,city_id,pincode,landmark,address,payable_price,commision_amount,
                comission_percentage, payable_amount_seller,payment_id,status,payment_mode,delivered_date,sellerService_id,hotel_from_date,
                hotel_to_date,hotel_adult_no,hotel_children_no,childrenExtraCharge,adultExtraCharge,hotel_room_no,
                hotel_nos_of_days,appointment_date,appointment_time,takeAway,created_at,updated_at;
        private List<CustomerOrderList>list;
        private Customer_Hotel_id hotel_id;

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

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getPayable_price() {
            return payable_price;
        }

        public void setPayable_price(String payable_price) {
            this.payable_price = payable_price;
        }

        public String getCommision_amount() {
            return commision_amount;
        }

        public void setCommision_amount(String commision_amount) {
            this.commision_amount = commision_amount;
        }

        public String getComission_percentage() {
            return comission_percentage;
        }

        public void setComission_percentage(String comission_percentage) {
            this.comission_percentage = comission_percentage;
        }

        public String getPayable_amount_seller() {
            return payable_amount_seller;
        }

        public void setPayable_amount_seller(String payable_amount_seller) {
            this.payable_amount_seller = payable_amount_seller;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayment_mode() {
            return payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.payment_mode = payment_mode;
        }

        public String getDelivered_date() {
            return delivered_date;
        }

        public void setDelivered_date(String delivered_date) {
            this.delivered_date = delivered_date;
        }

        public String getSellerService_id() {
            return sellerService_id;
        }

        public void setSellerService_id(String sellerService_id) {
            this.sellerService_id = sellerService_id;
        }

        public String getHotel_from_date() {
            return hotel_from_date;
        }

        public void setHotel_from_date(String hotel_from_date) {
            this.hotel_from_date = hotel_from_date;
        }

        public String getHotel_to_date() {
            return hotel_to_date;
        }

        public void setHotel_to_date(String hotel_to_date) {
            this.hotel_to_date = hotel_to_date;
        }

        public String getHotel_adult_no() {
            return hotel_adult_no;
        }

        public void setHotel_adult_no(String hotel_adult_no) {
            this.hotel_adult_no = hotel_adult_no;
        }

        public String getHotel_children_no() {
            return hotel_children_no;
        }

        public void setHotel_children_no(String hotel_children_no) {
            this.hotel_children_no = hotel_children_no;
        }

        public String getChildrenExtraCharge() {
            return childrenExtraCharge;
        }

        public void setChildrenExtraCharge(String childrenExtraCharge) {
            this.childrenExtraCharge = childrenExtraCharge;
        }

        public String getAdultExtraCharge() {
            return adultExtraCharge;
        }

        public void setAdultExtraCharge(String adultExtraCharge) {
            this.adultExtraCharge = adultExtraCharge;
        }

        public String getHotel_room_no() {
            return hotel_room_no;
        }

        public void setHotel_room_no(String hotel_room_no) {
            this.hotel_room_no = hotel_room_no;
        }

        public String getHotel_nos_of_days() {
            return hotel_nos_of_days;
        }

        public void setHotel_nos_of_days(String hotel_nos_of_days) {
            this.hotel_nos_of_days = hotel_nos_of_days;
        }

        public Customer_Hotel_id getHotel_id() {

            return hotel_id;
        }

        public void setHotel_id(Customer_Hotel_id hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getAppointment_date() {
            return appointment_date;
        }

        public void setAppointment_date(String appointment_date) {
            this.appointment_date = appointment_date;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

        public String getTakeAway() {
            return takeAway;
        }

        public void setTakeAway(String takeAway) {
            this.takeAway = takeAway;
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

        public List<CustomerOrderList> getList() {
            return list;
        }

        public void setList(List<CustomerOrderList> list) {
            this.list = list;
        }

        public class CustomerOrderList {
            private int id,order_id;
            private String mrp_price,selling_price,quantity,created_at,unit,updated_at;
            private Product_id product_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getMrp_price() {
                return mrp_price;
            }

            public void setMrp_price(String mrp_price) {
                this.mrp_price = mrp_price;
            }

            public String getSelling_price() {
                return selling_price;
            }

            public void setSelling_price(String selling_price) {
                this.selling_price = selling_price;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public Product_id getProduct_id() {
                return product_id;
            }

            public void setProduct_id(Product_id product_id) {
                this.product_id = product_id;
            }

            public class Product_id {
                private int id,category_id,seller_id;
                private String name,image,description,stock,mrp_price,selling_price,unit,
                        status,created_at,updated_at;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
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

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getStock() {
                    return stock;
                }

                public void setStock(String stock) {
                    this.stock = stock;
                }

                public String getMrp_price() {
                    return mrp_price;
                }

                public void setMrp_price(String mrp_price) {
                    this.mrp_price = mrp_price;
                }

                public String getSelling_price() {
                    return selling_price;
                }

                public void setSelling_price(String selling_price) {
                    this.selling_price = selling_price;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
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
        }

        public class Customer_Hotel_id {
            private int id;
            private String seller_id,name,description,price,checkin_time,checkout_time,image,google_location,status,amenities,bed_size,room_square_feet,available_rooms,created_at,updated_at,imageTwo,imageThree,imageFour,childrenExtra,adultExtra,imageFive,from_block,to_block,roomCapacity,priceDescription;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
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
        }
    }
}
