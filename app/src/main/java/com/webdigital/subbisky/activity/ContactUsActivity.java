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
import com.webdigital.subbisky.model.ContactUsModel;
import com.webdigital.subbisky.utils.Validation;

public class ContactUsActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    EditText edtname, edtemail, edtphone, edtsub, edtmesg;
    Button btnsubmit;
    String name, email, phone, subject, message;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        edtname = findViewById(R.id.editname);
        edtemail = findViewById(R.id.editemail);
        edtphone = findViewById(R.id.editphn);
        edtsub = findViewById(R.id.editsub);
        edtmesg = findViewById(R.id.editmsg);

        btnsubmit = findViewById(R.id.Submit);


        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validate()) {
                    name = edtname.getEditableText().toString();
                    email = edtemail.getEditableText().toString();
                    phone = edtphone.getEditableText().toString();
                    subject = edtsub.getEditableText().toString();
                    message = edtmesg.getEditableText().toString();
                    session = new Session(ContactUsActivity.this);
                    Call<ContactUsModel> call = RetrofitClient.getInstance().getApi().contactUs(name, message, subject, email, phone);
                    call.enqueue(new Callback<ContactUsModel>() {
                        @Override
                        public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ContactUsActivity.this, DashboradActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ContactUsModel> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }

    private boolean validate() {
        int flg = 0;
        if (Validation.isEmpty(edtname)) {
            flg = 1;
            edtname.requestFocus();
            edtname.setError("Please enter name");
        } else if (Validation.isEmpty(edtemail)) {
            flg = 1;
            edtemail.requestFocus();
            edtemail.setError("Please enter email");
        } else if (Validation.isEmailId(edtemail)) {
            flg = 1;
            edtemail.requestFocus();
            edtemail.setError("Please enter valid email");
        } else if (Validation.isEmpty(edtphone)) {
            flg = 1;
            edtphone.requestFocus();
            edtphone.setError("Please enter mobile number");
        } else if (Validation.isEmpty(edtsub)) {
            flg = 1;
            edtsub.requestFocus();
            edtsub.setError("Please enter subject");
        } else if (Validation.isEmpty(edtmesg)) {
            flg = 1;
            edtmesg.requestFocus();
            edtmesg.setError("Please enter message");
        }
        if (flg > 0) {
            return false;
        } else {
            return true;
        }
    }
}