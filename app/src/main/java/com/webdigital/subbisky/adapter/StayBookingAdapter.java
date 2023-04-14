package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.StayBookingDatesActivity;
import com.webdigital.subbisky.model.StayBookingModel;

import java.util.List;

public class StayBookingAdapter extends RecyclerView.Adapter<StayBookingAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<StayBookingModel> StayBookingList;

    //getting the context and product list with constructor
    public StayBookingAdapter(Context mCtx, List<StayBookingModel> StayBookingList) {
        this.mCtx = mCtx;
        this.StayBookingList = StayBookingList;
    }

    @Override
    public StayBookingAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_stay_booking, null);
        return new StayBookingAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StayBookingAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        StayBookingModel stayBooking = StayBookingList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(stayBooking.getTitle());
        holder.textViewShortDesc.setText(stayBooking.getShortdesc());

        holder.textViewCall.setText(stayBooking.getCall());
        holder.textViewEmail.setText(stayBooking.getEmail());
        holder.textViewRating.setText(String.valueOf(stayBooking.getRating()));


        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(stayBooking.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx, StayBookingDatesActivity.class);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return StayBookingList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc,textViewCall,textViewEmail, textViewRating, textViewReview,textViewQuote;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewCall = itemView.findViewById(R.id.textViewCall);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            textViewQuote = itemView.findViewById(R.id.textViewQuote);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}