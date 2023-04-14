package com.webdigital.subbisky.adapter;

import android.widget.ImageView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.BannersModel;
import com.smarteist.autoimageslider.SliderViewAdapter;


public class BannerAdapter extends SliderViewAdapter<BannerAdapter.BannerAdapterViewHolder> {
    private List<BannersModel.BannersLists> bannersLists = new ArrayList<>();
    private Context context;

    public BannerAdapter(List<BannersModel.BannersLists> bannersLists, Context context) {
        this.bannersLists = bannersLists;
        this.context = context;
    }



    @Override
    public BannerAdapterViewHolder onCreateViewHolder(android.view.ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new BannerAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.BannerAdapterViewHolder holder, int position) {
        final BannersModel.BannersLists sliderItem = bannersLists.get(position);
        Glide.with(holder.itemView)
                .load(sliderItem.getImage()).fitCenter().into(holder.iv_auto_image_slider);
    }



    @Override
    public int getCount() {
        return bannersLists.size();
    }

    class BannerAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView iv_auto_image_slider;
        public BannerAdapterViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
//            this.itemView = itemView;
            iv_auto_image_slider=itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }
}
