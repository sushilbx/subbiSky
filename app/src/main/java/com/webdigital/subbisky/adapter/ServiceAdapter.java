package com.webdigital.subbisky.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.ServiceDescriptionActivity;
import com.webdigital.subbisky.model.ServiceModel;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ServiceModel> ServiceList;

    //getting the context and product list with constructor
    public ServiceAdapter(Context mCtx, List<ServiceModel> ServiceList) {
        this.mCtx = mCtx;
        this.ServiceList = ServiceList;
    }

    @Override
    public ServiceAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_service, null);
        return new ServiceAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        ServiceModel service = ServiceList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(service.getTitle());
        holder.textViewShortDesc.setText(service.getShortdesc());

        holder.textViewCall.setText(service.getCall());
        holder.textViewEmail.setText(service.getEmail());
        holder.textViewRating.setText(String.valueOf(service.getRating()));


        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(service.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx, ServiceDescriptionActivity.class);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return ServiceList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc,textViewCall,textViewEmail, textViewRating, textViewReview,textViewQuote;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewCall = itemView.findViewById(R.id.textViewCall);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            textViewQuote = itemView.findViewById(R.id.textViewQuote);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}