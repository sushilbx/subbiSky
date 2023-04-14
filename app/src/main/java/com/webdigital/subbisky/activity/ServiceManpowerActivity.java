package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;

public class ServiceManpowerActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_manpower);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        btnPay=findViewById(R.id.btnPay);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceManpowerActivity.this,ServicePayActivity.class);
                startActivity(intent);
            }
        });
    }
}