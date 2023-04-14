package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import android.content.Intent;
import android.widget.Button;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.LogoutModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutActivity extends AppCompatActivity {
    Button logout_yes, logout_no;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logout_yes = findViewById(R.id.logout_yes);
        logout_no = findViewById(R.id.logout_no);
        session = new Session(LogoutActivity.this);
        logout_yes.setOnClickListener(v -> {
            ProgressDialog progressDialog = new ProgressDialog(LogoutActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            session = new Session(LogoutActivity.this);
            Call<LogoutModel> call = RetrofitClient.getInstance().getApi().logout(session.getAccessTokenType() + " " + session.getAccessToken());
            call.enqueue(new Callback<LogoutModel>() {
                @Override
                public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                    if (response.code() == 200) {
                        progressDialog.dismiss();
                        Toast.makeText(LogoutActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        session.logoutUser();
                        Intent redirect = new Intent(LogoutActivity.this, LoginWithActivity.class);
                        redirect.addCategory(Intent.CATEGORY_HOME);
                        redirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(redirect);
                        finish();


                    } else {
                        progressDialog.dismiss();
                        session.logoutUser();
                        Intent redirect = new Intent(LogoutActivity.this, LoginWithActivity.class);
                        redirect.addCategory(Intent.CATEGORY_HOME);
                        redirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(redirect);
                        finish();

//                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<LogoutModel> call, Throwable t) {
                    Toast.makeText(LogoutActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        });

        logout_no.setOnClickListener(v -> {

            if(session.getUserDetails().get(Session.MainUserType).equalsIgnoreCase("CUSTOMER")){
                Intent i = new Intent(LogoutActivity.this, DashboradActivity.class);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i = new Intent(LogoutActivity.this, SellerDashBoardActivity.class);
                startActivity(i);
                finish();

            }




        });
    }
}