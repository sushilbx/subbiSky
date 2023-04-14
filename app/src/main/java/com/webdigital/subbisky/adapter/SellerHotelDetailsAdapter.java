package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SellerHotelActivity;
import com.webdigital.subbisky.activity.SellerHotelListEditActivity;
import com.webdigital.subbisky.model.SellerHotelDeleteModel;
import com.webdigital.subbisky.model.SellerHotelListModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerHotelDetailsAdapter extends RecyclerView.Adapter<SellerHotelDetailsAdapter.SellerHotelViewHolder> {

    private List<SellerHotelListModel.SellerHotel> sellerHotels = new ArrayList<>();
    private Context context;
    private WebView mWebview;
    public SellerHotelDetailsAdapter(List<SellerHotelListModel.SellerHotel> sellerHotels, Context context) {
        this.sellerHotels = sellerHotels;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerHotelDetailsAdapter.SellerHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_row_design_hotel_list, null);
        return new SellerHotelDetailsAdapter.SellerHotelViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerHotelDetailsAdapter.SellerHotelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final SellerHotelListModel.SellerHotel Item = sellerHotels.get(position);


        holder.sellerhotelname.setText(sellerHotels.get(position).getName());
        holder.sellerhotelprice.setText(sellerHotels.get(position).getPrice());
        holder.sellerhotelstatus.setText(sellerHotels.get(position).getStatus());
        Glide.with(holder.itemView)
                .load(Item.getImage()).fitCenter().into(holder.sellerhotelimage1);
        Glide.with(holder.itemView)
                .load(Item.getImageTwo()).fitCenter().into(holder.sellerhotelimage2);
        Glide.with(holder.itemView)
                .load(Item.getImageThree()).fitCenter().into(holder.sellerhotelimage3);
        Glide.with(holder.itemView)
                .load(Item.getImageFour()).fitCenter().into(holder.sellerhotelimage4);
        Glide.with(holder.itemView)
                .load(Item.getImageFive()).fitCenter().into(holder.sellerhotelimage5);
        holder.edithotellist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hotel_id = sellerHotels.get(position).getId();
                int sellerid = sellerHotels.get(position).getSeller_id();

               // String TOASTID =sellerHotels.get(position).getId().toString();
               // Toast.makeText(context, TOASTID, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context, SellerHotelListEditActivity.class);
                intent.putExtra("hotel_id",hotel_id);
                intent.putExtra("newposition",position);
                intent.putExtra("sellerid",sellerid);


            context.startActivity(intent);
            }

        });
        holder.deletehotellist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(sellerHotels.get(position).getId());
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<SellerHotelDeleteModel> call = RetrofitClient.getInstance().getApi().deleteSellerHotel(auth,id);
                call.enqueue(new Callback<SellerHotelDeleteModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SellerHotelDeleteModel> call, Response<SellerHotelDeleteModel> response) {
                        if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, SellerHotelActivity.class);
                            context.startActivity(intent);
//                           refreshPage();

                        }else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SellerHotelDeleteModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }



        });

    }

    private void setContentView(WebView mWebview) {
    }

    @Override
    public int getItemCount() {
        return sellerHotels.size();
    }

    public class SellerHotelViewHolder extends RecyclerView.ViewHolder {
        ImageView edithotellist,deletehotellist,sellerhotelimage1,sellerhotelimage2,sellerhotelimage3,sellerhotelimage4,sellerhotelimage5;
        TextView sellerhotelname,sellerhotelprice,sellerhotelstatus;

        public SellerHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            edithotellist=itemView.findViewById(R.id.edithotellist);
            deletehotellist=itemView.findViewById(R.id.deletehotellist);
            sellerhotelimage1=itemView.findViewById(R.id.sellerhotelimage1);
            sellerhotelimage2=itemView.findViewById(R.id.sellerhotelimage2);
            sellerhotelimage3=itemView.findViewById(R.id.sellerhotelimage3);
            sellerhotelimage4=itemView.findViewById(R.id.sellerhotelimage4);
            sellerhotelimage5=itemView.findViewById(R.id.sellerhotelimage5);
            sellerhotelname=itemView.findViewById(R.id.sellerhotelname);
            sellerhotelprice=itemView.findViewById(R.id.sellerhotelprice);
            sellerhotelstatus=itemView.findViewById(R.id.sellerhotelstatus);

        }
    }
}
