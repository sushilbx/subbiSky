package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Session session;
    TextView Cname,Cemail,Cphone,Clandmark,Cpincode,Caddress,city,cart_badge;
    Button myOrders,myService,myBookings,editProfile,changePassword,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        Cname=findViewById(R.id.Cname);
        Cemail=findViewById(R.id.Cemail);
        Cphone=findViewById(R.id.Cphone);
        Clandmark=findViewById(R.id.Clandmark);
        Cpincode=findViewById(R.id.Cpincode);
        Caddress=findViewById(R.id.Caddress);
        city=findViewById(R.id.city);

        myOrders=findViewById(R.id.orderDetails);
        myService=findViewById(R.id.serviceDetails);
        myBookings=findViewById(R.id.bookingDetails);
        editProfile=findViewById(R.id.customerEditProfile);
        changePassword=findViewById(R.id.changepassword);
        logout=findViewById(R.id.logout);
        cart_badge=findViewById(R.id.cart_badge);

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,MyOrderActivity.class);
                startActivity(intent);
            }
        });
        getCartCount();
        myService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,MyServiceActivity.class);
                startActivity(intent);
            }
        });
        myBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,CustomerBookingList.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,LogoutActivity.class);
                startActivity(intent);
            }
        });

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        session = new Session(ProfileActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<UserModel> call = RetrofitClient.getInstance().getApi().userModel(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Cname.setText(response.body().getUser().getName());
                    Cemail.setText(response.body().getUser().getEmail());
                    Cphone.setText(response.body().getUser().getPhone());
                    Clandmark.setText(response.body().getUser().getLandmark());
                    Cpincode.setText(response.body().getUser().getPincode());
                    Caddress.setText(response.body().getUser().getAddress());
                    city.setText(response.body().getUser().getCity_id());


                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getCartCount() {
        Session session = new Session(ProfileActivity.this);
        String UserToken = session.getAccessTokenType() + " " + session.getAccessToken();
        Call<CartcountModel> call = RetrofitClient.getInstance().getApi().cartcount(UserToken);
        call.enqueue(new Callback<CartcountModel>() {
            @Override
            public void onResponse(Call<CartcountModel> call, Response<CartcountModel> response) {
                if (response.code() == 200) {
                    if (response.body().getMessage().equals("Cart is empty")) {
                        cart_badge.setText("0");
                    } else {
                        cart_badge.setText(response.body().getCount().toString());



                    }
                }
            }

            @Override
            public void onFailure(Call<CartcountModel> call, Throwable t) {

            }
        });

    }
}