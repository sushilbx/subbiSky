package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.CustomerOrderDetailsAdapter;
import com.webdigital.subbisky.adapter.CustomerOrderProductDetailsAdapter;
import com.webdigital.subbisky.model.CustomerOrderListModel;
import com.webdigital.subbisky.model.UserModel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerOrderDescriptionActivity extends AppCompatActivity {
    LinearLayout linerLayoutBack;
    ImageView productImage,produtimg;
    Intent intent;
    int OrderId;
    CustomerOrderProductDetailsAdapter customerOrderProductDetailsAdapter;
    Session session;
    RecyclerView productsDetailsRecycler;
    TextView orderId,amountPaid,paymentMode,Ostatus,productName,
            productPrice,productQuantity,productTotal,customer_Name,
            takeAway,customer_Email,customer_phone,customer_address,
            customer_state,customer_city,customer_pincode;
TextView produttotal,produtname,produtprice,produtquantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=getIntent();
        OrderId= intent.getIntExtra("Order_id",0);
        setContentView(R.layout.activity_customer_order_description);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        productImage=findViewById(R.id.productImage);
        orderId=findViewById(R.id.orderId);
        amountPaid=findViewById(R.id.amountPaid);
        paymentMode=findViewById(R.id.paymentMode);
        Ostatus=findViewById(R.id.Ostatus);
        productName=findViewById(R.id.productName);
        productPrice=findViewById(R.id.productPrice);
        productQuantity=findViewById(R.id.productQuantity);
        productTotal=findViewById(R.id.productTotal);
        customer_Name=findViewById(R.id.customer_Name);
        takeAway=findViewById(R.id.takeAway);
        customer_Email=findViewById(R.id.customer_Email);
        customer_phone=findViewById(R.id.customer_phone);
        customer_address=findViewById(R.id.customer_address);
        customer_state=findViewById(R.id.customer_state);
        customer_city=findViewById(R.id.customer_city);
        customer_pincode=findViewById(R.id.customer_pincode);
        produtimg=findViewById(R.id.produtimg);
        produttotal=findViewById(R.id.produttotal);
        produtname=findViewById(R.id.produtname);
        produtprice=findViewById(R.id.produtprice);
        produtquantity=findViewById(R.id.produtquantity);


        productsDetailsRecycler=findViewById(R.id.productsDetailsRecycler);
        productsDetailsRecycler.setHasFixedSize(true);
        productsDetailsRecycler.setLayoutManager(new LinearLayoutManager(CustomerOrderDescriptionActivity.this,LinearLayoutManager.VERTICAL, false));
      //  productListDetails();
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        CustomerOrderDescription();
    }



    private void CustomerOrderDescription() {
        intent=getIntent();
        OrderId= intent.getIntExtra("Order_id",0);
        Session session = new Session(CustomerOrderDescriptionActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Log.e("OrderId", String.valueOf(OrderId));
        Call<CustomerOrderListModel> call = RetrofitClient.getInstance().getApi().customerOrderDesc(auth, String.valueOf(OrderId));
        call.enqueue(new Callback<CustomerOrderListModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CustomerOrderListModel> call, Response<CustomerOrderListModel> response) {
                if (response.isSuccessful()){
//                    if (response.body()){
                    orderId.setText("Order Id : "+response.body().getOrder().getOrder_id());
                    amountPaid.setText("Amount Paid : : "+response.body().getOrder().getPayable_price());
                    paymentMode.setText("Payment Mode : "+response.body().getOrder().getPayment_mode());
                    Ostatus.setText("Status : "+response.body().getOrder().getStatus());
                    takeAway.setText("Take Away : "+response.body().getOrder().getTakeAway());
                    customer_Name.setText("Name : "+response.body().getOrder().getName());
                    customer_Email.setText("Email : "+response.body().getOrder().getEmail());
                    customer_phone.setText("Phone : "+response.body().getOrder().getPhone());
                    customer_address.setText("Address : "+response.body().getOrder().getAddress());
                    customer_state.setText("State : "+response.body().getOrder().getLandmark());
                    customer_city.setText("City : "+response.body().getOrder().getCity_id());
                    customer_pincode.setText("Pincode : "+response.body().getOrder().getPincode());
                    produtprice.setText(response.body().getOrder().getList().get(0).getSelling_price());
                    produtquantity.setText(response.body().getOrder().getList().get(0).getQuantity());
                    produtname.setText(response.body().getOrder().getList().get(0).getProduct_id().getName());
                    produttotal.setText(response.body().getOrder().getPayable_price());
                    Picasso.get().load(response.body().getOrder().getList().get(0).getProduct_id().getImage()).into(produtimg);

//                    customerOrderProductDetailsAdapter = new CustomerOrderProductDetailsAdapter((List<CustomerOrderListModel.OrderedProductDetail.CustomerOrderList>) response.body().getOrder().getList(),CustomerOrderDescriptionActivity.this);
//                    productsDetailsRecycler.setAdapter(customerOrderProductDetailsAdapter);

                    Toast.makeText(CustomerOrderDescriptionActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

//                           refreshPage();

//                }
                }
                else {
                    Toast.makeText(CustomerOrderDescriptionActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerOrderListModel> call, Throwable t) {
                Toast.makeText(CustomerOrderDescriptionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}