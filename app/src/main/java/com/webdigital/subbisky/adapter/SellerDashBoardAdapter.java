package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.model.SellerDashboardModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SellerDashBoardAdapter extends RecyclerView.Adapter<SellerDashBoardAdapter.SellerDashBoardViewHolder> {
    private List<SellerDashboardModel> sellerDashboardModels = new ArrayList<>();
    private Context context;

    public SellerDashBoardAdapter(List<SellerDashboardModel> sellerDashboardModels, Context context) {
        this.sellerDashboardModels = sellerDashboardModels;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerDashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_dashboard_item, null);
        return new SellerDashBoardViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerDashBoardViewHolder holder, int position) {
//        holder.itemName.setText(sellerDashboardModels.get(position).);
//        holder.itemCount.setText(sellerDashboardModels.get(position).ge());
//        holder.itemName.setText(sellerDashboardModels.get(position).getStageName());
//
//        if (sellerDashboardModels.get(position).get() != null) {
//            onStatusActive.OnStatus(position, dashBoardMoodel.get(0).getStatus());
//
//        }else{
//
//        }
    }


    @Override
    public int getItemCount() {
        return sellerDashboardModels.size();
    }

    public class SellerDashBoardViewHolder extends RecyclerView.ViewHolder {

        TextView itemName,itemCount;
        CardView dashBoardCard;
        public SellerDashBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemCount = itemView.findViewById(R.id.itemCount);
            dashBoardCard = itemView.findViewById(R.id.dashBoardCard);
        }
    }
    public interface OnStatusActive {
        public void OnStatus(int position, String status);

    }
}
