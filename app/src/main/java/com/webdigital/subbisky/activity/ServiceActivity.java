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
import com.webdigital.subbisky.adapter.ServiceAdapter;
import com.webdigital.subbisky.model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    //a list to store all the products
    List<ServiceModel> serviceList;
    //the recyclerview
    RecyclerView recyclerViewSevice;

    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
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
        recyclerViewSevice = (RecyclerView) findViewById(R.id.recyclerViewService);
        recyclerViewSevice.setHasFixedSize(true);
        recyclerViewSevice.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        serviceList = new ArrayList<>();

        serviceList.add(
                new ServiceModel(
                        1,
                        R.drawable.service,
                        "Coorag Restaurant",
                        "Malikeri Kodagu",
                        "8456321078",
                        "harshitharaj123@gmail.com",
                        4.2));

        //creating recyclerview adapter
        ServiceAdapter adapter = new ServiceAdapter(this, serviceList);

        //setting adapter to recyclerview
        recyclerViewSevice.setAdapter(adapter);
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