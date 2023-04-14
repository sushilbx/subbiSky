package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerMyShopModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerMyShopDetailsActivity extends AppCompatActivity {
        TextView SellermyShopNameInDetails,SellermyShopStatusInDetails,
                SellermyShopCategoryInDetails,SellermyShopCityInDetails,
                SellermyShopNumberInDetails,SellermyShopEmailInDetails,
                SellermyShopAddressInDetails,SellermyShopAboutUsInDetails,
                SellermyShopCapacityInDetails,SellermyopencloseInDetails;
        ImageView SellermyShopImageInDetails;
    LinearLayout linerLayoutBack,ecom,staybook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_my_shop_details);

        SellermyShopNameInDetails=findViewById(R.id.SellermyShopNameInDetails);
        SellermyShopStatusInDetails=findViewById(R.id.SellermyShopStatusInDetails);
        SellermyShopCategoryInDetails=findViewById(R.id.SellermyShopCategoryInDetails);
        SellermyShopCityInDetails=findViewById(R.id.SellermyShopCityInDetails);
        SellermyShopNumberInDetails=findViewById(R.id.SellermyShopNumberInDetails);
        SellermyShopEmailInDetails=findViewById(R.id.SellermyShopEmailInDetails);
        SellermyShopAddressInDetails=findViewById(R.id.SellermyShopAddressInDetails);
        SellermyShopAboutUsInDetails=findViewById(R.id.SellermyShopAboutUsInDetails);
        SellermyShopCapacityInDetails=findViewById(R.id.SellermyShopCapacityInDetails);
        SellermyShopImageInDetails=findViewById(R.id.SellermyShopImageInDetails);
        SellermyopencloseInDetails=findViewById(R.id.SellermyopencloseInDetails);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        staybook=findViewById(R.id.staybook);
        ecom=findViewById(R.id.ecom);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        if(SellerDashBoardActivity.usertype.equalsIgnoreCase("ecom")){
            staybook.setVisibility(View.GONE);
            ecom.setVisibility(View.VISIBLE);

        }
        else{
            staybook.setVisibility(View.VISIBLE);
            ecom.setVisibility(View.GONE);
        }


        Session session = new Session(SellerMyShopDetailsActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<SellerMyShopModel> call = RetrofitClient.getInstance().getApi().sellerMyShop(auth);
        call.enqueue(new Callback<SellerMyShopModel>() {
            @Override
            public void onResponse(Call<SellerMyShopModel> call, Response<SellerMyShopModel> response) {
                if (response.isSuccessful()){

                    Glide.with(SellermyShopImageInDetails)
                            .load(response.body().getSeller().getShop_image()).fitCenter().into(SellermyShopImageInDetails);
                    SellermyShopNameInDetails.setText(response.body().getSeller().getShop_name());
                    SellermyShopStatusInDetails.setText(response.body().getBasicinfo().getStatus());
                    SellermyShopCityInDetails.setText(response.body().getBasicinfo().getCity_id());
                    SellermyShopNumberInDetails.setText(response.body().getBasicinfo().getPhone());
                    SellermyShopEmailInDetails.setText(response.body().getBasicinfo().getEmail());
                    SellermyShopAddressInDetails.setText(response.body().getSeller().getShop_address());
                    SellermyShopAboutUsInDetails.setText(response.body().getSeller().getDescription());
                    SellermyopencloseInDetails.setText(response.body().getSeller().getOpen_close_time());
                    SellermyShopCapacityInDetails.setText(response.body().getSeller().getRoom_capacity());
                    Toast.makeText(SellerMyShopDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SellerMyShopDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerMyShopModel> call, Throwable t) {
                Toast.makeText(SellerMyShopDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}