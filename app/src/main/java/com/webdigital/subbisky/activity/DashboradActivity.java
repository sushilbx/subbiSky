package com.webdigital.subbisky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.juanlabrador.badgecounter.BadgeCounter;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.adapter.ExpandableListAdapter;
import com.webdigital.subbisky.fragments.HomeFragment;
import com.webdigital.subbisky.model.MenuModel;
import com.google.android.material.navigation.NavigationView;
import com.webdigital.subbisky.model.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboradActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Fragment fragment = null;
    public static int cNotificationCounter = 0;
    public static int wNotificationCounter = 0;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    public static MenuItem menuItem, wishItem;
    public static String value, pincode;
    private Menu menu;
    Boolean internet=false;
    AlertDialog.Builder alertDialog;
    android.app.AlertDialog dialog;
    android.app.AlertDialog.Builder builder;

    public static Layout cartItem;
    public static String backType = "";
    ImageView imageView ,menuRightSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashborad);
        alertDialog = new AlertDialog.Builder(DashboradActivity .this);
// Create an alert builder
        builder = new android.app.AlertDialog.Builder(this);
        showAlertDialog();
        internetcheck();
        menuRightSearch = findViewById(R.id.menuRightSearch);
        Toolbar toolbar = findViewById(R.id.toolbar);

        imageView = findViewById(R.id.menuRightCart);

        setSupportActionBar(toolbar);
      ;
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        //TextView navUsername = (TextView) headerView.findViewById(R.id.textView);
        displaySelectedScreen(R.id.nav_home);
       /* expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();*/

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Home");
        //   value=getIntent().getStringExtra("key");
      //  loadFragment(new HomeFragment());

        // bottomNavigation();



       /* mContext=this;
               navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);
        headerNavView();
        isOrderShowing = true;*/
    }
    public void onResume() {




        Log.d("TAG", "onCreateOptionsMenu: " + wNotificationCounter);
        RetrofitClient.getInstance().getCartCount(getApplicationContext());
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

// bro add the radious in badge
        //  RetrofitClient.getInstance().getCartCount(getApplicationContext());
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.cart);
        MenuItem itemsearch = menu.findItem(R.id.search);
        menuItem = item;


        BadgeCounter.update(this, menu.findItem(R.id.cart),
                R.drawable.ic_baseline_shopping_cart_24,

                cNotificationCounter);

        Log.d("TAG", "onCreateOptionsMenu: " + wNotificationCounter);
        RetrofitClient.getInstance().getCartCount(getApplicationContext());
        // RetrofitClient.getInstance().getWishlistCount(getApplicationContext());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:

                RetrofitClient.getInstance().getCartCount(getApplicationContext());
                menuItem = item;
                BadgeCounter.update(item, cNotificationCounter);
                Intent intent = new Intent(DashboradActivity.this,CartListActivity.class);
                startActivity(intent);
                break;
            case R.id.search:
                RetrofitClient.getInstance().getCartCount(getApplicationContext());
                wishItem=item;
                Intent intents = new Intent(DashboradActivity.this,  SearchActivity.class);
                startActivity(intents);
                break;


        }
        return true;

    }







    private boolean internetcheck() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if ( connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected() )
        {
            internet=true;
            if (dialog.isShowing()){

                dialog.dismiss();
                Intent intent = new Intent(DashboradActivity.this, DashboradActivity.class);
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


  private void displaySelectedScreen(int itemId) {
      //creating fragment object
      Fragment fragment = null;
      //initializing the fragment object which is selected
      switch (itemId) {
          case R.id.nav_home:
              if( internetcheck()==true){

              fragment = new HomeFragment();}
              break;
          case R.id.my_account:
              if( internetcheck()==true){
              Intent intent = new Intent(DashboradActivity.this,ProfileActivity.class);
              startActivity(intent);}
              break;
//          case R.id.my_profile:
//              Intent intent = new Intent(DashboradActivity.this,ProfileActivity.class);
//              startActivity(intent);
//              break;
//          case R.id.my_order:
//              Intent intent1 = new Intent(DashboradActivity.this,MyOrderActivity.class);
//              startActivity(intent1);
//              break;
//          case R.id.my_service:
//              Intent intent2 = new Intent(DashboradActivity.this,MyServiceActivity.class);
//              startActivity(intent2);
//              break;
//          case R.id.my_bookings:
//              Intent intent3 = new Intent(DashboradActivity.this,CustomerBookingList.class);
//              startActivity(intent3);
//              break;
//          case R.id.edit_profile:
//              Intent intent4 = new Intent(DashboradActivity.this,EditProfileActivity.class);
//              startActivity(intent4);
//              break;
//          case R.id.change_password:
//              Intent intent5 = new Intent(DashboradActivity.this,ChangePasswordActivity.class);
//              startActivity(intent5);
//              break;
          case R.id.about_us:
              if( internetcheck()==true){
                          Intent intent6 = new Intent(DashboradActivity.this,AboutUsActivity.class);
              startActivity(intent6);}
              break;
          case R.id.contact_us:
              if( internetcheck()==true){
              Intent intent7 = new Intent(DashboradActivity.this,ContactUsActivity.class);
              startActivity(intent7);}
              break;
          case R.id.logout:
              if( internetcheck()==true){
              Intent intent8 = new Intent(DashboradActivity.this,LogoutActivity.class);
              startActivity(intent8);}
              break;


      }
      //replacing the fragment
      if (fragment != null) {
          FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
          ft.replace(R.id.nav_host_fragment, fragment);
          ft.commit();
      }
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      drawer.closeDrawer(GravityCompat.START);
  }
    private void headerNavView()
    {
        //View headerView=navigationView.inflateHeaderView(R.layout.nav_header_main);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }





}





