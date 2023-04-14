package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.MyBookingDetailsActivity;
import com.webdigital.subbisky.model.BookingModel;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<BookingModel> productList;

    //getting the context and product list with constructor
    public BookingAdapter(Context mCtx, List<BookingModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.booking_view, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        BookingModel product = productList.get(position);

        //binding the data with the viewholder views
      //  rollNo.setText(Integer.toString(items[position].getRollNo()));

        holder.textViewId.setText(Integer.toString(product.getId()));
        //holder.textViewId.setText(product.getId());
        holder.textViewDate.setText(Integer.toString(product.getDate()));
        holder.textViewStatus.setText(String.valueOf(product.getStatus()));
        holder.textViewTotal.setText(Integer.toString(product.getTotal()));
        holder.textViewDetails.setText(String.valueOf(product.getView()));

        holder.textViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, MyBookingDetailsActivity.class);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewId, textViewDate, textViewStatus, textViewTotal,textViewDetails;


        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.textViewId);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            textViewDetails = itemView.findViewById(R.id.textViewDetails);

        }
    }
}