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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CityListModel;
import com.webdigital.subbisky.model.DeliveryChargeModel;
import com.webdigital.subbisky.model.EcomPlaceOrderModel;
import com.webdigital.subbisky.model.ServicePaymentModel;
import com.webdigital.subbisky.model.UserModel;
import com.google.gson.Gson;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.model.DeliveryChargeModel;
import com.webdigital.subbisky.model.EcomPlaceOrderModel;
import com.webdigital.subbisky.model.UserModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

import org.json.JSONException;
import org.json.JSONObject;

public class EcomProductCheckOutPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    LinearLayout linerLayoutBack,deliveryVisible;
    TextView ecomCheckoutdate, ecomCheckouttime, ecomCheckoutAmount, tdate, ttime, deliveryCharge;
    EditText ecomCheckoutName, ecomCheckoutEmail, ecomCheckoutPhone, ecomCheckoutPincode, ecomCheckoutLandmark, ecomCheckoutAddress,ecomCheckoutcity;
    Button ecomCheckoutBtn;
    int deliverychargeammount=0,amount=0;
    String customerType,  sellerId;

    List<String> cityList;
    String[] cityListSpinner;
    int[] cityListTitleId;
    int cListId;
    Spinner CityList, ecomCheckoutType;
    Session session,session1;
    String cityId, type = "";
    String takeAway = "", payment_mode = "";
    RadioGroup payment_type;
    RadioButton onlineRadioBtn, offlineRadioBtn;
    String name, email, phone, pincode, landmark, address, city, totalAmt;


   // PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
   // PayUmoneySdkInitializer.PaymentParam paymentParam = null;

    ////String payUMId = "7455888";//7440824
    //String payUKey = "GpUr6trc"; //Production//lRPTChYM
    //String payUSaltKey = "9BF4Bs5Gxl"; //Production//Sbvdj9epnT
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
    Date date = new Date();
    String txnId;
    String paymentId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecom_product_check_out_page);
        deliveryVisible = findViewById(R.id.deliveryVisible);
        CityList = findViewById(R.id.ecomCheckoutCity);
        deliveryCharge = findViewById(R.id.deliveryCharge);
        ecomCheckoutType = findViewById(R.id.ecomCheckoutType);
        ecomCheckoutName = findViewById(R.id.ecomCheckoutName);
        ecomCheckoutEmail = findViewById(R.id.ecomCheckoutEmail);
        ecomCheckoutPhone = findViewById(R.id.ecomCheckoutPhone);
        ecomCheckoutPincode = findViewById(R.id.ecomCheckoutPincode);
        ecomCheckoutLandmark = findViewById(R.id.ecomCheckoutLandmark);
        ecomCheckoutAddress = findViewById(R.id.ecomCheckoutAddress);
        ecomCheckoutcity = findViewById(R.id.ecomCheckoutcity);
        ecomCheckoutAmount = findViewById(R.id.ecomCheckoutAmount);
        ecomCheckoutBtn = findViewById(R.id.ecomCheckoutBtn);
        payment_type = findViewById(R.id.payment_type);
        onlineRadioBtn = findViewById(R.id.onlineRadioBtn);
        offlineRadioBtn = findViewById(R.id.offlineRadioBtn);
        getecomData();
        getDeliveryCharge();

       // deliveryVisible.setVisibility(View.GONE);
       // CityListSpinner();

        getecomData();
        getDeliveryCharge();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.EcomCheckoutType, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ecomCheckoutType.setAdapter(adapter);
        ecomCheckoutType.setOnItemSelectedListener(this);
        Intent intent = getIntent();
        amount = Integer.valueOf(intent.getStringExtra("amount"));
        sellerId = intent.getStringExtra("sellerId");
        Log.e("sellerId", sellerId);
        txnId = formatter.format(date);


        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        CityList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                if (i != 0) {
////                    ecom_id = cityList.get(i).toString();
////                    Log.e("ecomId", ecom_id);
////                } else {
////                    ecom_id = cityList.get(i).toString();
////                    Log.e("ecomId", ecom_id);
////                }
//                String selected = adapterView.getItemAtPosition(i).toString();
//                for (int k = 0; k < cityListSpinner.length; k++) {
//                    if (selected.equals(cityListSpinner[k]))
//                        cListId = cityListTitleId[k];
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        payment_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.onlineRadioBtn) {
                    payment_mode = "Online";
                    Log.e("payment_mode", payment_mode);
                } else if (checkedId == R.id.offlineRadioBtn) {
                    payment_mode = "Offline";
                    Log.e("payment_mode", payment_mode);
                }
            }
        });

        ecomCheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               name = ecomCheckoutName.getEditableText().toString();
                email = ecomCheckoutEmail.getEditableText().toString();
               phone = ecomCheckoutPhone.getEditableText().toString();
                pincode = ecomCheckoutPincode.getEditableText().toString();
                landmark = ecomCheckoutLandmark.getEditableText().toString();
                address = ecomCheckoutAddress.getEditableText().toString();
                city = ecomCheckoutcity.getEditableText().toString();
                //city = String.valueOf(cListId);
              // time = serviceCheckouttime.getEditableText().toString();
                totalAmt = String.valueOf(amount);
                if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !pincode.isEmpty() && !landmark.isEmpty() && !address.isEmpty() && !totalAmt.isEmpty() && !city.isEmpty()) {
                    if (payment_mode.equals("Offline")) {
                        paymentId = UUID.randomUUID().toString().substring(0, 5);
                        Log.e("paymentID", paymentId);
                        Log.e("takeAway", takeAway);

                        submitPayment();

                    }
                    else {
                        checkout();
                    }

                } else {
                    if (name.isEmpty()) {
                        ecomCheckoutName.setError("Please fill this field!");
                    } else if (email.isEmpty()) {
                        ecomCheckoutEmail.setError("Please fill this field!");
                    } else if (phone.isEmpty()) {
                        ecomCheckoutPhone.setError("Please fill this field!");
                    } else if (pincode.isEmpty()) {
                        ecomCheckoutPincode.setError("Please fill this field!");
                    }
                    else if (city.isEmpty()) {
                        ecomCheckoutcity.setError("Please fill this field!");
                    }else if (landmark.isEmpty()) {
                        ecomCheckoutLandmark.setError("Please fill this field!");
                    } else if (address.isEmpty()) {
                        ecomCheckoutAddress.setError("Please fill this field!");
                    } else if (totalAmt.isEmpty()) {
                        ecomCheckoutAmount.setError("Please fill this field!");
                    }
                }


           }
        });

        ecomCheckoutType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Delivery"))
                {
                    deliveryVisible.setVisibility(View.VISIBLE);
                    amount=amount+deliverychargeammount;
                    ecomCheckoutAmount.setText("Rs: " + amount);

                }
                else{
                    deliveryVisible.setVisibility(View.GONE);
                    amount=amount-deliverychargeammount;
                    ecomCheckoutAmount.setText("Rs: " + amount);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

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
            checkout.open(EcomProductCheckOutPage.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getDeliveryCharge() {
        session = new Session(EcomProductCheckOutPage.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<DeliveryChargeModel> call = RetrofitClient.getInstance().getApi().deliveryCharge(auth, sellerId);
        call.enqueue(new Callback<DeliveryChargeModel>() {
            @Override
            public void onResponse(Call<DeliveryChargeModel> call, Response<DeliveryChargeModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getMessage().equals("Enabled")) {
                        deliveryVisible.setVisibility(View.VISIBLE);
                        deliveryCharge.setText(response.body().getCharge());
                        takeAway = "Yes";
                        deliverychargeammount=Integer.valueOf(response.body().getCharge());
                        amount=amount+deliverychargeammount;
                        ecomCheckoutAmount.setText("Rs: " + amount);
                        Log.e("takeAway1", takeAway);
                    } else {
                        takeAway = "No";
                        Log.e("takeAway1", takeAway);
                        deliveryVisible.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DeliveryChargeModel> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getecomData() {
        session = new Session(EcomProductCheckOutPage.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<UserModel> call = RetrofitClient.getInstance().getApi().userModel(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    ecomCheckoutName.setText(response.body().getUser().getName());
                    ecomCheckoutEmail.setText(response.body().getUser().getEmail());
                    ecomCheckoutPhone.setText(response.body().getUser().getPhone());
                    ecomCheckoutLandmark.setText(response.body().getUser().getLandmark());
                    ecomCheckoutPincode.setText(response.body().getUser().getPincode());
                    ecomCheckoutAddress.setText(response.body().getUser().getAddress());
                   // ecomCheckoutcity.setText(response.body().getUser().getCity_id());
                    ecomCheckoutAmount.setText("Rs: " + amount);

//                    getDeliveryCharge();
                    session1 = new Session(EcomProductCheckOutPage.this);
                    String auth1 = "Bearer " + session1.getUserDetails().get(Session.TOKEN);

                    Log.e("sellerId",sellerId);
                    Call<DeliveryChargeModel> call1 = RetrofitClient.getInstance().getApi().deliveryCharge(auth1,sellerId);
                    call1.enqueue(new Callback<DeliveryChargeModel>() {
                        @Override
                        public void onResponse(Call<DeliveryChargeModel> call, Response<DeliveryChargeModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                if (response.body().getMessage().equals("Enabled")) {
                                    deliveryCharge.setVisibility(View.VISIBLE);
                                    deliveryCharge.setText(response.body().getCharge());
                                    takeAway = "Yes";

                                    Log.e("takeAway1", takeAway);
                                } else {
                                    takeAway = "No";
                                    Log.e("takeAway1", takeAway);
                                    deliveryCharge.setVisibility(View.GONE);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DeliveryChargeModel> call, Throwable throwable) {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void CityListSpinner() {

        Session session = new Session(EcomProductCheckOutPage.this);
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
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EcomProductCheckOutPage.this, android.R.layout.simple_spinner_item, cityList);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
//                .setIsDebug(false)
//                .setKey(payUKey)
//                .setMerchantId(payUMId);
//
//        try {
//            PayUmoneySdkInitializer.PaymentParam mPaymentParams = builder.build();
//
//            mPaymentParams = calculateServerSideHashAndInitiatePayment1(mPaymentParams);
//
//            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, EcomProductCheckOutPage.this, R.style.AppTheme, true);
//
//        } catch (Exception e) {
//            // some exception occurred
//            Toast.makeText(EcomProductCheckOutPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//    }

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
//                    Toast.makeText(EcomProductCheckOutPage.this, "Payment Successful", Toast.LENGTH_SHORT).show();
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

        Log.e("takeAway", takeAway);
        Log.e("payment_mode", payment_mode);
        session = new Session(EcomProductCheckOutPage.this);
        String userToken = session.getAccessTokenType() + " " + session.getAccessToken();
        Log.e("userToken", userToken);

        Call<EcomPlaceOrderModel> call = RetrofitClient.getInstance().getApi().placeOrder(userToken, String.valueOf(sellerId), name, phone, email, city, pincode, landmark, address, totalAmt, paymentId, payment_mode, takeAway);

        call.enqueue(new Callback<EcomPlaceOrderModel>() {
            @Override
            public void onResponse(Call<EcomPlaceOrderModel> call, Response<EcomPlaceOrderModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(EcomProductCheckOutPage.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
//                    balance.setText(response.body().getWallet().getAmount());
                    Intent i = new Intent(EcomProductCheckOutPage.this,DashboradActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<EcomPlaceOrderModel> call, Throwable t) {
                Toast.makeText(EcomProductCheckOutPage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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