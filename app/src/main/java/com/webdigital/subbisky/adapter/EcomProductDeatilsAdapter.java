package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.AddToCartDescription;
import com.webdigital.subbisky.model.ProductDetailModel;

import java.util.ArrayList;
import java.util.List;

public class EcomProductDeatilsAdapter extends RecyclerView.Adapter<EcomProductDeatilsAdapter.ecomProductDetailasHolder> {
    private List<ProductDetailModel.ProductDetails> productdetailsLists = new ArrayList<>();
    private Context context;


    public EcomProductDeatilsAdapter(List<ProductDetailModel.ProductDetails> productdetailsLists, Context context) {
        this.productdetailsLists = productdetailsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public EcomProductDeatilsAdapter.ecomProductDetailasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_desgin_addtocart, null);
        return new EcomProductDeatilsAdapter.ecomProductDetailasHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull EcomProductDeatilsAdapter.ecomProductDetailasHolder holder, int position) {
        final ProductDetailModel.ProductDetails Items = productdetailsLists.get(position);
//                String sellerName= String.valueOf(Integer.parseInt(productdetailsLists.get(position).getName()));
        int productId= Integer.parseInt(productdetailsLists.get(position).getId());

        Glide.with(holder.itemView)
                .load(Items.getImage()).fitCenter().into(holder.imageView1);
        holder.ItemName.setText(productdetailsLists.get(position).getName());
        holder.sellingPrice.setText("Rs."+productdetailsLists.get(position).getSelling_price());
        holder.mrpPrice.setText("Rs."+productdetailsLists.get(position).getMrp_price());
//        holder.sellerId.productdetailsLists.get(position).getSeller_id();
       // int sellerId= Integer.parseInt(productdetailsLists.get(position).getSeller_id());
        //int categoryId= Integer.parseInt(productdetailsLists.get(position).getCategory_id());
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sellerId= Integer.parseInt(productdetailsLists.get(position).getSeller_id());
                int categoryId= Integer.parseInt(productdetailsLists.get(position).getCategory_id());
                Intent intent=new Intent(context, AddToCartDescription.class);
                intent.putExtra("sellerId",sellerId);
                intent.putExtra("categoryId",categoryId);
                intent.putExtra("productId",productId);
//                intent.putExtra("sellerName",sellerName);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productdetailsLists.size();
    }

    public class ecomProductDetailasHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView ItemName,sellingPrice,mrpPrice;
        Button addToCart;
        String sellerId;
        public ecomProductDetailasHolder(@NonNull View itemView) {
            super(itemView);
            imageView1=itemView.findViewById(R.id.imageView1);
            addToCart=itemView.findViewById(R.id.addToCart);
            ItemName=itemView.findViewById(R.id.ItemName);
            sellingPrice=itemView.findViewById(R.id.sellingPrice);
            mrpPrice=itemView.findViewById(R.id.mrpPrice);
//            mrpPrice.setText(someString);
            mrpPrice.setPaintFlags(mrpPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
