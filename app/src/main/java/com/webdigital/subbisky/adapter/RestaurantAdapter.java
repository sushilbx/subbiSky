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
import com.webdigital.subbisky.activity.AddToCartRestaurantActivity;
import com.webdigital.subbisky.model.RestaurantModel;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<RestaurantModel> RestaurantList;

    //getting the context and product list with constructor
    public RestaurantAdapter(Context mCtx, List<RestaurantModel> RestaurantList) {
        this.mCtx = mCtx;
        this.RestaurantList = RestaurantList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_restaurant, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder  holder, int position) {
        //getting the product of the specified position
        RestaurantModel restaurant = RestaurantList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(restaurant.getTitle());
        holder.textViewShortDesc.setText(restaurant.getShortdesc());

        holder.textViewCall.setText(restaurant.getCall());
        holder.textViewEmail.setText(restaurant.getEmail());
        holder.textViewRating.setText(String.valueOf(restaurant.getRating()));


        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(restaurant.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx, AddToCartRestaurantActivity.class);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return RestaurantList.size();
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
