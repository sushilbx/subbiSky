package com.webdigital.subbisky.activity;

import java.util.ArrayList;
import java.util.List;

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
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.CustomerSignUpModel;
import com.webdigital.subbisky.utils.Validation;

import retrofit2.Call;

import com.webdigital.subbisky.Preferences.Session;

import retrofit2.Callback;
import retrofit2.Response;
public class SignupActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private EditText edt_name,edt_email,edt_mob,edt_pass,edt_conPass;
    private Button btn_signUp;
    private TextView txt_signIn;
    private String edt_name1,edt_email1,edt_mob1,edt_pass1,edt_conPass1;
    LinearLayout linerLayoutBack;
    List<String> cityList;
    String[] cityListSpinner;
    int[] cityListTitleId;
    int sListId;
    Spinner CityList;
    String[] country = { "--Select--", "USA", "China", "Japan", "Other"};
    private String cityId="1";
    public static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
    String Sid;
     String service_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_name=findViewById(R.id.ed_user_name);
        edt_email=findViewById(R.id.ed_email);
        edt_mob=findViewById(R.id.ed_phone);
        edt_pass=findViewById(R.id.ed_password);
        edt_conPass=findViewById(R.id.ed_confirm_password);

        txt_signIn=findViewById(R.id.txt_SignIn);

        btn_signUp=findViewById(R.id.btnSignup);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);

        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });

        CityList = (Spinner) findViewById(R.id.spinner);
        CityListSpinner();
        CityList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i != 0) {
//                    service_id = cityList.get(i).toString();
//                    Log.e("ServiceId", service_id);
//                } else {
//                    service_id = cityList.get(i).toString();
//                    Log.e("ServiceId", service_id);
//                }
                String selected = adapterView.getItemAtPosition(i).toString();
                for(int k=0;k<cityListSpinner.length;k++)
                {
                    if(selected.equals(cityListSpinner[k]))
                        sListId=cityListTitleId[k];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        //Creating the ArrayAdapter instance having the country list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        StateList.setAdapter(aa);

        txt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignupActivity.this, Login.class);
                startActivity(intent);

            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate())
                {
//                    &&!edt_mob1.equals(MOBILE_PATTERN)
                    edt_name1=edt_name.getEditableText().toString().trim();
                    edt_email1=edt_email.getEditableText().toString().trim();
                    edt_mob1=edt_mob.getEditableText().toString().trim();
                    edt_pass1=edt_pass.getEditableText().toString().trim();
                    edt_conPass1=edt_conPass.getEditableText().toString().trim();
//                    if (!edt_email1.equals(emailPattern)){
//                        edt_email.setError("Please enter valid email");
//                        Toast.makeText( getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
//
//                    }

//                     edt_mob.setError("Please enter valid mobile number");
////                        Toast.makeText( getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                    Sid= String.valueOf(sListId);
//                    Log.e("Sid",service_id);
                    Call<CustomerSignUpModel> call = RetrofitClient.getInstance().getApi().customersignUp(edt_name1,edt_email1,edt_mob1,edt_pass1,edt_conPass1);
                    call.enqueue(new Callback<CustomerSignUpModel>() {
                        @Override
                        public void onResponse(Call<CustomerSignUpModel> call, Response<CustomerSignUpModel> response) {
                            if (response.code()==201) {
                                Session session = new Session(SignupActivity.this);
                                Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                session.createLoginSession(response.body().getUser().getId().toString());
                                String  userId= response.body().getUser().getId().toString();
                                Intent intent = new Intent(SignupActivity.this, OTPVerifyActivity.class);
                                intent.putExtra("userId",userId);
                                startActivity(intent);
                            }
                            else if(response.code()==422){
                                Toast.makeText( getApplicationContext()," This phone number is already used .Please use diffrent number", Toast.LENGTH_SHORT).show();
                                Toast.makeText( getApplicationContext()," The email has already been taken.", Toast.LENGTH_SHORT).show();

                               // Toast.makeText( getApplicationContext(), response.body().getErrors().getEmail().toString(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<CustomerSignUpModel> call, Throwable throwable) {
                            Toast.makeText( getApplicationContext()," This email is already used .Please use diffrent email", Toast.LENGTH_SHORT).show();


                        }
                    });
                }
                }

        });

    }

    private void CityListSpinner()  {

        Session session = new Session(SignupActivity.this);
//        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//
//        Log.e("Bearer ",auth);
//        HashMap<String, String> user = session.getUserDetails();
        Call<CityListModel> call=RetrofitClient.getInstance().getApi().cityList();
        call.enqueue(new Callback<CityListModel>() {
            @Override
            public void onResponse(Call<CityListModel> call, Response<CityListModel> response) {
                if (response.isSuccessful()) {
//                    progressDialog.dismiss();
                    if (response.body().getCities().size()!=0) {
                        int k = 0;
                        cityList= new ArrayList<>();
                        cityListSpinner= new String[response.body().getCities().size()];
                        cityListTitleId = new int[response.body().getCities().size()];
                        sListId = response.body().getCities().get(0).getId();
                        for (int i = 0; i < response.body().getCities().size(); i++) {
                            cityListSpinner[i] = response.body().getCities().get(i).getName();
                            if (response.body().getStatusCode()==200) {
                                cityList.add(response.body().getCities().get(i).getName());
                                Log.d("TAG", "onResponse: " + response.body().getCities().get(i).getName());
                                cityListTitleId[k] = response.body().getCities().get(i).getId();
                                k++;
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_spinner_item, cityList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        CityList.setAdapter(adapter);
                        Log.e("cityList", String.valueOf(CityList));

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CityListModel> call, Throwable t) {

            }


        });




    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    private boolean validate()
    {
        int flg = 0;
        edt_pass1=edt_pass.getEditableText().toString();
        edt_conPass1=edt_conPass.getEditableText().toString();
        if(Validation.isEmpty(edt_name))
        {
            flg=1;
            edt_name.requestFocus();
            edt_name.setError("Please enter name");
        }
        else if(Validation.isEmpty(edt_email))
        {
            flg = 1;
            edt_email.requestFocus();
            edt_email.setError("Please enter email");
        }
        else if(Validation.isEmailId(edt_email))
        {
            flg = 1;
            edt_email.requestFocus();
            edt_email.setError("Please enter valid email");
        }
        else if(Validation.isEmpty(edt_mob))
        {
            flg = 1;
            edt_mob.requestFocus();
            edt_mob.setError("Please enter valid mobile number");
        }
        else if(Validation.isEmpty(edt_pass))
        {
            flg = 1;
            edt_pass.requestFocus();
            edt_pass.setError("Please enter password");
        }
        else if(Validation.isEmpty(edt_conPass))
        {
            flg = 1;
            edt_conPass.requestFocus();
            edt_conPass.setError("Please enter password");
        }
        else if(!edt_conPass1.equals(edt_pass1))
        {
            flg = 1;
            android.widget.Toast.makeText(SignupActivity.this, "Password must to be match", Toast.LENGTH_SHORT).show();
        }
        if(flg>0)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }


}