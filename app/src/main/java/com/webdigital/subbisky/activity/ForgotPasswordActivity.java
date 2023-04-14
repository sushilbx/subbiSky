package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.ForgotPasswordCreateModel;
import com.webdigital.subbisky.model.ForgotPasswordOtpModel;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edt_email,enterOtp;
    private Button sendOtp,verfiyOtp;
    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        edt_email=findViewById(R.id.forgotemail);
        enterOtp=findViewById(R.id.entermailOtp);
        sendOtp=findViewById(R.id.sendOtptomail);
        verfiyOtp=findViewById(R.id.verifyEmailOtp);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        enterOtp.setVisibility(View.GONE);
        verfiyOtp.setVisibility(View.GONE);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = edt_email.getEditableText().toString();
                if (!emails.isEmpty()) {
                    Call<ForgotPasswordCreateModel> call = RetrofitClient.getInstance().getApi().passwordCreate(emails);
                    call.enqueue(new Callback<ForgotPasswordCreateModel>() {
                        @Override
                        public void onResponse(Call<ForgotPasswordCreateModel> call, Response<ForgotPasswordCreateModel> response) {
                            if (response.isSuccessful()) {
                                enterOtp.setVisibility(View.VISIBLE);
                                verfiyOtp.setVisibility(View.VISIBLE);

                                Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ForgotPasswordCreateModel> call, Throwable throwable) {
                            Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    edt_email.requestFocus();
                    edt_email.setError("Please enter Email");
                }
            }
        });

        verfiyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otps = enterOtp.getEditableText().toString();
                if (!otps.isEmpty()) {
                    Call<ForgotPasswordOtpModel> call = RetrofitClient.getInstance().getApi().passwordFind(otps);
                    call.enqueue(new Callback<ForgotPasswordOtpModel>() {
                        @Override
                        public void onResponse(Call<ForgotPasswordOtpModel> call, Response<ForgotPasswordOtpModel> response) {
                            if (response.code()==200) {
//                                Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                                intent.putExtra("token",otps);
                                startActivity(intent);
                            }else if(response.code()==404) {
                                Toast.makeText( getApplicationContext(), "This password reset token is invalid.", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<ForgotPasswordOtpModel> call, Throwable throwable) {
                            Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    enterOtp.requestFocus();
                    enterOtp.setError("Please enter OTP");
                }
            }
        });
    }
}