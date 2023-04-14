package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.EcomProductDeatilsAdapter;
import com.webdigital.subbisky.model.AddToCartDescriptionModel;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.CustomerAddCartModel;
import com.webdigital.subbisky.model.ProductDetailModel;
import com.webdigital.subbisky.APIs.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartDescription extends AppCompatActivity {
    ImageView productsImage;
    TextView productsSellingPrice,productsMrp,productsQuantity,productsSeller,productsUnit,cart_badge,
            productsDesc,productsName;
    Button btnAddToCart,minus,plus;
    LinearLayout linerLayoutBack;
    Intent intent;
    int seller_id,product_id,pid;
    int qty = 1;


    ImageView imgCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_description);
        intent=getIntent();
        seller_id=intent.getIntExtra("sellerId",0);
        product_id=intent.getIntExtra("categoryId",0);
        pid=intent.getIntExtra("productId",0);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);
        btnAddToCart=findViewById(R.id.btnAddToCart);
        productsImage=findViewById(R.id.productsImage);
        productsName=findViewById(R.id.productsName);
        productsSellingPrice=findViewById(R.id.productsSellingPrice);
        productsMrp=findViewById(R.id.productsMrp);
        productsQuantity=findViewById(R.id.productsQuantity);
        productsSeller=findViewById(R.id.productsSeller);
        productsUnit=findViewById(R.id.productsUnit);
        productsDesc=findViewById(R.id.productsDesc);
        cart_badge=findViewById(R.id.cart_badge);
        minus.setOnClickListener(v -> {
            if (qty > 1) {

                --qty;
                productsQuantity.setText((qty) + "");
//                get_price.setText("Get Price");
//                price = "0";
            } else {

                Log.e("qty", String.valueOf(qty));
                Toast.makeText(AddToCartDescription.this, "Minimum Succeed", Toast.LENGTH_SHORT).show();
            }
        });
        plus.setOnClickListener(v -> {
//            for (qty=1; qty<=20;qty++) {
            ++qty;
            productsQuantity.setText((qty) + "");
//            get_price.setText("Get Price");
//            price = "0";
//            }
//
//            }

        });
        getCartCount();
        btnAddToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("qty", String.valueOf(qty));
                Log.e("pId", String.valueOf(pid));
                Session session = new Session(AddToCartDescription.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                Call<CustomerAddCartModel> call = RetrofitClient.getInstance().getApi().customerAddCart(auth,String.valueOf(pid), String.valueOf(qty));
                call.enqueue(new Callback<CustomerAddCartModel>() {
                    @Override
                    public void onResponse(Call<CustomerAddCartModel> call, Response<CustomerAddCartModel> response) {
                        if (response.isSuccessful()) {
                            getCartCount();
                            Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CustomerAddCartModel> call, Throwable throwable) {
                        Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        imgCart = findViewById(R.id.imgCart);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddToCartDescription.this,CartListActivity.class);
                startActivity(intent);
            }
        });
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ProductDesc();
    }

    private void getCartCount() {
        Session session = new Session(AddToCartDescription.this);
        String UserToken = session.getAccessTokenType() + " " + session.getAccessToken();
        Call<CartcountModel> call = RetrofitClient.getInstance().getApi().cartcount(UserToken);
        call.enqueue(new Callback<CartcountModel>() {
            @Override
            public void onResponse(Call<CartcountModel> call, Response<CartcountModel> response) {
                if (response.code() == 200) {
                    if (response.body().getMessage().equals("Cart is empty")) {
                        cart_badge.setText("0");
                    } else {
                        cart_badge.setText(response.body().getCount().toString());



                    }
                }
            }

            @Override
            public void onFailure(Call<CartcountModel> call, Throwable t) {

            }
        });

    }

    //    public static final String MY_PREFS_NAME = "MyPrefsFile";
//    SharedPreferences prefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedpreferences.edit();
//    String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
////    int idName = prefs.getInt("idName", 0); //0 is the default value.
//SharedPreferences sharedPreferences = PreferenceManager
//        .getDefaultSharedPreferences(AddToCartDescription.this);
//    String key;
//    String name = sharedPreferences.getString(key, "default value");
    private void ProductDesc() {
        Call<AddToCartDescriptionModel> call = RetrofitClient.getInstance().getApi().addToCartDescription(seller_id,pid);
        call.enqueue(new Callback<AddToCartDescriptionModel>() {
            @Override
            public void onResponse(Call<AddToCartDescriptionModel> call, Response<AddToCartDescriptionModel> response) {
                if (response.isSuccessful()){
                    SharedPreferences prefs = getSharedPreferences("your_file_name",
                            MODE_PRIVATE);
                    String name = prefs.getString("yourStringName",
                            "default_value_here_if_string_is_missing");
                    Glide.with(productsImage)
                            .load(response.body().getProduct().getImage()).fitCenter().into(productsImage);
                    productsImage=findViewById(R.id.productsImage);
                    productsName.setText(response.body().getProduct().getName());
                    productsSellingPrice.setText("Rs."+response.body().getProduct().getSelling_price());
                    productsMrp.setText("Rs."+response.body().getProduct().getMrp_price());
//                    productsQuantity.setText(response.body().getProduct().get());
                    productsSeller.setText(name);
                    Log.e("name",name);
                    productsUnit.setText(response.body().getProduct().getUnit());
                    productsDesc.setText(response.body().getProduct().getDescription());
                    productsMrp.setPaintFlags(productsMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                }
                else {
                    //Toast.makeText(AddToCartDescription.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddToCartDescription.this, "hello", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartDescriptionModel> call, Throwable t) {
                Toast.makeText(AddToCartDescription.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}