package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.CartListActivity;
import com.webdigital.subbisky.model.CartDecreaseModel;
import com.webdigital.subbisky.model.CartDeleteModel;
import com.webdigital.subbisky.model.CartIncreaseModel;
import com.webdigital.subbisky.model.CartListModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.cartViewHolder>{
    private List<CartListModel.Carts> cartsList = new ArrayList<>();
    private Context context;
public static String cartscreen= "no";
    public CartListAdapter(List<CartListModel.Carts> cartsList, Context context) {
        this.cartsList = cartsList;
        this.context = context;
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.maincart_item, null);
        return new cartViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cartname.setText(cartsList.get(position).getProduct_id().getName());
        holder.cartunits.setText("Units:"+cartsList.get(position).getProduct_id().getUnit());
        holder.cartquantity.setText(cartsList.get(position).getQuantity());
        Glide.with(holder.itemView)
                .load(cartsList.get(position).getProduct_id().getImage()).fitCenter().into(holder.cartimage);

        holder.cartprice.setText("Unit price:Rs"+cartsList.get(position).getProduct_id().getSelling_price());
        holder.carttotal.setText("Total price:Rs"+Integer.parseInt(cartsList.get(position).getProduct_id().getSelling_price()) * Integer.valueOf(cartsList.get(position).getQuantity()));
        holder.cartincrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cartId = cartsList.get(position).getId();
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                int q=Integer.parseInt(holder.cartquantity.getText().toString());
                holder.cartquantity.setText(String.valueOf(q+1));
                Call<CartIncreaseModel> call = RetrofitClient.getInstance().getApi().increaseQuantity(auth,cartId);
                call.enqueue(new Callback<CartIncreaseModel>() {
                    @Override
                    public void onResponse(Call<CartIncreaseModel> call, Response<CartIncreaseModel> response) {
                        Log.d("TAG", "onResponsebb: " + response.code());

                        if (response.isSuccessful()) {
                            cartscreen="yes";
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, CartListActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
//                            AppCompatActivity activity = (AppCompatActivity) context;
//                            Fragment fm = new CartFragment();
//                            activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).
//                                    replace(R.id.nav_host_fragment, fm).commit();
//                            progressDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartIncreaseModel> call, Throwable t) {
//                        progressDialog.dismiss();
                    }
                });
            }
        });
        holder.cartdecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cartId = cartsList.get(position).getId();
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
                int q=Integer.parseInt(holder.cartquantity.getText().toString());
//                holder.cartquantity.setText(String.valueOf(q+1));
                holder.cartquantity.setText(String.valueOf(q-1));
                    Call<CartDecreaseModel> call = RetrofitClient.getInstance().getApi().decreaseQuantity(auth,cartId);
                    call.enqueue(new Callback<CartDecreaseModel>() {
                        @Override
                        public void onResponse(Call<CartDecreaseModel> call, Response<CartDecreaseModel> response) {
                            Log.d("TAG", "onResponsebb: " + response.code());
                            Log.d("checkk", "onResponse: "+ String.valueOf(cartsList.get(position).getId())+ String.valueOf(cartsList.get(position).getId()));
                            if (response.isSuccessful()) {
                                cartscreen="yes";
                                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, CartListActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent);
//                                AppCompatActivity activity = (AppCompatActivity) context;
//                                Fragment fm = new CartFragment();
//                                activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).
//                                        replace(R.id.nav_host_fragment, fm).commit();
//                                progressDialog.dismiss();
                            }
                            else
                            {
                                holder.cartquantity.setText(String.valueOf(q+1));
                                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
//                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<CartDecreaseModel> call, Throwable t) {
//                            progressDialog.dismiss();
                        }
                    });
                }
//                else
//                {
//                    Toast.makeText(context,"This is the minimum quantity.",Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            }
        });
        holder.deletecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cartId = cartsList.get(position).getId();
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);
//                int q=Integer.parseInt(holder.cartquantity.getText().toString());
////                holder.cartquantity.setText(String.valueOf(q+1));
//                holder.cartquantity.setText(String.valueOf(q-1));
                Call<CartDeleteModel> call = RetrofitClient.getInstance().getApi().deleteCart(auth,cartId);
                call.enqueue(new Callback<CartDeleteModel>() {
                    @Override
                    public void onResponse(Call<CartDeleteModel> call, Response<CartDeleteModel> response) {
                        Log.d("TAG", "onResponsebb: " + response.code());
                        Log.d("checkk", "onResponse: "+ String.valueOf(cartsList.get(position).getId())+ String.valueOf(cartsList.get(position).getId()));
                        if (response.isSuccessful()) {
                            cartscreen="yes";
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, CartListActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
//                                AppCompatActivity activity = (AppCompatActivity) context;
//                                Fragment fm = new CartFragment();
//                                activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).
//                                        replace(R.id.nav_host_fragment, fm).commit();
//                                progressDialog.dismiss();
                        }
                        else
                        {
//                            holder.cartquantity.setText(String.valueOf(q+1));
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
//                                progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartDeleteModel> call, Throwable t) {
//                            progressDialog.dismiss();
                    }
                });
            }
//                else
//                {
//                    Toast.makeText(context,"This is the minimum quantity.",Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            }
        });
    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder {
        TextView cartname,cartunits,cartquantity,carttotal,cartprice;

        ImageView deletecart,cartimage,cartincrease,cartdecrease;
        public cartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartimage = itemView.findViewById(R.id.product_image);
            cartprice = itemView.findViewById(R.id.product_price);
            cartname = itemView.findViewById(R.id.product_name);
            cartunits = itemView.findViewById(R.id.unitName);
            cartquantity = itemView.findViewById(R.id.txt_qty);
            carttotal = itemView.findViewById(R.id.total_price);
            cartincrease = itemView.findViewById(R.id.image_plus);
            cartdecrease = itemView.findViewById(R.id.image_minus);
            deletecart = itemView.findViewById(R.id.deleteImage);
        }
    }
}
