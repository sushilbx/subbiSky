package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;

public class FoodListActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    Button btnRestaurant,btnCafe,btnHotels,btnCaterinngServices,btnJuices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        btnRestaurant=findViewById(R.id.btnRestaurant);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FoodListActivity.this,RestaurantActivity.class);
                startActivity(intent);
            }
        });



    }
}