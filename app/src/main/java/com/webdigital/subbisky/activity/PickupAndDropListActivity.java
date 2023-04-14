package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;

public class PickupAndDropListActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Button btnGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_and_drop_list);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        btnGoods=findViewById(R.id.btnGoods);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        btnGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PickupAndDropListActivity.this,PickAndDropActivity.class);
                startActivity(intent);
            }
        });
    }
}