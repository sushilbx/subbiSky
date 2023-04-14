package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.ServicePaymentModel;
import com.webdigital.subbisky.model.UserModel;
import com.google.gson.Gson;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicePayActivity extends AppCompatActivity {

    LinearLayout linerLayoutBack;
    TextView serviceCheckoutdate,serviceCheckouttime,serviceCheckoutAmount,tdate,ttime;
    EditText serviceCheckoutName,serviceCheckoutEmail,serviceCheckoutcity,serviceCheckoutPhone,serviceCheckoutPincode,serviceCheckoutLandmark,serviceCheckoutAddress;
    Button serviceCheckoutBtn;
    String customerType,amount;
    List<String> cityList;
    String[] cityListSpinner;
    int[] cityListTitleId;
    int cListId;
    Spinner CityList;
    Session session;
    String cityId;
    DatePickerDialog datePickerDialog;
    String name,email,phone,pincode,landmark,address,city,hdate ,time,totalAmt;
    int sellerId,serviceId;
    int hour,min;


    //PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
  //  PayUmoneySdkInitializer.PaymentParam paymentParam = null;

    String payUMId = "7455888";
    String payUKey = "GpUr6trc"; //Production
    String payUSaltKey = "9BF4Bs5Gxl"; //Production
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
    Date date = new Date();
    String txnId;
    String paymentId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_pay);
        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        serviceCheckoutdate=findViewById(R.id.serviceCheckoutdate);
        CityList=findViewById(R.id.serviceCheckoutCity);
        serviceCheckouttime=findViewById(R.id.serviceCheckouttime);
        serviceCheckoutName=findViewById(R.id.serviceCheckoutName);
        serviceCheckoutEmail=findViewById(R.id.serviceCheckoutEmail);
        serviceCheckoutPhone=findViewById(R.id.serviceCheckoutPhone);
        serviceCheckoutPincode=findViewById(R.id.serviceCheckoutPincode);
        serviceCheckoutLandmark=findViewById(R.id.serviceCheckoutLandmark);
        serviceCheckoutAddress=findViewById(R.id.serviceCheckoutAddress);
        serviceCheckoutcity=findViewById(R.id.serviceCheckoutcity);
        serviceCheckoutAmount=findViewById(R.id.serviceCheckoutAmount);
        serviceCheckoutBtn=findViewById(R.id.serviceCheckoutBtn);
        tdate=findViewById(R.id.tdate);
        ttime=findViewById(R.id.ttime);
        CityListSpinner();

        serviceCheckoutdate.setVisibility(View.GONE);
        serviceCheckouttime.setVisibility(View.GONE);
        ttime.setVisibility(View.GONE);
        tdate.setVisibility(View.GONE);
        Intent intent = getIntent();
        customerType = intent.getStringExtra("customerType");
        amount = intent.getStringExtra("amount");
        sellerId = intent.getIntExtra("sellerId",0);
        serviceId = intent.getIntExtra("serviceId",0);
        txnId = formatter.format(date);
        if (customerType.equals("health")){
            serviceCheckoutdate.setVisibility(View.VISIBLE);
            serviceCheckouttime.setVisibility(View.VISIBLE);
            ttime.setVisibility(View.VISIBLE);
            tdate.setVisibility(View.VISIBLE);
        }else {
            serviceCheckoutdate.setVisibility(View.GONE);
            serviceCheckouttime.setVisibility(View.GONE);
            ttime.setVisibility(View.GONE);
            tdate.setVisibility(View.GONE);
        }
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
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
                        cListId=cityListTitleId[k];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getServiceData();
        Calendar mcurrentTime = Calendar.getInstance();
        hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        min = mcurrentTime.get(Calendar.MINUTE);
        serviceCheckouttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(hour,min);
            }
        });
        serviceCheckoutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(ServicePayActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        serviceCheckoutdate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
//                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        serviceCheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = serviceCheckoutName.getEditableText().toString();
                email = serviceCheckoutEmail.getEditableText().toString();
                phone = serviceCheckoutPhone.getEditableText().toString();
                pincode = serviceCheckoutPincode.getEditableText().toString();
                landmark = serviceCheckoutLandmark.getEditableText().toString();
                address = serviceCheckoutAddress.getEditableText().toString();
                hdate = serviceCheckoutdate.getEditableText().toString();
                time = serviceCheckouttime.getEditableText().toString();
                totalAmt = serviceCheckoutAmount.getText().toString();
                city = serviceCheckoutcity.getText().toString();
                //city = String.valueOf(cListId);
                if (!name.isEmpty()&& !email.isEmpty()&& !phone.isEmpty()&& !pincode.isEmpty()&& !landmark.isEmpty()&& !address.isEmpty()&& !totalAmt.isEmpty() &&!city.isEmpty() ){
                    if (customerType.equals("health")){
                        if (!hdate.isEmpty()&& !time.isEmpty()){
                            //startPayment(Double.parseDouble(totalAmt));
                            submitPayment();
                            checkout();
                        }else if (hdate.isEmpty()){
                            serviceCheckoutdate.setError("Please fill this field!");
                        }else if (time.isEmpty()){
                            serviceCheckouttime.setError("Please fill this field!");
                        }
                    }else {


                    }
                }else {
                    if (name.isEmpty()){
                        serviceCheckoutName.setError("Please fill this field!");
                    }else if (email.isEmpty()){
                        serviceCheckoutEmail.setError("Please fill this field!");
                    }else if (phone.isEmpty()){
                        serviceCheckoutPhone.setError("Please fill this field!");
                    }else if (pincode.isEmpty()){
                        serviceCheckoutPincode.setError("Please fill this field!");
                    }else if (landmark.isEmpty()){
                        serviceCheckoutLandmark.setError("Please fill this field!");
                    }else if (address.isEmpty()){
                        serviceCheckoutAddress.setError("Please fill this field!");
                    }else if (totalAmt.isEmpty()){
                        serviceCheckoutAmount.setError("Please fill this field!");
                    }
                    else if (city.isEmpty()){
                        serviceCheckoutcity.setError("Please fill this field!");
                    }
                }
            }
        });
    }

    private void checkout() {
        // startPayment(Double.parseDouble(totalAmt));
        //  totalAmt =amount.getText().toString().trim();
        totalAmt = String.valueOf(amount);
        //totalAmt = String.valueOf("1");
        // on below line we are getting
        // amount that is entered by user.
        //String samount = amount.getText().toString();

        // rounding off the amount.
        int amount = Math.round(Float.parseFloat(totalAmt) * 100);

        // initialize Razorpay account.
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_live_Au4hmecg8VDUOt");

        // set image


        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Subbisky ");

            // put description
            object.put("description", "Test payment");

            // to set theme color
            object.put("theme.color", "");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", amount);

            // put mobile number
            object.put("prefill.contact", phone);

            // put email
            object.put("prefill.email", email);

            // open razorpay to checkout activity
            checkout.open(ServicePayActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showTime(int hours, int minte) {
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(ServicePayActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                serviceCheckouttime.setText( hourOfDay + ":" + minute);
                hour=hourOfDay;
                min=minute;
            }
        }, hours, minte, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void getServiceData() {
        session = new Session(ServicePayActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<UserModel> call = RetrofitClient.getInstance().getApi().userModel(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    serviceCheckoutName.setText(response.body().getUser().getName());
                    serviceCheckoutEmail.setText(response.body().getUser().getEmail());
                    serviceCheckoutPhone.setText(response.body().getUser().getPhone());
                    serviceCheckoutLandmark.setText(response.body().getUser().getLandmark());
                    serviceCheckoutPincode.setText(response.body().getUser().getPincode());
                    serviceCheckoutAddress.setText(response.body().getUser().getAddress());
                    serviceCheckoutcity.setText(response.body().getUser().getCity_id());
                    serviceCheckoutAmount.setText(amount);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void CityListSpinner()  {

        Session session = new Session(ServicePayActivity.this);
//        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//
//        Log.e("Bearer ",auth);
//        HashMap<String, String> user = session.getUserDetails();
        Call<CityListModel> call= RetrofitClient.getInstance().getApi().cityList();
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
                        cListId = response.body().getCities().get(0).getId();
                        for (int i = 0; i < response.body().getCities().size(); i++) {
                            cityListSpinner[i] = response.body().getCities().get(i).getName();
                            if (response.body().getStatusCode()==200) {
                                cityList.add(response.body().getCities().get(i).getName());
                                Log.d("TAG", "onResponse: " + response.body().getCities().get(i).getName());
                                cityListTitleId[k] = response.body().getCities().get(i).getId();
                                k++;
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServicePayActivity.this, android.R.layout.simple_spinner_item, cityList);
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


    ///////////////////////////////////////////paymentgateway////////////////////////////////
//    private void startPayment(double pamount) {
//        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
//
//        String payAmt = "Book Service";
//        String udf1 = "";
//        String udf2 = "";
//        String udf3 = "";
//        String udf4 = "";
//        String udf5 = "";
//
//        builder.setAmount(String.valueOf(pamount))
//                .setTxnId(txnId)
//                .setPhone(phone)
//                .setProductName(payAmt)
//                .setFirstName(name)
//                .setEmail(email)
//                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
//                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
//                .setUdf1(udf1)
//                .setUdf2(udf2)
//                .setUdf3(udf3)
//                .setUdf4(udf4)
//                .setUdf5(udf5)
//                .setIsDebug(true)
//                .setKey(payUKey)
//                .setMerchantId(payUMId);
//
//        try {
//            PayUmoneySdkInitializer.PaymentParam mPaymentParams = builder.build();
//
//            mPaymentParams = calculateServerSideHashAndInitiatePayment1(mPaymentParams);
//
//            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, ServicePayActivity.this, R.style.AppTheme, true);
//
//        } catch (Exception e) {
//            // some exception occurred
//            Toast.makeText(ServicePayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//    }
//
//    public static String hashCal(String str) {
//        byte[] hashseq = str.getBytes();
//        StringBuilder hexString = new StringBuilder();
//        try {
//            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
//            algorithm.reset();
//            algorithm.update(hashseq);
//            byte messageDigest[] = algorithm.digest();
//            for (byte aMessageDigest : messageDigest) {
//                String hex = Integer.toHexString(0xFF & aMessageDigest);
//                if (hex.length() == 1) {
//                    hexString.append("0");
//                }
//                hexString.append(hex);
//            }
//        } catch (NoSuchAlgorithmException ignored) {
//        }
//        return hexString.toString();
//    }
//
//    private PayUmoneySdkInitializer.PaymentParam calculateServerSideHashAndInitiatePayment1(final PayUmoneySdkInitializer.PaymentParam paymentParam) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        HashMap<String, String> params = paymentParam.getParams();
//        stringBuilder.append(params.get(PayUmoneyConstants.KEY)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.TXNID)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF1)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF2)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF3)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF4)).append("|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF5)).append("||||||");
//
//        stringBuilder.append(payUSaltKey);
//
//        String hash = hashCal(stringBuilder.toString());
//        paymentParam.setMerchantHash(hash);
//
//
//        return paymentParam;
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("MyWalletFragment", "request code " + requestCode + " resultcode " + resultCode);
//        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
//                null) {
//            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
//                    .INTENT_EXTRA_TRANSACTION_RESPONSE);
//
//            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);
//
//            // Check which object is non-null
//            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
//                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
//                    //Success Transaction
//                    Toast.makeText(ServicePayActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
//                    Gson g = new Gson();
//                    PayuMoneyResponse payuMoneyResponse = g.fromJson(transactionResponse.getPayuResponse(), PayuMoneyResponse.class);
//                    paymentId = payuMoneyResponse.getResult().paymentId;
//                    Log.e("payment id", payuMoneyResponse.getResult().paymentId);
//                    submitPayment();
//                } else {
//                    //Failure Transaction
//                }
//
//                // Response from Payumoney
//                String payuResponse = transactionResponse.getPayuResponse();
//
//                // Response from SURl and FURL
//                String merchantResponse = transactionResponse.getTransactionDetails();
//
//            } else if (resultModel != null && resultModel.getError() != null) {
//                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
//            } else {
//                Log.d(TAG, "Both objects are null!");
//            }
//        }
//    }

    private void submitPayment() {
        if (!customerType.equals("health")) {
            session = new Session(ServicePayActivity.this);
            String userToken = session.getAccessTokenType() + " " + session.getAccessToken();
            Log.e("userToken", userToken);

            Call<ServicePaymentModel> call = RetrofitClient.getInstance().getApi().servicePayment(userToken, String.valueOf(sellerId), name, email, phone, city, pincode, landmark, address, totalAmt, paymentId, String.valueOf(serviceId));

            call.enqueue(new Callback<ServicePaymentModel>() {
                @Override
                public void onResponse(Call<ServicePaymentModel> call, Response<ServicePaymentModel> response) {
                    if (response.code() == 200) {
                        Toast.makeText(ServicePayActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
//                    balance.setText(response.body().getWallet().getAmount());
//                    Intent i = new Intent(WalletPayment.this,MainActivity.class);
//                    startActivity(i);
                        Intent i = new Intent(ServicePayActivity.this,DashboradActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<ServicePaymentModel> call, Throwable t) {
                    Toast.makeText(ServicePayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            session = new Session(ServicePayActivity.this);
            String userToken = session.getAccessTokenType() + " " + session.getAccessToken();
            Log.e("userToken", userToken);

            Call<ServicePaymentModel> call = RetrofitClient.getInstance().getApi().servicePaymentforHealth(userToken, String.valueOf(sellerId), name, email, phone, city, pincode, landmark, address, totalAmt, paymentId, String.valueOf(serviceId),time,hdate);

            call.enqueue(new Callback<ServicePaymentModel>() {
                @Override
                public void onResponse(Call<ServicePaymentModel> call, Response<ServicePaymentModel> response) {
                    if (response.code() == 200) {
                        Toast.makeText(ServicePayActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
//                    balance.setText(response.body().getWallet().getAmount());
//                    Intent i = new Intent(WalletPayment.this,MainActivity.class);
//                    startActivity(i);
                        Intent i = new Intent(ServicePayActivity.this,DashboradActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<ServicePaymentModel> call, Throwable t) {
                    Toast.makeText(ServicePayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

//
//    public class PayuMoneyResponse {
//        private PayuMoneyResponse.PayuResult result;
//
//        public PayuMoneyResponse.PayuResult getResult() {
//            return result;
//        }
//
//        public void setResult(PayuMoneyResponse.PayuResult result) {
//            this.result = result;
//        }
//
//        public class PayuResult {
//            private String paymentId;
//
//            public String getPaymentId() {
//                return paymentId;
//            }
//
//            public void setPaymentId(String paymentId) {
//                this.paymentId = paymentId;
//            }
//        }
//    }
    // @Override
    public void onPaymentSuccess(String s) {
        // this method is called on payment success.
        submitPayment();
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    // @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}