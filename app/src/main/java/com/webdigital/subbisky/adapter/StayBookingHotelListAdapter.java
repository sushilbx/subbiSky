package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.StayBookingActivity;
import com.webdigital.subbisky.activity.StayBookingDetailsActivity;
import com.webdigital.subbisky.activity.StayBookingPayActivity;
import com.webdigital.subbisky.activity.StaybookroomaddingActivity;
import com.webdigital.subbisky.model.StayBookingSearchModel;
import com.webdigital.subbisky.model.StayBookinghModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.sroonsize;

public class StayBookingHotelListAdapter extends RecyclerView.Adapter<StayBookingHotelListAdapter.StayBookingHotelListHolder> {
    private List<StayBookinghModel.Hotel> stayBookingHotels = new ArrayList<>();
    private Context context;
    private StayBookingSearchModel bookingDetails ;
public static int availablerooms;
    public StayBookingHotelListAdapter(StayBookingSearchModel bookingDetails,StayBookingActivity context) {
        this.bookingDetails = bookingDetails;
        this.context = context;
    }

    public StayBookingHotelListAdapter(List<StayBookinghModel.Hotel> hotels, StayBookingDetailsActivity context) {
        this.stayBookingHotels = hotels;
        this.context = context;
    }

    @NonNull
    @Override
    public StayBookingHotelListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.stay_booking_detail_item, null);
        return new StayBookingHotelListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull StayBookingHotelListHolder holder, int position) {
        holder.hotelName.setText(stayBookingHotels.get(position).getName());
        holder.adultsRoom.setText("Per Adult / Night: "+stayBookingHotels.get(position).getPrice()+" Rs");
        holder.bedSize.setText(stayBookingHotels.get(position).getBedSize()+" bed- "+stayBookingHotels.get(position).getRoomSquareFeet());
        holder.hotelName.setText(stayBookingHotels.get(position).getName());
        holder.checkInTime.setText(stayBookingHotels.get(position).getCheckinTime());
        holder.checkOutTime.setText(stayBookingHotels.get(position).getCheckoutTime());
        //holder.bathRoom.setText(stayBookingHotels.get(position).getAmenities().toString());
        Glide.with(holder.itemView)
                .load(stayBookingHotels.get(position).getImage()).placeholder(R.drawable.noimg).fitCenter().into(holder.imageView);

        final String fromDate,toDate,childrenextraamount,adultsextraamount,adultsextra,childrenextra,room,price,totalAmount,hotelId,adults,children,availroom;
//        String
        SharedPreferences prefs = context.getSharedPreferences("your_file_name",
                MODE_PRIVATE);
        fromDate= prefs.getString("fromDate", "fromDate");
        toDate= prefs.getString("toDate", "toDate");
        adultsextra= prefs.getString("adultsextra", "adultsextra");
        childrenextra= prefs.getString("childrenextra", "childrenextra");
        adults= prefs.getString("adults", "adults");
        children= prefs.getString("children", "children");
        room= prefs.getString("room", "room");
//        fromDate = bookingDetails.getBookingDetails().getFrom();
//        toDate = bookingDetails.getBookingDetails().getTo();
//        adults = bookingDetails.getBookingDetails().getAdult();
//        children = bookingDetails.getBookingDetails().getTotalChildren();
//
        childrenextraamount = stayBookingHotels.get(position).getChildrenExtra();
        adultsextraamount = stayBookingHotels.get(position).getAdultExtra();

        price = stayBookingHotels.get(position).getPrice().toString();
        totalAmount = stayBookingHotels.get(position).getPrice().toString();
        hotelId = String.valueOf(stayBookingHotels.get(position).getId());
        availablerooms=(stayBookingHotels.get(position).getAvailableRooms());
        holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(availablerooms>=sroonsize){
                    Intent intent = new Intent(context, StayBookingPayActivity.class);
                    intent.putExtra("hotelId",hotelId);
                    intent.putExtra("fromDate",fromDate);
                    intent.putExtra("toDate",toDate);
                    intent.putExtra("adults",adults);
                    intent.putExtra("children",children);
                    intent.putExtra("room",room);
                    intent.putExtra("price",price);
                    intent.putExtra("totalAmount",totalAmount);
                    intent.putExtra("adultsextraamount",adultsextraamount);
                    intent.putExtra("childrenextraamount",childrenextraamount);

                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, "Sufficient Rooms not available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return stayBookingHotels.size();
    }

    public class StayBookingHotelListHolder extends RecyclerView.ViewHolder {
        TextView hotelName,bathRoom,food,parking,restaurant,spa,swimmingpool,tv,wifi,ac,adultsRoom,bedSize,checkInTime,checkOutTime;
        Button bookNow;
        ImageView imageView;
        public StayBookingHotelListHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotelName);
            bathRoom = itemView.findViewById(R.id.bathroom);
            imageView = itemView.findViewById(R.id.imageView);
            food = itemView.findViewById(R.id.food);
            parking = itemView.findViewById(R.id.parking);
            restaurant = itemView.findViewById(R.id.restaurant);
            spa = itemView.findViewById(R.id.spa);
            swimmingpool = itemView.findViewById(R.id.swimmingpool);
            tv = itemView.findViewById(R.id.tv);
            wifi = itemView.findViewById(R.id.wifi);
            ac = itemView.findViewById(R.id.ac);
            adultsRoom = itemView.findViewById(R.id.adultsRoom);
            bedSize = itemView.findViewById(R.id.bedSize);
            checkInTime = itemView.findViewById(R.id.checkInTime);
            checkOutTime = itemView.findViewById(R.id.checkOutTime);
            bookNow = itemView.findViewById(R.id.bookNow);
        }
    }
}
