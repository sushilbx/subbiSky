package com.webdigital.subbisky.activity;

import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.sadultjson;
import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.schildrenjson;
import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.sfromdate;
import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.sroonjson;
import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.stodate;
import static com.webdigital.subbisky.adapter.SubServiceBySlugAdapter.mainsellerid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.StayBookingHotelListAdapter;
import com.webdigital.subbisky.model.AddReviewModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.StayBookinghModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StayBookingDetailsActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    ImageView backImage;
    TextView name,address,review,quotes;
    String image;
    RecyclerView hotelsRecycler;
    StayBookingHotelListAdapter stayBookingHotelListAdapter;
    Session session;
    Intent intent;
    String from,to,orderId;
    String  sellerId;
    Dialog myDialog;
    JSONObject completeResponse;
    ArrayList<String> Stay;
    JSONArray array;
    String fromDate,toDate,roomJason,adultJason,childJson;
    int i = 0;
    public static String totaladult,totalroom,totalchildren,overalladult,overallchild,extrachildcharge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_booking_details);
        backImage = findViewById(R.id.backImage);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        review = findViewById(R.id.textViewReview);
        quotes = findViewById(R.id.textViewquota);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        hotelsRecycler = findViewById(R.id.hotelsRecycler);
        intent =getIntent();
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                finish();
            }
        });
//        name.setText(intent.getStringExtra("name"));
//        image =intent.getStringExtra("image");
//        address.setText(intent.getStringExtra("address"));
//        from = intent.getStringExtra("from");
//        to = intent.getStringExtra("to");
//        orderId = intent.getStringExtra("orderId");
        sellerId = intent.getStringExtra("seller_id");
        sellerId=mainsellerid;
        Log.e("sellerId",sellerId);

        session = new Session(StayBookingDetailsActivity.this);
        fromDate = session.getFrom();
        toDate = session.getTo();
        roomJason = session.getRoomJson();
        adultJason = session.getAdultJson();
        childJson = session.getChildJson();
//
//        Log.e("fromDate",fromDate);
//        Log.e("toDate",toDate);
//        Log.e("roomJason",roomJason);
//        Log.e("adultJason",adultJason);
//        Log.e("childJson",childJson);

//        Glide.with(backImage)
//                .load(image).fitCenter().into(backImage);

//        Bundle args = intent.getBundleExtra("BUNDLE");
//        stayBookingSearchModels = intent.getStringArrayListExtra("jsonData");
//        String jsonArray = intent.getStringExtra("jsonArray");

//        try {
//             array = new JSONArray(jsonArray);
//            System.out.println(array.toString(2));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        stayBookingSearchModels = intent.getExtras().getParcelableArrayList("jsonData");
//        ArrayList<StayBookingSearchModel.StayBookingHotels> object = (ArrayList<StayBookingSearchModel.StayBookingHotels>) args.getSerializable("jsonData");
//        Log.e("stayBookingSearchModels", String.valueOf(stayBookingSearchModels));
//        backImage.setImageResource(Integer.parseInt(image));
//        completeResponse = intent.getStringExtra("completeResponse");
//        try {
//            completeResponse = new JSONObject(getIntent().getStringExtra("completeResponse"));
//            Log.e("completeResponse", String.valueOf(completeResponse));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        hotelsRecycler.setHasFixedSize(true);

        hotelsRecycler.setLayoutManager(new LinearLayoutManager(StayBookingDetailsActivity.this,LinearLayoutManager.VERTICAL, false));


        quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewPopup();
            }
        });
//        try {
//            String nameq = array.getJSONObject(i).getString("name");
//            Log.e("namepqs",nameq);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
       getHotels();

    }

    private void getHotels() {
        session = new Session(StayBookingDetailsActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        ArrayList<Integer> adult = new ArrayList();
        ArrayList<Integer> child = new ArrayList();
        ArrayList<Integer> room = new ArrayList();
        adult.add(5);
        child.add(5);
        room.add(5);
        Call<StayBookinghModel> call = RetrofitClient.getInstance().getApi().stayBookingSearchroom(sellerId,sroonjson,sadultjson,schildrenjson,sfromdate,stodate);
        call.enqueue(new Callback<StayBookinghModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<StayBookinghModel> call, Response<StayBookinghModel> response) {
                System.out.print(response);
                if (response.isSuccessful()) {

                    System.out.print(response.body());
                 //   totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    //if(response.body().getHotels().size()!= 0){
                        stayBookingHotelListAdapter = new StayBookingHotelListAdapter(response.body().getHotels(),StayBookingDetailsActivity.this);
                        hotelsRecycler.setAdapter(stayBookingHotelListAdapter);
                        name.setText(response.body().getShop().getShopName());
                        address.setText(response.body().getShop().getShopAddress());
                    totaladult=response.body().getAdultExtra().toString();
                    totalchildren=response.body().getChildrenExtra().toString();
                    overalladult=response.body().getTotalAdult().toString();
                    overallchild=response.body().getTotalChildren().toString();
                    totalroom=response.body().getTotalRoom().toString();
                        Glide.with(backImage)
                                .load(response.body().getShop().getShopImage()).fitCenter().placeholder(R.drawable.noimg).into(backImage);


                       // Toast.makeText(StayBookingDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }else {
                     // Toast.makeText(StayBookingDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                 //   }

             //   } else {
                   // String error = response.errorBody().toString();
                    //System.out.print(error);
                    //  Toast.makeText(StayBookingDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<StayBookinghModel> call, Throwable t) {
                Toast.makeText(StayBookingDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void ShowPopup() {
        EditText ed_name,ed_mobile,ed_email,ed_msg;
        Button btnsubmitNow;
        myDialog = new Dialog(StayBookingDetailsActivity.this);
        myDialog.setContentView(R.layout.enqiury_now);
        ed_name =(EditText) myDialog.findViewById(R.id.ed_name1);
        ed_mobile =(EditText) myDialog.findViewById(R.id.ed_mobile1);
        ed_email =(EditText) myDialog.findViewById(R.id.ed_email1);
        ed_msg =(EditText) myDialog.findViewById(R.id.ed_msg1);

        btnsubmitNow =(Button) myDialog.findViewById(R.id.btnsubmitNow1);
//        intent = getIntent();
//        seller_id = intent.getIntExtra("seller_id",0);
//        Log.e("Quote_seller_id", String.valueOf(seller_id));
        btnsubmitNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,mobile,email,msg;
                name=ed_name.getEditableText().toString().trim();
                mobile=ed_mobile.getEditableText().toString().trim();
                email=ed_email.getEditableText().toString().trim();
                msg=ed_msg.getEditableText().toString().trim();
                Log.e("Quote_ed_name1", name);
                Log.e("Quote_ed_mobile1", mobile);
                Log.e("Quote_ed_email1", email);
                Log.e("Quote_ed_msg1", msg);
                Log.e("Quote_seller_id", sellerId);
                if (!name.isEmpty()&& !mobile.isEmpty()&&!email.isEmpty()&&!msg.isEmpty()) {
                    Call<SaveQuoteModel> call = RetrofitClient.getInstance().getApi().saveQuote(name, mobile, email, msg, sellerId);
                    call.enqueue(new Callback<SaveQuoteModel>() {
                        @Override
                        public void onResponse(Call<SaveQuoteModel> call, Response<SaveQuoteModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ServiceDetailsPageActivity.this, ServiceDetailsPageActivity.class);
//                            startActivity(intent);
                                myDialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
                            }
                        }

                        @Override
                        public void onFailure(Call<SaveQuoteModel> call, Throwable throwable) {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }else {
                    Toast.makeText(StayBookingDetailsActivity.this, "please complete all fields to complete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void ReviewPopup() {
        EditText editReview;
        Spinner reviewSpinner;
        RatingBar ratingbar;
        Button btnsubmitNow;
        myDialog = new Dialog(StayBookingDetailsActivity.this);
        myDialog.setContentView(R.layout.review_popup);
        editReview =(EditText) myDialog.findViewById(R.id.enterReview);
//        reviewSpinner =(Spinner) myDialog.findViewById(R.id.reviewSpinner);
        ratingbar =(RatingBar) myDialog.findViewById(R.id.ratingBar);
        btnsubmitNow =(Button) myDialog.findViewById(R.id.btnsubmitNow1);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Reviews,R.layout.support_simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        reviewSpinner.setAdapter(adapter);
//        reviewSpinner.setOnItemSelectedListener(this);
        btnsubmitNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,revi,email,msg;
                name=editReview.getEditableText().toString().trim();
                String rating=String.valueOf(ratingbar.getRating());
                Log.e("Quote_ed_email1", name);
                Log.e("rating", rating);
                session = new Session(StayBookingDetailsActivity.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<AddReviewModel> call = RetrofitClient.getInstance().getApi().customerAddReview(auth,name,rating);
                call.enqueue(new Callback<AddReviewModel>() {
                    @Override
                    public void onResponse(Call<AddReviewModel> call, Response<AddReviewModel> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ServiceDetailsPageActivity.this, ServiceDetailsPageActivity.class);
//                            startActivity(intent);
                            myDialog.dismiss();
                        }else
                        {
                            Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
                        }
                    }

                    @Override
                    public void onFailure(Call<AddReviewModel> call, Throwable throwable) {
                        Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
//        intent = getIntent();
//        seller_id = intent.getIntExtra("seller_id",0);
//        Log.e("Quote_seller_id", String.valueOf(seller_id));

    }
}