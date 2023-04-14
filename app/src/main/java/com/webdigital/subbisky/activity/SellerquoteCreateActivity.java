package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SellerWalletAdapter;
import com.webdigital.subbisky.model.CustomerEditProfile;
import com.webdigital.subbisky.model.SellerSaveQuotesModel;
import com.webdigital.subbisky.model.SellerWalletModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerquoteCreateActivity extends AppCompatActivity {
    Session session;
EditText message;
Button createbtn;
String smessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerquote_create);

        session = new Session(SellerquoteCreateActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        createbtn=findViewById(R.id.createbtn);
        message=findViewById(R.id.message);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smessage = message.getText().toString().trim();

                session = new Session(SellerquoteCreateActivity .this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                Call<SellerSaveQuotesModel> call = RetrofitClient.getInstance().getApi().sellerQuotes(auth,smessage);
                call.enqueue(new Callback<SellerSaveQuotesModel>() {
                    @Override
                    public void onResponse(Call<SellerSaveQuotesModel> call, Response<SellerSaveQuotesModel> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SellerquoteCreateActivity.this,SellerQuotesActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<SellerSaveQuotesModel> call, Throwable throwable) {
                        Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}