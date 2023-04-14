package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.CustomerOrderDescriptionActivity;
import com.webdigital.subbisky.model.UserModel;
import com.webdigital.subbisky.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyserviceListAdapter extends RecyclerView.Adapter<MyserviceListAdapter.customerOrderDetailsHolder>{
    private List<UserModel.Services> ordersLists = new ArrayList<>();
    private Context context;

    public MyserviceListAdapter(List<UserModel.Services> ordersLists, Context context) {
        this.ordersLists = ordersLists;
        this.context = context;
    }

    @NonNull
    @Override
    public customerOrderDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.myservice_list_item, null);
        return new customerOrderDetailsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull customerOrderDetailsHolder holder, int position) {
        holder.customerOrderId.setText(ordersLists.get(position).getOrder_id());
        holder.customerbuydate.setText(ordersLists.get(position).getCreated_at());
        holder.customerstatus.setText(ordersLists.get(position).getStatus());
        holder.total.setText("₹ " +ordersLists.get(position).getPayable_price());
        int Order_id= Integer.parseInt(ordersLists.get(position).getId());

        holder.customerorderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CustomerOrderDescriptionActivity.class);
                intent.putExtra("Order_id",Order_id);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersLists.size();
    }

    public class customerOrderDetailsHolder extends RecyclerView.ViewHolder {
        TextView customerOrderId,customerbuydate,customerstatus,customerpaymentmode,total,customerorderdetails;

        public customerOrderDetailsHolder(@NonNull View itemView) {
            super(itemView);
            customerOrderId=itemView.findViewById(R.id.customerOrderId);
            customerbuydate=itemView.findViewById(R.id.customerbuydate);
            customerstatus=itemView.findViewById(R.id.customerstatus);
            total=itemView.findViewById(R.id.total);
            customerorderdetails=itemView.findViewById(R.id.customerorderdetails);

        }
    }
}
