package com.codepath.anmallya.nytsearch.network;

import com.codepath.anmallya.nytsearch.helper.UrlUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anmallya on 10/23/2016.
 */
public class NetworkFactory {
    private static Retrofit retrofit;
    private static ApiEndPointInterface service;

    public static ApiEndPointInterface getService(){
            if(service == null){
                service = getRetrofit().create(ApiEndPointInterface.class);
            }
        return service;
    }

    private static  Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlUtils.getNytUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
