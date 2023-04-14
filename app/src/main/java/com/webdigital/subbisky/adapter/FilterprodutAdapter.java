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
import com.webdigital.subbisky.model.CategoriesNearByItemModel;
import com.webdigital.subbisky.model.ProductDetailModel;

import java.util.ArrayList;
import java.util.List;



public class FilterprodutAdapter extends RecyclerView.Adapter<FilterprodutAdapter.filterHolder> {
    private List<CategoriesNearByItemModel.Product> productdetailsLists = new ArrayList<>();
    private Context context;

public static String sellersId,categorysId;
    public FilterprodutAdapter(List<CategoriesNearByItemModel.Product> productdetailsLists, Context context) {
        this.productdetailsLists = productdetailsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public FilterprodutAdapter.filterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_desgin_addtocart, null);
        return new FilterprodutAdapter.filterHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterprodutAdapter.filterHolder holder, int position) {
        final CategoriesNearByItemModel.Product Items = productdetailsLists.get(position);
      //String sellerName= String.valueOf(Integer.parseInt(productdetailsLists.get(position).getName()));
       // String productId= String.valueOf(productdetailsLists.get(position).getId());
        int productId= Integer.parseInt(productdetailsLists.get(position).getId());

        Glide.with(holder.itemView)
                .load(Items.getImage()).fitCenter().into(holder.imageView1);
        holder.ItemName.setText(productdetailsLists.get(position).getName());
        holder.sellingPrice.setText("Rs."+productdetailsLists.get(position).getSellingPrice());
        holder.mrpPrice.setText("Rs."+productdetailsLists.get(position).getMrpPrice());




        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sellerId= Integer.parseInt(productdetailsLists.get(position).getSellerId());
                int categoryId= Integer.parseInt(productdetailsLists.get(position).getCategoryId());
                Intent intent=new Intent(context, AddToCartDescription.class);
                intent.putExtra("sellerId",sellerId);
                intent.putExtra("categoryId",categoryId);
                intent.putExtra("productId",productId);

                context.startActivity(intent);
            }
        });

    }

   @Override
   public int getItemCount() {
      return productdetailsLists.size();
   }

    public class filterHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView ItemName,sellingPrice,mrpPrice;
        Button addToCart;
        String sellerId;
        public filterHolder(@NonNull View itemView) {
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
