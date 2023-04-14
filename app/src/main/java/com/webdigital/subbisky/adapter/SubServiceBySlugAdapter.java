package com.webdigital.subbisky.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.EcomProductDetailActivity;
import com.webdigital.subbisky.activity.ServiceDetailsPageActivity;
import com.webdigital.subbisky.activity.StayBookingActivity;
import com.webdigital.subbisky.activity.StayBookingDetailsActivity;
import com.webdigital.subbisky.activity.SubServiceBySlugActivity;
import com.webdigital.subbisky.model.AddReviewModel;
import com.webdigital.subbisky.model.SaveQuoteModel;
import com.webdigital.subbisky.model.SubServiceBySlugModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

import static com.webdigital.subbisky.adapter.HomeServiceListAdapter.cattype;
import static com.webdigital.subbisky.adapter.HomeServiceListAdapter.type1;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubServiceBySlugAdapter extends RecyclerView.Adapter<SubServiceBySlugAdapter.subServiceBySlugHolder> {
    private List<SubServiceBySlugModel.NearbyShop> nearby_shopList = new ArrayList<>();
    private Context context;
    private SubServiceBySlugModel subServiceBySlugModel;
    Dialog myDialog;
    Session session;
    Intent intent;
    String type;
    int seller_id;
public static String mainsellerid;
    public SubServiceBySlugAdapter(List<SubServiceBySlugModel.NearbyShop> nearby_shopList,String type, Context context) {
        this.nearby_shopList = nearby_shopList;
        this.type=type;
        this.context = context;
    }

    @NonNull
    @Override
    public SubServiceBySlugAdapter.subServiceBySlugHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_subservice_by_slug, null);
        return new SubServiceBySlugAdapter.subServiceBySlugHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceBySlugAdapter.subServiceBySlugHolder holder, int position) {

        final SubServiceBySlugModel.NearbyShop Item = nearby_shopList.get(position);
        int seller_id=nearby_shopList.get(position).getId();
//        final  SubServiceBySlugModel  subServiceBySlugModel= new SubServiceBySlugModel();
        String sellerName= String.valueOf(nearby_shopList.get(position).getShopName());

//        Log.e("cat_type",cat_type);
        Glide.with(holder.itemView)
                .load(Item.getShopImage()).fitCenter().into(holder.imageView);
        holder.textViewTitle.setText(nearby_shopList.get(position).getShopName());
        holder.textViewShortDesc.setText(nearby_shopList.get(position).getShopAddress());
        holder.textViewroomcapcity.setText("Room capacity: "+nearby_shopList.get(position).getRoomCapacity());
        if(type.equals("ecom")){
          holder.textViewroomcapcity.setVisibility(View.GONE);
        }
        else {
            holder.textViewroomcapcity.setVisibility(View.VISIBLE);
        }
        holder.textViewroomavailble.setText("Rooms Available: "+nearby_shopList.get(position).getAvailableRooms());
        if(type.equals("ecom")){
            holder.textViewroomavailble.setVisibility(View.GONE);
        }
        else {
            holder.textViewroomavailble.setVisibility(View.VISIBLE);
        }
        holder.textViewTime.setText("Opening-Closing Time: "+nearby_shopList.get(position).getOpenCloseTime());
        holder.textViewAboutUs.setText("About Us: "+nearby_shopList.get(position).getDescription());
        holder.textViewCall.setText(nearby_shopList.get(position).getUserId().getPhone());
        holder.textViewEmail.setText(nearby_shopList.get(position).getUserId().getEmail());
        holder.textViewAddress.setText("Address: "+nearby_shopList.get(position).getShopAddress());


        if(HomeServiceListAdapter.type.equalsIgnoreCase("ecom")){
            holder.deiaillist.setText(" ₹ Order Now");

        }

        else{
            holder.deiaillist.setText("₹ Book Now");
        }



        if(nearby_shopList.get(position).getCloseStatus().equals(1)){
            holder.deiaillist.setEnabled(false);
            holder.deiaillist.setBackgroundColor(Color.parseColor("#F47777"));
        }else{
            holder.deiaillist.setEnabled(true);
            holder.deiaillist.setBackgroundColor(Color.parseColor("#d71c32"));
        }
        holder.deiaillist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String cat_type= subServiceBySlugModel.getType();
                Log.e("cat_type", type);
                if ( type.equals("ecom")) {
                    Intent intent = new Intent(context, EcomProductDetailActivity.class);
                    intent.putExtra("seller_id", seller_id);
                    intent.putExtra("sellerName",sellerName);

                    context.startActivity(intent);
                } else if ( type.equals("staybooking")) {
                    Intent intent = new Intent(context, StayBookingDetailsActivity.class);
                    intent.putExtra("seller_id", String.valueOf(seller_id));
                    mainsellerid=String.valueOf(seller_id);
                    intent.putExtra("cat_type",  type);
                    Log.e("seller_id", String.valueOf(seller_id));
                    context.startActivity(intent);
                }
//                else {
//                    Intent intent = new Intent(context, ServiceDetailsPageActivity.class);
//                    intent.putExtra("seller_id", seller_id);
//                    intent.putExtra("cat_type", cat_type);
//                    Log.e("seller_id", String.valueOf(seller_id));
//                    context.startActivity(intent);
//                    }
            }

        });


       holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//                String cat_type= subServiceBySlugModel.getType();
               Log.e("cat_type", type);
               if ( type.equals("ecom")) {
                   Intent intent = new Intent(context, EcomProductDetailActivity.class);
                   intent.putExtra("seller_id", seller_id);
                   intent.putExtra("sellerName",sellerName);

                   context.startActivity(intent);
               } else if ( type.equals("staybooking")) {
                   Intent intent = new Intent(context, StayBookingDetailsActivity.class);
                   intent.putExtra("seller_id", String.valueOf(seller_id));
                   intent.putExtra("cat_type",  type);
                   Log.e("seller_id", String.valueOf(seller_id));
                   context.startActivity(intent);
               }
//                else {
//                    Intent intent = new Intent(context, ServiceDetailsPageActivity.class);
//                    intent.putExtra("seller_id", seller_id);
//                    intent.putExtra("cat_type", cat_type);
//                    Log.e("seller_id", String.valueOf(seller_id));
//                    context.startActivity(intent);
//                    }
           }

       });
        holder.textViewQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }



        public void ShowPopup() {
            EditText ed_name,ed_mobile,ed_email,ed_msg;
            Button btnsubmitNow;
            myDialog = new Dialog(context);
            myDialog.setContentView(R.layout.enqiury_now);
            ed_name =(EditText) myDialog.findViewById(R.id.ed_name1);
            ed_mobile =(EditText) myDialog.findViewById(R.id.ed_mobile1);
            ed_email =(EditText) myDialog.findViewById(R.id.ed_email1);
            ed_msg =(EditText) myDialog.findViewById(R.id.ed_msg1);

            btnsubmitNow =(Button) myDialog.findViewById(R.id.btnsubmitNow1);
//            intent = getIntent();
//            seller_id = intent.getIntExtra("seller_id",0);
//            Log.e("Quote_seller_id", String.valueOf(seller_id));
            btnsubmitNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name,mobile,email,msg;
                    name=ed_name.getEditableText().toString().trim();
                    mobile=ed_mobile.getEditableText().toString().trim();
                    email=ed_email.getEditableText().toString().trim();
                    msg=ed_msg.getEditableText().toString().trim();
                    Log.e("Quote_ed_name1", name);
                    Log.e("Quote_ed_mobile1", mobile);
                    Log.e("Quote_ed_email1", email);
                    Log.e("Quote_ed_msg1", msg);
                    Log.e("Quote_seller_id", String.valueOf(seller_id));
                    Call<SaveQuoteModel> call = RetrofitClient.getInstance().getApi().saveQuote(name,mobile,email,msg, String.valueOf(seller_id));
                    call.enqueue(new Callback<SaveQuoteModel>() {
                        @Override
                        public void onResponse(Call<SaveQuoteModel> call, Response<SaveQuoteModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ServiceDetailsPageActivity.this, ServiceDetailsPageActivity.class);
//                            startActivity(intent);
                                myDialog.dismiss();
                            }else
                            {
                                Toast.makeText( context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
                            }
                        }

                        @Override
                        public void onFailure(Call<SaveQuoteModel> call, Throwable throwable) {
                            Toast.makeText( context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }
        });
        holder.rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             ReviewPopup();
            }
            private void ReviewPopup() {
                EditText editReview;
                Spinner reviewSpinner;
                RatingBar ratingbar;
                Button btnsubmitNow;
                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.review_popup);
                editReview =(EditText) myDialog.findViewById(R.id.enterReview);
//        reviewSpinner =(Spinner) myDialog.findViewById(R.id.reviewSpinner);
                ratingbar =(RatingBar) myDialog.findViewById(R.id.ratingBar);
                btnsubmitNow =(Button) myDialog.findViewById(R.id.btnsubmitNow1);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Reviews,R.layout.support_simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        reviewSpinner.setAdapter(adapter);
//        reviewSpinner.setOnItemSelectedListener(this);
                btnsubmitNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name,revi,email,msg;
                        name=editReview.getEditableText().toString().trim();
                        String rating=String.valueOf(ratingbar.getRating());
                        Log.e("Quote_ed_email1", name);
                        Log.e("rating", rating);
                        session = new Session(context.getApplicationContext());
                        String auth = "Bearer " + session.getUserDetails().get(Session.TOKEN);

                        Call<AddReviewModel> call = RetrofitClient.getInstance().getApi().customerAddReview(auth,name,rating);
                        call.enqueue(new Callback<AddReviewModel>() {
                            @Override
                            public void onResponse(Call<AddReviewModel> call, Response<AddReviewModel> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText( context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ServiceDetailsPageActivity.this, ServiceDetailsPageActivity.class);
//                            startActivity(intent);
                                    myDialog.dismiss();
                                }else
                                {
                                    Toast.makeText( context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
                                }
                            }

                            @Override
                            public void onFailure(Call<AddReviewModel> call, Throwable throwable) {
                                Toast.makeText( context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
//        intent = getIntent();
//        seller_id = intent.getIntExtra("seller_id",0);
//        Log.e("Quote_seller_id", String.valueOf(seller_id));

            }
        });



    }

    @Override
    public int getItemCount() {
        return nearby_shopList.size();
    }

    public class subServiceBySlugHolder extends RecyclerView.ViewHolder {
//        public BreakIterator ;

        ImageView imageView;
        TextView textViewroomcapcity,rateus,textViewTitle,textViewAddress,textViewShortDesc,textViewTime,textViewAboutUs,textViewCall,deiaillist,
                textViewEmail,textViewReview,textViewQuote,textViewRating,textViewroomavailble;
      //  LinearLayout deiaillist;
        public subServiceBySlugHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc=itemView.findViewById(R.id.textViewShortDesc);
            textViewAddress=itemView.findViewById(R.id.textViewAddress);
            textViewTime=itemView.findViewById(R.id.textViewTime);
            textViewAboutUs=itemView.findViewById(R.id.textViewAboutUs);
            textViewCall=itemView.findViewById(R.id.textViewCall);
            textViewEmail=itemView.findViewById(R.id.textViewEmail);
            textViewReview=itemView.findViewById(R.id.textViewReview);
            textViewQuote=itemView.findViewById(R.id.textViewQuote);
            textViewRating=itemView.findViewById(R.id.textViewRating);
            deiaillist=itemView.findViewById(R.id.ordernow);
            rateus=itemView.findViewById(R.id.rateus);
            textViewroomcapcity=itemView.findViewById(R.id.textViewroomcapcity);
            textViewroomavailble=itemView.findViewById(R.id.textViewroomavailble);

        }
    }

}
