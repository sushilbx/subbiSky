package com.webdigital.subbisky.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerHotelAmenitiesModel;

import java.util.List;

public class AmenitiesAdapter extends ArrayAdapter<SellerHotelAmenitiesModel> {

    List<SellerHotelAmenitiesModel.Amenities> amenitiesArrayList;
    Context context;
    int resource;

    public AmenitiesAdapter(  Context context, int resource,List<SellerHotelAmenitiesModel.Amenities> amenitiesArrayList) {
        super(context, resource);
        this.amenitiesArrayList = amenitiesArrayList;
        this.context = context;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.row_design_seller_hotel_list_create_amenities,null,true);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.textViewForCreateHotel);
       CheckBox checkboxForCreateHotel =(CheckBox)convertView.findViewById(R.id.checkboxForCreateHotel);
       textView.setText(amenitiesArrayList.get(position).getName());
       checkboxForCreateHotel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
//                   SellerHotelListEditActivity.
               }
           }
       });
        return convertView;
    }
}
