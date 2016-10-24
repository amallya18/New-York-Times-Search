package com.codepath.anmallya.nytsearch.network;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.codepath.anmallya.nytsearch.adapter.NewsAdapter;
import com.codepath.anmallya.nytsearch.helper.UrlUtils;
import com.codepath.anmallya.nytsearch.model.Filter;
import com.codepath.anmallya.nytsearch.model.News;
import com.codepath.anmallya.nytsearch.model.NewsList;
import com.codepath.anmallya.nytsearch.model.TopStories;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anmallya on 10/23/2016.
 */
public class NetworkHelper{

    public void fetchNewsList(int page,
                              final View parent,
                              Filter filter,
                              ArrayList<News> newsList,
                              NewsAdapter adapter){

        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.getNytUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        ApiEndPointInterface apiService = NetworkFactory.getService();

        Call<NewsList> call = apiService.getNews(UrlUtils.getNytKey(),
                filter.getBeginDate(),
                filter.getSort(),
                filter.getNewsDeskString(),
                filter.getFilterSearchQuery(),
                page);

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList news = response.body();
                print(news);
                newsList.addAll(news.getResponse().getDocs());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                if(t instanceof IOException){
                    Snackbar.make(parent, "No Internet Connection", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void fetchTopStories(String type,
                              final View parent,
                              ArrayList<News> newsList,
                              NewsAdapter adapter){

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.getNytUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        ApiEndPointInterface apiService = NetworkFactory.getService();
        //ApiEndPointInterface apiService = retrofit.create(ApiEndPointInterface.class);
        String val = getStoryType(type);
        Call<NewsList> call = apiService.getNews(UrlUtils.getNytKey(), null, null, val, null, 1);

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                int statusCode = response.code();
                NewsList news = response.body();
                print(news);
                newsList.clear();
                newsList.addAll(news.getResponse().getDocs());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                if(t instanceof IOException){
                    Snackbar.make(parent, "No Internet Connection", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private void print(NewsList news){
        Gson gson = new Gson();
        String json = gson.toJson(news);
        System.out.println("Json recived:" +gson);
    }

    private String getStoryType(String type){
        switch(type){
            case "HOME":
                return "news_desk:(\"Home\")";
            case "EDUCATION":
                return "news_desk:(\"Education\")";
            case "TECH":
                return "news_desk:(\"Technology\")";
            case "NATIONAL":
                return "news_desk:(\"National\")";
            case "POLITICS":
                return "news_desk:(\"Politics\")";
            case "BUSINESS":
                return "news_desk:(\"Business\")";
            case "HEALTH":
                return "news_desk:(\"Health\")";
            default:
                return "news_desk:(\"Home\")";
        }
    }
}
