package com.webdigital.subbisky.adapter;

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

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.StaybookroomaddingActivity;
import com.webdigital.subbisky.activity.SubServicesListActivity;
import com.webdigital.subbisky.model.ExplorecitylistResponse;
import com.webdigital.subbisky.model.ToptrendinglistResponse;

import java.util.ArrayList;
import java.util.List;



public class TrendindAdapter extends  RecyclerView.Adapter<TrendindAdapter.MyViewHolder> {
    public List<ToptrendinglistResponse.TopTrending> trendingList = new ArrayList<>();
    public Context context;
    public static String faqid="";
    String serviceName,serviceId,type;
    public TrendindAdapter(List<ToptrendinglistResponse.TopTrending> list, Context context) {
        this.trendingList = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.toptrending, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.textViewTitle.setText(trendingList.get(position).getName().trim());
        Glide.with(holder.imageView)
                .load(trendingList.get(position).getImage()).fitCenter().into(holder.imageView);


        holder.toptrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( trendingList.get(position).getName().equalsIgnoreCase("Homestay")){

                    serviceName = trendingList.get(position).getName();
                    serviceId=trendingList.get(position).getId().toString();

                   // type = trendingList.getType();

                    Log.e("serviceId",serviceId);
                    Log.e("type",type);
                    Log.e("serviceName",serviceName);
                    Intent intent=new Intent(context, StaybookroomaddingActivity.class);
//                    intent.putExtra("serviceName",serviceName);
//                    intent.putExtra("serviceId",serviceId);
//                    intent.putExtra("type",type);
                    context.startActivity(intent);
                }
                else{

                    serviceName = trendingList.get(position).getName();
                    //type = trendingList.getType();
                    serviceId = trendingList.get(position).getId().toString();
                    Log.e("serviceId", serviceId);
                    Log.e("type", ""+type);
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
        return this.trendingList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;
      RelativeLayout toptrend;
        MyViewHolder(View itemView) {
            super(itemView);

            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            imageView=itemView.findViewById(R.id.imageView);
            toptrend=itemView.findViewById(R.id.toptrend);



        }

    }

}