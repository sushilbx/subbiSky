package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.AddToCartAdapter;
import com.webdigital.subbisky.model.AddToCartModel;

import java.util.ArrayList;
import java.util.List;

public class AddToCartRestaurantActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    //a list to store all the products
    List<AddToCartModel> addToCartList;

    //the recyclerview
    RecyclerView recyclerViewAddCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_restaurant);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                //finish();
            }
        });

        //getting the recyclerview from xml
        recyclerViewAddCart = (RecyclerView) findViewById(R.id.recyclerViewAddCart);
        recyclerViewAddCart.setHasFixedSize(true);
        recyclerViewAddCart.setLayoutManager(new GridLayoutManager(this,2));

        //initializing the productlist
        addToCartList = new ArrayList<>();


        //adding some items to our list
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));

        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));
        addToCartList.add(
                new AddToCartModel(1, "Mutton Birayani", "\u20B9 150", "\u20B9 220", R.drawable.food));

        //creating recyclerview adapter
        AddToCartAdapter adapter = new AddToCartAdapter(this, addToCartList);

        //setting adapter to recyclerview
        recyclerViewAddCart.setAdapter(adapter);
    }
}