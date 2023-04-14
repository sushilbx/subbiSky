package com.webdigital.subbisky.adapter;

import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.addroomrecyler;
import static com.webdigital.subbisky.activity.StaybookroomaddingActivity.items;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.ServiceDescriptionActivity;
import com.webdigital.subbisky.activity.StaybookroomaddingActivity;
import com.webdigital.subbisky.model.Addroompojo;
import com.webdigital.subbisky.model.ServiceModel;

import java.util.List;


public class AddroomAdapter extends RecyclerView.Adapter<AddroomAdapter.ProductViewHolder> {

    Dialog myDialog;
    //private Context context;
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Addroompojo> ServiceList;

    //getting the context and product list with constructor
    public AddroomAdapter(Context mCtx, List<Addroompojo> ServiceList) {
        this.mCtx = mCtx;
        this.ServiceList = ServiceList;
    }

    @Override
    public AddroomAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.addrooms_item, null);
        return new AddroomAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddroomAdapter.ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Addroompojo service = ServiceList.get(position);

        //binding the data with the viewholder views
       if(Integer.parseInt(service.getadult().toString())==0){
           holder.adult.setText("");
           holder.adult.setHint("Adults");
       }
       else {
           holder.adult.setText(service.getadult().toString());
       }
        holder.adult.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                //items.add(new Addroompojo("0","0","1"));

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                items.get(position).setadult(s.toString());
                items.get(position).setsno("2");
                items.get(position).setcheck("checked");
            }
        });
         //  holder.adult.setText(service.getadult());
        if(Integer.parseInt(service.getchildren().toString())==0){
            holder.children.setText("");
            holder.children.setHint("childrens");
        }
        else {
            holder.children.setText(service.getchildren().toString());
        }
        holder.children.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                items.get(position).setchildren(s.toString());
                items.get(position).setsno("2");
                items.get(position).setcheck("checked");
            }
        });
       // holder.children.setText(service.getchildren());


//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               String adult=service.getadult().toString();
//               String child=service.getchildren().toString();
//                ShowPopup(adult,child,position);
//            }
//        });
//
if(position==0){
    holder.delt.setVisibility(View.GONE);
}

       else{
           holder.delt.setVisibility(View.VISIBLE);
}

           holder.delt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            items.remove(position);
            AddroomAdapter adapter = new AddroomAdapter(mCtx, items);

            //setting adapter to recyclerview
            addroomrecyler.setHasFixedSize(true);
            addroomrecyler.setLayoutManager(new LinearLayoutManager(mCtx,LinearLayoutManager.VERTICAL, false));

            addroomrecyler.setAdapter(adapter);
        }
    });


    }


    @Override
    public int getItemCount() {
        return ServiceList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView edit,delt;
EditText adult,children;

        public ProductViewHolder(View itemView) {
            super(itemView);

            adult = itemView.findViewById(R.id.adults);
            children = itemView.findViewById(R.id.children);
            edit = itemView.findViewById(R.id.edit);
            delt = itemView.findViewById(R.id.delt);

        }
    }
    public void ShowPopup(String sadult,String schild, int position) {
        EditText adults,children;
        Button updateroom;

        myDialog = new Dialog(mCtx);
        myDialog.setContentView(R.layout.roomedit_dialog);
        adults =(EditText) myDialog.findViewById(R.id.adults);
        children =(EditText) myDialog.findViewById(R.id.children);

        updateroom =(Button) myDialog.findViewById(R.id.updateroom);
        adults.setText(sadult);
        children.setText(schild);

        updateroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sadults,schildren;
                sadults=adults.getEditableText().toString().trim();
                schildren=children.getEditableText().toString().trim();
                if(sadults.equalsIgnoreCase("")||sadults==null){
                    //adults.setError("please enter value");
                    Toast.makeText(mCtx, "please enter value", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(sadults)>=0&&Integer.parseInt(sadults)<=6){
                    items.get(position).setadult(sadults);

                }
                else {
                    //adults.setError("please enter below 6");
                    Toast.makeText(mCtx, "please enter below 6", Toast.LENGTH_SHORT).show();
                }
                if(schildren.equalsIgnoreCase("")||schildren==null){
                    //children.setError("please enter value");
                    Toast.makeText(mCtx, "please enter below 6", Toast.LENGTH_SHORT).show();

                }
                else if(Integer.parseInt(schildren)>=0&&Integer.parseInt(schildren)<=6){
                    items.get(position).setchildren(schildren);
                }
                else {
                    //children.setError("");
                    Toast.makeText(mCtx, "please enter below 6", Toast.LENGTH_SHORT).show();
                }

                if((sadults.length()>0&&(Integer.parseInt(sadults)>=0&&Integer.parseInt(sadults)<=6))&&(schildren.length()>0)&&(Integer.parseInt(schildren)>=0&&Integer.parseInt(schildren)<=6)){
                  //  items.add(new Addroompojo(sadults,schildren));
                    myDialog.dismiss();
                    notifyDataSetChanged();
                }
                else {
                    //myDialog.dismiss();
                    Toast.makeText(mCtx, "please enter correct value", Toast.LENGTH_SHORT).show();
                }









            }
        });

        //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}