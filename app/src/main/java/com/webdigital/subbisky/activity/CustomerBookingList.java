package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.CustomerBookingAdapter;
import com.webdigital.subbisky.model.UserModel;

import java.util.List;

public class CustomerBookingList extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Session session;
    RecyclerView customerBookingRecycler;
    CustomerBookingAdapter customerBookingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking_list);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        customerBookingRecycler=findViewById(R.id.customerBookingRecycler);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        customerBookingRecycler.setHasFixedSize(true);
        customerBookingRecycler.setLayoutManager(new LinearLayoutManager(CustomerBookingList.this,LinearLayoutManager.VERTICAL, false));
        session = new Session(CustomerBookingList.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<UserModel> call = RetrofitClient.getInstance().getApi().customerBookingList(auth);
        call.enqueue(new Callback<UserModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    if(response.body().getBooking().size() != 0){
                        customerBookingAdapter = new CustomerBookingAdapter((List<UserModel.Booking>) response.body().getBooking(),CustomerBookingList.this);
                        customerBookingRecycler.setAdapter(customerBookingAdapter);
                        Toast.makeText(CustomerBookingList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(CustomerBookingList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(CustomerBookingList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(CustomerBookingList.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}