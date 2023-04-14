package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SellerCreateHotelDetailsActivity;
import com.webdigital.subbisky.model.SellerHotelAmenitiesModel;

import java.util.ArrayList;
import java.util.List;

public class SellerHotelListCreateAmenitiesAdapter extends RecyclerView.Adapter<SellerHotelListCreateAmenitiesAdapter.sellerHotelListCreateAmenitiesHolder> {
private List<SellerHotelAmenitiesModel.Amenities> amenitiesLists = new ArrayList<>();
private Context context;
//    NestedScrollView Rl;
//    String chk;
//    CircularArray<Object> List3;
//    List<List3> listOfCircles = new ArrayList<List3>();
    public SellerHotelListCreateAmenitiesAdapter(List<SellerHotelAmenitiesModel.Amenities> amenitiesLists, Context context) {
        this.amenitiesLists = amenitiesLists;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerHotelListCreateAmenitiesAdapter.sellerHotelListCreateAmenitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_seller_hotel_list_create_amenities, null);
        return new SellerHotelListCreateAmenitiesAdapter.sellerHotelListCreateAmenitiesHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerHotelListCreateAmenitiesAdapter.sellerHotelListCreateAmenitiesHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewForCreateHotel.setText(amenitiesLists.get(position).getName());
//        if(holder.checkboxForCreateHotel.isChecked())
//        {
//            Integer AcId=amenitiesLists.get(position).getId();
//        }
//        List <Integer> AcId = new ArrayList<Integer>();
////        List<CheckBox> items = new ArrayList<CheckBox>();
////        for (CheckBox item : items){
////
////        }
//        if(holder.checkboxForCreateHotel.isChecked())
//            AcId= Collections.set(amenitiesLists.get(position).getId());
//        Log.e("AcId", String.valueOf(AcId));
//        ArrayList<String> selchkboxlist=new ArrayList<String>();
//        CheckBox[] cbs;
//        cbs = new CheckBox[20];
//        for(int k=0; k<List3.size(); k++)
//        {
//            String arr = String.valueOf(List3.get(k));
//            cbs[k] = new CheckBox(context);
//            Rl.addView(cbs[k]);
////            cbs[k].setText((CharSequence) arr.get(2));
//            cbs[k].setId(k);
//            cbs[k].setOnClickListener( new View.OnClickListener()
//            {
//                public void onClick(View v)
//                {
//                    CheckBox cb = (CheckBox) v ;
//                    if ((holder.checkboxForCreateHotel).isChecked()) {
//                        chk = Integer.toString(v.getId());
//                        selchkboxlist.add(chk);
//                        Log.e("selchkboxlist", String.valueOf(selchkboxlist));
//                        Toast.makeText(context, "Selected CheckBox ID" + v.getId(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        selchkboxlist.remove(chk);
//                        Toast.makeText(context, "Not selected", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//    }
        holder.checkboxForCreateHotel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SellerCreateHotelDetailsActivity.amenitiesList.add(String.valueOf(amenitiesLists.get(position).getId()));
                }else {
                    SellerCreateHotelDetailsActivity.amenitiesList.remove(String.valueOf(amenitiesLists.get(position).getId()));
                }
                Log.e("amenitiesLists", SellerCreateHotelDetailsActivity.amenitiesList.toString());
            }
        });
    }
    @Override
    public int getItemCount() {
        return amenitiesLists.size();
    }

    public class sellerHotelListCreateAmenitiesHolder extends RecyclerView.ViewHolder {
        CheckBox checkboxForCreateHotel;
        TextView textViewForCreateHotel;
        public sellerHotelListCreateAmenitiesHolder(@NonNull View itemView) {
            super(itemView);
            textViewForCreateHotel=itemView.findViewById(R.id.textViewForCreateHotel);
            checkboxForCreateHotel=itemView.findViewById(R.id.checkboxForCreateHotel);

        }
    }


}
