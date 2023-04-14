package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webdigital.subbisky.R;

public class AboutUsActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    TextView aboutUsDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        aboutUsDetails=findViewById(R.id.aboutUsDetails);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}