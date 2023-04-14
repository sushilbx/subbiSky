package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.CustomerOrderDetailsAdapter;
import com.webdigital.subbisky.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity {
    Session session;
    LinearLayout linerLayoutBack;
    RecyclerView customerOrderRecycler;
    CustomerOrderDetailsAdapter customerOrderDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        customerOrderRecycler=findViewById(R.id.customerOrderRecycler);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        customerOrderRecycler.setHasFixedSize(true);
        customerOrderRecycler.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this,LinearLayoutManager.VERTICAL, false));

        customerOrderList();
    }
    private void customerOrderList() {
        session = new Session(MyOrderActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<UserModel> call = RetrofitClient.getInstance().getApi().customerOrderList(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getOrders().size()!=0){
                        Toast.makeText( getApplicationContext(),"Orders" , Toast.LENGTH_SHORT).show();
                        customerOrderDetailsAdapter = new CustomerOrderDetailsAdapter((List<UserModel.Orders>) response.body().getOrders(),MyOrderActivity.this);
                        customerOrderRecycler.setAdapter(customerOrderDetailsAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}