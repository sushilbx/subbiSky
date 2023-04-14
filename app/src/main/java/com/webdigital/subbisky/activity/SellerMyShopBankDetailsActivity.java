package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerBankModel;

public class SellerMyShopBankDetailsActivity extends AppCompatActivity {
    EditText sellerAccountNumber,sellerAccountName,sellerIfsc,sellerUpiId;
    String  sellerAccNo,sellerAccName,sellerIFSC,sellerUPI;
    Button sellerBankUpdate;
    Session session;

    LinearLayout linerLayoutBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_my_shop_bank_details);
        sellerAccountName = findViewById(R.id.sellerAccountName);
        sellerAccountNumber = findViewById(R.id.sellerAccountNumber);
        sellerIfsc = findViewById(R.id.sellerIfsc);
        sellerUpiId = findViewById(R.id.sellerUpiId);
        sellerBankUpdate = findViewById(R.id.sellerBankUpdate);

        linerLayoutBack=findViewById(R.id.linerLayoutBack);
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();
            }
        });
        sellerBankUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellerAccNo = sellerAccountNumber.getEditableText().toString();
                sellerAccName = sellerAccountName.getEditableText().toString();
                sellerIFSC = sellerIfsc.getEditableText().toString();
                sellerUPI = sellerUpiId.getEditableText().toString();
                session = new Session(SellerMyShopBankDetailsActivity.this);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                if (!sellerAccNo.isEmpty() && !sellerAccName.isEmpty() && !sellerIFSC.isEmpty()  ){

                Call<SellerBankModel> call = RetrofitClient.getInstance().getApi().sellerBank(auth,sellerAccNo,sellerAccName,sellerIFSC,sellerUPI);
                call.enqueue(new Callback<SellerBankModel>() {
                    @Override
                    public void onResponse(Call<SellerBankModel> call, Response<SellerBankModel> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(SellerMyShopBankDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(SellerMyShopBankDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        }

                    @Override
                    public void onFailure(Call<SellerBankModel> call, Throwable t) {
                        Toast.makeText(SellerMyShopBankDetailsActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                );
                } else
                {
                    if(sellerAccNo.isEmpty())
                        sellerAccountNumber.setError("This field is required");
                    if (sellerAccName.isEmpty())
                        sellerAccountName.setError("This field is required");
                    if (sellerIFSC.isEmpty())
                        sellerIfsc.setError("This field is required");

                }
            }
        });

    }
}