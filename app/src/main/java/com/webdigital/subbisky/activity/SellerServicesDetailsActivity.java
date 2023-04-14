package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.ServicewithChargeAdapter;
import com.webdigital.subbisky.model.SellerServiceDetailsModel;
import com.webdigital.subbisky.model.ServiceDetailsModel;
import com.webdigital.subbisky.APIs.RetrofitClient;

import java.util.List;

public class SellerServicesDetailsActivity extends AppCompatActivity {
    Intent intent;
    int serviceId,sellerId,sellerServiceId;
    TextView sellerDetailTitle,sellerDetailsPageprice,sellerDetailsPageLocation,sellerDetailsPageDescription;
    ImageView sellerDetailsPageImage;
    Button btnPay;
    String customerType;

    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_services_details);
        intent = getIntent();
        serviceId = intent.getIntExtra("serviceId",0);
        sellerId = intent.getIntExtra("sellerId",0);
        btnPay = findViewById(R.id.btnPay);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        sellerDetailTitle = findViewById(R.id.sellerDetailTitle);
        sellerDetailsPageprice = findViewById(R.id.sellerDetailsPageprice);
        sellerDetailsPageLocation = findViewById(R.id.sellerDetailsPageLocation);
        sellerDetailsPageDescription = findViewById(R.id.sellerDetailsPageDescription);
        sellerDetailsPageImage = findViewById(R.id.sellerDetailsPageImage);
        getData();
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = sellerDetailsPageprice.getText().toString();
                if (!customerType.equals("pd")){
                    Intent intent = new Intent(SellerServicesDetailsActivity.this,ServicePayActivity.class);
                    intent.putExtra("customerType",customerType);
                    intent.putExtra("amount",amount);
                    intent.putExtra("sellerId",sellerId);
                    intent.putExtra("serviceId",serviceId);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SellerServicesDetailsActivity.this,PickDropPayActivity.class);
                    intent.putExtra("customerType",customerType);
                    intent.putExtra("amount",amount);
                    intent.putExtra("sellerId",sellerId);
                    intent.putExtra("serviceId",serviceId);
                    startActivity(intent);
                }
            }
        });
    }

    private void getData() {
        Log.e("serviceId", String.valueOf(serviceId));
        Log.e("sellerId", String.valueOf(sellerId));
        Call<SellerServiceDetailsModel> call = RetrofitClient.getInstance().getApi().getSellerServiceDetails(sellerId,serviceId);
        call.enqueue(new Callback<SellerServiceDetailsModel>() {
            @Override
            public void onResponse(Call<SellerServiceDetailsModel> call, Response<SellerServiceDetailsModel> response) {
                if (response.isSuccessful()){
                    sellerDetailTitle.setText(response.body().getSellerService().getName());
                    sellerDetailsPageprice.setText(response.body().getSellerService().getPrice());
                    sellerDetailsPageLocation.setText(response.body().getSeller().getShop_name());
                    sellerDetailsPageDescription.setText(response.body().getSellerService().getDescription());
                    Glide.with(sellerDetailsPageImage)
                            .load(response.body().getSeller().getShop_image()).fitCenter().into(sellerDetailsPageImage);
                    customerType = response.body().getMain().getType();
//                    sellerServiceId = Integer.parseInt(response.body().getSeller().getService_id());
                    Log.e("customerType",customerType);
                }
            }

            @Override
            public void onFailure(Call<SellerServiceDetailsModel> call, Throwable t) {

            }
        });
    }
}