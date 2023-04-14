package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.ServiceDetailsModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ServiceDetailsPageImageAdapter extends SliderViewAdapter<ServiceDetailsPageImageAdapter.ServiceImageViewHolder> {
    private List<ServiceDetailsModel.ServiceSellerServices> serviceSellerServices = new ArrayList<>();
    private Context context;

    public ServiceDetailsPageImageAdapter(List<ServiceDetailsModel.ServiceSellerServices> serviceSellerServices, Context context) {
        this.serviceSellerServices = serviceSellerServices;
        this.context = context;
    }

    @Override
    public ServiceImageViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new ServiceImageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ServiceImageViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(serviceSellerServices.get(position).getImage()).fitCenter().into(holder.iv_auto_image_slider);
//        holder.serviceImageText.setText(serviceSellerServices.get(position).getName());
    }

    @Override
    public int getCount() {
        return serviceSellerServices.size();
    }

    public class ServiceImageViewHolder extends ViewHolder {
        ImageView iv_auto_image_slider;
        TextView serviceImageText;
        public ServiceImageViewHolder(View itemView) {
            super(itemView);
            iv_auto_image_slider=itemView.findViewById(R.id.iv_auto_image_slider);
//            serviceImageText=itemView.findViewById(R.id.serviceImageText);
        }
    }
}
