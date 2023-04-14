package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.HomeImageModel;

import java.util.List;

public class HomeImageAdapter extends RecyclerView.Adapter<HomeImageAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<HomeImageModel> imageList;

    //getting the context and product list with constructor
    public HomeImageAdapter(Context mCtx, List<HomeImageModel> imageList) {
        this.mCtx = mCtx;
        this.imageList = imageList;
    }

    @Override
    public HomeImageAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.citieslisting, null);
        return new HomeImageAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeImageAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        HomeImageModel images = imageList.get(position);

        //binding the data with the viewholder views

        holder.imageViewRecycler.setImageDrawable(mCtx.getResources().getDrawable(images.getImage()));
holder.name.setText(images.getname().toString());
holder.listing.setText(images.getlisting().toString());
    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {


        ImageView imageViewRecycler;
        TextView name,listing;

        public ProductViewHolder(View itemView) {
            super(itemView);


            imageViewRecycler = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            listing = itemView.findViewById(R.id.listing);

        }
    }
}
