package com.webdigital.subbisky.adapter;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.webdigital.subbisky.R;
        import com.webdigital.subbisky.activity.SellerHotelListEditActivity;
        import com.webdigital.subbisky.model.SellerHotelAmenitiesModel;

        import java.util.ArrayList;
        import java.util.List;

public class SellerHotelListEditAmenitiesAdapter extends RecyclerView.Adapter<SellerHotelListEditAmenitiesAdapter.sellerHotelListEditAmenitiesHolder> {
    private List<SellerHotelAmenitiesModel.Amenities> amenitiesLists = new ArrayList<>();
    private Context context;

    public SellerHotelListEditAmenitiesAdapter(List<SellerHotelAmenitiesModel.Amenities> amenitiesLists, Context context) {
        this.amenitiesLists = amenitiesLists;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerHotelListEditAmenitiesAdapter.sellerHotelListEditAmenitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_seller_hotel_list_edit_amenities, null);
        return new SellerHotelListEditAmenitiesAdapter.sellerHotelListEditAmenitiesHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerHotelListEditAmenitiesAdapter.sellerHotelListEditAmenitiesHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewForEditHotel.setText(amenitiesLists.get(position).getName());
        holder.checkboxForEditHotel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SellerHotelListEditActivity.amenitiesList.add(String.valueOf(amenitiesLists.get(position).getId()));
                }else {
                    SellerHotelListEditActivity.amenitiesList.remove(String.valueOf(amenitiesLists.get(position).getId()));
                }
                Log.e("amenitiesLists", SellerHotelListEditActivity.amenitiesList.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return amenitiesLists.size();
    }

    public class sellerHotelListEditAmenitiesHolder extends RecyclerView.ViewHolder {
        CheckBox checkboxForEditHotel;
        TextView textViewForEditHotel;
        public sellerHotelListEditAmenitiesHolder(@NonNull View itemView) {
            super(itemView);
            textViewForEditHotel=itemView.findViewById(R.id.textViewForEditHotel);
            checkboxForEditHotel=itemView.findViewById(R.id.checkboxForEditHotel);
        }
    }
}
