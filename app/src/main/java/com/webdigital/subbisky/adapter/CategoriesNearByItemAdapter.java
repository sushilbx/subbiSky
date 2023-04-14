package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.FilteredCategoryProductsActivity;
import com.webdigital.subbisky.activity.SellerCategoryActivity;
import com.webdigital.subbisky.model.CategoriesNearByItemModel;
import com.webdigital.subbisky.model.ProductDetailModel;
import com.webdigital.subbisky.model.SellerCategoryModel;
import com.webdigital.subbisky.model.ProductDetailModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CategoriesNearByItemAdapter extends RecyclerView.Adapter<CategoriesNearByItemAdapter.categoriesNearByItemHolder> {
    private List<ProductDetailModel.ProductDetails> categoryProducts = new ArrayList<>();
    private Context context;

    public CategoriesNearByItemAdapter(List<ProductDetailModel.ProductDetails> categoryProducts, Context context) {
        this.categoryProducts = categoryProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesNearByItemAdapter.categoriesNearByItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorynearbyitem, null);
        return new CategoriesNearByItemAdapter.categoriesNearByItemHolder(inflate);    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesNearByItemAdapter.categoriesNearByItemHolder holder, int position) {
        holder.categoriesName.setText(categoryProducts.get(position).getName());
       // holder.categoriesCount.setText(categoryProducts.get(position).());
        int cId= Integer.parseInt(categoryProducts.get(position).getCategory_id());
        int selid= Integer.parseInt(categoryProducts.get(position).getSeller_id());
        holder.CategoriesProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, FilteredCategoryProductsActivity.class);
               intent.putExtra("cId",cId);
               intent.putExtra("selid",selid);
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryProducts.size();
    }

    public class categoriesNearByItemHolder extends RecyclerView.ViewHolder {
        TextView categoriesName,categoriesCount;
        LinearLayout CategoriesProducts;
        public categoriesNearByItemHolder(@NonNull View itemView) {
            super(itemView);
            CategoriesProducts=itemView.findViewById(R.id.CategoriesProducts);
            categoriesCount=itemView.findViewById(R.id.categoriesCount);
            categoriesName=itemView.findViewById(R.id.categoriesName);
        }
    }
}
