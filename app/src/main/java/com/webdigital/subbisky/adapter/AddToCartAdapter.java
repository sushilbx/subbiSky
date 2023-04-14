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
import com.webdigital.subbisky.activity.AddToCartDescription;
import com.webdigital.subbisky.model.AddToCartModel;

import java.util.List;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<AddToCartModel> addToCartList;

    //getting the context and product list with constructor
    public AddToCartAdapter(Context mCtx, List<AddToCartModel> addToCartList) {
        this.mCtx = mCtx;
        this.addToCartList = addToCartList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_add_cart, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        AddToCartModel addToCart = addToCartList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(addToCart.getTitle());
        holder.textViewRup.setText(addToCart.getRup());
        holder.textViewRup1.setText(addToCart.getRup1());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(addToCart.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx, AddToCartDescription.class);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return addToCartList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewRup, textViewRup1;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRup = itemView.findViewById(R.id.textViewRup);
            textViewRup1 = itemView.findViewById(R.id.textViewRup1);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}