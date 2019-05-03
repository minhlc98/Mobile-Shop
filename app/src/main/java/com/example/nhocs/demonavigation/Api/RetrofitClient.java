package com.example.nhocs.demonavigation.Api;

import com.example.nhocs.demonavigation.Model.ThongTinSanPham;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static RetrofitClient mInstance;
    public static final String BaseURL="http://minhlc.000webhostapp.com/";
    public RetrofitService service;
    public Retrofit retrofit;
    public RetrofitClient(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                                    .writeTimeout(60, TimeUnit.SECONDS)
                                    .readTimeout(60,TimeUnit.SECONDS)
                                    .connectTimeout(60,TimeUnit.SECONDS)
                                    .retryOnConnectionFailure(true)
                                    .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(BaseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstace(){
        if (mInstance==null){
            mInstance=new RetrofitClient();
        }
         return mInstance;
    }
    public RetrofitService getService(){
        return retrofit.create(RetrofitService.class);
    }
}
