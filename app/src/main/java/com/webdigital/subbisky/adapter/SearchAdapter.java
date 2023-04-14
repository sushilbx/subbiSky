package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.AddToCartDescription;
import com.webdigital.subbisky.activity.EcomProductDetailActivity;
import com.webdigital.subbisky.activity.SubServicesListActivity;
import com.webdigital.subbisky.model.SearchResModel;
import com.webdigital.subbisky.model.Searchlistnewmodel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class   SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    List<Searchlistnewmodel> searchlist = new ArrayList<>();
    Context context;

    public SearchAdapter(List<Searchlistnewmodel> searchlist, Context context) {
        this.searchlist = searchlist;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.search_name.setText(searchlist.get(position).getName());
        holder.search_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(searchlist.get(position).getType().equalsIgnoreCase("service")){
                    String  serviceName = searchlist.get(position).getName();
                    String  serviceId = String.valueOf(searchlist.get(position).getId());
                    String  type = searchlist.get(position).getType();
                    Intent intent=new Intent(context, SubServicesListActivity.class);
                    intent.putExtra("serviceName",serviceName);
                    intent.putExtra("serviceId",serviceId);
                    intent.putExtra("type",type);
                    context.startActivity(intent);
                }
                else if(searchlist.get(position).getType().equalsIgnoreCase("products")){

                    Integer  productId = Integer.valueOf(searchlist.get(position).getId());
                    Integer  sellerId = Integer.valueOf(searchlist.get(position).getExtraid().toString());
                    Intent intent=new Intent(context, AddToCartDescription.class);
                    intent.putExtra("sellerId",sellerId);
                    intent.putExtra("productId",productId);
                    context.startActivity(intent);
                }
                 else if(searchlist.get(position).getType().equalsIgnoreCase("vendor")){

                    Integer  sellerid = Integer.valueOf(searchlist.get(position).getId());
                    String  sellername = String.valueOf(searchlist.get(position).getName());
                    String  type = searchlist.get(position).getType();
                    Intent intent=new Intent(context, EcomProductDetailActivity.class);

                    intent.putExtra("seller_id",sellerid);
                    intent.putExtra("sellerName",sellername);
                    intent.putExtra("type",type);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return searchlist.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView search_name;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            search_name = itemView.findViewById(R.id.search_name);
        }
    }
}
