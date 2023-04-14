package com.webdigital.subbisky.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;

public class LoginWithActivity extends AppCompatActivity {
    Session session;
    String CUSTOMER = "CUSTOMER", SELLER = "SELLER";
    AlertDialog.Builder alertDialog;
    LinearLayout linerLayoutBack;
    private Button btn_btnCusLogin, btnCafe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertDialog = new AlertDialog.Builder(LoginWithActivity.this);
        setContentView(R.layout.activity_login_with);
        session = new Session(LoginWithActivity.this);

        linerLayoutBack = findViewById(R.id.linerLayoutBack);
        btn_btnCusLogin = findViewById(R.id.btnCusLogin);
        btnCafe = findViewById(R.id.btnCafe);

        CheckLoginType();
        linerLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();

            }
        });


        btn_btnCusLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = new Session(LoginWithActivity.this);
                session.createUserTypeSessionToken(CUSTOMER);
                Intent intent = new Intent(LoginWithActivity.this, Login.class);
                intent.putExtra("Type",CUSTOMER);
                startActivity(intent);
//                CheckLoginType();
                finish();
            }
        });
        btnCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session = new Session(LoginWithActivity.this);
                session.createUserTypeSessionToken(SELLER);

                Intent intent = new Intent(LoginWithActivity.this, Login.class);
                intent.putExtra("Type",SELLER);
                startActivity(intent);
//                CheckLoginType();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        alertDialog.setMessage("Do you want to Exit app ?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
                finish();
                //write your code here after click yes
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }

    private void CheckLoginType() {
        if ((session.isLoggedIn())) {
//            if ((session.userType())){
            String userType;
            SharedPreferences prefs = this.getSharedPreferences("your_file_name",
                    MODE_PRIVATE);
            userType= prefs.getString("UserType", "UserType");

//            String userType = session.MainUserType;
                Log.e("userType",userType);
                if (userType.equals(SELLER)){

                    Intent intent = new Intent(LoginWithActivity.this, SellerDashBoardActivity.class);


                    startActivity(intent);
                    finish();
                }else if (userType.equals(CUSTOMER)){
                    Intent intent = new Intent(LoginWithActivity.this, DashboradActivity.class);
                    startActivity(intent);
                    finish();
                }


        }
    }
}