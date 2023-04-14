package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.webdigital.subbisky.adapter.ServiceDetailsPageImageAdapter;
import com.webdigital.subbisky.adapter.ServicewithChargeAdapter;
import com.webdigital.subbisky.model.AddReviewModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.ServiceDetailsModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ServiceDetailsPageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Dialog myDialog;
    Intent intent;
    int seller_id;
    LinearLayout hidecontactDetails;
    ServiceDetailsPageImageAdapter serviceDetailsPageImageAdapter;
    ServicewithChargeAdapter chargeAdapter;
    SliderView  servicePageImageRecycler;
    ImageView bannerImage;
    RecyclerView serviceswithChargesRecycler;
    String type,customerType;
    Session session;

    LinearLayout linerLayoutBack;
    TextView serviceName,serviceAddress,serviceNumber,serviceContact,serviceEmail,serviceReview,serviceQuote,serviceAbout,enquiryService,serviceImageListHeader,serviceChargeTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details_page);
        linerLayoutBack  = findViewById(R.id.linerLayoutBack);
        serviceName  = findViewById(R.id.serviceName);
        bannerImage  = findViewById(R.id.bannerImage);
        serviceAddress  = findViewById(R.id.serviceAddress);
        hidecontactDetails  = findViewById(R.id.hidecontactDetails);
        serviceNumber  = findViewById(R.id.serviceNumber);
        serviceContact  = findViewById(R.id.serviceContact);
        serviceEmail  = findViewById(R.id.serviceEmail);
        serviceReview  = findViewById(R.id.serviceReview);
        serviceQuote  = findViewById(R.id.serviceQuote);
        serviceswithChargesRecycler  = findViewById(R.id.serviceswithChargesRecycler);
        servicePageImageRecycler  = findViewById(R.id.servicePageImageRecycler);
        enquiryService  = findViewById(R.id.enquiryService);
        serviceChargeTitle  = findViewById(R.id.serviceChargeTitle);
        serviceImageListHeader  = findViewById(R.id.serviceImageListHeader);
        serviceAbout  = findViewById(R.id.aboutServiceText);
        intent = getIntent();
        seller_id = intent.getIntExtra("seller_id",0);
        Log.e("seller_id", String.valueOf(seller_id));

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        serviceswithChargesRecycler.setHasFixedSize(true);
        serviceswithChargesRecycler.setLayoutManager(new LinearLayoutManager(ServiceDetailsPageActivity.this,LinearLayoutManager.VERTICAL, true));
        getServiceWithCharges();
        getImages();
        enquiryService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });
        serviceQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });
        serviceReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewPopup();
            }
        });
        Call<ServiceDetailsModel> call = RetrofitClient.getInstance().getApi().serviceDetails(seller_id);
        call.enqueue(new Callback<ServiceDetailsModel>() {
            @Override
            public void onResponse(Call<ServiceDetailsModel> call, Response<ServiceDetailsModel> response) {
                if (response.isSuccessful()){
                    serviceName.setText(response.body().getSeller().getShop_name());
                    Log.e("serviceName",serviceName.getText().toString());
                    serviceAddress.setText(response.body().getSeller().getShop_address());
//                serviceNumber.setText(response.body().getSeller().getnu());
                    serviceAbout.setText(response.body().getSeller().getDescription());
                    serviceImageListHeader.setText(response.body().getSeller().getShop_name()+" Services");
                    serviceChargeTitle.setText(response.body().getSeller().getShop_name()+" Services with Charges");
                    Glide.with(bannerImage)
                            .load(response.body().getSeller().getShop_image()).fitCenter().into(bannerImage);
//                    customerType= response.body().getSeller()
                }
            }

            @Override
            public void onFailure(Call<ServiceDetailsModel> call, Throwable t) {

            }
        });


    }

    private void ReviewPopup() {
        EditText editReview;
        Spinner reviewSpinner;
        RatingBar ratingbar;
        Button btnsubmitNow;
        myDialog = new Dialog(ServiceDetailsPageActivity.this);
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
                session = new Session(ServiceDetailsPageActivity.this);
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
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String type = adapterView.getItemAtPosition(i).toString();
//        leaveType.setText(type);
//    }

    private void getServiceWithCharges() {
        Call<ServiceDetailsModel> call = RetrofitClient.getInstance().getApi().serviceDetails(seller_id);
        call.enqueue(new Callback<ServiceDetailsModel>() {
            @Override
            public void onResponse(Call<ServiceDetailsModel> call, Response<ServiceDetailsModel> response) {
                if (response.isSuccessful()){

                    if(response.body().getServices().size() != 0){
                        chargeAdapter = new ServicewithChargeAdapter((List<ServiceDetailsModel.ServiceSellerServices>) response.body().getServices(),ServiceDetailsPageActivity.this);
                        serviceswithChargesRecycler.setAdapter(chargeAdapter);
                        Toast.makeText(ServiceDetailsPageActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ServiceDetailsPageActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ServiceDetailsModel> call, Throwable t) {

            }
        });
    }

    private void getImages() {
        Call<ServiceDetailsModel> call = RetrofitClient.getInstance().getApi().serviceDetails(seller_id);
        call.enqueue(new Callback<ServiceDetailsModel>() {
            @Override
            public void onResponse(Call<ServiceDetailsModel> call, Response<ServiceDetailsModel> response) {
                if (response.isSuccessful()){

                    serviceDetailsPageImageAdapter = new ServiceDetailsPageImageAdapter((List<ServiceDetailsModel.ServiceSellerServices>) response.body().getServices(), ServiceDetailsPageActivity.this);
                    servicePageImageRecycler.setSliderAdapter(serviceDetailsPageImageAdapter);
                    servicePageImageRecycler.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    servicePageImageRecycler.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    servicePageImageRecycler.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    servicePageImageRecycler.setIndicatorSelectedColor(Color.WHITE);
                    servicePageImageRecycler.setIndicatorUnselectedColor(Color.GRAY);
                    servicePageImageRecycler.setScrollTimeInSec(3);
                    servicePageImageRecycler.setAutoCycle(true);
                    servicePageImageRecycler.startAutoCycle();
                }
            }

            @Override
            public void onFailure(Call<ServiceDetailsModel> call, Throwable t) {

            }
        });
    }

    public void ShowPopup() {
        EditText ed_name,ed_mobile,ed_email,ed_msg;
        Button btnsubmitNow;
        myDialog = new Dialog(ServiceDetailsPageActivity.this);
        myDialog.setContentView(R.layout.enqiury_now);
        ed_name =(EditText) myDialog.findViewById(R.id.ed_name1);
        ed_mobile =(EditText) myDialog.findViewById(R.id.ed_mobile1);
        ed_email =(EditText) myDialog.findViewById(R.id.ed_email1);
        ed_msg =(EditText) myDialog.findViewById(R.id.ed_msg1);

        btnsubmitNow =(Button) myDialog.findViewById(R.id.btnsubmitNow1);
        intent = getIntent();
        seller_id = intent.getIntExtra("seller_id",0);
        Log.e("Quote_seller_id", String.valueOf(seller_id));
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
                Log.e("Quote_seller_id", String.valueOf(seller_id));
                Call<SaveQuoteModel> call = RetrofitClient.getInstance().getApi().saveQuote(name,mobile,email,msg, String.valueOf(seller_id));
                call.enqueue(new Callback<SaveQuoteModel>() {
                    @Override
                    public void onResponse(Call<SaveQuoteModel> call, Response<SaveQuoteModel> response) {
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
                    public void onFailure(Call<SaveQuoteModel> call, Throwable throwable) {
                        Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         type = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}