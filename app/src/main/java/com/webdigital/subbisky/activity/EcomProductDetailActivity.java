package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.CategoriesNearByItemAdapter;
import com.webdigital.subbisky.adapter.EcomProductDeatilsAdapter;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.CategoriesNearByItemModel;
import com.webdigital.subbisky.model.ProductDetailModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EcomProductDetailActivity extends AppCompatActivity {
    Dialog myDialog;
    RecyclerView recyclerViewEcom,recyclerViewCategories;
    //    String slugName;
    private EcomProductDeatilsAdapter ecomProductDetailasAdapter;
    int seller_id;
    Intent intent;
    CategoriesNearByItemAdapter categoriesNearByItemAdapter;
    int category_id;
    String sellerName;
TextView cart_badge;
    ImageView imgCart;
    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecom_product_detail);
        myDialog = new Dialog(this);
        intent=getIntent();
        seller_id=intent.getIntExtra("seller_id",0);
       // sellerName=intent.getStringExtra("sellerName");
      //  Log.e("sellerName",sellerName);
        category_id=intent.getIntExtra("cId",0);
        recyclerViewEcom=findViewById(R.id.recyclerViewProduct);
        recyclerViewEcom.setHasFixedSize(false);
        recyclerViewEcom.setLayoutManager(new GridLayoutManager(EcomProductDetailActivity.this,2));
        ProductDetails();
//        SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("yourStringName", sellerName);
//        editor.commit();
        imgCart = findViewById(R.id.imgCart);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        cart_badge = findViewById(R.id.cart_badge);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcomProductDetailActivity.this,CartListActivity.class);
                startActivity(intent);
            }
        });
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                //finish();
            }
        });
        getCartCount();
    }
    public void onResume() {
        super.onResume();
        getCartCount();
    }
    private void getCartCount() {
        Session session = new Session(EcomProductDetailActivity.this);
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

    private void ProductDetails() {
        Call<ProductDetailModel> call = RetrofitClient.getInstance().getApi().productDetail(seller_id);
        call.enqueue(new Callback<ProductDetailModel>() {
            @Override
            public void onResponse(Call<ProductDetailModel> call, Response<ProductDetailModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getProducts().size() != 0){
                        ecomProductDetailasAdapter = new EcomProductDeatilsAdapter((List<ProductDetailModel.ProductDetails>) response.body().getProducts(),EcomProductDetailActivity.this);
                        recyclerViewEcom.setAdapter(ecomProductDetailasAdapter);
                    }else {
                        Toast.makeText(EcomProductDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(EcomProductDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProductDetailModel> call, Throwable t) {
                Toast.makeText(EcomProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void ShowPopup(View v) {
//        TextView txtHead,textSub;
//        ImageView imgs;
//        int i = 0;
//        Button btnFollow;
//        categoriesNearByItemAdapter = new CategoriesNearByItemAdapter((List<CategoriesNearByItemModel.CategoryProducts>) response.body().getProducts(),EcomProductDetailActivity.this);
//        recyclerViewCategories.setAdapter(categoriesNearByItemAdapter);
        myDialog.setContentView(R.layout.custompopup3);
//        txtHead =(TextView) myDialog.findViewById(R.id.texthead);
//        textSub =(TextView) myDialog.findViewById(R.id.textSub);
//        imgs =(ImageView) myDialog.findViewById(R.id.imgs);
        recyclerViewCategories=myDialog.findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setHasFixedSize(true);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(EcomProductDetailActivity.this,LinearLayoutManager.VERTICAL, false));
        Call<ProductDetailModel> call = RetrofitClient.getInstance().getApi().productDetail(seller_id);
        call.enqueue(new Callback<ProductDetailModel>() {
            @Override
            public void onResponse(Call<ProductDetailModel> call, Response<ProductDetailModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getProducts().size() != 0){
                        categoriesNearByItemAdapter = new CategoriesNearByItemAdapter((List<ProductDetailModel.ProductDetails>) response.body().getProducts(),EcomProductDetailActivity.this);
                        recyclerViewCategories.setAdapter(categoriesNearByItemAdapter);
                    }else {
                        Toast.makeText(EcomProductDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(EcomProductDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProductDetailModel> call, Throwable t) {
                Toast.makeText(EcomProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}