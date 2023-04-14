package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.StayBookingActivity;
import com.webdigital.subbisky.activity.StaybookroomaddingActivity;
import com.webdigital.subbisky.activity.SubServicesListActivity;
import com.webdigital.subbisky.model.BannersModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HomeServiceListAdapter extends RecyclerView.Adapter<HomeServiceListAdapter.HomeServiceListViewHolder> {
    private List<BannersModel.ServicesLists> servicesLists = new ArrayList<>();
    private Context context;
    public  static  String serviceName,serviceId,type,type1;
    public  static String cattype="";


    public HomeServiceListAdapter(List<BannersModel.ServicesLists> servicesLists, Context context) {
        this.servicesLists = servicesLists;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeServiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_item, null);
        return new HomeServiceListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeServiceListViewHolder holder, int position) {
        final BannersModel.ServicesLists listItem = servicesLists.get(position);
        Glide.with(holder.itemView)
                .load(listItem.getImage()).fitCenter().into(holder.homeservicelistimage);
        holder.homeservicelistname.setText(listItem.getName());
        cattype=(listItem.getType());

        holder.homeservicelistcount.setText("Show All ("+listItem.getShopCount()+")");
        holder.serviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( listItem.getType().equalsIgnoreCase("staybooking")){

                    serviceName = listItem.getName();
                    serviceId=listItem.getId().toString();

                    type = listItem.getType();

                    cattype=type;





                    Log.e("serviceId",serviceId);
                    Log.e("type",type);
                    Log.e("serviceName",serviceName);
                    Intent intent=new Intent(context, StaybookroomaddingActivity.class);
                  intent.putExtra("serviceName",serviceName);
                    intent.putExtra("serviceId",serviceId);
                    intent.putExtra("type",type);
                    context.startActivity(intent);
                }
                else{
                    cattype="ecom";



                    serviceName = listItem.getName();
                    type = listItem.getType();
                    type1 = listItem.getType();
                    serviceId = listItem.getId().toString();
                    Log.e("serviceId", serviceId);
                    Log.e("type", type);

                    Log.e("serviceName", serviceName);
                    Intent intent = new Intent(context, SubServicesListActivity.class);
                    intent.putExtra("serviceName", serviceName);
                    intent.putExtra("serviceId", serviceId);
                    intent.putExtra("type", type);

                    context.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesLists.size();
    }

    public class HomeServiceListViewHolder extends RecyclerView.ViewHolder {
        ImageView homeservicelistimage;
        TextView homeservicelistname,homeservicelistcount;
        CardView serviceCard;
        public HomeServiceListViewHolder(@NonNull View itemView) {
            super(itemView);
            homeservicelistimage = itemView.findViewById(R.id.servicelistimage);
            homeservicelistname = itemView.findViewById(R.id.servicelistimagename);
            homeservicelistcount = itemView.findViewById(R.id.servicelistimagecount);
            serviceCard = itemView.findViewById(R.id.homeservicelistcard);
        }
    }
}
