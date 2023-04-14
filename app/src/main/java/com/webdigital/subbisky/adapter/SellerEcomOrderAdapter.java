package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SellerOrderActivity;
import com.webdigital.subbisky.activity.SellerOrderDetailsActivity;
import com.webdigital.subbisky.model.SellerOrderUpdateStatusModel;
import com.webdigital.subbisky.model.SellerServiceOrderModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerEcomOrderAdapter extends RecyclerView.Adapter<SellerEcomOrderAdapter.sellerOrderAdapterHolder> {
    private List<SellerServiceOrderModel.ServiceOrderList> serviceOrderList = new ArrayList<>();
    private Context context;

    public SellerEcomOrderAdapter(List<SellerServiceOrderModel.ServiceOrderList> serviceOrderList, Context context) {
        this.serviceOrderList = serviceOrderList;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerEcomOrderAdapter.sellerOrderAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_ecom_order_list, null);
        return new SellerEcomOrderAdapter.sellerOrderAdapterHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerEcomOrderAdapter.sellerOrderAdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final SellerServiceOrderModel.ServiceOrderList Item = serviceOrderList.get(position);

        holder.sellerservicebookingId.setText(serviceOrderList.get(position).getOrder_id());
        holder.customerpp.setText("₹ " +serviceOrderList.get(position).getPayable_price());
        holder.adminComission.setText("₹ " +serviceOrderList.get(position).getCommision_amount());
        holder.yourAmount.setText("₹ " +serviceOrderList.get(position).getPayable_amount_seller());
        holder.customerName.setText(serviceOrderList.get(position).getName());
        holder.customerEmail.setText(serviceOrderList.get(position).getEmail());
        holder.customerPhone.setText(serviceOrderList.get(position).getPhone());
        holder.customerBookingStatus.setText(serviceOrderList.get(position).getStatus());
        Log.e("UpdatedStatus",holder.customerBookingStatus.getText().toString());
        holder.customerBookingDate.setText(serviceOrderList.get(position).getCreated_at());
        holder.sellerservicebookingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderId =  serviceOrderList.get(position).getOrder_id();
                Intent intent = new Intent(context, SellerOrderDetailsActivity.class);
                intent.putExtra("orderId",orderId);
                context.startActivity(intent);
            }
        });
        holder.changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();

                PopupWindow builder;
//                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialougView = LayoutInflater.from(v.getContext()).inflate(R.layout.sellerorderstatuspoup, null);
                int widt = LinearLayout.LayoutParams.MATCH_PARENT;
                int hight = LinearLayout.LayoutParams.WRAP_CONTENT;
                Boolean focus = true;

                builder = new PopupWindow(dialougView, widt, hight, focus);

                builder.setOutsideTouchable(false);
                builder.setFocusable(true);

                // Removes default black background
                builder.setBackgroundDrawable(new BitmapDrawable());
                Spinner OrderStatus;
                Button cancelBtn,ChangeStatusBtn;
//                cancelBtn = dialougView.findViewById(R.id.cancelBtn);
                OrderStatus = dialougView.findViewById(R.id.OrderStatus);
                ChangeStatusBtn = dialougView.findViewById(R.id.ChangeStatusBtn);
//                cancelBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        builder.dismiss();
//                    }
//                });
                ChangeStatusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String Status;
                        String orderId =  serviceOrderList.get(position).getOrder_id();
                        Status = OrderStatus.getSelectedItem().toString();
                        Log.e("Status",Status);
                        Session session = new Session(context);
                        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                        Call<SellerOrderUpdateStatusModel> call = RetrofitClient.getInstance().getApi().sellerOrderUpdate(auth,orderId,Status);
                        call.enqueue(new Callback<SellerOrderUpdateStatusModel>() {
                            @Override
                            public void onResponse(Call<SellerOrderUpdateStatusModel> call, Response<SellerOrderUpdateStatusModel> response) {
                                if (response.isSuccessful()){
                                    Intent intent = new Intent(context, SellerOrderActivity.class);
//                                    intent.putExtra("orderId",orderId);
                                    context.startActivity(intent);
//                                    context.finish();

                                }
                            }

                            @Override
                            public void onFailure(Call<SellerOrderUpdateStatusModel> call, Throwable t) {

                            }
                        });
                    }
                });

                builder.showAtLocation(v, Gravity.CENTER, 0, 0);

            }
        });



    }

    @Override
    public int getItemCount() {
        return serviceOrderList.size();
    }

    public class sellerOrderAdapterHolder extends RecyclerView.ViewHolder {
        TextView sellerservicebookingId,customerpp,adminComission,yourAmount,customerName,customerEmail,customerPhone,customerBookingStatus,customerBookingDate;
        Button changeStatus;
        public sellerOrderAdapterHolder(@NonNull View itemView) {
            super(itemView);
            changeStatus=itemView.findViewById(R.id.changeStatus);
            sellerservicebookingId=itemView.findViewById(R.id.sellerservicebookingId);
            customerpp=itemView.findViewById(R.id.customerpp);
            adminComission=itemView.findViewById(R.id.adminComission);
            yourAmount=itemView.findViewById(R.id.yourAmount);
            customerName=itemView.findViewById(R.id.customerName);
            customerEmail=itemView.findViewById(R.id.customerEmail);
            customerPhone=itemView.findViewById(R.id.customerPhone);
            customerBookingStatus=itemView.findViewById(R.id.customerBookingStatus);
            customerBookingDate=itemView.findViewById(R.id.customerBookingDate);

        }
    }
}
