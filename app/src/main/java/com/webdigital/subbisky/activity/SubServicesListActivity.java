package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.HomeServiceListAdapter;
import com.webdigital.subbisky.adapter.SubServiceListAdapter;
import com.webdigital.subbisky.model.BannersModel;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.SubServiceListModel;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.model.SubServiceListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubServicesListActivity extends AppCompatActivity {
    RecyclerView subservicelistRecycler;
    private SubServiceListAdapter subServiceListAdapter;
    Intent intent;
  public static   String serviceName,serviceId,type,type1;
    TextView subServicesListName,cart_badge;
    ImageView imgCart;
    LinearLayout linerLayoutBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_services_list);
        intent=getIntent();
        serviceId=intent.getStringExtra("serviceId");
        serviceName=intent.getStringExtra("serviceName");
        type=intent.getStringExtra("type");

        Log.e("serviceId1",serviceId);
        Log.e("serviceName1",serviceName);
        Log.e("type",""+type);

        subservicelistRecycler=findViewById(R.id.subservicelistRecycler);
        subServicesListName=findViewById(R.id.subServicesListName);
        subServicesListName.setText(serviceName +" List");
        subservicelistRecycler.setHasFixedSize(true);

        subservicelistRecycler.setLayoutManager(new LinearLayoutManager(SubServicesListActivity.this,LinearLayoutManager.VERTICAL, false));
        getCartCount();
        getSubServiceList();
        SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("yourStringName", type);
        editor.commit();
        imgCart = findViewById(R.id.imgCart);
        cart_badge = findViewById(R.id.cart_badge);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubServicesListActivity.this,CartListActivity.class);
                startActivity(intent);
            }
        });
        getCartCount();
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                //finish();
            }
        });





    }
    public void onResume() {
        super.onResume();
        getCartCount();
    }
    private void getCartCount() {
        Session session = new Session(SubServicesListActivity.this);
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

    private void getSubServiceList() {
        Call<SubServiceListModel> call = RetrofitClient.getInstance().getApi().subServiceList(serviceId);
        call.enqueue(new Callback<SubServiceListModel>() {
            @Override
            public void onResponse(Call<SubServiceListModel> call, Response<SubServiceListModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getSub().size() != 0){
                        subServiceListAdapter = new SubServiceListAdapter((List<SubServiceListModel.SubListClass>) response.body().getSub(),SubServicesListActivity.this);
                        subservicelistRecycler.setAdapter(subServiceListAdapter);
                        Toast.makeText(SubServicesListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SubServicesListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SubServicesListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SubServiceListModel> call, Throwable t) {
                Toast.makeText(SubServicesListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}