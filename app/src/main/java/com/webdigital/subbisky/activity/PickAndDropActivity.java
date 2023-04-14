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
import com.webdigital.subbisky.adapter.PickAndDropAdapter;
import com.webdigital.subbisky.model.PickAndDropModel;

import java.util.ArrayList;
import java.util.List;

public class PickAndDropActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    //a list to store all the products
    List<PickAndDropModel> pickList;
    //the recyclerview
    RecyclerView recyclerViewPick;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_drop);

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
        recyclerViewPick = (RecyclerView) findViewById(R.id.recyclerViewPick);
        recyclerViewPick.setHasFixedSize(true);
        recyclerViewPick.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        pickList = new ArrayList<>();

        pickList.add(
                new PickAndDropModel(
                        1,
                        R.drawable.pickdrop,
                        "Coorag Pick & Drop Service",
                        "Malikeri Kodagu",
                        "8456321078",
                        "harshitharaj123@gmail.com",
                        4.2));

        //creating recyclerview adapter
        PickAndDropAdapter adapter = new PickAndDropAdapter(this, pickList);

        //setting adapter to recyclerview
        recyclerViewPick.setAdapter(adapter);
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