package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerWalletModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SellerWalletAdapter extends RecyclerView.Adapter<SellerWalletAdapter.SellerWalletViewHolder> {
    private List<SellerWalletModel.SellerWallet> sellerWallets = new ArrayList<>();
    private Context context;

    public SellerWalletAdapter(List<SellerWalletModel.SellerWallet> sellerWallets, Context context) {
        this.sellerWallets = sellerWallets;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerWalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_wallet_item, null);
        return new SellerWalletViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerWalletViewHolder holder, int position) {
        holder.sellerWalletAmount.setText(sellerWallets.get(position).getAmount());
        holder.sellerWalletStatus.setText(sellerWallets.get(position).getStatus());
        holder.sellerWalletOrderId.setText(sellerWallets.get(position).getOrder_id());
    }

    @Override
    public int getItemCount() {
        return sellerWallets.size();
    }

    public class SellerWalletViewHolder extends RecyclerView.ViewHolder {
        TextView sellerWalletAmount,sellerWalletStatus,sellerWalletOrderId;
        public SellerWalletViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerWalletAmount = itemView.findViewById(R.id.sellerWalletAmount);
            sellerWalletStatus = itemView.findViewById(R.id.sellerWalletStatus);
            sellerWalletOrderId = itemView.findViewById(R.id.sellerWalletOrderId);
        }
    }
}
