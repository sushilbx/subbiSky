package com.webdigital.subbisky.adapter;

import static com.webdigital.subbisky.activity.SubServicesListActivity.serviceId;
import static com.webdigital.subbisky.activity.SubServicesListActivity.serviceName;
import static com.webdigital.subbisky.activity.SubServicesListActivity.type;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.CartListActivity;
import com.webdigital.subbisky.activity.MyBookingDetailsActivity;
import com.webdigital.subbisky.activity.StaybookroomaddingActivity;
import com.webdigital.subbisky.activity.SubServicesListActivity;
import com.webdigital.subbisky.fragments.HomeFragment;
import com.webdigital.subbisky.model.BookingModel;
import com.webdigital.subbisky.model.CartIncreaseModel;
import com.webdigital.subbisky.model.ExplorecitylistResponse;
import com.webdigital.subbisky.model.HomecityResponse;
import com.webdigital.subbisky.model.ProductDetailModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreCityAdapter extends RecyclerView.Adapter<ExploreCityAdapter.MyViewHolder> {
    public List<ExplorecitylistResponse.City> cityList = new ArrayList<>();
    public Context context;

    int position;

    public ExploreCityAdapter(List<ExplorecitylistResponse.City> list, Context context) {
        this.cityList = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.citieslisting, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(cityList.get(position).getName());

        Glide.with(holder.image).load(cityList.get(position).getImage()).into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment.cityname = cityList.get(position).getName().toString();
                AppCompatActivity activity = (AppCompatActivity) context;
                Fragment fm = new HomeFragment();
                activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).
                        replace(R.id.nav_host_fragment, fm).commit();


            }
        });

    }


    @Override
    public int getItemCount() {
        return this.cityList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        RelativeLayout detail;

        MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
           // detail = itemView.findViewById(R.id.detail);


        }

    }

}