package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.MyBookingDetailsActivity;
import com.webdigital.subbisky.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerBookingAdapter extends  RecyclerView.Adapter<CustomerBookingAdapter.customerBookingListHolder>{
    private List<UserModel.Booking> bookingList = new ArrayList<>();
    private Context context;

    public CustomerBookingAdapter(List<UserModel.Booking> bookingList, Context context) {
        this.bookingList = bookingList;
        this.context = context;
    }

    @NonNull
    @Override
    public customerBookingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_booking_list_item, null);
        return new customerBookingListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull customerBookingListHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bookingId.setText(bookingList.get(position).getOrder_id());
        holder.bookingdate.setText(bookingList.get(position).getCreated_at());
        holder.status.setText(bookingList.get(position).getStatus());
        holder.total.setText("₹ " +bookingList.get(position).getPayable_price());
        holder.viewBookingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderId = String.valueOf(bookingList.get(position).getId());
                Intent intent=new Intent(context, MyBookingDetailsActivity.class);
                intent.putExtra("orderId",orderId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class customerBookingListHolder extends RecyclerView.ViewHolder {
        TextView bookingId,bookingdate,status,total,viewBookingDetails;
        public customerBookingListHolder(@NonNull View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.bookingId);
            bookingdate = itemView.findViewById(R.id.bookingdate);
            status = itemView.findViewById(R.id.status);
            total = itemView.findViewById(R.id.total);
            viewBookingDetails = itemView.findViewById(R.id.viewBookingDetails);
        }
    }
}
