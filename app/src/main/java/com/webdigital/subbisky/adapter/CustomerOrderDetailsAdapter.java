package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
//import CustomerOrderDescriptionActivity;
import com.webdigital.subbisky.activity.CustomerOrderDescriptionActivity;
import com.webdigital.subbisky.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDetailsAdapter extends RecyclerView.Adapter<CustomerOrderDetailsAdapter.customerOrderDetailsHolder>{
    private List<UserModel.Orders> ordersLists = new ArrayList<>();
    private Context context;

    public CustomerOrderDetailsAdapter(List<UserModel.Orders> ordersLists, Context context) {
        this.ordersLists = ordersLists;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerOrderDetailsAdapter.customerOrderDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_customer_order_details, null);
        return new CustomerOrderDetailsAdapter.customerOrderDetailsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderDetailsAdapter.customerOrderDetailsHolder holder, int position) {
        holder.customerOrderId.setText(ordersLists.get(position).getOrder_id());
        holder.customerbuydate.setText(ordersLists.get(position).getCreated_at());
        holder.customerstatus.setText(ordersLists.get(position).getStatus());
        holder.customerpaymentmode.setText(ordersLists.get(position).getPayment_mode());
        holder.total.setText("₹ " +ordersLists.get(position).getPayable_price());
        int Order_id=ordersLists.get(position).getId();

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
            customerpaymentmode=itemView.findViewById(R.id.customerpaymentmode);
            total=itemView.findViewById(R.id.total);
            customerorderdetails=itemView.findViewById(R.id.customerorderdetails);

        }
    }
}
