package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import com.webdigital.subbisky.adapter.FilterprodutAdapter;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.CategoriesNearByItemModel;
import com.webdigital.subbisky.model.ProductDetailModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredCategoryProductsActivity extends AppCompatActivity {
RecyclerView recyclerViewfilter;
    ImageView imgCart;
    TextView cart_badge;
    LinearLayout linerLayoutBack;

    Intent intent;
    int seller_id;
    int category_id;
    int cId;
    int selid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_category_products);
        intent=getIntent();
        seller_id=intent.getIntExtra("seller_id",0);
        category_id=intent.getIntExtra("cId",0);
        cId=intent.getIntExtra("cId",0);
        selid=intent.getIntExtra("selid",0);
        imgCart = findViewById(R.id.imgCart);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        cart_badge = findViewById(R.id.cart_badge);

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilteredCategoryProductsActivity.this,CartListActivity.class);
                startActivity(intent);
            }
        });
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

                finish();
            }
        });
        getCartCount();
        recyclerViewfilter=findViewById(R.id.recyclerViewfilter);
        recyclerViewfilter.setHasFixedSize(false);
        recyclerViewfilter.setLayoutManager(new GridLayoutManager(FilteredCategoryProductsActivity.this,2));
        ProductDetails();
    }
    public void onResume() {

        super.onResume();
        getCartCount();
    }
    private void getCartCount() {
        Session session = new Session(FilteredCategoryProductsActivity.this);
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
        Call<CategoriesNearByItemModel> call = RetrofitClient.getInstance().getApi().categoriesNearByItem(selid,cId);
        call.enqueue(new Callback<CategoriesNearByItemModel>() {
            @Override
            public void onResponse(Call<CategoriesNearByItemModel> call, Response<CategoriesNearByItemModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getProducts().size() != 0) {


                        FilterprodutAdapter filterproductadapter = new FilterprodutAdapter((List<CategoriesNearByItemModel.Product>) response.body().getProducts(), FilteredCategoryProductsActivity.this);
                        recyclerViewfilter.setAdapter(filterproductadapter);
                    } else {
                        Toast.makeText(FilteredCategoryProductsActivity.this, "No products available", Toast.LENGTH_SHORT).show();

                    }
                }
//                else {
//                    Toast.makeText(FilteredCategoryProductsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
            }

            @Override
            public void onFailure(Call<CategoriesNearByItemModel> call, Throwable t) {
                Toast.makeText(FilteredCategoryProductsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}