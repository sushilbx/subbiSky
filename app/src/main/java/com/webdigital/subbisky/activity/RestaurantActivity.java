package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.RestaurantAdapter;
import com.webdigital.subbisky.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

        LinearLayout linerLayoutBack;
    //a list to store all the products
    List<RestaurantModel> RestaurantList;
    //the recyclerview
    RecyclerView recyclerViewRestaurant;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        myDialog = new Dialog(this);

        //getting the recyclerview from xml
        recyclerViewRestaurant = (RecyclerView) findViewById(R.id.recyclerViewRestaurant);
        recyclerViewRestaurant.setHasFixedSize(true);
        recyclerViewRestaurant.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        RestaurantList = new ArrayList<>();

        RestaurantList.add(
                new RestaurantModel(
                        1,
                        R.drawable.food,
                        "Coorag Restaurant",
                        "Malikeri Kodagu",
                        "8456321078",
                        "harshitharaj123@gmail.com",
                        4.2));

        //creating recyclerview adapter
        RestaurantAdapter adapter = new RestaurantAdapter(this, RestaurantList);

        //setting adapter to recyclerview
        recyclerViewRestaurant.setAdapter(adapter);
    }

    public void ShowPopup(View v) {
        TextView txtHeader;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtHeader =(TextView) myDialog.findViewById(R.id.txtHeader);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}