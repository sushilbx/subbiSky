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
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.ChanagePassword;
import com.webdigital.subbisky.model.PasswordReset;
import com.webdigital.subbisky.utils.Validation;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.model.PasswordReset;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText email,pass,confirmPass;
    Button btn_update;
    Session session;
    LinearLayout linerLayoutBack;
    Intent intent;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        confirmPass=findViewById(R.id.confirmPass);

        btn_update=findViewById(R.id.update);

        intent = getIntent();
        token = intent.getStringExtra("token");


        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
                if(validate())
                {
                    String CPass,nPass,Email;
                    nPass = pass.getEditableText().toString();
                    CPass = confirmPass.getEditableText().toString();
                    Email = email.getEditableText().toString();
                    session = new Session(ResetPasswordActivity.this);
                    String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                    Call<PasswordReset> call = RetrofitClient.getInstance().getApi().passwordReset(Email,nPass,CPass,token);
                    call.enqueue(new Callback<PasswordReset>() {
                        @Override
                        public void onResponse(Call<PasswordReset> call, Response<PasswordReset> response) {
                            if (response.isSuccessful()) {
//                                Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResetPasswordActivity.this, LoginWithActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<PasswordReset> call, Throwable throwable) {
                            Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
//                    Intent intent = new Intent(ChangePasswordActivity.this, HomeFragment.class);
//                startActivity(intent);
                }
            }
        });
    }
    private boolean validate()
    {
        int flg = 0;
        if(Validation.isEmpty(email))
        {
            flg=1;
            email.requestFocus();
            email.setError("Please enter email");
        }
        else if(Validation.isEmpty(pass))
        {
            flg = 1;
            pass.requestFocus();
            pass.setError("Please enter password");
        }
        else if(Validation.isEmpty(confirmPass))
        {
            flg = 1;
            confirmPass.requestFocus();
            confirmPass.setError("Please enter confirm password");
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