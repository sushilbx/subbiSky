package com.webdigital.subbisky.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.SubServiceBySlugActivity;
import com.webdigital.subbisky.model.SubServiceListModel;

import java.util.ArrayList;
import java.util.List;

public class SubServiceListAdapter extends RecyclerView.Adapter<SubServiceListAdapter.subServiceListHolder> {
    public static List<SubServiceListModel.SubListClass> subServiceLists = new ArrayList<>();
    private Context context;

    public SubServiceListAdapter(List<SubServiceListModel.SubListClass> subServiceLists, Context context) {
        this.subServiceLists = subServiceLists;
        this.context = context;
    }

    @NonNull
    @Override
    public SubServiceListAdapter.subServiceListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.subservicelistitem, null);
        return new SubServiceListAdapter.subServiceListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceListAdapter.subServiceListHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.subServiceSlugName.setText(subServiceLists.get(position).getName());
        holder.subServicesListItemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slugName=subServiceLists.get(position).getSlug();
                Intent intent=new Intent(context, SubServiceBySlugActivity.class);
                intent.putExtra("slugName",slugName);
                intent.putExtra("type","ecom");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subServiceLists.size();
    }

    public class subServiceListHolder extends RecyclerView.ViewHolder {
        CardView subServicesListItemCard;
        TextView subServiceSlugName;
        public subServiceListHolder(@NonNull View itemView) {
            super(itemView);
            subServicesListItemCard=itemView.findViewById(R.id.subServicesListItemCard);
            subServiceSlugName=itemView.findViewById(R.id.subServiceSlugName);


        }
    }
}
