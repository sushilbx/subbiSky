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
import com.webdigital.subbisky.activity.PickupAndDropListActivity;
import com.webdigital.subbisky.activity.ServiceListActivity;
import com.webdigital.subbisky.model.HomeProductModel;

import java.util.List;

public class HomeProductAdapter1 extends RecyclerView.Adapter<HomeProductAdapter1.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<HomeProductModel> productList1;

    //getting the context and product list with constructor
    public HomeProductAdapter1(Context mCtx, List<HomeProductModel> productList1) {
        this.mCtx = mCtx;
        this.productList1 = productList1;
    }

    @Override
    public HomeProductAdapter1.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_home_products1, null);
        return new HomeProductAdapter1.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeProductAdapter1.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        HomeProductModel product = productList1.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShowAll.setText(product.getShowall());

        holder.imageView1.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        if (position == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mCtx, ServiceListActivity.class);
                    mCtx.startActivity(intent);
                }
            });
        }
        if (position == 3){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mCtx, PickupAndDropListActivity.class);
                    mCtx.startActivity(intent);
                }
            });
        }



    }


    @Override
    public int getItemCount() {
        return productList1.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShowAll;
        ImageView imageView1;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShowAll = itemView.findViewById(R.id.textViewShowAll);
            imageView1 = itemView.findViewById(R.id.imageView1);
        }
    }
}
