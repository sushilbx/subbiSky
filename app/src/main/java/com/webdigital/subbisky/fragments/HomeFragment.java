package com.webdigital.subbisky.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webdigital.subbisky.APIs.RetrofitClient;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.R;
import com.webdigital.subbisky.activity.AboutUsActivity;
import com.webdigital.subbisky.adapter.BannerAdapter;
import com.webdigital.subbisky.adapter.ExploreCityAdapter;
import com.webdigital.subbisky.adapter.HomeImageAdapter;
import com.webdigital.subbisky.adapter.HomeServiceListAdapter;
import com.webdigital.subbisky.adapter.SliderAdapterExample;
import com.webdigital.subbisky.adapter.TrendindAdapter;
import com.webdigital.subbisky.model.BannersModel;
import com.webdigital.subbisky.model.ExplorecitylistResponse;
import com.webdigital.subbisky.model.HomeHotelModel;
import com.webdigital.subbisky.model.HomeImageModel;
import com.webdigital.subbisky.model.HomeProductModel;
import com.webdigital.subbisky.model.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.webdigital.subbisky.model.ToptrendinglistResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    //a list to store all the products
    List<HomeProductModel> productList;
    List<HomeProductModel> productList2;

    //the recyclerview for product
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView servicelistRecycler;

    //the recyclerview for Images
    RecyclerView recyclerViewImg,recyclertoptrending;
    RecyclerView recyclerViewImg1;
    List<HomeImageModel> imageList;


    //a list to store all the Hotels
    List<HomeHotelModel> hotelList;
    RecyclerView recyclerViewHotels;

    Session session;
    SliderView sliderView;
    private SliderAdapterExample adapter;
    private BannerAdapter bannerAdapters;
    private HomeServiceListAdapter homeServiceListAdapter;
    SliderView bannerView;
    public static String cityname="";
    Button aboutUsMore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

       /*auto image Slider */
        bannerView =view.findViewById(R.id.imageSlider);
        servicelistRecycler = view.findViewById(R.id.servicelistRecycler);
        aboutUsMore = view.findViewById(R.id.aboutUsMore);

        adapter = new SliderAdapterExample(getContext());




        servicelistRecycler.setHasFixedSize(true);
        servicelistRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        getServiceList();
        getHomeData();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /* Image list*/
        //getting the recyclerview from xml
        recyclerViewImg=view.findViewById(R.id.explorecityre);
      /*  recyclerViewImg.setHasFixedSize(true);
        recyclerViewImg.setLayoutManager(new GridLayoutManager(getContext(),2));*/


        Call<ExplorecitylistResponse> call =  RetrofitClient.getInstance().getApi().explorecity();

        call.enqueue(new Callback<ExplorecitylistResponse>() {
            @Override
            public void onResponse(Call<ExplorecitylistResponse> call, Response<ExplorecitylistResponse> response)
            {
                if(response.code() == 200)
                {

                        ExploreCityAdapter faqAdapter = new ExploreCityAdapter(response.body().getCities(), getContext());
                        recyclerViewImg.setAdapter(faqAdapter);

                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<ExplorecitylistResponse> call, Throwable t)
            {
                Log.e("message",t.getMessage());
            }
        });


        recyclertoptrending = view.findViewById(R.id.recyclertoptrending);
        recyclertoptrending.setHasFixedSize(true);
        recyclertoptrending.setLayoutManager(new LinearLayoutManager(getContext()));
        Call<ToptrendinglistResponse> calls =  RetrofitClient.getInstance().getApi().toptrending();

        calls.enqueue(new Callback<ToptrendinglistResponse>() {
            @Override
            public void onResponse(Call<ToptrendinglistResponse> call, Response<ToptrendinglistResponse> response)
            {
                if(response.code() == 200)
                {
                    if(response.body() != null)
                    {
                        TrendindAdapter trendingAdapter = new TrendindAdapter((List<ToptrendinglistResponse.TopTrending>) response.body().getTopTrendings(),getActivity());
                        recyclertoptrending.setAdapter(trendingAdapter);

                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }



            }

            @Override
            public void onFailure(Call<ToptrendinglistResponse> call, Throwable t)
            {
                Log.e("message",t.getMessage());
            }
        });



        imageList = new ArrayList<>();




        //creating recyclerview adapter
        HomeImageAdapter adapterImg = new HomeImageAdapter(getContext(), imageList);

        //setting adapter to recyclerview
        recyclerViewImg.setAdapter(adapterImg);
        //recyclerViewImg1.setAdapter(adapterImg);


        /*hotel list*/
        //getting the recyclerview from xml

        //initializing the productlist





        aboutUsMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        renewItems(view);

        return view;
    }

    private void getServiceList() {


    }

    /*   slider images*/
    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl(R.drawable.slider1);
                //sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            } else {
                sliderItem.setImageUrl(R.drawable.slider2);
               // sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    private void getHomeData() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        session = new Session(getContext());
        String userToken = session.getAccessTokenType() + " " + session.getAccessToken();
        Log.e("userToken", userToken);
        Call<BannersModel> call = RetrofitClient.getInstance().getApi().bannersModel(HomeFragment.cityname);
        call.enqueue(new Callback<BannersModel>() {
            @Override
            public void onResponse(Call<BannersModel> call, Response<BannersModel> response) {
                if (response.code() == 200) {
                    HomeFragment.cityname="";
                    progressDialog.dismiss();

                    bannerAdapters = new BannerAdapter((List<BannersModel.BannersLists>) response.body().getBanners(), getContext());
                    bannerView.setSliderAdapter(bannerAdapters);
                    bannerView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    bannerView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    bannerView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    bannerView.setIndicatorSelectedColor(Color.WHITE);
                    bannerView.setIndicatorUnselectedColor(Color.GRAY);
                    bannerView.setScrollTimeInSec(3);
                    bannerView.setAutoCycle(true);
                    bannerView.startAutoCycle();


                    homeServiceListAdapter = new HomeServiceListAdapter((List<BannersModel.ServicesLists>) response.body().getServices(), getContext());
                    servicelistRecycler.setAdapter(homeServiceListAdapter);



                } else {
                    progressDialog.dismiss();
       //             Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BannersModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}