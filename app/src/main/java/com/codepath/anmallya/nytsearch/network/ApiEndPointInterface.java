package com.codepath.anmallya.nytsearch.network;

import com.codepath.anmallya.nytsearch.model.News;
import com.codepath.anmallya.nytsearch.model.NewsList;
import com.codepath.anmallya.nytsearch.model.TopStories;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by anmallya on 10/23/2016.
 */
public interface ApiEndPointInterface {
    @GET("svc/search/v2/articlesearch.json")
    Call<NewsList> getNews(@Query("api-key") String key,
                           @Query("begin_date") String date,
                           @Query("sort") String order,
                           @Query("fq") String newsDesk,
                           @Query("q") String query,
                           @Query("page") Integer page
    );

    @GET("/svc/topstories/v1/home.json")
    Call<TopStories> getHomeNews(@Query("api-key") String key);

    @GET("/svc/topstories/v1/world.json")
    Call<TopStories> getWorldNews(@Query("api-key") String key);

    @GET("/svc/topstories/v1/national.json")
    Call<TopStories> getNationalNews(@Query("api-key") String key);

    @GET("/svc/topstories/v1/politics.json")
    Call<TopStories> getPoliticsNews(@Query("api-key") String key);

    @GET("/svc/topstories/v1/business.json")
    Call<TopStories> getBusinessNews(@Query("api-key") String key);
}

