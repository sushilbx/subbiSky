package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerMyShopModel;

public class SellerMyShopActivity extends AppCompatActivity {
    ImageView SellermyShopImage;
    TextView SellermyShopName,SellermyShopPhone,SellermyShopEmail;
    Button SellerMyShopDetails,sellerMyShopEditProfile,sellerMyShopBankDetails,sellerMyShopDeliveryStatus;
    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_my_shop);
        SellermyShopImage=findViewById(R.id.SellermyShopImage);
        SellermyShopName=findViewById(R.id.SellermyShopName);
        SellermyShopPhone=findViewById(R.id.SellermyShopPhone);
        SellermyShopEmail=findViewById(R.id.SellermyShopEmail);
        SellerMyShopDetails=findViewById(R.id.SellerMyShopDetails);
        sellerMyShopEditProfile=findViewById(R.id.sellerMyShopEditProfile);
        sellerMyShopBankDetails=findViewById(R.id.sellerMyShopBankDetails);
        sellerMyShopDeliveryStatus=findViewById(R.id.sellerMyShopDeliveryStatus);

        sellerMyShopDeliveryStatus.setVisibility(View.GONE);
        callApi();

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        SellerMyShopDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerMyShopActivity.this,SellerMyShopDetailsActivity.class);
                startActivity(intent);
            }
        });
        sellerMyShopEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerMyShopActivity.this,SellerMyShopEditProfileActivity.class);
                startActivity(intent);
            }
        });
        sellerMyShopBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerMyShopActivity.this,SellerMyShopBankDetailsActivity.class);
                startActivity(intent);
            }
        });

        sellerMyShopDeliveryStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerMyShopActivity.this,SellerDeliveryStatusUpdateActivity.class);
                startActivity(intent);
            }
        });


    }

    private void callApi() {
        Session session = new Session(SellerMyShopActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Log.e("authmyshop",auth);
        Call<SellerMyShopModel> call = RetrofitClient.getInstance().getApi().sellerMyShop(auth);
        call.enqueue(new Callback<SellerMyShopModel>() {
            @Override
            public void onResponse(Call<SellerMyShopModel> call, Response<SellerMyShopModel> response) {
                if (response.isSuccessful()){
//                    if (response.code()==200) {
                        Log.e("Bearers", session.getUserDetails().get(Session.TOKEN));
                        Glide.with(SellermyShopImage)
                                .load(response.body().getSeller().getShop_image()).fitCenter().into(SellermyShopImage);
                        SellermyShopName.setText(response.body().getSeller().getShop_name());
                        SellermyShopPhone.setText(response.body().getBasicinfo().getPhone());
                        SellermyShopEmail.setText(response.body().getBasicinfo().getEmail());
                    if (response.body().getSeller().getService_parent_id().getType().equals("ecom"))
                    {
                        sellerMyShopDeliveryStatus.setVisibility(View.VISIBLE);
                    }else {

                            sellerMyShopDeliveryStatus.setVisibility(View.GONE);
                    }
                        Toast.makeText(SellerMyShopActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                }else {
                    Toast.makeText(SellerMyShopActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerMyShopModel> call, Throwable t) {
                Toast.makeText(SellerMyShopActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}