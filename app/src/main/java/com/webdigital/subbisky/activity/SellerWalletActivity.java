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
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SellerWalletAdapter;
import com.webdigital.subbisky.model.SellerWalletModel;

import java.util.List;

public class SellerWalletActivity extends AppCompatActivity {
    Session session;
    RecyclerView sellerWalletRecycler;
    SellerWalletAdapter sellerWalletAdapter;
    LinearLayout linerLayoutBack;
    TextView totalWalletAmountSeller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_wallet);
        totalWalletAmountSeller = findViewById(R.id.totalWalletAmountSeller);
        sellerWalletRecycler=findViewById(R.id.sellerWalletRecycler);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        sellerWalletRecycler.setHasFixedSize(true);
        sellerWalletRecycler.setLayoutManager(new LinearLayoutManager(SellerWalletActivity.this,LinearLayoutManager.VERTICAL, false));


        session = new Session(SellerWalletActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<SellerWalletModel> call = RetrofitClient.getInstance().getApi().getSellerWallet(auth);
        call.enqueue(new Callback<SellerWalletModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SellerWalletModel> call, Response<SellerWalletModel> response) {
                if (response.isSuccessful()){
                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    if(response.body().getWallet().size() != 0){
                        sellerWalletAdapter = new SellerWalletAdapter((List<SellerWalletModel.SellerWallet>) response.body().getWallet(),SellerWalletActivity.this);
                        sellerWalletRecycler.setAdapter(sellerWalletAdapter);
                        Toast.makeText(SellerWalletActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerWalletActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerWalletActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerWalletModel> call, Throwable t) {
                Toast.makeText(SellerWalletActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}