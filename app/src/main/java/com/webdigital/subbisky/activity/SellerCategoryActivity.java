package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SellerCategoryAdapter;
import com.webdigital.subbisky.model.SellerCategoryModel;
import com.webdigital.subbisky.model.SellerCreateCategoryModel;

import java.util.List;

public class SellerCategoryActivity extends AppCompatActivity {
    Session session;
    RecyclerView sellerCategoryRecycler;
    SellerCategoryAdapter sellerCategoryAdapter;
    LinearLayout linerLayoutBack;
    Button createSellerCategory;
    TextView totalWalletAmountSeller;
    Integer category_Id;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_category);
        sellerCategoryRecycler = findViewById(R.id.sellerCategoryRecycler);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        createSellerCategory=findViewById(R.id.createSellerCategory);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        createSellerCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();

                PopupWindow builder;
//                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialougView = LayoutInflater.from(v.getContext()).inflate(R.layout.create_category_popup, null);
                int widt = LinearLayout.LayoutParams.MATCH_PARENT;
                int hight = LinearLayout.LayoutParams.WRAP_CONTENT;
                Boolean focus = true;

                builder = new PopupWindow(dialougView, widt, hight, focus);

                builder.setOutsideTouchable(false);
                builder.setFocusable(true);

                // Removes default black background
                builder.setBackgroundDrawable(new BitmapDrawable());
                EditText addCatName,addCatStatus;
                Button AddCatBtn,cancelBtn;
                addCatName = dialougView.findViewById(R.id.addCatName);
                addCatStatus = dialougView.findViewById(R.id.addCatStatus);
                cancelBtn = dialougView.findViewById(R.id.cancelBtn);
                AddCatBtn = dialougView.findViewById(R.id.AddCatBtn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
                AddCatBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String addCategoryName,addCategoryStatus;
                        addCategoryName = addCatName.getEditableText().toString();
                        addCategoryStatus = addCatStatus.getEditableText().toString();
                        if (!addCategoryName.isEmpty() && !addCategoryStatus.isEmpty()){
                            session = new Session(SellerCategoryActivity.this);
                            String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                            Call<SellerCreateCategoryModel> call = RetrofitClient.getInstance().getApi().createSellerCategory(auth,addCategoryName,addCategoryStatus);
                            call.enqueue(new Callback<SellerCreateCategoryModel>() {
                                @Override
                                public void onResponse(Call<SellerCreateCategoryModel> call, Response<SellerCreateCategoryModel> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(SellerCategoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        getCategoryList();
                                        builder.dismiss();
                                    }else {
                                        Toast.makeText(SellerCategoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<SellerCreateCategoryModel> call, Throwable t) {

                                }
                            });
                        }else {
                            if (addCategoryName.isEmpty()){
                                addCatName.setError("Please add Category Name");
                            }else if (addCategoryStatus.isEmpty()){
                                addCatName.setError("Please add Category Status");
                            }
                        }
                    }
                });

                builder.showAtLocation(v, Gravity.CENTER, 0, 0);

            }
        });

        sellerCategoryRecycler.setHasFixedSize(true);
        sellerCategoryRecycler.setLayoutManager(new LinearLayoutManager(SellerCategoryActivity.this,LinearLayoutManager.VERTICAL, false));

        getCategoryList();


    }

    @Override
    public void onBackPressed() {
        Intent intent3 = new Intent(SellerCategoryActivity.this,SellerDashBoardActivity.class);
        startActivity(intent3);
        finish();
    }

    private void getCategoryList() {
        session = new Session(SellerCategoryActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<SellerCategoryModel> call = RetrofitClient.getInstance().getApi().getSellerCategory(auth);
        call.enqueue(new Callback<SellerCategoryModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SellerCategoryModel> call, Response<SellerCategoryModel> response) {
                if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                    if(response.body().getCategories().size() != 0){
                        sellerCategoryAdapter = new SellerCategoryAdapter((List<SellerCategoryModel.SellerCategory>) response.body().getCategories(),SellerCategoryActivity.this);
                        sellerCategoryRecycler.setAdapter(sellerCategoryAdapter);
//                        session.getSellerCategoryId(response.body().getCategories().get(i).getId());
//                        Toast.makeText(SellerCategoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SellerCategoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(SellerCategoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerCategoryModel> call, Throwable t) {
                Toast.makeText(SellerCategoryActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}