package com.webdigital.subbisky.model;

public class PickupPaymentModel {
    private Integer statusCode;
    private Boolean success;
    private String message;
    private PickUpOrder order;
    private PickUpOrderDetails details;

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

    public PickUpOrder getOrder() {
        return order;
    }

    public void setOrder(PickUpOrder order) {
        this.order = order;
    }

    public PickUpOrderDetails getDetails() {
        return details;
    }

    public void setDetails(PickUpOrderDetails details) {
        this.details = details;
    }

    public class PickUpOrder {
        private int id,user_id;
        private String order_id,seller_id,name,phone,email,city_id,pincode,landmark,address,payable_price,payment_id,status,payment_mode,takeAway,updated_at,created_at,comission_percentage,commision_amount,payable_amount_seller;
        private PickUpSeller seller;

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getTakeAway() {
            return takeAway;
        }

        public void setTakeAway(String takeAway) {
            this.takeAway = takeAway;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getComission_percentage() {
            return comission_percentage;
        }

        public void setComission_percentage(String comission_percentage) {
            this.comission_percentage = comission_percentage;
        }

        public String getCommision_amount() {
            return commision_amount;
        }

        public void setCommision_amount(String commision_amount) {
            this.commision_amount = commision_amount;
        }

        public String getPayable_amount_seller() {
            return payable_amount_seller;
        }

        public void setPayable_amount_seller(String payable_amount_seller) {
            this.payable_amount_seller = payable_amount_seller;
        }

        public PickUpSeller getSeller() {
            return seller;
        }

        public void setSeller(PickUpSeller seller) {
            this.seller = seller;
        }

        public class PickUpSeller {
            private int id,user_id;
            private String shop_name,shop_address,status,show_mobile_email,shop_image,service_id,description,service_parent_id,iframe,open_close_time,deliveryStatus,deliveryCharge,room_capacity,cod,created_at,updated_at;
            private PickUpUser user;

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

            public PickUpUser getUser() {
                return user;
            }

            public void setUser(PickUpUser user) {
                this.user = user;
            }

            public class PickUpUser {
                private int id,user_id;
                private String name,email,phone,email_verified_at,status,city_id,pincode,landmark,address,otp,verify,verifyOtp,created_at,updated_at;

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
    }

    public class PickUpOrderDetails {
        private int order_id,id;
        private String quantity,pickUpAddress,pickUplatitude,pickUplogitude,dropAddressdropLatitude,dropLongitude,price,distance,deliveryContact,sellerService_id,updated_at,created_at;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPickUpAddress() {
            return pickUpAddress;
        }

        public void setPickUpAddress(String pickUpAddress) {
            this.pickUpAddress = pickUpAddress;
        }

        public String getPickUplatitude() {
            return pickUplatitude;
        }

        public void setPickUplatitude(String pickUplatitude) {
            this.pickUplatitude = pickUplatitude;
        }

        public String getPickUplogitude() {
            return pickUplogitude;
        }

        public void setPickUplogitude(String pickUplogitude) {
            this.pickUplogitude = pickUplogitude;
        }

        public String getDropAddressdropLatitude() {
            return dropAddressdropLatitude;
        }

        public void setDropAddressdropLatitude(String dropAddressdropLatitude) {
            this.dropAddressdropLatitude = dropAddressdropLatitude;
        }

        public String getDropLongitude() {
            return dropLongitude;
        }

        public void setDropLongitude(String dropLongitude) {
            this.dropLongitude = dropLongitude;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getDeliveryContact() {
            return deliveryContact;
        }

        public void setDeliveryContact(String deliveryContact) {
            this.deliveryContact = deliveryContact;
        }

        public String getSellerService_id() {
            return sellerService_id;
        }

        public void setSellerService_id(String sellerService_id) {
            this.sellerService_id = sellerService_id;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
