package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.VerifyAccountModel;

public class OTPVerifyActivity extends AppCompatActivity {

    Session session;
    TextView otpafterSignUp;
    Button verifyOtp;
    Intent intent;
   public static String userId,userOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);
        otpafterSignUp = findViewById(R.id.otpafterSignUp);
        verifyOtp = findViewById(R.id.verifyOtpSignUp);
        intent = getIntent();
        userId= intent.getStringExtra("userId");

        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOtp = otpafterSignUp.getText().toString();
                if (!userOtp.isEmpty()) {
                    Call<VerifyAccountModel> call = RetrofitClient.getInstance().getApi().verifyAcount(userId, userOtp);
                    call.enqueue(new Callback<VerifyAccountModel>() {
                        @Override
                        public void onResponse(Call<VerifyAccountModel> call, Response<VerifyAccountModel> response) {
                            if (response.isSuccessful()) {
                                Session session = new Session(OTPVerifyActivity.this);
                                Toast.makeText(OTPVerifyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                session.createLoginSession(response.body().getUser().getId().toString());
//                    String  userId= response.body().getSeller().getUser_id();
                                Intent intent = new Intent(OTPVerifyActivity.this, Login.class);
//                    intent.putExtra("userId",userId);
                                startActivity(intent);
                            } else {
                                Toast.makeText(OTPVerifyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyAccountModel> call, Throwable throwable) {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }else {
                    otpafterSignUp.setError("Please Enter Your Otp to Verify...");
                }
            }
        });


    }
}