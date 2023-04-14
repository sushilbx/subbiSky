package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

import com.razorpay.Checkout;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.NoofDaysModel;
import com.webdigital.subbisky.model.ServicePaymentModel;
import com.webdigital.subbisky.model.StayBookingPaymentModel;
import com.webdigital.subbisky.model.StayBookingSearchModel;
import com.webdigital.subbisky.model.UserModel;
import com.webdigital.subbisky.model.ViewAddedRoomsModel;
import com.google.gson.Gson;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.model.NoofDaysModel;
import com.webdigital.subbisky.model.StayBookingPaymentModel;
import com.webdigital.subbisky.model.UserModel;
import com.webdigital.subbisky.model.ViewAddedRoomsModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.webdigital.subbisky.activity.StayBookingDetailsActivity.overalladult;
import static com.webdigital.subbisky.activity.StayBookingDetailsActivity.overallchild;
import static com.webdigital.subbisky.activity.StayBookingDetailsActivity.totaladult;
import static com.webdigital.subbisky.activity.StayBookingDetailsActivity.totalchildren;

import org.json.JSONException;
import org.json.JSONObject;

public class StayBookingPayActivity extends AppCompatActivity {

    String fromDate,toDate,adults,children,room,price, totalAmount,extraadult,extrachildren,noofnights,rooms;
    String name,email,phone,city,state,pincode,address;
    List<String> cityList;
    String[] cityListSpinner;
    int[] cityListTitleId;
    int cListId;
    Spinner CityList;
    TextView stayBookingname,stayBookingnorooms,stayBookingchild,stayBookingphone,stayBookingemail,stayBookingcity,stayBookingstate,stayBookingpincode,stayBookingaddress,staybookingfromDate,staybookingtodate,staybookingadults,staybookingrooms,staybookingtotalAmount,staybookingprice,extraadults,stayBookingchildren;
    String hotelId;
    Intent intent;
    int totalchildrecextracost,totaladultextracost,totalfinalcost,totalroomcost;
    Button stayBookingPayBtn;

    String  totalAmt,SfromDate,StoDate,Sadultsextra,Schildrenextra,Sroom,Sprice,StotalAmount,ShotelId,Sadultsamount,Schildrenamount;

//    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
//    PayUmoneySdkInitializer.PaymentParam paymentParam = null;
//
//    String payUMId = "7455888";
//    String payUKey = "GpUr6trc"; //Production
//    String payUSaltKey = "9BF4Bs5Gxl"; //Production
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
    Date date = new Date();
    String txnId;
    String paymentId = "";

    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_booking_pay);
        getuserData();
        stayBookingname = findViewById(R.id.stayBookingname);
        stayBookingphone = findViewById(R.id.stayBookingPhone);
        stayBookingemail = findViewById(R.id.stayBookingEmail);
        staybookingfromDate = findViewById(R.id.stayBookingfromDate);
        staybookingtodate = findViewById(R.id.stayBookingtoDate);
        staybookingtotalAmount = findViewById(R.id.staybookingtotalamount);
        staybookingadults = findViewById(R.id.stayBookingadults);
       // staybookingchildren = findViewById(R.id.stayBookingchildren);
        extraadults = findViewById(R.id.extraadults);
        stayBookingchildren = findViewById(R.id.stayBookingchildren);
        staybookingrooms = findViewById(R.id.stayBookingrooms);
        CityList = findViewById(R.id.citySpinner);
        stayBookingPayBtn = findViewById(R.id.stayBookingPayBtn);
        stayBookingstate = findViewById(R.id.stayBookingstate);
        stayBookingpincode = findViewById(R.id.stayBookingpincode);
        stayBookingaddress = findViewById(R.id.stayBookingaddress);
        stayBookingcity = findViewById(R.id.stayBookingcity);
        stayBookingchild = findViewById(R.id.stayBookingchild);
        stayBookingnorooms = findViewById(R.id.stayBookingnorooms);

        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });

        txnId = formatter.format(date);
        intent = getIntent();
        hotelId = intent.getStringExtra("hotelId");
        Log.e("hotelId",hotelId);
        fromDate = intent.getStringExtra("fromDate");
        Log.e("fromDate",fromDate);
        toDate = intent.getStringExtra("toDate");
        Log.e("toDate",toDate);
        adults = intent.getStringExtra("adults");
        Log.e("adults",adults);
        children = intent.getStringExtra("children");
        Log.e("children",children);
        room = intent.getStringExtra("room");
        Log.e("room",room);
        totalAmount = intent.getStringExtra("totalAmount");
        Log.e("totalAmount",totalAmount);
        Sadultsextra = intent.getStringExtra("adultsextra");
      //  Log.e("adultsextra",Sadultsextra);
        Schildrenextra = intent.getStringExtra("childrenextra");
       // Log.e("childrenextra",Schildrenextra);

        Sadultsamount = intent.getStringExtra("adultsextraamount");
        Log.e("adultsextraamount",Sadultsamount);

        Schildrenamount = intent.getStringExtra("childrenextraamount");
        Log.e("childrenextraamount",Schildrenamount);

        staybookingfromDate.setText(StaybookroomaddingActivity.sfromdate);
        staybookingtodate.setText(StaybookroomaddingActivity.stodate);
        stayBookingnorooms.setText(String.valueOf(StaybookroomaddingActivity.sroonsize));
                staybookingadults.setText(overalladult);
                stayBookingchild.setText(overallchild);




        //stayBookingchildren.setText(StayBookingDetailsActivity.extrachildcharge);
       // extraadults.setText(StayBookingDetailsActivity.extraadultcharge);


//

//        totalAmount = "4000";
        CityListSpinner();

        CityList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i != 0) {
//                    ecom_id = cityList.get(i).toString();
//                    Log.e("ecomId", ecom_id);
//                } else {
//                    ecom_id = cityList.get(i).toString();
//                    Log.e("ecomId", ecom_id);
//                }
                String selected = adapterView.getItemAtPosition(i).toString();
                for (int k = 0; k < cityListSpinner.length; k++) {
                    if (selected.equals(cityListSpinner[k]))
                        cListId = cityListTitleId[k];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getNumberofDates();
        //viewAddedRooms();

        stayBookingPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = stayBookingname.getEditableText().toString();
                email = stayBookingemail.getEditableText().toString();
                phone = stayBookingphone.getEditableText().toString();
                state = stayBookingstate.getEditableText().toString();
                address = stayBookingaddress.getEditableText().toString();
                pincode = stayBookingpincode.getEditableText().toString();
                city = stayBookingcity.getEditableText().toString();
                //city = String.valueOf(cListId);
                fromDate = staybookingfromDate.getText().toString();
              toDate = staybookingtodate.getText().toString();
               adults = staybookingadults.getText().toString();
               room = staybookingrooms.getText().toString();
//                children ="0";
                //children = stayBookingchildren.getText().toString();
                children = stayBookingchild.getText().toString();
               extraadult = extraadults.getText().toString();
                totalAmt = String.valueOf(totalfinalcost);
             if (!name.isEmpty()&& !email.isEmpty() &&!phone.isEmpty() &&!city.isEmpty() &&!pincode.isEmpty() &&!address.isEmpty() &&!state.isEmpty()){

                 checkout();
                totalAmt = String.valueOf(totalfinalcost);

                }


                if (name.isEmpty()){
                    stayBookingname.setError("Please fill this field!");
                }else if (email.isEmpty()){
                    stayBookingemail.setError("Please fill this field!");
                }else if (phone.isEmpty()){
                    stayBookingphone.setError("Please fill this field!");
                }
                else if (pincode.isEmpty()){
                    stayBookingpincode.setError("Please fill this field!");
                }
                else if (state.isEmpty()){
                    stayBookingstate.setError("Please fill this field!");
                }
                else if (city.isEmpty()){
                    stayBookingcity.setError("Please fill this field!");
                }
                else if (address.isEmpty()){
                    stayBookingaddress.setError("Please fill this field!");
                }

            }
        });

    }

    private void checkout() {
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
            checkout.open(StayBookingPayActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getuserData() {
        Session session = new Session(StayBookingPayActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<UserModel> call = RetrofitClient.getInstance().getApi().userModel(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    stayBookingname.setText(response.body().getUser().getName());
                    stayBookingemail.setText(response.body().getUser().getEmail());
                    stayBookingphone.setText(response.body().getUser().getPhone());
                  //  stayBookingcity.setText(response.body().getUser().getCity_id());
//                    serviceCheckoutLandmark.setText(response.body().getUser().getLandmark());
//                    serviceCheckoutPincode.setText(response.body().getUser().getPincode());
//                    serviceCheckoutAddress.setText(response.body().getUser().getAddress());
//                    serviceCheckoutAmount.setText(amount);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void viewAddedRooms() {
//        Session session = new Session(StayBookingPayActivity.this);
//        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//
//        Call<ViewAddedRoomsModel> call = RetrofitClient.getInstance().getApi().viewAddedRooms(auth, String.valueOf(57));
//        call.enqueue(new Callback<ViewAddedRoomsModel>() {
//            @Override
//            public void onResponse(Call<ViewAddedRoomsModel> call, Response<ViewAddedRoomsModel> response) {
//                if (response.isSuccessful()){
//                    int i = 0;
//                    if (response.body().getDetails().size()!=0) {
//                        extraadult = response.body().getDetails().get(i).getAdult();
//                        extrachildren = response.body().getDetails().get(i).getChildren();
//                        extraadults.setText(response.body().getDetails().get(i).getAdult() + " * "+ Sadultsextra+" * "+Sadultsamount);
//                        stayBookingchildren.setText(response.body().getDetails().get(i).getChildren() + " * "+Schildrenextra+" * " +Schildrenamount);
//
////                    }
//                    }
//                }else {
////                    Toast.makeText(StayBookingPayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    extraadults.setText( " 0 * "+ Sadultsextra+" * 400");
//                    stayBookingchildren.setText( "0 * "+Schildrenextra+"  * 400");
////                    extraadult ="0";
////                    extrachildren ="0";
////
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ViewAddedRoomsModel> call, Throwable t) {
//                Toast.makeText(StayBookingPayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void getNumberofDates() {
        String fromDate = staybookingfromDate.getText().toString();
        String toDate = staybookingtodate.getText().toString();
        Session session = new Session(StayBookingPayActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<NoofDaysModel> call = RetrofitClient.getInstance().getApi().noofDays(auth,fromDate,toDate);
        call.enqueue(new Callback<NoofDaysModel>() {
            @Override
            public void onResponse(Call<NoofDaysModel> call, Response<NoofDaysModel> response) {
                if (response.isSuccessful()){
                    staybookingrooms.setText(response.body().getDays()+" * "+totalAmount);
                    noofnights = response.body().getDays();

                    extraadults.setText( noofnights+ " * "+ totaladult+" * "+Sadultsamount);
                    stayBookingchildren.setText(noofnights + " * "+totalchildren+" * " +Schildrenamount);

                    totalchildrecextracost = Integer.parseInt(noofnights)*Integer.parseInt(totalchildren)*Integer.parseInt(Schildrenamount);
                    totaladultextracost = Integer.parseInt(noofnights)*Integer.parseInt(totaladult)*Integer.parseInt(Sadultsamount);
                    totalroomcost=Integer.parseInt(noofnights)*Integer.parseInt(totalAmount);
                    totalfinalcost=totalchildrecextracost+totaladultextracost+totalroomcost;
                    staybookingtotalAmount.setText("Rs: "+totalfinalcost);

                    Toast.makeText(StayBookingPayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

//                    }
                }else {
                   // Toast.makeText(StayBookingPayActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<NoofDaysModel> call, Throwable t) {
                Toast.makeText(StayBookingPayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void CityListSpinner() {

        Session session = new Session(StayBookingPayActivity.this);
//        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//
//        Log.e("Bearer ",auth);
//        HashMap<String, String> user = session.getUserDetails();
        Call<CityListModel> call = RetrofitClient.getInstance().getApi().cityList();
        call.enqueue(new Callback<CityListModel>() {
            @Override
            public void onResponse(Call<CityListModel> call, Response<CityListModel> response) {
                if (response.isSuccessful()) {
//                    progressDialog.dismiss();
                    if (response.body().getCities().size() != 0) {
                        int k = 0;
                        cityList = new ArrayList<>();
                        cityListSpinner = new String[response.body().getCities().size()];
                        cityListTitleId = new int[response.body().getCities().size()];
                        cListId = response.body().getCities().get(0).getId();
                        for (int i = 0; i < response.body().getCities().size(); i++) {
                            cityListSpinner[i] = response.body().getCities().get(i).getName();
                            if (response.body().getStatusCode() == 200) {
                                cityList.add(response.body().getCities().get(i).getName());
                                Log.d("TAG", "onResponse: " + response.body().getCities().get(i).getName());
                                cityListTitleId[k] = response.body().getCities().get(i).getId();
                                k++;
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(StayBookingPayActivity.this, android.R.layout.simple_spinner_item, cityList);
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


//    ///////////////////////////////////////////paymentgateway////////////////////////////////
//    private void startPayment(double pamount) {
//        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
//
//        String payAmt = "Book Hotel";
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
//            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, StayBookingPayActivity.this, R.style.AppTheme, true);
//
//        } catch (Exception e) {
//            // some exception occurred
//            Toast.makeText(StayBookingPayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
//
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
//                    Toast.makeText(StayBookingPayActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
//                    Gson g = new Gson();
//                   PayuMoneyResponse payuMoneyResponse = g.fromJson(transactionResponse.getPayuResponse(), PayuMoneyResponse.class);
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
        Session session = new Session(StayBookingPayActivity.this);
            String userToken = session.getAccessTokenType() + " " + session.getAccessToken();
            Log.e("userToken", userToken);

            Call<StayBookingPaymentModel> call = RetrofitClient.getInstance().getApi().stayBookingPayment(userToken, String.valueOf(5), name, email, phone, city,state,pincode,address, totalAmount, paymentId, fromDate,toDate,noofnights,adults,children,room,extrachildren,extraadult);

            call.enqueue(new Callback<StayBookingPaymentModel>() {
                @Override
                public void onResponse(Call<StayBookingPaymentModel> call, Response<StayBookingPaymentModel> response) {
                    if (response.code() == 200) {
                        Toast.makeText(StayBookingPayActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
//                    balance.setText(response.body().getWallet().getAmount());
//                    Intent i = new Intent(WalletPayment.this,MainActivity.class);
//                    startActivity(i);
                        Intent i = new Intent(StayBookingPayActivity.this,DashboradActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<StayBookingPaymentModel> call, Throwable t) {
                    Toast.makeText(StayBookingPayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


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