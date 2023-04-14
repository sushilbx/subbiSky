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
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.ChanagePassword;
import com.webdigital.subbisky.utils.Validation;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText currentPass,newPass,confirmNewPass;
    Button btn_update;
    TextView cart_badge;
    Session session;
    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        cart_badge=findViewById(R.id.cart_badge);

        currentPass=findViewById(R.id.currentPass);
        newPass=findViewById(R.id.newPass);
        confirmNewPass=findViewById(R.id.confirmNewPass);

        btn_update=findViewById(R.id.update);


        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        getCartCount();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
               if(validate())
                {
                    String cPass,nPass,conNewPass;
                    cPass = currentPass.getEditableText().toString();
                    nPass = newPass.getEditableText().toString();
                    conNewPass = confirmNewPass.getEditableText().toString();
                    session = new Session(ChangePasswordActivity.this);
                    String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                    Call<ChanagePassword> call = RetrofitClient.getInstance().getApi().changePassword(auth,cPass,nPass,conNewPass);
                    call.enqueue(new Callback<ChanagePassword>() {
                        @Override
                        public void onResponse(Call<ChanagePassword> call, Response<ChanagePassword> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChangePasswordActivity.this, DashboradActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ChanagePassword> call, Throwable throwable) {
                            Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
//                    Intent intent = new Intent(ChangePasswordActivity.this, HomeFragment.class);
//                startActivity(intent);
                }
            }
        });
    }

    private void getCartCount() {
        Session session = new Session(ChangePasswordActivity.this);
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

    private boolean validate()
    {
        int flg = 0;
        if(Validation.isEmpty(currentPass))
        {
            flg=1;
            currentPass.requestFocus();
            currentPass.setError("Please enter current password");
        }
        else if(Validation.isEmpty(newPass))
        {
            flg = 1;
            newPass.requestFocus();
            newPass.setError("Please enter new password");
        }
        else if(Validation.isEmpty(confirmNewPass))
        {
            flg = 1;
            confirmNewPass.requestFocus();
            confirmNewPass.setError("Please enter confirm  new password");
        }

        if(flg>0)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
}