package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SellerServicesDetailsActivity;
import com.webdigital.subbisky.model.ServiceDetailsModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServicewithChargeAdapter extends RecyclerView.Adapter<ServicewithChargeAdapter.ServicewithChargeViewHolder> {
    private List<ServiceDetailsModel.ServiceSellerServices> serviceSellerServices = new ArrayList<>();
    private Context context;

    public ServicewithChargeAdapter(List<ServiceDetailsModel.ServiceSellerServices> serviceSellerServices, Context context) {
        this.serviceSellerServices = serviceSellerServices;
        this.context = context;
    }

    @NonNull
    @Override
    public ServicewithChargeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_with_charge_item, null);
        return new ServicewithChargeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicewithChargeViewHolder holder, int position) {
        holder.serviceListTitle.setText(serviceSellerServices.get(position).getName());
        holder.serviceChargeAmount.setText(serviceSellerServices.get(position).getPrice());
        holder.servicechargePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  sellerId= serviceSellerServices.get(position).getSeller_id();
                int  serviceId= serviceSellerServices.get(position).getId();
                Intent intent = new Intent(context, SellerServicesDetailsActivity.class);
                intent.putExtra("sellerId",sellerId);
                intent.putExtra("serviceId",serviceId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceSellerServices.size();
    }

    public class ServicewithChargeViewHolder extends RecyclerView.ViewHolder {
        TextView serviceListTitle,serviceChargeAmount,servicechargePay;
        public ServicewithChargeViewHolder(@NonNull View itemView) {
            super(itemView);
            servicechargePay = itemView.findViewById(R.id.servicechargePay);
            serviceListTitle = itemView.findViewById(R.id.serviceListTitle);
            serviceChargeAmount = itemView.findViewById(R.id.serviceChargeAmount);
        }
    }
}
