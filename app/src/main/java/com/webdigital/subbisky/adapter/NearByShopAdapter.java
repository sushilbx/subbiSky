package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.EcomProductDetailActivity;
import com.webdigital.subbisky.activity.ServiceDetailsPageActivity;
import com.webdigital.subbisky.activity.StayBookingDetailsActivity;
import com.webdigital.subbisky.model.SubServiceBySlugModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NearByShopAdapter extends RecyclerView.Adapter<NearByShopAdapter.nearByShopHolder> {
    private List<SubServiceBySlugModel.NearbyShop> nearbyList = new ArrayList<>();
    private Context context;
    private SubServiceBySlugModel subServiceBySlugModel;

    public NearByShopAdapter(SubServiceBySlugModel subServiceBySlugModel) {
        this.subServiceBySlugModel = subServiceBySlugModel;
    }

    public NearByShopAdapter(List<SubServiceBySlugModel.NearbyShop> nearbyList, Context context) {
        this.nearbyList = nearbyList;
        this.context = context;
    }

    @NonNull
    @Override
    public NearByShopAdapter.nearByShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_nearby, null);
        return new NearByShopAdapter.nearByShopHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NearByShopAdapter.nearByShopHolder holder, @SuppressLint("RecyclerView") int position) {
        final SubServiceBySlugModel.NearbyShop Item = nearbyList.get(position);

        String sellerName= String.valueOf(nearbyList.get(position).getShopName());
        Glide.with(holder.itemView)
                .load(Item.getShopImage()).fitCenter().into(holder.imgs);
        holder.textHead.setText(nearbyList.get(position).getShopName());
        holder.textSub.setText(nearbyList.get(position).getShopAddress());

        holder.nextPageintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = context.getSharedPreferences("your_file_name",
                        MODE_PRIVATE);
                String cat_type = prefs.getString("yourStringName",
                        "default_value_here_if_string_is_missing");
//                String cat_type= subServiceBySlugModel.getType();
                Log.e("cat_type",cat_type);
//                String cat_type= subServiceBySlugModel.getType();
                int seller_id=nearbyList.get(position).getId();
//
                Intent intent;

                if (HomeServiceListAdapter.cattype.equals("ecom")) {
                    intent = new Intent(context, EcomProductDetailActivity.class);
                    intent.putExtra("seller_id", seller_id);
                    intent.putExtra("sellerName",sellerName);
                }
                else{

                    cat_type=HomeServiceListAdapter.cattype;
                    intent = new Intent(context, StayBookingDetailsActivity.class);
                    intent.putExtra("seller_id", String.valueOf(seller_id));
                    intent.putExtra("cat_type", cat_type);
                    Log.e("seller_id", String.valueOf(seller_id));
                }
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nearbyList.size();
    }

    public class nearByShopHolder extends RecyclerView.ViewHolder {
        ImageView imgs;
        TextView textHead,textSub;
        LinearLayout nextPageintent;
        public nearByShopHolder(@NonNull View itemView) {
            super(itemView);
            imgs=itemView.findViewById(R.id.imgs);
            textHead=itemView.findViewById(R.id.texthead);
            textSub=itemView.findViewById(R.id.textSub);
            nextPageintent=itemView.findViewById(R.id.nextPageintent);
        }
    }
}