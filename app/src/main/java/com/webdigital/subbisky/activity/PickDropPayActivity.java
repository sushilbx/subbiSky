package com.webdigital.subbisky.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.DistanceCalcModel;
import com.webdigital.subbisky.model.UserModel;
import com.webdigital.subbisky.model.PickupPaymentModel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;



import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PickDropPayActivity extends AppCompatActivity {
    Session session;
    LinearLayout linerLayoutBack;
    TextView pdAmountperKm,pdDistance,pdtotalAmount;
    EditText pdCheckoutName,pdCheckoutEmail,pdCheckoutPhone,pdDeliveryNum,pdPickUpAddress,pdDropAddress,pdQuantity;
    String name,email,phone,delnumber,pickupadd,dropadd,quantity,distance,totalAmt;
    Button pdCheckoutBtn,pdCalcDistance;
    String customerType,amount;
    long delay = 5000; // 1 seconds after user stops typing
    long last_text_edit = 0;
    LatLng p1 = null;
    LatLng picAddress, dropAddress, from, to;
    int i =0;
    int calcted,calc ;
    Handler handler = new Handler();
    int sellerId,serviceId;


    //PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
   // PayUmoneySdkInitializer.PaymentParam paymentParam = null;

    String payUMId = "7440824";
    String payUKey = "lRPTChYM"; //Production
    String payUSaltKey = "Sbvdj9epnT"; //Production
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
    Date date = new Date();
    String txnId;
    String paymentId = "";
    String pickUpLat,pickUpLong,dropLat,dropLong;

//    String totalAmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_drop_pay);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        pdAmountperKm=findViewById(R.id.pdAmountperKm);
        pdDistance=findViewById(R.id.pdDistance);
        pdtotalAmount=findViewById(R.id.pdtotalAmount);
        pdCheckoutName=findViewById(R.id.pdName);
        pdCheckoutEmail=findViewById(R.id.pdEmail);
        pdCheckoutPhone=findViewById(R.id.pdPhone);
        pdDeliveryNum=findViewById(R.id.pdDeliveryNum);
        pdPickUpAddress=findViewById(R.id.pdPickupAddress);
        pdDropAddress=findViewById(R.id.pdDropAddress);
        pdQuantity=findViewById(R.id.pdQuantity);
        pdCheckoutBtn=findViewById(R.id.pdCheckoutBtn);
        pdCalcDistance=findViewById(R.id.pdCalcDistance);
        Intent intent = getIntent();
        customerType = intent.getStringExtra("customerType");
        amount = intent.getStringExtra("amount");

        sellerId = intent.getIntExtra("sellerId",0);
        serviceId = intent.getIntExtra("serviceId",0);
        txnId = formatter.format(date);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        getPdData();

        Places.initialize(PickDropPayActivity.this,"AIzaSyATk5VvnPJoy8U5Aw71ArL5vqGIj6fLIHI");
        pdPickUpAddress.setFocusable(false);
        pdDropAddress.setFocusable(false);
        ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            // your operation....
                            Place place = Autocomplete.getPlaceFromIntent(data);
                            Log.e("pic_ address", place.getAddress());
                            pdPickUpAddress.setText((place.getAddress()));

//            from = place.getLatLng();
//                            fromLat = from.latitude;
//                            fromLag = from.longitude;
//            Log.e("from", String.valueOf(from));
//            Log.e("fromLat", String.valueOf(fromLat));
//            Log.e("fromLag", String.valueOf(fromLag));

                        } else if (result.getResultCode() == AutocompleteActivity.RESULT_ERROR) {
                            Status status = Autocomplete.getStatusFromIntent(data);
                            Toast.makeText(PickDropPayActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        pdPickUpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG,
                        Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        , fieldList).build(PickDropPayActivity.this);
                launchSomeActivity.launch(intent);
//               startActivityForResult(intent,100);
            }
        });
        ActivityResultLauncher<Intent> launchSomeActivity1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            // your operation....
                            Place place = Autocomplete.getPlaceFromIntent(data);
                            Log.e("drop_ address", place.getAddress());
                            pdDropAddress.setText((place.getAddress()));

//            from = place.getLatLng();
//                            fromLat = from.latitude;
//                            fromLag = from.longitude;
//            Log.e("from", String.valueOf(from));
//            Log.e("fromLat", String.valueOf(fromLat));
//            Log.e("fromLag", String.valueOf(fromLag));

                        } else if (result.getResultCode() == AutocompleteActivity.RESULT_ERROR) {
                            Status status = Autocomplete.getStatusFromIntent(data);
                            Toast.makeText(PickDropPayActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        pdDropAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG,
                        Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        , fieldList).build(PickDropPayActivity.this);
                launchSomeActivity1.launch(intent);
//               startActivityForResult(intent,100);
            }
        });
        pdPickUpAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                //You need to remove this to run only once
                handler.removeCallbacks(pickup_input_checker);
            }

            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is empty
                if (s.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(pickup_input_checker, delay);
                }
            }
        });

        pdDropAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                //You need to remove this to run only once
                handler.removeCallbacks(delivery_input_checker);
            }

            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is empty
                if (s.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(delivery_input_checker, delay);
                }
            }
        });

        pdCalcDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String padd = pdPickUpAddress.getEditableText().toString();
                String dadd = pdDropAddress.getEditableText().toString();
                Log.e("padd",padd);
                Log.e("dadd",dadd);
                session = new Session(PickDropPayActivity.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<DistanceCalcModel> call = RetrofitClient.getInstance().getApi().distanceCalc(auth,padd,dadd);
                call.enqueue(new Callback<DistanceCalcModel>() {
                    @Override
                    public void onResponse(Call<DistanceCalcModel> call, Response<DistanceCalcModel> response) {
                        if (response.isSuccessful()) {
//                            Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            pdDistance.setText(String.valueOf((response.body().getDistanceMeter()/1000)+" km"));
                            calcted = response.body().getDistanceMeter();
                            Log.e("calcted", String.valueOf(calcted));
                            calc = (calcted/1000)*Integer.parseInt(amount);
                            Log.e("calc", String.valueOf(calc));
                            pdtotalAmount.setText("Rs :" +String.valueOf(calc));
//                            totalAmt = pdtotalAmount.getText().toString();
//                            Log.e("totalAmt",totalAmt);
                        }
                    }

                    @Override
                    public void onFailure(Call<DistanceCalcModel> call, Throwable throwable) {
                        Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        pdCheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               name = pdCheckoutName.getEditableText().toString();
               email = pdCheckoutEmail.getEditableText().toString();
               phone = pdCheckoutPhone.getEditableText().toString();
               delnumber = pdDeliveryNum.getEditableText().toString();
               pickupadd = pdPickUpAddress.getEditableText().toString();
                dropadd = pdDropAddress.getEditableText().toString();
               distance = String.valueOf(calcted/1000);
               totalAmt = String.valueOf(calc);
               quantity = pdQuantity.getEditableText().toString();
               if (!name.isEmpty()&& !email.isEmpty()&& !phone.isEmpty()&& !delnumber.isEmpty()&& !pickupadd.isEmpty()&& !dropadd.isEmpty()&& !distance.isEmpty()&& !totalAmt.isEmpty() ){

                   if (!name.isEmpty()) {

                       submitPayment();
                   }
                   else {
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
                           checkout.open(PickDropPayActivity.this, object);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }

               }


              else  {
                   if (name.isEmpty()){
                       pdCheckoutName.setError("Please fill this field!");
                   }else if (email.isEmpty()){
                       pdCheckoutEmail.setError("Please fill this field!");
                   }else if (phone.isEmpty()){
                       pdCheckoutPhone.setError("Please fill this field!");
                   }else if (delnumber.isEmpty()){
                       pdDeliveryNum.setError("Please fill this field!");
                   }else if (pickupadd.isEmpty()){
                       pdPickUpAddress.setError("Please fill this field!");
                   }else if (dropadd.isEmpty()){
                       pdDropAddress.setError("Please fill this field!");
                   }else if (distance.isEmpty()){
                       pdDistance.setError("Please fill this field!");
                   }else if (totalAmt.isEmpty()){
                       pdtotalAmount.setError("Please fill this field!");
                   }
               }
            }
        });
    }

    private final Runnable pickup_input_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                picAddress = getLocationFromAddress(PickDropPayActivity.this, pdPickUpAddress.getText().toString().trim());
//                if (points1 != null) {
//                    s_latitude1 = String.valueOf(points1.latitude);
//                    s_longitude1 = String.valueOf(points1.longitude);
//                    if (points2 != null) {
//                        getDistance(String.valueOf(points1.latitude), String.valueOf(points1.longitude), String.valueOf(points2.latitude), String.valueOf(points2.longitude));
////                        actualDistance = String.format("%.2f", SphericalUtil.computeDistanceBetween(points1, points2) / 1000);
////                        distanceTxt.setText(actualDistance + " Km");
//                    }
//                }
            }
        }
    };

    private final Runnable delivery_input_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                dropAddress = getLocationFromAddress(PickDropPayActivity.this, pdDropAddress.getText().toString().trim());
//                if (points2 != null) {
//                    s_latitude2 = String.valueOf(points2.latitude);
//                    s_longitude2 = String.valueOf(points2.longitude);
//                    if (points1 != null) {
//                        getDistance(String.valueOf(points1.latitude), String.valueOf(points1.longitude), String.valueOf(points2.latitude), String.valueOf(points2.longitude));
////                        actualDistance = String.format("%.2f", SphericalUtil.computeDistanceBetween(points1, points2) / 1000);
////                        distanceTxt.setText(actualDistance + " Km");
//                    }
//                }
            }
        }
    };

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;


        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            } else {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return p1;
    }

    private void getPdData() {
        session = new Session(PickDropPayActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

        Call<UserModel> call = RetrofitClient.getInstance().getApi().userModel(auth);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText( getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pdCheckoutName.setText(response.body().getUser().getName());
                    pdCheckoutEmail.setText(response.body().getUser().getEmail());
                    pdCheckoutPhone.setText(response.body().getUser().getPhone());
//                    pdQuantity.setText(response.body().getUser().getLandmark());
//                    serviceCheckoutPincode.setText(response.body().getUser().getPincode());
//                    serviceCheckoutAddress.setText(response.body().getUser().getAddress());
                    pdAmountperKm.setText(amount);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Toast.makeText( getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    ///////////////////////////////////////////paymentgateway////////////////////////////////
//    private void startPayment(double pamount) {
//        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
//
//        String payAmt = "Book Delivery";
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
//            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, PickDropPayActivity.this, R.style.AppTheme, true);
//
//        } catch (Exception e) {
//            // some exception occurred
//            Toast.makeText(PickDropPayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
//                    Toast.makeText(PickDropPayActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
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
////               Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
//            } else {
////                Log.d(TAG, "Both objects are null!");
//            }
//        }
//    }

    private void submitPayment() {
        pickUpLat = "0";
        pickUpLong = "0";
        dropLat = "0";
        dropLong = "0";
            session = new Session(PickDropPayActivity.this);
            String userToken = session.getAccessTokenType() + " " + session.getAccessToken();
            Log.e("userToken", userToken);

            Call<PickupPaymentModel> call = RetrofitClient.getInstance().getApi().pickupPayment(userToken, quantity,totalAmt,distance,delnumber,String.valueOf(sellerId),name,email,phone,paymentId,pickupadd,dropadd,pickUpLat,pickUpLong,dropLat,dropLong,String.valueOf(serviceId));

            call.enqueue(new Callback<PickupPaymentModel>() {
                @Override
                public void onResponse(Call<PickupPaymentModel> call, Response<PickupPaymentModel> response) {
                    if (response.code() == 200) {
                        Toast.makeText(PickDropPayActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
//                    balance.setText(response.body().getWallet().getAmount());
//                    Intent i = new Intent(WalletPayment.this,MainActivity.class);
//                    startActivity(i);
                        Intent i = new Intent(PickDropPayActivity.this,DashboradActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<PickupPaymentModel> call, Throwable t) {
                    Toast.makeText(PickDropPayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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