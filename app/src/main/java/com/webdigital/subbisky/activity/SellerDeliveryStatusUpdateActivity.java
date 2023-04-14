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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.DeliveryStatusUpdateModel;

public class SellerDeliveryStatusUpdateActivity extends AppCompatActivity {

    RadioButton deliveryStatusActive,deliveryStatusInActive,codActive,codInActive;
    EditText sellerdeliverySharge;
    Button sellerDeliveryStatusUpdate;
    String deliveryStatus = "",cod= "";
    RadioGroup codStatus,deliveryStatusRadios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_delivery_status_update);

        sellerdeliverySharge=findViewById(R.id.sellerdeliverySharge);
        deliveryStatusActive=findViewById(R.id.deliveryStatusActive);
        deliveryStatusInActive=findViewById(R.id.deliveryStatusInActive);
        codActive=findViewById(R.id.codActive);
        codInActive=findViewById(R.id.codInActive);
        codStatus=findViewById(R.id.codStatus);
        deliveryStatusRadios=findViewById(R.id.deliveryStatus);
        sellerDeliveryStatusUpdate=findViewById(R.id.sellerDeliveryStatusUpdate);

        deliveryStatusRadios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.deliveryStatusActive){
                    deliveryStatus = "Active";

                    Log.e("deliveryStatus",deliveryStatus);
                }else  if (checkedId == R.id.deliveryStatusInActive){
                    deliveryStatus = "InActive";

                    Log.e("deliveryStatus",deliveryStatus);
                }
            }
        });

        codStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.codActive){
                    cod = "Active";

                    Log.e("deliver yStatus",deliveryStatus);
                }else  if (checkedId == R.id.codInActive){
                    cod = "InActive";

                    Log.e("deliveryStatus",deliveryStatus);
                }
            }
        });
//        if (deliveryStatusActive.isChecked()){
//            deliveryStatus = "Active";
//
//            Log.e("deliveryStatus",deliveryStatus);
//        }
//        if (deliveryStatusInActive.isChecked()){
//            deliveryStatus = "InActive";
//
//            Log.e("deliveryStatus",deliveryStatus);
//        }
//        if (codActive.isChecked()){
//            cod = "Active";
//            Log.e("cod",cod);
//        }
//        if (codInActive.isChecked()){
//            cod = "Active";
//            Log.e("cod",cod);
//        }
        sellerDeliveryStatusUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deliveryCharge = sellerdeliverySharge.getEditableText().toString();
                Log.e("deliveryCharge",deliveryCharge);
                Log.e("deliveryStatus",deliveryStatus);
                Log.e("cod",cod);
                Session session = new Session(SellerDeliveryStatusUpdateActivity.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                Log.e("authmyshop",auth);
                Call<DeliveryStatusUpdateModel> call = RetrofitClient.getInstance().getApi().deliveryStatusUpdate(auth,deliveryCharge,deliveryStatus,cod);
                call.enqueue(new Callback<DeliveryStatusUpdateModel>() {
                    @Override
                    public void onResponse(Call<DeliveryStatusUpdateModel> call, Response<DeliveryStatusUpdateModel> response) {
                        if (response.isSuccessful()){
//                    if (response.code()==200) {
//                            Log.e("Bearers", session.getUserDetails().get(Session.TOKEN));
//                            Glide.with(SellermyShopImage)
//                                    .load(response.body().getSeller().getShop_image()).fitCenter().into(SellermyShopImage);
//                            SellermyShopName.setText(response.body().getSeller().getShop_name());
//                            SellermyShopPhone.setText(response.body().getBasicinfo().getPhone());
//                            SellermyShopEmail.setText(response.body().getBasicinfo().getEmail());
                            Intent intent=new Intent(SellerDeliveryStatusUpdateActivity.this,SellerMyShopActivity.class);
                            startActivity(intent);
                            Toast.makeText(SellerDeliveryStatusUpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                        }else {
                            Toast.makeText(SellerDeliveryStatusUpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<DeliveryStatusUpdateModel> call, Throwable t) {
                        Toast.makeText(SellerDeliveryStatusUpdateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}