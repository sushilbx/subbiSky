package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;

public class ServiceListActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Button btnManPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        btnManPower=findViewById(R.id.btnManPower);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
               // finish();
            }
        });

        btnManPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceListActivity.this,ServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}