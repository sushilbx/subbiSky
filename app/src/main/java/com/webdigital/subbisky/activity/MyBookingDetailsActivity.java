package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CustomerOrderListModel;

public class MyBookingDetailsActivity extends AppCompatActivity {
    TextView bookingId,hotel,checkInTime,checkOutTime,numOfAdult,numOfChildren,numOfRoom,adultExtraCharge,childrenExtraCharge,amountPaid,status,locate;
    Session session;
    Intent intent ;
    String orderId;
    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking_details);

        bookingId = findViewById(R.id.orderBookingId);
        hotel = findViewById(R.id.orderHotelName);
        checkInTime = findViewById(R.id.hotelCheckInTime);
        checkOutTime = findViewById(R.id.hotelCheckOutTime);
        numOfAdult = findViewById(R.id.numOfAdults);
        numOfChildren = findViewById(R.id.numOfChildren);
        numOfRoom = findViewById(R.id.numOfRoom);
        adultExtraCharge = findViewById(R.id.adultExtraCharge);
        childrenExtraCharge = findViewById(R.id.childrenExtraCharge);
        locate = findViewById(R.id.locate);
        amountPaid = findViewById(R.id.amountPaid);
        status = findViewById(R.id.orderStatus);
        intent = getIntent();
        orderId = intent.getStringExtra("orderId");

        getOrderDetails(orderId);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }
    private void getOrderDetails(String orderId) {
        Log.e("orderId",orderId);
        session = new Session(MyBookingDetailsActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Log.e("auth",auth);
        Call<CustomerOrderListModel> call = RetrofitClient.getInstance().getApi().customerOrderDetails(auth,orderId);
        call.enqueue(new Callback<CustomerOrderListModel>() {
            @Override
            public void onResponse(Call<CustomerOrderListModel> call, Response<CustomerOrderListModel> response) {
                if (response.isSuccessful()){
                    bookingId.setText("Booking Id :"+response.body().getOrder().getOrder_id());
                    hotel.setText("Hotel :"+response.body().getOrder().getHotel_id().getName());
                    checkInTime.setText("Checkin Time :"+response.body().getOrder().getHotel_id().getCheckin_time());
                    checkOutTime.setText("Checkout Time :"+response.body().getOrder().getHotel_id().getCheckout_time());
                    numOfAdult.setText("Nos of Adult :"+response.body().getOrder().getHotel_adult_no());
                    numOfChildren.setText("Nos of Children :"+response.body().getOrder().getHotel_children_no());
                    numOfRoom.setText("Nos of Room :"+response.body().getOrder().getHotel_room_no());
                    adultExtraCharge.setText("Adult Extra Charge :"+response.body().getOrder().getAdultExtraCharge());
                    childrenExtraCharge.setText("Children Extra Charge :"+response.body().getOrder().getChildrenExtraCharge());
                    amountPaid.setText("Amount Paid :"+response.body().getOrder().getPayable_price());
                    status.setText("Status :"+response.body().getOrder().getStatus());
                    locate.setText("Locate :Get Direction");
                    locate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String address = response.body().getOrder().getHotel_id().getGoogle_location();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CustomerOrderListModel> call, Throwable t) {

            }
        });

    }
}