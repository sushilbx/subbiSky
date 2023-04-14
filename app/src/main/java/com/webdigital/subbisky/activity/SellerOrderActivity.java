package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SellerEcomOrderAdapter;
import com.webdigital.subbisky.adapter.SellerOrderAdapter;
import com.webdigital.subbisky.model.SellerDashboardModel;
import com.webdigital.subbisky.model.SellerServiceOrderModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerOrderActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Session session;
    HorizontalScrollView ecomOrderManagementList,serviceOrderManagementList;
    SellerOrderAdapter sellerOrderAdapter;
    SellerEcomOrderAdapter sellerEcomOrderAdapter;
    RecyclerView sellerServiceOrderRecycler,sellerEcomOrderRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        ecomOrderManagementList=findViewById(R.id.ecomOrderManagementList);
        serviceOrderManagementList=findViewById(R.id.serviceOrderManagementList);
        sellerEcomOrderRecycler=findViewById(R.id.sellerEcomOrderRecycler);
        sellerServiceOrderRecycler=findViewById(R.id.sellerServiceOrderRecycler);
        sellerServiceOrderRecycler.setHasFixedSize(true);
        sellerServiceOrderRecycler.setLayoutManager(new LinearLayoutManager(SellerOrderActivity.this,LinearLayoutManager.VERTICAL, false));
        sellerEcomOrderRecycler.setHasFixedSize(true);
        sellerEcomOrderRecycler.setLayoutManager(new LinearLayoutManager(SellerOrderActivity.this, LinearLayoutManager.VERTICAL, false));

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
//        if ()
        Session session = new Session(SellerOrderActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Log.e("authmyshop",auth);

        Call<SellerDashboardModel> call = RetrofitClient.getInstance().getApi().sellerDashboard(auth);
        call.enqueue(new Callback<SellerDashboardModel>() {
            @Override
            public void onResponse(Call<SellerDashboardModel> call, Response<SellerDashboardModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getType().equals("ecom")) {
                        serviceOrderManagementList.setVisibility(View.GONE);

                        ecomServiceOrder();
                    } else if (response.body().getType().equals("staybooking")) {
                        ecomOrderManagementList.setVisibility(View.GONE);

                        sellerServiceOrder();
                    }
                }
            }

            @Override
            public void onFailure(Call<SellerDashboardModel> call, Throwable t) {

            }
        });

    }

    private void ecomServiceOrder() {
        Session session = new Session(SellerOrderActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<SellerServiceOrderModel> call = RetrofitClient.getInstance().getApi().sellerServiceOrder(auth);
        call.enqueue(new Callback<SellerServiceOrderModel>() {
            @Override
            public void onResponse(Call<SellerServiceOrderModel> call, Response<SellerServiceOrderModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getOrder().size() != 0){
                        sellerEcomOrderAdapter= new SellerEcomOrderAdapter((List<SellerServiceOrderModel.ServiceOrderList>) response.body().getOrder(),SellerOrderActivity.this);
                        sellerEcomOrderRecycler.setAdapter(sellerEcomOrderAdapter);
                        Toast.makeText(SellerOrderActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerOrderActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerOrderActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerServiceOrderModel> call, Throwable t) {
                Toast.makeText(SellerOrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void sellerServiceOrder() {
        Session session = new Session(SellerOrderActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<SellerServiceOrderModel> call = RetrofitClient.getInstance().getApi().sellerServiceOrder(auth);
        call.enqueue(new Callback<SellerServiceOrderModel>() {
            @Override
            public void onResponse(Call<SellerServiceOrderModel> call, Response<SellerServiceOrderModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getOrder().size() != 0){
                        sellerOrderAdapter= new SellerOrderAdapter((List<SellerServiceOrderModel.ServiceOrderList>) response.body().getOrder(),SellerOrderActivity.this);
                        sellerServiceOrderRecycler.setAdapter(sellerOrderAdapter);
                        Toast.makeText(SellerOrderActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerOrderActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerOrderActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerServiceOrderModel> call, Throwable t) {
                Toast.makeText(SellerOrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}