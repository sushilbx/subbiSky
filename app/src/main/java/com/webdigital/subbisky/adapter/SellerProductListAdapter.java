package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SellerProductListActivity;
import com.webdigital.subbisky.activity.SellerProductListEditActivity;
import com.webdigital.subbisky.model.SellerProductDeleteModel;
import com.webdigital.subbisky.model.SellerProductModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerProductListAdapter extends  RecyclerView.Adapter<SellerProductListAdapter.sellerProductListHolder> {
    private List<SellerProductModel.SellerProductList> sellerProductList = new ArrayList<>();
    private Context context;

    public SellerProductListAdapter(List<SellerProductModel.SellerProductList> sellerProductList, Context context) {
        this.sellerProductList = sellerProductList;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerProductListAdapter.sellerProductListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_seller_product_list, null);
        return new SellerProductListAdapter.sellerProductListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerProductListAdapter.sellerProductListHolder holder, @SuppressLint("RecyclerView") int position) {
        final SellerProductModel.SellerProductList Item = sellerProductList.get(position);

        Glide.with(holder.itemView)
                .load(Item.getImage()).fitCenter().into(holder.sellerproductimage);
        holder.sellerproductname.setText(sellerProductList.get(position).getName());
        holder.sellerproductmrpprice.setText(sellerProductList.get(position).getMrp_price());
        holder.sellerproductsellingprice.setText(sellerProductList.get(position).getSelling_price());
        holder.sellerproductcategory.setText(sellerProductList.get(position).getId());
        holder.sellerproductstatus.setText(sellerProductList.get(position).getStatus());
        int productId= Integer.parseInt(sellerProductList.get(position).getId());

        holder.editproductlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SellerProductListEditActivity.class);
                intent.putExtra("productId",productId);
                context.startActivity(intent);
            }
        });
        holder.deleteproductlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(sellerProductList.get(position).getId());
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<SellerProductDeleteModel> call = RetrofitClient.getInstance().getApi().sellerProductDelete(auth,id);
                call.enqueue(new Callback<SellerProductDeleteModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SellerProductDeleteModel> call, Response<SellerProductDeleteModel> response) {
                        if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, SellerProductListActivity.class);
                            context.startActivity(intent);
//                           refreshPage();

                        }else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SellerProductDeleteModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return sellerProductList.size();
    }

    public class sellerProductListHolder extends RecyclerView.ViewHolder {
        ImageView sellerproductimage,editproductlist,deleteproductlist;
        TextView sellerproductname,sellerproductmrpprice,sellerproductsellingprice,sellerproductcategory,sellerproductstatus;

        public sellerProductListHolder(@NonNull View itemView) {
            super(itemView);
            sellerproductimage=itemView.findViewById(R.id.sellerproductimage);
            editproductlist=itemView.findViewById(R.id.editproductlist);
            deleteproductlist=itemView.findViewById(R.id.deleteproductlist);
            sellerproductname=itemView.findViewById(R.id.sellerproductname);
            sellerproductmrpprice=itemView.findViewById(R.id.sellerproductmrpprice);
            sellerproductsellingprice=itemView.findViewById(R.id.sellerproductsellingprice);
            sellerproductcategory=itemView.findViewById(R.id.sellerproductcategory);
            sellerproductstatus=itemView.findViewById(R.id.sellerproductstatus);
        }
    }
}
