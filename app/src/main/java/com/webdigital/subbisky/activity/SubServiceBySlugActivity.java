package com.webdigital.subbisky.activity;

import static com.webdigital.subbisky.activity.DashboradActivity.cNotificationCounter;
import static com.webdigital.subbisky.activity.DashboradActivity.wNotificationCounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.juanlabrador.badgecounter.BadgeCounter;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.HomeServiceListAdapter;
import com.webdigital.subbisky.adapter.NearByShopAdapter;
import com.webdigital.subbisky.adapter.SubServiceBySlugAdapter;
import com.webdigital.subbisky.fragments.HomeFragment;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.SubServiceBySlugModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubServiceBySlugActivity extends AppCompatActivity {
    RecyclerView recyclerViewSlug;
    RecyclerView recyclerView;
    Dialog myDialog;
    TextView cart_badge;
    private SubServiceBySlugAdapter subServiceBySlugAdapter;
    private NearByShopAdapter nearByShopAdapter;
    Intent intent;
    String slugName;
    public static String type;
     ImageView imgCart;
    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_service_by_slug);
        recyclerViewSlug=findViewById(R.id.recyclerViewSlug);
        cart_badge=findViewById(R.id.cart_badge);
        getCartCount();
        imgCart = findViewById(R.id.imgCart);
        intent=getIntent();
        slugName=intent.getStringExtra("slugName");
        type=intent.getStringExtra("type");
        Log.e("slugName",slugName);
        if(HomeServiceListAdapter.type.equalsIgnoreCase("staybooking"))  {
            imgCart.setVisibility(View.GONE);
            cart_badge.setVisibility(View.GONE);
        }
        else {
            imgCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SubServiceBySlugActivity.this,CartListActivity.class);
                    startActivity(intent);
                }
            });
        }
        recyclerViewSlug.setHasFixedSize(true);
        recyclerViewSlug.setLayoutManager(new LinearLayoutManager(SubServiceBySlugActivity.this, LinearLayoutManager.VERTICAL, false));
        myDialog = new Dialog(this);
        SlugNameMethod(slugName);

        linerLayoutBack = findViewById(R.id.linerLayoutBack);


        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCartCount();
                onBackPressed();
               // finish();
            }
        });


        getCartCount();
    }
    public void onResume() {
        super.onResume();
        getCartCount();
    }
    private void getCartCount() {
        Session session = new Session(SubServiceBySlugActivity.this);
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



    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

// bro add the radious in badge
        //  RetrofitClient.getInstance().getCartCount(getApplicationContext());
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.cart);

        MenuItem menuItem = item;


        BadgeCounter.update(this, menu.findItem(R.id.cart),
                R.drawable.ic_baseline_shopping_cart_24,

                cNotificationCounter);

        Log.d("TAG", "onCreateOptionsMenu: " + wNotificationCounter);
        RetrofitClient.getInstance().getCartCount(getApplicationContext());
        // RetrofitClient.getInstance().getWishlistCount(getApplicationContext());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:

                RetrofitClient.getInstance().getCartCount(getApplicationContext());

                BadgeCounter.update(item, cNotificationCounter);
                Intent intent = new Intent(SubServiceBySlugActivity.this,CartListActivity.class);
                startActivity(intent);
                break;



        }
        return true;

    }


    private void SlugNameMethod(String slugName) {
        Call<SubServiceBySlugModel> call = RetrofitClient.getInstance().getApi().subServiceBySlug(slugName,HomeFragment.cityname);
        call.enqueue(new Callback<SubServiceBySlugModel>() {
            @Override
            public void onResponse(Call<SubServiceBySlugModel> call, Response<SubServiceBySlugModel> response) {
                if (response.isSuccessful()){

                    if(response.body().getNearbyShop()!=null){
                        HomeFragment.cityname="";
                        subServiceBySlugAdapter = new SubServiceBySlugAdapter((List<SubServiceBySlugModel.NearbyShop>) response.body().getNearbyShop(),type,SubServiceBySlugActivity.this);
                        recyclerViewSlug.setAdapter(subServiceBySlugAdapter);
                        Toast.makeText(SubServiceBySlugActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SubServiceBySlugActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SubServiceBySlugActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SubServiceBySlugModel> call, Throwable t) {
                Toast.makeText(SubServiceBySlugActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void ShowPopup(View v) {
//        TextView txtHead,textSub;
//        ImageView imgs;
//        int i = 0;
//        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
//        txtHead =(TextView) myDialog.findViewById(R.id.texthead);
//        textSub =(TextView) myDialog.findViewById(R.id.textSub);
//        imgs =(ImageView) myDialog.findViewById(R.id.imgs);
        recyclerView=myDialog.findViewById(R.id.recyclerViewNearBy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubServiceBySlugActivity.this,LinearLayoutManager.VERTICAL, false));

        Call<SubServiceBySlugModel> call = RetrofitClient.getInstance().getApi().subServiceBySlug(slugName,"");
        call.enqueue(new Callback<SubServiceBySlugModel>() {
            @Override
            public void onResponse(Call<SubServiceBySlugModel> call, Response<SubServiceBySlugModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getNearbyShop()!=null){
                        nearByShopAdapter = new NearByShopAdapter((List<SubServiceBySlugModel.NearbyShop>) response.body().getNearbyShop(),SubServiceBySlugActivity.this);
                        recyclerView.setAdapter(nearByShopAdapter);

                    }else {
                        Toast.makeText(SubServiceBySlugActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SubServiceBySlugActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SubServiceBySlugModel> call, Throwable t) {
                Toast.makeText(SubServiceBySlugActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}