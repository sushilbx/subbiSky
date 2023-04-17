package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.CustomerOrderListModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderProductDetailsAdapter  extends RecyclerView.Adapter<CustomerOrderProductDetailsAdapter.customerOrderProductDetailsHolder>{
    private List<CustomerOrderListModel.ListProduct> ordersLists = new ArrayList<>();
    private Context context;

    public CustomerOrderProductDetailsAdapter(List<CustomerOrderListModel.ListProduct> ordersLists, Context context) {
        this.ordersLists = ordersLists;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerOrderProductDetailsAdapter.customerOrderProductDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_customer_order_product_details, null);
        return new CustomerOrderProductDetailsAdapter.customerOrderProductDetailsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderProductDetailsAdapter.customerOrderProductDetailsHolder holder, int position) {
//        final CustomerOrderListModel.OrderedProductDetail Item = ordersLists.get(position);

        Glide.with(holder.itemView)
                .load(ordersLists.get(position).product_id.image).into(holder.productImage);
        holder.productName.setText(ordersLists.get(position).product_id.name);
        holder.productPrice.setText(ordersLists.get(position).product_id.selling_price);
        holder.productQuantity.setText(ordersLists.get(position).quantity);
        holder.productTotal.setText(ordersLists.get(position).selling_price);
    }

    @Override
    public int getItemCount() {
        return ordersLists.size();
    }

    public class customerOrderProductDetailsHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice,productQuantity,productTotal;
        ImageView productImage;

        public customerOrderProductDetailsHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            productPrice=itemView.findViewById(R.id.productPrice);
            productQuantity=itemView.findViewById(R.id.productQuantity);
            productTotal=itemView.findViewById(R.id.productTotal);
            productImage=itemView.findViewById(R.id.productImage);

        }
    }
}
