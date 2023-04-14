package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.webdigital.subbisky.adapter.RestaurantAdapter;
import com.webdigital.subbisky.adapter.StayBookingAdapter;
import com.webdigital.subbisky.adapter.StayBookingHotelListAdapter;
import com.webdigital.subbisky.model.AddReviewModel;
import com.webdigital.subbisky.model.DeliveryStatusUpdateModel;
import com.webdigital.subbisky.model.RestaurantModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.StayBookingModel;
import com.webdigital.subbisky.model.StayBookingSearchModel;
import com.webdigital.subbisky.model.StoreRoomModel;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.adapter.StayBookingHotelListAdapter;
import com.webdigital.subbisky.model.AddReviewModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.StayBookingSearchModel;
import com.webdigital.subbisky.model.StoreRoomModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StayBookingActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack,addRoomLayout,hotelDetailsLayout;
    TextView fromDate,toDate;
    EditText adults,children;
    Button addRoomFirstTime,addRoomNextTime,searchRoom;
    DatePickerDialog datePickerDialog;
    Intent intent;
    String from,to,adultNo,childrenNo,cat_type;
    int seller_id;
    String orderId="";
//    StayBookingSearchModel stayBookingHotels = new StayBookingSearchModel();
    List<StayBookingSearchModel.StayBookingHotels> stayBookingHotels ;
//    = new ArrayList<StayBookingSearchModel.StayBookingHotels>();
    List<StayBookingSearchModel.StayBookingHotels> object = new ArrayList<>();
//    List<StayBookingSearchModel> hotels = stayBookingHotels.getHotels();
JSONArray jsonObject;

/////////////////////////////////////////////hotelDetails/////////////////////////////////
//LinearLayout linerLayoutBack;
    ImageView backImage;
    TextView name,address,review,quotes;
    String image;
    RecyclerView hotelsRecycler;
    StayBookingHotelListAdapter stayBookingHotelListAdapter;
    Session session;
//    Intent intent;
//    String from,to,orderId;
    String  sellerId;
    Dialog myDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_booking);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        addRoomLayout=findViewById(R.id.addRoomLayout);
        hotelDetailsLayout=findViewById(R.id.hotelDetailsLayout);
        fromDate=findViewById(R.id.fromDate);
        toDate=findViewById(R.id.toDate);
        adults=findViewById(R.id.adults);
        children=findViewById(R.id.children);
        addRoomFirstTime=findViewById(R.id.addRoomFirstTime);
        addRoomNextTime=findViewById(R.id.addRoomNextTime);
        searchRoom=findViewById(R.id.searchRoom);
        addRoomNextTime.setVisibility(View.GONE);
        searchRoom.setVisibility(View.GONE);

        ////////////////////////////////////////////////////////
        backImage = findViewById(R.id.backImage);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        review = findViewById(R.id.textViewReview);
        quotes = findViewById(R.id.textViewquota);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        hotelsRecycler = findViewById(R.id.hotelsRecycler);
        hotelsRecycler.setHasFixedSize(true);

        hotelsRecycler.setLayoutManager(new LinearLayoutManager(StayBookingActivity.this,LinearLayoutManager.VERTICAL, false));


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
        hotelDetailsLayout.setVisibility(View.GONE);
        /////////////////////////////////////////////////////////////////////////
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(StayBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
//                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
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
                datePickerDialog = new DatePickerDialog(StayBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        toDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        toDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
//                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
        intent = getIntent();
        seller_id = intent.getIntExtra("seller_id",0);
        cat_type = intent.getStringExtra("cat_type");

//        Log.e("stayBookingHotels1",stayBookingHotels.toString());
        addRoomFirstTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adultNo = adults.getEditableText().toString();
                childrenNo = children.getEditableText().toString();
                if (!adultNo.isEmpty() && !childrenNo.isEmpty()){
                Session session = new Session(StayBookingActivity.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                Log.e("authmyshop",auth);
                    Call<StoreRoomModel> call = RetrofitClient.getInstance().getApi().storeRoom(auth, String.valueOf(seller_id),adultNo,childrenNo);
                    call.enqueue(new Callback<StoreRoomModel>() {
                        @Override
                        public void onResponse(Call<StoreRoomModel> call, Response<StoreRoomModel> response) {
                            if (response.isSuccessful()){
//                                Intent intent = new Intent(StayBookingActivity.this,StayBookingDetailsActivity.class);
//                                startActivity(intent);
                                orderId= String.valueOf(response.body().getOrder_id());
                                addRoomNextTime.setVisibility(View.VISIBLE);
                                addRoomFirstTime.setVisibility(View.GONE);
                                searchRoom.setVisibility(View.VISIBLE);
                                Toast.makeText(StayBookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                            }else {
                                Toast.makeText(StayBookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<StoreRoomModel> call, Throwable t) {
                            Toast.makeText(StayBookingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }else if (adultNo.isEmpty()){
                    adults.setError("Please fill this field!!");
                }else if (childrenNo.isEmpty()){
                    children.setError("Please fill this field!!");
                }
            }
        });
        addRoomNextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adultNo = adults.getEditableText().toString();
                childrenNo = children.getEditableText().toString();
                Log.e("orderId", orderId);
                if (!adultNo.isEmpty() && !childrenNo.isEmpty()){
                    Session session = new Session(StayBookingActivity.this);
                    String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                    Log.e("authmyshop",auth);
                    Call<StoreRoomModel> call = RetrofitClient.getInstance().getApi().storeRoomNextTime(auth, String.valueOf(seller_id),adultNo,childrenNo,orderId);
                    call.enqueue(new Callback<StoreRoomModel>() {
                        @Override
                        public void onResponse(Call<StoreRoomModel> call, Response<StoreRoomModel> response) {
                            if (response.isSuccessful()){
//                                Intent intent = new Intent(StayBookingActivity.this,StayBookingDetailsActivity.class);
//                                startActivity(intent);
                                searchRoom.setVisibility(View.VISIBLE);
//                                Intent intent = new Intent(StayBookingActivity.this,StayBookingPayActivity.class);
//                                intent.putExtra("sellerId", String.valueOf(seller_id));
//                                startActivity(intent);
                                Toast.makeText(StayBookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                            }else {
                                Toast.makeText(StayBookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<StoreRoomModel> call, Throwable t) {
                            Toast.makeText(StayBookingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }else if (adultNo.isEmpty()){
                    adults.setError("Please fill this field!!");
                }else if (childrenNo.isEmpty()){
                    children.setError("Please fill this field!!");
                }
            }
        });

        searchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adultNo = adults.getEditableText().toString();
                childrenNo = children.getEditableText().toString();
                from = fromDate.getText().toString();
                to = toDate.getText().toString();
                Log.e("orderId", orderId);
                if (!adultNo.isEmpty() && !childrenNo.isEmpty() && !orderId.isEmpty()) {
                    Session session = new Session(StayBookingActivity.this);
                    String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                    Log.e("authmyshop", auth);
                Call<StayBookingSearchModel> call = RetrofitClient.getInstance().getApi().stayBookingSearch(auth,from,to,orderId, String.valueOf(seller_id));
                call.enqueue(new Callback<StayBookingSearchModel>() {
                    @Override
                    public void onResponse(Call<StayBookingSearchModel> call, Response<StayBookingSearchModel> response) {
                        if (response.isSuccessful()){
                            Log.e("complete response",response.body().toString());
                            addRoomLayout.setVisibility(View.GONE);
                            hotelDetailsLayout.setVisibility(View.VISIBLE);
                            name.setText(response.body().getShop().getShop_name());
                            address.setText(response.body().getShop().getShop_address());
                            Glide.with(backImage)
                                    .load(response.body().getShop().getShop_image()).fitCenter().into(backImage);
                            String fromDate,toDate,adultsextra,childrenextra,room,price,totalAmount,hotelId,adults,children;

                            fromDate = response.body().getBookingDetails().getFrom();
                            toDate = response.body().getBookingDetails().getTo();
                            adults = response.body().getBookingDetails().getAdult();
                            children = response.body().getBookingDetails().getTotalChildren();
                            adultsextra = response.body().getBookingDetails().getAdultExtra();
                            childrenextra = response.body().getBookingDetails().getChildrenExtra();
                            room = response.body().getBookingDetails().getTotalRoom();
                            SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("fromDate", fromDate);
                            editor.putString("toDate", toDate);
                            editor.putString("adults", adults);
                            editor.putString("children", children);
                            editor.putString("adultsextra", adultsextra);
                            editor.putString("childrenextra", childrenextra);
                            editor.putString("room", room);
                            editor.commit();
                          // stayBookingHotelListAdapter = new StayBookingHotelListAdapter(response.body().getHotels(),StayBookingActivity.this);
                          // hotelsRecycler.setAdapter(stayBookingHotelListAdapter);

                            Toast.makeText(StayBookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                        }else {
                            Toast.makeText(StayBookingActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<StayBookingSearchModel> call, Throwable t) {
                        Toast.makeText(StayBookingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                }else if (adultNo.isEmpty()){
                    adults.setError("Please fill this field!!");
                }else if (childrenNo.isEmpty()){
                    children.setError("Please fill this field!!");
                }
            }
        });
    }


    public void ShowPopup() {
        EditText ed_name,ed_mobile,ed_email,ed_msg;
        Button btnsubmitNow;
        myDialog = new Dialog(StayBookingActivity.this);
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
                    Toast.makeText(StayBookingActivity.this, "please complete all fields to complete", Toast.LENGTH_SHORT).show();
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
        myDialog = new Dialog(StayBookingActivity.this);
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
                session = new Session(StayBookingActivity.this);
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


//                            String responseFull = response.body().getHotels().toString();
//                            try {
//                                 jsonObject = new JSONArray(responseFull);
//                                Log.e("jsonData", jsonObject.toString());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            Intent intent = new Intent(StayBookingActivity.this,StayBookingDetailsActivity.class);
//                            intent.putExtra("from",from);
//                            intent.putExtra("to",to);
//                            intent.putExtra("orderId",orderId);
//                            intent.putExtra("name",name);
//                            intent.putExtra("address",address);
//                            intent.putExtra("image",image);
//                            intent.putExtra("seller_id",String.valueOf(seller_id));
//                            intent.putExtra("jsonArray", jsonObject.toString());
//                            Log.e("jsonData", jsonObject.toString());
////                            intent.putExtra("completeResponse",  response.body().toString());
//                            Bundle bundle = new Bundle();
////                            intent.putExtra("jsonData", (Serializable) object);
////                            bundle.putParcelableArrayList("jsonData", (ArrayList<? extends Parcelable>) stayBookingHotels);
////                            Log.e("jsonData", String.valueOf(stayBookingHotels));
//                            intent.putExtra("BUNDLE",bundle);
//                            startActivity(intent);