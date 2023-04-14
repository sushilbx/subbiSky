package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.SearchAdapter;
import com.webdigital.subbisky.model.SearchResModel;
import com.webdigital.subbisky.model.Searchlistnewmodel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView search_recycler;
    EditText search;
    TextView record,cancel;
    Button clear;
    List<Searchlistnewmodel> searchlistname = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_recycler = findViewById(R.id.search_recycler);
        search_recycler.setHasFixedSize(true);
        search_recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        search = findViewById(R.id.search);
        record = findViewById(R.id.record);
        clear = findViewById(R.id.clear);
        cancel =findViewById(R.id.cancel);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchProduct(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clear.setOnClickListener(v -> {
            search.setText("");
            searchProduct("nosearchproduct");
        });
        cancel.setOnClickListener(v->{
            Intent intent = new Intent(SearchActivity.this,DashboradActivity.class);
            startActivity(intent);
            finish();
        });
    }
    public void searchProduct(String s) {
        if (s.isEmpty()) {
            s = "nosearchproduct";
        }
        final ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Session session = new Session(SearchActivity.this);
        Call<SearchResModel> call = RetrofitClient.getInstance().getApi().searchResult(s);
        call.enqueue(new Callback<SearchResModel>() {
            @Override
            public void onResponse(Call<SearchResModel> call, Response<SearchResModel> response) {
                if (response.isSuccessful()) {


                    searchlistname.clear();
//                Log.e("count",String.valueOf(response.body().getProducts()));
                    if (response.body().getService() == null) {
                        Log.e("count", "0");
                        progressDialog.dismiss();
                        search_recycler.setVisibility(View.GONE);
                        record.setVisibility(View.VISIBLE);
                        record.setText("No Records Found");
                    } else {


                        if (response.body().getService().size() != 0) {

                            for (int i = 0; i < response.body().getService().size(); i++) {

                                searchlistname.add(new Searchlistnewmodel("service", response.body().getService().get(i).getName().toString(), response.body().getService().get(i).getId().toString(),"0"));
                            }

                        }




                    }



                    if (response.body().getProducts() == null) {

                    } else {


                        if (response.body().getProducts().size() != 0) {

                            for (int i = 0; i < response.body().getProducts().size(); i++) {

                                searchlistname.add(new Searchlistnewmodel("products", response.body().getProducts().get(i).getName().toString(), response.body().getProducts().get(i).getId().toString(),response.body().getProducts().get(i).getSellerId().toString()));
                            }

                        }


                    }


                    if (response.body().getVendors() == null) {

                    } else {


                        if (response.body().getVendors().size() != 0) {

                            for (int i = 0; i < response.body().getVendors().size(); i++) {

                                searchlistname.add(new Searchlistnewmodel("vendor", response.body().getVendors().get(i).getShopName().toString(), response.body().getVendors().get(i).getId().toString(),"0"));
                            }

                        }


                    }
                    SearchAdapter adapter = new SearchAdapter(searchlistname, SearchActivity.this);
                    search_recycler.setAdapter(adapter);
                    search_recycler.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                    record.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SearchResModel> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}