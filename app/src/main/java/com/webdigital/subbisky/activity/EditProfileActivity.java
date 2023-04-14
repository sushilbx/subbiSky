package com.webdigital.subbisky.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CartcountModel;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.CustomerEditProfile;
import com.webdigital.subbisky.model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    Session session;
    EditText editusername, edituseremail, edituserphone, edituserlandmark, edituserpincode, edituseraddress, editusercity;
TextView cart_badge;
    LinearLayout linerLayoutBack;
    Button updateuser;
    Spinner cityspinner;
    List<String> cityList;
    //String[] cityListSpinner;
    int[] cityListTitleId;
    int sListId;
    String[] country = {"--Select--", "USA", "China", "Japan", "Other"};
    String sname, semail, sphone, slandmark, spincode, sadress, city_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        cart_badge = findViewById(R.id.cart_badge);
        editusername = findViewById(R.id.editusername);
        edituseremail = findViewById(R.id.edituseremail);
        edituserphone = findViewById(R.id.edituserphone);
        edituserlandmark = findViewById(R.id.edituserlandmark);
        edituserpincode = findViewById(R.id.edituserpincode);
        edituseraddress = findViewById(R.id.edituseraddress);
        updateuser = findViewById(R.id.userprofileupdate);
        editusercity = findViewById(R.id.editusercity);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        getuserdetails();
        getCartCount();
        //  getCitylist();
        // cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //  @Override
        //public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i != 0) {
//                    service_id = cityList.get(i).toString();
//                    Log.e("ServiceId", service_id);
//                } else {
//                    service_id = cityList.get(i).toString();
//                    Log.e("ServiceId", service_id);
//                }
//                String selected = adapterView.getItemAtPosition(i).toString();
//                for(int k=0;k<cityListSpinner.length;k++)
//                {
//                    if(selected.equals(cityListSpinner[k]))
//                        sListId=cityListTitleId[k];
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sid = String.valueOf(sListId);
                sname = editusername.getEditableText().toString();
                semail = edituseremail.getEditableText().toString();
                sphone = edituserphone.getEditableText().toString();
                slandmark = edituserlandmark.getEditableText().toString();
                spincode = edituserpincode.getEditableText().toString();
                sadress = edituseraddress.getEditableText().toString();
                city_id = editusercity.getEditableText().toString();
                session = new Session(EditProfileActivity.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                Call<CustomerEditProfile> call = RetrofitClient.getInstance().getApi().updateProfile(auth, sname, semail, sphone, spincode, slandmark, sadress, city_id);
                call.enqueue(new Callback<CustomerEditProfile>() {
                    @Override
                    public void onResponse(Call<CustomerEditProfile> call, Response<CustomerEditProfile> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditProfileActivity.this, DashboradActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<CustomerEditProfile> call, Throwable throwable) {
                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               finish();
            }
        });
    }

    private void getCartCount() {
        Session session = new Session(EditProfileActivity.this);
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

    private void getuserdetails() {
        session = new Session(EditProfileActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Call<UserModel> call = RetrofitClient.getInstance().getApi().userModel(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    editusername.setText(response.body().getUser().getName());
                    edituseremail.setText(response.body().getUser().getEmail());
                    edituserphone.setText(response.body().getUser().getPhone());
                    edituserlandmark.setText(response.body().getUser().getLandmark());
                    edituserpincode.setText(response.body().getUser().getPincode());
                    edituseraddress.setText(response.body().getUser().getAddress());
                    editusercity.setText(response.body().getUser().getCity_id());


                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void getCitylist() {

        Session session = new Session(EditProfileActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Log.e("Bearer ", auth);
        HashMap<String, String> user = session.getUserDetails();
        // Call<CityListModel> call=RetrofitClient.getInstance().getApi().cityList();
        // call.enqueue(new Callback<CityListModel>() {
        // @Override
        // public void onResponse(Call<CityListModel> call, Response<CityListModel> response) {
//                if (response.isSuccessful()) {
////                    progressDialog.dismiss();
//                    if (response.body().getCities().size()!=0) {
//                        int k = 0;
//                        cityList= new ArrayList<>();
//                        cityListSpinner= new String[response.body().getCities().size()];
//                        cityListTitleId = new int[response.body().getCities().size()];
//                        sListId = response.body().getCities().get(0).getId();
//                        for (int i = 0; i < response.body().getCities().size(); i++) {
//                            cityListSpinner[i] = response.body().getCities().get(i).getName();
//                            if (response.body().getStatusCode()==200) {
//                                cityList.add(response.body().getCities().get(i).getName());
//                                Log.d("TAG", "onResponse: " + response.body().getCities().get(i).getName());
//                                cityListTitleId[k] = response.body().getCities().get(i).getId();
//                                k++;
//                            }
//
//                        }
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProfileActivity.this, android.R.layout.simple_spinner_item, cityList);
//                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        cityspinner.setAdapter(adapter);
//                        Log.e("cityList", String.valueOf(cityspinner));
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
////                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CityListModel> call, Throwable t) {
//
//            }
//
//
//        });
        //}

    }
}
