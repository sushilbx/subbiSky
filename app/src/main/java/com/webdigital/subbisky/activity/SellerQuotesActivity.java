package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SellerQuotesAdapter;
import com.webdigital.subbisky.model.SellerQuotesModel;

import java.util.List;

public class SellerQuotesActivity extends AppCompatActivity {

    Session session;
    RecyclerView sellerQuotesRecycler;
    SellerQuotesAdapter sellerQuotesAdapter;
    LinearLayout linerLayoutBack;
    Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_quotes);

        sellerQuotesRecycler=findViewById(R.id.sellerQuotesRecycler);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        create=findViewById(R.id.create);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SellerQuotesActivity.this,SellerquoteCreateActivity.class);
                startActivity(intent);
            }
        });

        sellerQuotesRecycler.setHasFixedSize(true);
        sellerQuotesRecycler.setLayoutManager(new LinearLayoutManager(SellerQuotesActivity.this,LinearLayoutManager.VERTICAL, false));

         session = new Session(SellerQuotesActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<SellerQuotesModel> call = RetrofitClient.getInstance().getApi().getSellerQuotes(auth);
        call.enqueue(new Callback<SellerQuotesModel>() {
            @Override
            public void onResponse(Call<SellerQuotesModel> call, Response<SellerQuotesModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getQuotes().size() != 0){
                        sellerQuotesAdapter = new SellerQuotesAdapter((List<SellerQuotesModel.SellerQuotes>) response.body().getQuotes(),SellerQuotesActivity.this);
                        sellerQuotesRecycler.setAdapter(sellerQuotesAdapter);
                        Toast.makeText(SellerQuotesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerQuotesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerQuotesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerQuotesModel> call, Throwable t) {
                Toast.makeText(SellerQuotesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}