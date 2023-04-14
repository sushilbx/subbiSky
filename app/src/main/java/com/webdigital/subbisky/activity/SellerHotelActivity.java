package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SellerHotelDetailsAdapter;
import com.webdigital.subbisky.model.SellerHotelListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerHotelActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Button createbtn;
    SellerHotelDetailsAdapter sellerHotelDetailsAdapter;
    Session session;
    RecyclerView sellerHotelRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_hotel);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        sellerHotelRecycler = findViewById(R.id.sellerHotelsRecycler);

        createbtn=findViewById(R.id.createbtn);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerHotelActivity.this,SellerCreateHotelDetailsActivity.class);
                startActivity(intent);
            }
        });
        sellerHotelRecycler.setHasFixedSize(true);
        sellerHotelRecycler.setLayoutManager(new LinearLayoutManager(SellerHotelActivity.this,LinearLayoutManager.VERTICAL, false));

        getHotelList();
    }
    private void getHotelList() {
        session = new Session(SellerHotelActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<SellerHotelListModel> call = RetrofitClient.getInstance().getApi().sellerHotelList(auth);
        call.enqueue(new Callback<SellerHotelListModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SellerHotelListModel> call, Response<SellerHotelListModel> response) {
                if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    if(response.body().getHotels().size() != 0){
                        sellerHotelDetailsAdapter = new SellerHotelDetailsAdapter((List<SellerHotelListModel.SellerHotel>) response.body().getHotels(),SellerHotelActivity.this);
                        sellerHotelRecycler.setAdapter(sellerHotelDetailsAdapter);
                        Toast.makeText(SellerHotelActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerHotelActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerHotelActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerHotelListModel> call, Throwable t) {
                Toast.makeText(SellerHotelActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}