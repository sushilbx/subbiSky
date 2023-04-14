package com.webdigital.subbisky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerDashboardModel;
import com.google.android.material.navigation.NavigationView;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerDashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    AlertDialog.Builder alertDialog;
    RecyclerView sellerDashBoardRecycler;

    android.app.AlertDialog dialog;
    android.app.AlertDialog.Builder builder;
    Boolean internet=false;
    TextView total_products,totalproductText,total_activeProducts,totalActiveProductText,total_InactiveProducts,totalInactiveProductText,total_categories,totalCategoriesText,total_orders,totalOrdertext;
    String type = "";
   public static String usertype="";
    LinearLayout hideforStayBooking;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        alertDialog = new AlertDialog.Builder(SellerDashBoardActivity .this);
        setContentView(R.layout.activity_seller_dash_board);
        total_products=findViewById(R.id.total_products);
        total_activeProducts=findViewById(R.id.total_activeProducts);
        total_InactiveProducts=findViewById(R.id.total_InactiveProducts);
        total_categories=findViewById(R.id.total_categories);
        total_orders=findViewById(R.id.total_orders);
        totalproductText=findViewById(R.id.totalProductText);
        totalActiveProductText=findViewById(R.id.totalActiveProductText);
        totalInactiveProductText=findViewById(R.id.totalIncativeProductText);
        totalCategoriesText=findViewById(R.id.totalCategoriesText);
        totalOrdertext=findViewById(R.id.totalOrderText);
        hideforStayBooking=findViewById(R.id.hideforStayBooking);

        hideforStayBooking.setVisibility(View.GONE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create an alert builder
        builder = new android.app.AlertDialog.Builder(this);
        showAlertDialog();
        internetcheck();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.seller_nav_home);
        setNavigationViewAccordingToUserType(navigationView);
////        View headerView = navigationView.getHeaderView(0);
//        //TextView navUsername = (TextView) headerView.findViewById(R.id.textView);
//        displaySelectedScreen(R.id.dashboard);
       /* expandableListView = findViewById(R.id.expandableListView);
//        prepareMenuData();
//        populateExpandableList();*/
//
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Home");
        Session session = new Session(SellerDashBoardActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Log.e("authmyshop",auth);

        Call<SellerDashboardModel> call = RetrofitClient.getInstance().getApi().sellerDashboard(auth);
        call.enqueue(new Callback<SellerDashboardModel>() {
            @Override
            public void onResponse(Call<SellerDashboardModel> call, Response<SellerDashboardModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getType().equals("ecom")){
                        total_products.setText(response.body().getTotalProduct());
                        total_activeProducts.setText(response.body().getActiveProduct());
                        total_InactiveProducts.setText(response.body().getInactiveProduct());
                        total_categories.setText(response.body().getTotalCategories());
                        total_orders.setText(response.body().getTotalOrders());

                        hideforStayBooking.setVisibility(View.VISIBLE);
                        totalproductText.setText("Total Products");
                        totalActiveProductText.setText("Total Active Products");
                        totalInactiveProductText.setText("Total Inactive Products");
                        totalCategoriesText.setText("Total Categories");
                        totalOrdertext.setText("Total Orders");
                        Toast.makeText(SellerDashBoardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(response.body().getType().equals("staybooking")){
                        total_products.setText(response.body().getTotalService());
                        total_activeProducts.setText(response.body().getActiveService());
                        total_InactiveProducts.setText(response.body().getInactiveService());
//                        total_categories.setText(response.body().getTotalCategories());
                        total_orders.setText(response.body().getTotalOrders());
                        hideforStayBooking.setVisibility(View.GONE);

                        totalproductText.setText("Total Services");
                        totalActiveProductText.setText("Total Active Services");
                        totalInactiveProductText.setText("Total Inactive Services");
//                        totalCategoriesText.setText("Total Categories");
                        totalOrdertext.setText("Total Orders");

                        hideforStayBooking.setVisibility(View.GONE);
                        Toast.makeText(SellerDashBoardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }else {
                    //Toast.makeText(SellerDashBoardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SellerDashboardModel> call, Throwable t) {
                Toast.makeText(SellerDashBoardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean internetcheck() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if ( connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected() )
        {
            internet=true;
            if (dialog.isShowing()){

                dialog.dismiss();
                Intent intent = new Intent(SellerDashBoardActivity.this, SellerDashBoardActivity.class);
                startActivity(intent);
            }


            // Toast.makeText(DrawerActivity.this, "Internet Available", Toast.LENGTH_SHORT).show();
        }

        else
        {
            internet=false;
            dialog.show();
            // Toast.makeText(DrawerActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }
        return internet;
    }
    public void showAlertDialog()
    {


        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.internetaleart,
                        null);
        builder.setView(customLayout);
        TextView retry
                = customLayout
                .findViewById(
                        R.id.retry);


        // create and show
        // the alert dialog
        dialog = builder.create();
        dialog.setCancelable(false);
        //  dialog.show();
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internetcheck();
            }
        });
    }
    @Override
    public void onBackPressed() {
        alertDialog.setMessage("Do you want to Exit app ?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
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



    private void setNavigationViewAccordingToUserType(NavigationView navigationView) {
        Session session = new Session(SellerDashBoardActivity.this);
        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
        Log.e("authmyshop",auth);

        Call<SellerDashboardModel> call = RetrofitClient.getInstance().getApi().sellerDashboard(auth);
        call.enqueue(new Callback<SellerDashboardModel>() {
            @Override
            public void onResponse(Call<SellerDashboardModel> call, Response<SellerDashboardModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getType().equals("ecom")) {
                        usertype="ecom";

                        navigationView.getMenu().getItem(2).setVisible(false);
//                        navigationView.getMenu().getItem(5).setVisible(false);
//                        navigationView.getMenu().getItem(3).setVisible(false);
                    } else if (response.body().getType().equals("staybooking")) {
                        usertype="staybooking";

                        navigationView.getMenu().getItem(3).setVisible(false);
                        navigationView.getMenu().getItem(4).setVisible(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<SellerDashboardModel> call, Throwable t) {

            }
        });
    }

    private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.dashboard:

                if( internetcheck()==true){

                    Intent intent1 = new Intent(SellerDashBoardActivity.this,SellerDashBoardActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.myshop:
                if( internetcheck()==true) {
                    Intent intent2 = new Intent(SellerDashBoardActivity.this, SellerMyShopActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.allhotel:
                if( internetcheck()==true){
                Intent intent3 = new Intent(SellerDashBoardActivity.this,SellerHotelActivity.class);
                startActivity(intent3);}
                break;
            case R.id.categories:
                if( internetcheck()==true){
                Intent intentcategories = new Intent(SellerDashBoardActivity.this,SellerCategoryActivity.class);
                startActivity(intentcategories);}
                break;
            case R.id.order:
                if( internetcheck()==true){
                Intent intent4 = new Intent(SellerDashBoardActivity.this,SellerOrderActivity.class);
                startActivity(intent4);}
                break;
            case R.id.wallet:
                if( internetcheck()==true){
                Intent intent5 = new Intent(SellerDashBoardActivity.this,SellerWalletActivity.class);
                startActivity(intent5);}
                break;
            case R.id.quotes:
                if( internetcheck()==true){
                Intent intent6 = new Intent(SellerDashBoardActivity.this,SellerQuotesActivity.class);
                startActivity(intent6);}
                break;
            case R.id.logout:
                if( internetcheck()==true){
                Intent intent7 = new Intent(SellerDashBoardActivity.this,LogoutActivity.class);
                startActivity(intent7);}
                break;
            case R.id.products:
                if( internetcheck()==true) {
                    Intent intent8 = new Intent(SellerDashBoardActivity.this, SellerProductListActivity.class);
                    startActivity(intent8);
                }
                break;

        }
        //replacing the fragment
//        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.nav_host_fragment, fragment);
//            ft.commit();
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }
}