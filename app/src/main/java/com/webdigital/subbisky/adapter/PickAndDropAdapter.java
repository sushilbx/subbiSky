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
import com.webdigital.subbisky.activity.PickAndDropDescriptionActivity;
import com.webdigital.subbisky.model.PickAndDropModel;

import java.util.List;

public class PickAndDropAdapter extends RecyclerView.Adapter<PickAndDropAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<PickAndDropModel> PickList;

    //getting the context and product list with constructor
    public PickAndDropAdapter(Context mCtx, List<PickAndDropModel> PickList) {
        this.mCtx = mCtx;
        this.PickList = PickList;
    }

    @Override
    public PickAndDropAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_pick_and_drop, null);
        return new PickAndDropAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PickAndDropAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        PickAndDropModel PickAndDrop = PickList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(PickAndDrop.getTitle());
        holder.textViewShortDesc.setText(PickAndDrop.getShortdesc());

        holder.textViewCall.setText(PickAndDrop.getCall());
        holder.textViewEmail.setText(PickAndDrop.getEmail());
        holder.textViewRating.setText(String.valueOf(PickAndDrop.getRating()));


        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(PickAndDrop.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx, PickAndDropDescriptionActivity.class);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return PickList.size();
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