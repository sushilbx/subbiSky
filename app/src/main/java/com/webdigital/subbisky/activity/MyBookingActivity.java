package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.BookingAdapter;
import com.webdigital.subbisky.model.BookingModel;

import java.util.ArrayList;
import java.util.List;

public class MyBookingActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    List<BookingModel> BookingList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);


        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        BookingList = new ArrayList<>();


        //adding some items to our list
        BookingList.add(
                new BookingModel(
                        1,
                        2021-10-20,
                        "Booked",
                        6000,
                        "view"));


        //creating recyclerview adapter
        BookingAdapter adapter = new BookingAdapter(this, BookingList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    public class DBWork{
        public String getText(){
            //do stuff
            return "";
        }
    }
}