package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerQuotesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SellerQuotesAdapter extends RecyclerView.Adapter<SellerQuotesAdapter.SellerQuotesViewHolder> {
    private List<SellerQuotesModel.SellerQuotes> sellerQuotes = new ArrayList<>();
    private Context context;

    public SellerQuotesAdapter(List<SellerQuotesModel.SellerQuotes> sellerQuotes, Context context) {
        this.sellerQuotes = sellerQuotes;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerQuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_quotes_list, null);
        return new SellerQuotesViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerQuotesViewHolder holder, int position) {
        holder.sellerquotesname.setText(sellerQuotes.get(position).getName());
        holder.sellerquotesphone.setText(sellerQuotes.get(position).getPhone());
        holder.sellerquotesemail.setText(sellerQuotes.get(position).getEmail());
        holder.sellerquotesmessage.setText(sellerQuotes.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return sellerQuotes.size();
    }

    public static class SellerQuotesViewHolder extends RecyclerView.ViewHolder {
        TextView sellerquotesname,sellerquotesphone,sellerquotesemail,sellerquotesmessage;
        public SellerQuotesViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerquotesname = itemView.findViewById(R.id.sellerquotesname);
            sellerquotesphone = itemView.findViewById(R.id.sellerquotesphone);
            sellerquotesemail = itemView.findViewById(R.id.sellerquotesemail);
            sellerquotesmessage = itemView.findViewById(R.id.sellerquotesmessage);
        }
    }
}
