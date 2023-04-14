package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SellerCategoryActivity;
import com.webdigital.subbisky.model.SellerCategoryDeleteModel;
import com.webdigital.subbisky.model.SellerCategoryModel;
import com.webdigital.subbisky.model.SellerCategoryUpdateModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerCategoryAdapter extends RecyclerView.Adapter<SellerCategoryAdapter.SellerCategoryViewHolder> {
    private List<SellerCategoryModel.SellerCategory> sellerCategories = new ArrayList<>();
    private Context context;

    public SellerCategoryAdapter(List<SellerCategoryModel.SellerCategory> sellerCategories, Context context) {
        this.sellerCategories = sellerCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_category_list_item, null);
        return new SellerCategoryViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerCategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.sellercategoryname.setText(sellerCategories.get(position).getName());
        holder.sellercategorystatus.setText(sellerCategories.get(position).getStatus());
        holder.editcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();

                PopupWindow builder;
//                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialougView = LayoutInflater.from(v.getContext()).inflate(R.layout.update_category_popup, null);
                int widt = LinearLayout.LayoutParams.WRAP_CONTENT;
                int hight = LinearLayout.LayoutParams.WRAP_CONTENT;
                Boolean focus = true;

                builder = new PopupWindow(dialougView, widt, hight, focus);

                builder.setOutsideTouchable(false);
                builder.setFocusable(true);

                // Removes default black background
                builder.setBackgroundDrawable(new BitmapDrawable());
                EditText addCatName,addCatStatus;
                Button AddCatBtn,cancelBtn;
                addCatName = dialougView.findViewById(R.id.addCatName);
                addCatStatus = dialougView.findViewById(R.id.addCatStatus);
                cancelBtn = dialougView.findViewById(R.id.cancelBtn);
                AddCatBtn = dialougView.findViewById(R.id.AddCatBtn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
                AddCatBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String id = String.valueOf(sellerCategories.get(position).getId());
                        String addCategoryName,addCategoryStatus;
                        addCategoryName = addCatName.getEditableText().toString();
                        addCategoryStatus = addCatStatus.getEditableText().toString();
                        if (!addCategoryName.isEmpty() && !addCategoryStatus.isEmpty()){
                            Session session = new Session(context);
                            String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                            Call<SellerCategoryUpdateModel> call = RetrofitClient.getInstance().getApi().updateSellerCategory(auth,id,addCategoryName,addCategoryStatus);
                            call.enqueue(new Callback<SellerCategoryUpdateModel>() {
                                @Override
                                public void onResponse(Call<SellerCategoryUpdateModel> call, Response<SellerCategoryUpdateModel> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                        refreshPage();
                                        Intent intent = new Intent(context, SellerCategoryActivity.class);
                                        context.startActivity(intent);
                                        builder.dismiss();
                                    }else {
                                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<SellerCategoryUpdateModel> call, Throwable t) {

                                }
                            });
                        }else {
                            if (addCategoryName.isEmpty()){
                                addCatName.setError("Please add Category Name");
                            }else if (addCategoryStatus.isEmpty()){
                                addCatName.setError("Please add Category Status");
                            }
                        }
                    }
                });

                builder.showAtLocation(v, Gravity.CENTER, 0, 0);

            }

            private void refreshPage() {
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<SellerCategoryModel> call = RetrofitClient.getInstance().getApi().getSellerCategory(auth);
                call.enqueue(new Callback<SellerCategoryModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SellerCategoryModel> call, Response<SellerCategoryModel> response) {
                        if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                            if(response.body().getCategories().size() != 0){
                                holder.sellercategoryname.setText(response.body().getCategories().get(position).getName());
                                holder.sellercategorystatus.setText(response.body().getCategories().get(position).getStatus());
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SellerCategoryModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        holder.deletecategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(sellerCategories.get(position).getId());
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<SellerCategoryDeleteModel> call = RetrofitClient.getInstance().getApi().deleteSellerCategory(auth,id);
                call.enqueue(new Callback<SellerCategoryDeleteModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SellerCategoryDeleteModel> call, Response<SellerCategoryDeleteModel> response) {
                        if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                           Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,SellerCategoryActivity.class);
                            context.startActivity(intent);
//                           refreshPage();

                        }else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SellerCategoryDeleteModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            private void refreshPage() {
                Session session = new Session(context);
                String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                Call<SellerCategoryModel> call = RetrofitClient.getInstance().getApi().getSellerCategory(auth);
                call.enqueue(new Callback<SellerCategoryModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SellerCategoryModel> call, Response<SellerCategoryModel> response) {
                        if (response.isSuccessful()){
//                    totalWalletAmountSeller.setText("Wallet Balance: ₹" +response.body().getAmount());
                            if(response.body().getCategories().size() != 0){
                                holder.sellercategoryname.setText(response.body().getCategories().get(position).getName());
                                holder.sellercategorystatus.setText(response.body().getCategories().get(position).getStatus());
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SellerCategoryModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }


        });

    }

    @Override
    public int getItemCount() {
        return sellerCategories.size();
    }

    public class SellerCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView sellercategoryname,sellercategorystatus;
        ImageView editcategory,deletecategory;
        public SellerCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            sellercategoryname = itemView.findViewById(R.id.sellercategoryname);
            sellercategorystatus = itemView.findViewById(R.id.sellercategorystatus);
            editcategory = itemView.findViewById(R.id.editcategory);
            deletecategory = itemView.findViewById(R.id.deletecategory);
        }
    }
}
