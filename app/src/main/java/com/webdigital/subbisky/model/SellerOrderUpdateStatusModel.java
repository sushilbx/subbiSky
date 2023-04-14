package com.webdigital.subbisky.model;

public class SellerOrderUpdateStatusModel {
    private String message;
    private Boolean success;
    private Integer statusCode;
    private OrderStatus order;

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

    public OrderStatus getOrder() {
        return order;
    }

    public void setOrder(OrderStatus order) {
        this.order = order;
    }

    public class OrderStatus {
        private String id,seller_id,user_id,payable_price,commision_amount,comission_percentage,
                hotel_id,payable_amount_seller;
        private String order_id,name,phone,email,city_id,pincode,landmark,address,payment_id,
                status,payment_mode,delivered_date,sellerService_id,hotel_from_date,hotel_to_date,
                hotel_adult_no,hotel_children_no,childrenExtraCharge,adultExtraCharge,hotel_room_no,
                hotel_nos_of_days,appointment_date,appointment_time,takeAway,created_at,updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getPayable_amount_seller() {
            return payable_amount_seller;
        }

        public void setPayable_amount_seller(String payable_amount_seller) {
            this.payable_amount_seller = payable_amount_seller;
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
    }
}
