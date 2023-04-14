package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.PreferenceManager;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.AddroomAdapter;
import com.webdigital.subbisky.adapter.ServiceAdapter;
import com.webdigital.subbisky.adapter.SubServiceListAdapter;

import com.webdigital.subbisky.model.Addroompojo;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.StayBookinghModel;
import com.webdigital.subbisky.model.SubServiceListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaybookroomaddingActivity extends AppCompatActivity {
    Intent intent;
    TextView toDate,fromDate,addroom;
    Session session;
    DatePickerDialog datePickerDialog;
    Button searchRoom;
    Dialog myDialog;
    Spinner spinnerhotels;
    public static RecyclerView addroomrecyler;
    String subService;
    public static String sfromdate="",stodate="";
    public static String sroonjson="";
    public static int sroonsize=0;
    LinearLayout linerLayoutBack;
    public static String sadultjson="",schildrenjson="";
    ArrayList<String> sublist = new ArrayList<>();
    ArrayList<String> subListSlug = new ArrayList<>();
    ArrayList adultvalue=new ArrayList();
    ArrayList childrenvalue=new ArrayList();

    public static List<Addroompojo> items = new ArrayList<Addroompojo>();

    String serviceId,serviceName,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staybookroomadding);

        intent=getIntent();
        serviceId=intent.getStringExtra("serviceId");
        serviceName=intent.getStringExtra("serviceName");
        type=intent.getStringExtra("type");

        fromDate=findViewById(R.id.fromDate);
        toDate=findViewById(R.id.toDate);
        addroom=findViewById(R.id.addroom);
        searchRoom=findViewById(R.id.searchRoom);
        spinnerhotels=findViewById(R.id.spinnerhotels);
        addroomrecyler=findViewById(R.id.addroomrecyler);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSubServiceList();

        items.add(new Addroompojo("0","0","1","uncheck"));
        AddroomAdapter adapter = new AddroomAdapter(StaybookroomaddingActivity.this, items);

        //setting adapter to recyclerview
        addroomrecyler.setHasFixedSize(true);
        addroomrecyler.setLayoutManager(new LinearLayoutManager(StaybookroomaddingActivity.this,LinearLayoutManager.VERTICAL, false));

        addroomrecyler.setAdapter(adapter);
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(StaybookroomaddingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromDate.setText((month + 1)+ "/" + dayOfMonth + "/" +year);
                        sfromdate = String.valueOf((month + 1)+ "/" + dayOfMonth + "/" +year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(StaybookroomaddingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        toDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        toDate.setText((month + 1)+ "/" + dayOfMonth + "/" +year);
                        stodate=String.valueOf((month + 1)+ "/" + dayOfMonth + "/" +year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

       // if(items==null){
          //  searchRoom.setVisibility(View.GONE);

        //}
        searchRoom.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                                    if (sfromdate.isEmpty()) {
                        Toast.makeText(StaybookroomaddingActivity.this, "Please select  date", Toast.LENGTH_SHORT).show();

                    } else if (stodate.isEmpty()) {
                        Toast.makeText(StaybookroomaddingActivity.this, "Please select  date", Toast.LENGTH_SHORT).show();

                    }
                                    else
                                    {




                for (int j = 0; j < items.size(); j++) {
                    if(items.get(j).getcheck().equalsIgnoreCase("uncheck")&&items.get(j).getsno().equalsIgnoreCase("2")){
                        items.get(j).setcheck("checked");
                    }
                    if (items.get(j).getcheck().equalsIgnoreCase("uncheck")) {

                        for (int l = 0; l < items.size(); l++) {
                            items.get(l).setcheck("uncheck");
                        }
                        Toast.makeText(StaybookroomaddingActivity.this, "Please fill the adult and children values", Toast.LENGTH_SHORT).show();
                    }

                }
if(items.get(0).getcheck().equalsIgnoreCase("checked")){
    JSONArray roomJson = new JSONArray();
    JSONArray adultJson = new JSONArray();
    JSONArray childJson = new JSONArray();
    sroonjson="";
    schildrenjson="";
    sadultjson="";
    sroonsize=0;
    for (int i = 0; i < items.size(); i++) {
        roomJson.put(1);
        sroonjson = sroonjson + 1 + ",";
        sroonsize=sroonsize+1;
        adultJson.put(items.get(i).getadult());
        sadultjson = sadultjson + items.get(i).getadult() + ",";
        childJson.put(items.get(i).getchildren());
        schildrenjson = schildrenjson + items.get(i).getchildren() + ",";
    }
    StringBuilder srom = new StringBuilder(sroonjson);

    sroonjson = String.valueOf(srom.deleteCharAt(srom.length() - 1));


    StringBuilder sadult = new StringBuilder(sadultjson);

    sadultjson = String.valueOf(sadult.deleteCharAt(sadult.length() - 1));


    StringBuilder schild = new StringBuilder(schildrenjson);

    schildrenjson = String.valueOf(schild.deleteCharAt(schild.length() - 1));


    session = new Session(StaybookroomaddingActivity.this);
    session.setRoomInfo(fromDate.getText().toString(), toDate.getText().toString(), roomJson.toString(), adultJson.toString(), childJson.toString());
    Toast.makeText(StaybookroomaddingActivity.this, roomJson.toString(), Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(StaybookroomaddingActivity.this, SubServiceBySlugActivity.class);
    intent.putExtra("slugName", subService);
    intent.putExtra("type", type);
    startActivity(intent);
}
else
{
    Toast.makeText(StaybookroomaddingActivity.this, "Please fill the adult and children values", Toast.LENGTH_SHORT).show();
}


}
//                for (int j = 0; j < items.size(); j++) {
//
//                    if (items.get(j).getcheck().equalsIgnoreCase("checked")) {
//
//                        for (int m = 0; m < items.size(); m++) {
//                            if (items.get(m).getsno().equalsIgnoreCase("1")) {
//                                Toast.makeText(StaybookroomaddingActivity.this, "Please fill the adult and children values", Toast.LENGTH_SHORT).show();
//                            } else if (items.get(m).getcheck().equalsIgnoreCase("checked")) {
//
//                        }
//                    }
//
//
//                    if (sfromdate.length() <= 0) {
//                        Toast.makeText(StaybookroomaddingActivity.this, "Please select from date", Toast.LENGTH_SHORT).show();
//
//                    } else if (stodate.length() <= 0) {
//                        Toast.makeText(StaybookroomaddingActivity.this, "Please select to date", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
////                Call<StayBookinghModel> call = RetrofitClient.getInstance().getApi().stayBookingSearchroom(adultJson.toString(),
////                        fromDate.getText().toString().trim(),toDate.getText().toString().trim(),roomJson.toString(),childJson.toString(),serviceId);
////                call.enqueue(new Callback<StayBookinghModel>() {
////                    @Override
////                    public void onResponse(Call<StayBookinghModel> call, Response<StayBookinghModel> response) {
////                        if(response.body().getStatusCode()==200){
////                            Log.e("ResponseMsg: ",response.body().getMessage());
////                            roomJson = new JSONArray(new ArrayList<String>());
////                            adultJson = new JSONArray(new ArrayList<String>());
////                            childJson = new JSONArray(new ArrayList<String>());
////
////                            Toast.makeText(StaybookroomaddingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
////
////                        }else {
////                            Log.e("ResError", response.body().getMessage());
////                            roomJson = new JSONArray(new ArrayList<String>());
////                            adultJson = new JSONArray(new ArrayList<String>());
////                            childJson = new JSONArray(new ArrayList<String>());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<StayBookinghModel> call, Throwable t) {
////                        Log.e("Error", t.getMessage());
////                        roomJson = new JSONArray(new ArrayList<String>());
////                        adultJson = new JSONArray(new ArrayList<String>());
////                        childJson = new JSONArray(new ArrayList<String>());
////                    }
////                });
//                }
//            }

            }

        });


        addroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                items.add(new Addroompojo("0","0","1","uncheck"));
                //  adapter.notifyDataSetChanged();
                AddroomAdapter adapter = new AddroomAdapter(StaybookroomaddingActivity.this, items);

                //setting adapter to recyclerview
                addroomrecyler.setHasFixedSize(true);
                addroomrecyler.setLayoutManager(new LinearLayoutManager(StaybookroomaddingActivity.this,LinearLayoutManager.VERTICAL, false));

                addroomrecyler.setAdapter(adapter);
//                //ShowPopup();
            }



            public void ShowPopup() {
                EditText adults,children;
                Button dilogaddroom;
                myDialog = new Dialog(StaybookroomaddingActivity.this);
                myDialog.setContentView(R.layout.addroomdialog);
                adults =(EditText) myDialog.findViewById(R.id.adults);
                children =(EditText) myDialog.findViewById(R.id.children);

                dilogaddroom =(Button) myDialog.findViewById(R.id.dilogaddroom);

//            intent = getIntent();
//            seller_id = intent.getIntExtra("seller_id",0);
//            Log.e("Quote_seller_id", String.valueOf(seller_id));
                dilogaddroom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String sadults,schildren;
                        sadults=adults.getEditableText().toString().trim();
                        schildren=children.getEditableText().toString().trim();
if(sadults.equalsIgnoreCase("")||sadults==null){
    //adults.setError("please enter value");
    Toast.makeText(StaybookroomaddingActivity.this, "please enter value", Toast.LENGTH_SHORT).show();
}
else if(Integer.parseInt(sadults)>=0&&Integer.parseInt(sadults)<=6){
    adultvalue.add( sadults);

                        }
else {
    //adults.setError("please enter below 6");
    Toast.makeText(StaybookroomaddingActivity.this, "please enter below 6", Toast.LENGTH_SHORT).show();
}
                        if(schildren.equalsIgnoreCase("")||schildren==null){
                            //children.setError("please enter value");
                            Toast.makeText(StaybookroomaddingActivity.this, "please enter below 6", Toast.LENGTH_SHORT).show();

                        }
                        else if(Integer.parseInt(schildren)>=0&&Integer.parseInt(schildren)<=6){
                            childrenvalue.add( schildren);
                        }
else {
                            //children.setError("");
                            Toast.makeText(StaybookroomaddingActivity.this, "please enter below 6", Toast.LENGTH_SHORT).show();
                        }

if((sadults.length()>0&&(Integer.parseInt(sadults)>=0&&Integer.parseInt(sadults)<=6))&&(schildren.length()>0)&&(Integer.parseInt(schildren)>=0&&Integer.parseInt(schildren)<=6)){
    items.add(new Addroompojo(sadults,schildren,"1","uncheck"));
    myDialog.dismiss();
}
else {
    //myDialog.dismiss();
    Toast.makeText(StaybookroomaddingActivity.this, "please enter correct value", Toast.LENGTH_SHORT).show();
}



                       // myDialog.dismiss();





                            searchRoom.setVisibility(View.VISIBLE);




//creating recyclerview adapter
                        AddroomAdapter adapter = new AddroomAdapter(StaybookroomaddingActivity.this, items);

                        //setting adapter to recyclerview
                        addroomrecyler.setHasFixedSize(true);
                        addroomrecyler.setLayoutManager(new LinearLayoutManager(StaybookroomaddingActivity.this,LinearLayoutManager.VERTICAL, false));

                        addroomrecyler.setAdapter(adapter);


                    }
                });

                //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }


        });

    }
    private void getSubServiceList() {
        Call<SubServiceListModel> call = RetrofitClient.getInstance().getApi().subServiceList(serviceId);
        call.enqueue(new Callback<SubServiceListModel>() {
            @Override
            public void onResponse(Call<SubServiceListModel> call, Response<SubServiceListModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getSub().size() != 0){
                        sublist.clear();
                        for(int i=0;i<response.body().getSub().size();i++){
                            String  sublistD=  response.body().getSub().get(i).getName().toString();
                            sublist.add(response.body().getSub().get(i).getName());
                            subListSlug.add(response.body().getSub().get(i).getSlug());
                            Spinner spinnercode = findViewById(R.id.spinnerhotels);
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StaybookroomaddingActivity.this,
                                    android.R.layout.simple_spinner_item, sublist);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnercode.setAdapter(arrayAdapter);
                            spinnercode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    subService = subListSlug.get(position);
                                    Toast.makeText(parent.getContext(), "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                                }
                                @Override
                                public void onNothingSelected(AdapterView <?> parent) {
                                }
                            });

                        }

                        Toast.makeText(StaybookroomaddingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(StaybookroomaddingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(StaybookroomaddingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SubServiceListModel> call, Throwable t) {
                Toast.makeText(StaybookroomaddingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        items.clear();
        sfromdate="";
        stodate="";




        super.onBackPressed();

    }
}