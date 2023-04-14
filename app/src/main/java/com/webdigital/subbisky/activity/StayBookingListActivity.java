package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;

public class StayBookingListActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    Button btnHomeStay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_booking_list);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        btnHomeStay=findViewById(R.id.btnRestaurant);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });

        btnHomeStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StayBookingListActivity.this,StayBookingActivity.class);
                startActivity(intent);
            }
        });

    }
}