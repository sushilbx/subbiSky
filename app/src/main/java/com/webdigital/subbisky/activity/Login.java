package com.webdigital.subbisky.activity;

import static com.webdigital.subbisky.utils.Validation.EMAIL_PATTERN;
import static com.webdigital.subbisky.utils.Validation.MOBILE_PATTERN;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CustomerLoginModel;
import com.webdigital.subbisky.model.CustomerSignUpModel;
import com.webdigital.subbisky.model.LoginSellerModel;
import com.webdigital.subbisky.model.LoginWithOtpModel;
import com.webdigital.subbisky.model.SellerLoginWithOtpModel;
import com.webdigital.subbisky.model.SellerVerifyOtpModel;
import com.webdigital.subbisky.model.VerifyOtpModel;
import com.webdigital.subbisky.utils.Validation;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.model.CustomerLoginModel;
import com.webdigital.subbisky.model.LoginSellerModel;
import com.webdigital.subbisky.model.LoginWithOtpModel;
import com.webdigital.subbisky.model.SellerLoginWithOtpModel;

import androidx.cardview.widget.CardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText edt_email, edt_pass, enterphone, enterOtp, enterotpemail, enterverifyotpemail;
    private Button btn_signIn, sendOtp, emailCardOpen, phoneCardOpen, verfiyOtp;
    private TextView txt_register, forgetPassword;
    LinearLayout linerLayoutBack;
    private String edt_email1, edt_pass1;
    Boolean remember_me;
    Session session;
    String Check_Main_User_Type;
    Intent intent;
    String type;
    CardView emailCard, phoneCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(Login.this);
        Check_Main_User_Type = session.getUserDetails().get(Session.MainUserType);
        // Log.e("Check_Main_User_Type",  Check_Main_User_Type);

        intent = getIntent();
        type = intent.getStringExtra("Type");
        Log.e("Type", "" + type);
        edt_pass = findViewById(R.id.ed_password1);
        edt_email = findViewById(R.id.ed_email1);

        txt_register = findViewById(R.id.txt_register);
        forgetPassword = findViewById(R.id.forgetPassword);
        enterphone = findViewById(R.id.phone);
        enterotpemail = findViewById(R.id.otpemail);
        enterverifyotpemail = findViewById(R.id.verifyotpemail);

        sendOtp = findViewById(R.id.sendOtp);

        btn_signIn = findViewById(R.id.btnSignIn);
        phoneCardOpen = findViewById(R.id.txt_lgnOTP);
        emailCardOpen = findViewById(R.id.btnlgnEmail);
        emailCard = findViewById(R.id.loginwithEmailcard);
        phoneCard = findViewById(R.id.loginwithotpcard);
        enterOtp = findViewById(R.id.enterOtp);
        verfiyOtp = findViewById(R.id.verifyOtp);
        phoneCard.setVisibility(View.GONE);

        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        sendOtp.setEnabled(true);
        sendOtp.setBackgroundResource(R.drawable.round_bag);
        emailCardOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCard.setVisibility(View.GONE);
                emailCard.setVisibility(View.VISIBLE);
            }
        });
        phoneCardOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equalsIgnoreCase("SELLER")) {
                    phoneCard.setVisibility(View.VISIBLE);
                    enterotpemail.setVisibility(View.VISIBLE);
                    verfiyOtp.setVisibility(View.GONE);
                    emailCard.setVisibility(View.GONE);
                    enterOtp.setVisibility(View.GONE);
                } else {
                    emailCard.setVisibility(View.GONE);
                    phoneCard.setVisibility(View.VISIBLE);
                    enterOtp.setVisibility(View.GONE);
                    verfiyOtp.setVisibility(View.GONE);
                    enterotpemail.setVisibility(View.GONE);
                }

            }
        });

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phones = enterphone.getEditableText().toString();

                if (!phones.isEmpty()) {
                    if (Check_Main_User_Type.equals("CUSTOMER")) {
                        Call<LoginWithOtpModel> call = RetrofitClient.getInstance().getApi().customerSendOtp(phones);
                        call.enqueue(new Callback<LoginWithOtpModel>() {
                            @Override
                            public void onResponse(Call<LoginWithOtpModel> call, Response<LoginWithOtpModel> response) {
                                if (response.isSuccessful()) {
                                    sendOtp.setEnabled(false);
                                    sendOtp.setBackgroundResource(R.drawable.dullbtn);
                                    update();
                                    enterOtp.setVisibility(View.VISIBLE);
                                    verfiyOtp.setVisibility(View.VISIBLE);
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<LoginWithOtpModel> call, Throwable throwable) {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        String emails = enterotpemail.getEditableText().toString();
                        session = new Session(Login.this);
//                        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                        Call<SellerLoginWithOtpModel> call = RetrofitClient.getInstance().getApi().sellerSendOtp(phones, emails);
                        call.enqueue(new Callback<SellerLoginWithOtpModel>() {
                            @Override
                            public void onResponse(Call<SellerLoginWithOtpModel> call, Response<SellerLoginWithOtpModel> response) {
                                if (response.code() == 200) {
                                    sendOtp.setEnabled(false);
                                    sendOtp.setBackgroundResource(R.drawable.dullbtn);
                                    update();
                                    enterOtp.setVisibility(View.VISIBLE);
                                    verfiyOtp.setVisibility(View.VISIBLE);
                                    enterverifyotpemail.setVisibility(View.VISIBLE);

                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                } else if (response.code() == 422) {
                                    Toast.makeText(getApplicationContext(), "Phone not Found in server", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onFailure(Call<SellerLoginWithOtpModel> call, Throwable throwable) {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                } else {
                    enterphone.requestFocus();
                    enterphone.setError("Please enter Phone Number");

                }
            }
        });
        verfiyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phones = enterphone.getEditableText().toString();
                String emails = enterphone.getEditableText().toString();
                String otps = enterOtp.getEditableText().toString();
                if (!otps.isEmpty()) {
                    if (Check_Main_User_Type.equals("CUSTOMER")) {
                        Call<VerifyOtpModel> call = RetrofitClient.getInstance().getApi().verifyOtp(phones, otps);
                        call.enqueue(new Callback<VerifyOtpModel>() {
                            @Override
                            public void onResponse(Call<VerifyOtpModel> call, Response<VerifyOtpModel> response) {
                                if (response.isSuccessful()) {
                                    session = new Session(Login.this);
                                    session.saveSession(response.body().getToken_type(), response.body().getAccess_token());
                                    session.createLoginSession(String.valueOf(response.body().getUser().getId()), response.body().getAccess_token());
                                    session.createLoginSessionToken(response.body().getAccess_token().toString());
                                    SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("UserType", "CUSTOMER");
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, DashboradActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<VerifyOtpModel> call, Throwable throwable) {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        session = new Session(Login.this);
//                        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                        Call<SellerVerifyOtpModel> call = RetrofitClient.getInstance().getApi().sellerVerifyOtp(phones, emails, otps);
                        call.enqueue(new Callback<SellerVerifyOtpModel>() {
                            @Override
                            public void onResponse(Call<SellerVerifyOtpModel> call, Response<SellerVerifyOtpModel> response) {
                                if (response.isSuccessful()) {
                                    session = new Session(Login.this);
                                    session.saveSession(response.body().getTokenType(), response.body().getAccessToken());
                                    //session.createLoginSession(String.valueOf(response.body().getUser().getId()), response.body().getAccessToken());
                                    session.createLoginSessionToken(response.body().getAccessToken().toString());
                                    SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("UserType", "SELLER");
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, SellerDashBoardActivity.class);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onFailure(Call<SellerVerifyOtpModel> call, Throwable throwable) {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                } else {
                    enterOtp.requestFocus();
                    enterOtp.setError("Please enter OTP");
                }
            }
        });


        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Main_User_Type.equals("CUSTOMER")) {
                    Intent intent = new Intent(Login.this, SignupActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Login.this, SignupSellerActivity.class);
                    startActivity(intent);
                }

            }
        });
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                if (validate()) {
                    remember_me = true;
                    edt_email1 = edt_email.getEditableText().toString().trim();
                    edt_pass1 = edt_pass.getEditableText().toString().trim();
//                        Session session = new Session(Login.this);
//                        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                    if (Check_Main_User_Type.equals("CUSTOMER")) {
                        Call<CustomerLoginModel> call = RetrofitClient.getInstance().getApi().customerLogin(edt_email1, edt_pass1);
                        call.enqueue(new Callback<CustomerLoginModel>() {
                            @Override
                            public void onResponse(Call<CustomerLoginModel> call, Response<CustomerLoginModel> response) {
                                if (response.isSuccessful()) {
                                    session = new Session(Login.this);
                                    session.saveSession(response.body().getToken_type(), response.body().getAccess_token());
                                    session.createLoginSession(String.valueOf(response.body().getUser().getId()), response.body().getAccess_token());
                                    session.createLoginSessionToken(response.body().getAccess_token().toString());
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("UserType", "CUSTOMER");
                                    editor.commit();
                                    Intent intent = new Intent(Login.this, DashboradActivity.class);
                                    startActivity(intent);
                                    progressDialog.dismiss();

//                                }else
//                                {
//                                    Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
                                } else if (response.code() == 401) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Account Verification not done", Toast.LENGTH_SHORT).show();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<CustomerLoginModel> call, Throwable throwable) {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        session = new Session(Login.this);
//                        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                        Call<LoginSellerModel> call = RetrofitClient.getInstance().getApi().sellerLogin(edt_email1, edt_pass1);

                        call.enqueue(new Callback<LoginSellerModel>() {

                            public void onResponse(Call<LoginSellerModel> call, Response<LoginSellerModel> response) {
                                progressDialog.show();
                                if (response.isSuccessful()) {


                                    if (response.body().getSuccess()) {


                                        if (response.body().getSeller().getStatus().equalsIgnoreCase("active")) {


                                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            Log.e("Access_token", response.body().getAccess_token());
                                            session.saveSession(response.body().getToken_type(), response.body().getAccess_token());
                                            session.createLoginSession(String.valueOf(response.body().getSeller().getId()), response.body().getAccess_token());
                                            session.createLoginSessionToken(response.body().getAccess_token().toString());
                                            SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = prefs.edit();
                                            editor.putString("UserType", "SELLER");
                                            editor.commit();
                                            Intent intent = new Intent(Login.this, SellerDashBoardActivity.class);
                                            startActivity(intent);
                                            progressDialog.dismiss();
                                        } else {
                                            Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();

                                        }


                                    }


                                } else if (response.code() == 401) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Account Verification not done", Toast.LENGTH_SHORT).show();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override

                            public void onFailure(Call<LoginSellerModel> call, Throwable throwable) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


    }

    private void update() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendOtp.setEnabled(true);
                sendOtp.setBackgroundResource(R.drawable.round_bag);
                //the current activity will get finished.
            }
        }, 120000);
    }


    private boolean validate() {
        int flg = 0;
        if (Validation.isEmpty(edt_email)) {
            flg = 1;
            edt_email.requestFocus();
            edt_email.setError("Please enter email");
        } else if (Validation.isEmailId(edt_email)) {
            flg = 1;
            edt_email.requestFocus();
            edt_email.setError("Please enter valid email");
        } else if (Validation.isEmpty(edt_pass)) {
            flg = 1;
            edt_pass.requestFocus();
            edt_pass.setError("Please enter password");
        }

        if (flg > 0) {
            return false;
        } else {
            return true;
        }
    }
}