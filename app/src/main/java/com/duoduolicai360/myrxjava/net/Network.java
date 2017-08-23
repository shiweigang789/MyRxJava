package com.duoduolicai360.myrxjava.net;

import com.duoduolicai360.myrxjava.net.api.GanApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by swg on 2017/8/23.
 */

public class Network {

    private static GanApi sGanApi;
    private static OkHttpClient sOkHttpClient = new OkHttpClient();

    public static GanApi getGanApi(){
        if (sGanApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(sOkHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            sGanApi = retrofit.create(GanApi.class);
        }
        return sGanApi;
    }




}
