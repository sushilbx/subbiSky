package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.CartListAdapter;
import com.webdigital.subbisky.model.CartListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListActivity extends AppCompatActivity {
    Button proceedToCheckOut;
    LinearLayout linerLayoutBack,showandhidecart;
    Session session;
    CartListAdapter cartListAdapter;
    RecyclerView cartListRecycler;
    TextView totalAmountCart;

    String sellerId;
    public static int total = 0;
    int i;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        cartListRecycler = findViewById(R.id.cartListRecycler);
        proceedToCheckOut = findViewById(R.id.proceedToCheckOut);
        totalAmountCart = findViewById(R.id.totalAmountCart);
        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        showandhidecart = findViewById(R.id.showandhidecart);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        cartListRecycler.setHasFixedSize(true);
        cartListRecycler.setLayoutManager(new LinearLayoutManager(CartListActivity.this, LinearLayoutManager.VERTICAL, false));
        getCartList();
        proceedToCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                amount = totalAmountCart.getText().toString();
                Intent intent = new Intent(CartListActivity.this, EcomProductCheckOutPage.class);
                intent.putExtra("amount", amount);
                intent.putExtra("sellerId", sellerId);
                Log.e("sellerId", sellerId);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {

        if(CartListAdapter.cartscreen.equalsIgnoreCase("yes")){
            Intent intent = new Intent(CartListActivity.this, DashboradActivity.class);
            startActivity(intent);
            finish();

        }
        else


        {
            super.onBackPressed();

        }

    }

    private void getCartList() {
        session = new Session(CartListActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<CartListModel> call = RetrofitClient.getInstance().getApi().cartList(auth);
        call.enqueue(new Callback<CartListModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CartListModel> call, Response<CartListModel> response) {
                if (response.isSuccessful()) {
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    if (response.body().getCarts().size() != 0) {
                        cartListAdapter = new CartListAdapter((List<CartListModel.Carts>) response.body().getCarts(), CartListActivity.this);
                        cartListRecycler.setAdapter(cartListAdapter);

                        sellerId = response.body().getCarts().get(i).getSeller_id();
                        Toast.makeText(CartListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        total = 0;
                        for (int i = 0; i < response.body().getCarts().size(); i++) {
                            total += Integer.parseInt(response.body().getCarts().get(i).getProduct_id().getSelling_price()) * Integer.parseInt(response.body().getCarts().get(i).getQuantity());
//                            gst += response.body().getData().get(i).getGst();
                        }
                        totalAmountCart.setText("Total Rs: " + String.valueOf(total));
                        amount = String.valueOf(total);
                    } else {
                        showandhidecart.setVisibility(View.GONE);
//                        Toast.makeText(CartListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CartListActivity.this, EmptyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(CartListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CartListModel> call, Throwable t) {
                Toast.makeText(CartListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}