package com.webdigital.subbisky.APIs;

import android.content.Context;

import com.github.juanlabrador.badgecounter.BadgeCounter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdigital.subbisky.Preferences.Session;
import com.webdigital.subbisky.activity.DashboradActivity;
import com.webdigital.subbisky.fragments.HomeFragment;
import com.webdigital.subbisky.model.CartcountModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL="https://subbisky.com/api/";

    Retrofit retrofit;
    private static RetrofitClient instance;

    public RetrofitClient() {
        //logging of request and response
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit=new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                client(okHttpClient).
                build();
    }
    public static synchronized RetrofitClient getInstance()
    {
        if(instance==null)
        {
            instance=new RetrofitClient();
        }
        return instance;
    }
    public RetrofitApi getApi()
    {
        return retrofit.create(RetrofitApi.class);
    }
    public void getCartCount(final Context context) {
        Session session = new Session(context);
        String UserToken = session.getAccessTokenType() + " " + session.getAccessToken();
        Call<CartcountModel> call = RetrofitClient.getInstance().getApi().cartcount(UserToken);
        call.enqueue(new Callback<CartcountModel>() {
            @Override
            public void onResponse(Call<CartcountModel> call, Response<CartcountModel> response) {
                if (response.code() == 200) {
                    if (response.body().getMessage().equals("Cart is empty")) {
                        DashboradActivity.cNotificationCounter =0;
                       BadgeCounter.update(DashboradActivity.menuItem, DashboradActivity.cNotificationCounter);
                    } else {
                        DashboradActivity.cNotificationCounter = response.body().getCount();
                        BadgeCounter.update(DashboradActivity.menuItem, DashboradActivity.cNotificationCounter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CartcountModel> call, Throwable t) {

            }
        });

    }
}
