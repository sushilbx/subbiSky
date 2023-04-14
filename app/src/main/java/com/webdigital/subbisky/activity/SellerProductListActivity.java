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
import com.webdigital.subbisky.adapter.SellerProductListAdapter;
import com.webdigital.subbisky.model.SellerProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerProductListActivity extends AppCompatActivity {
    Button createbtn;
    LinearLayout linerLayoutBack;
    Session session;
    SellerProductListAdapter sellerProductListAdapter;
    RecyclerView sellerProductRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_list);
        createbtn=findViewById(R.id.createbtn);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        sellerProductRecycler=findViewById(R.id.sellerProductRecycler);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        createbtn.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerProductListActivity.this,SellerCreateProductActivity.class);
                startActivity(intent);
            }
        });
        sellerProductRecycler.setHasFixedSize(true);
        sellerProductRecycler.setLayoutManager(new LinearLayoutManager(SellerProductListActivity.this,LinearLayoutManager.VERTICAL, false));

        getProductList();

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SellerProductListActivity.this,SellerDashBoardActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void getProductList() {
        session = new Session(SellerProductListActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<SellerProductModel> call = RetrofitClient.getInstance().getApi().getSellerProducts(auth);
        call.enqueue(new Callback<SellerProductModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SellerProductModel> call, Response<SellerProductModel> response) {
                if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    if(response.body().getProducts().size() != 0){
                        sellerProductListAdapter = new SellerProductListAdapter((List<SellerProductModel.SellerProductList>) response.body().getProducts(),SellerProductListActivity.this);
                        sellerProductRecycler.setAdapter(sellerProductListAdapter);
                        Toast.makeText(SellerProductListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerProductListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerProductListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerProductModel> call, Throwable t) {
                Toast.makeText(SellerProductListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}