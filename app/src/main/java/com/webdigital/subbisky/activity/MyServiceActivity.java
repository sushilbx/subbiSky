package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.MyserviceListAdapter;
import com.webdigital.subbisky.model.UserModel;

import java.util.List;

public class MyServiceActivity extends AppCompatActivity {
    Session session;
    LinearLayout linerLayoutBack;
    RecyclerView customerOrderRecycler;
    MyserviceListAdapter myserviceListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        customerOrderRecycler=findViewById(R.id.serviceOrderRecycler);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        customerOrderRecycler.setHasFixedSize(true);
        customerOrderRecycler.setLayoutManager(new LinearLayoutManager(MyServiceActivity.this,LinearLayoutManager.VERTICAL, false));

        customerOrderList();
    }

    private void customerOrderList() {
        session = new Session(MyServiceActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<UserModel> call = RetrofitClient.getInstance().getApi().customerOrderList(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getServices().size()!=0){
                        Toast.makeText( getApplicationContext(),"Services" , Toast.LENGTH_SHORT).show();
                        myserviceListAdapter = new MyserviceListAdapter((List<UserModel.Services>) response.body().getServices(),MyServiceActivity.this);
                        customerOrderRecycler.setAdapter(myserviceListAdapter);

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