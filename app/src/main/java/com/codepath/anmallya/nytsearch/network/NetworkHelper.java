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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.getNytUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndPointInterface apiService = retrofit.create(ApiEndPointInterface.class);

        Call<NewsList> call = apiService.getNews(UrlUtils.getNytKey(),
                filter.getBeginDate(),
                filter.getSort(),
                filter.getNewsDeskString(),
                filter.getFilterSearchQuery(),
                page);

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                int statusCode = response.code();
                System.out.println("Status Code: "+statusCode);
                System.out.println("Response: "+call.request().url());
                NewsList news = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(news);
                System.out.println("Response Body to string "+json);
                newsList.addAll(news.getResponse().getDocs());
                adapter.notifyDataSetChanged();
                System.out.println();
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

        System.out.println("fetch stories called");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.getNytUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndPointInterface apiService = retrofit.create(ApiEndPointInterface.class);

        //Call<TopStories> call;
        String val = null;
        switch(type){
            case "HOME":
                 val = "news_desk:(\"Home\")";
                 break;
            case "EDUCATION":
                val = "news_desk:(\"Education\")";
                break;
            case "TECH":
                val = "news_desk:(\"Technology\")";
                break;
            case "NATIONAL":
                val = "news_desk:(\"National\")";
                break;
            case "POLITICS":
                val = "news_desk:(\"Politics\")";
                break;
            case "BUSINESS":
                val = "news_desk:(\"Business\")";
                break;
            case "HEALTH":
                val = "news_desk:(\"Health\")";
                break;
            default:
                val = "news_desk:(\"Home\")";
                break;
        }

        Call<NewsList> call = apiService.getNews(UrlUtils.getNytKey(), null, null, val, null, 1);

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                int statusCode = response.code();
                System.out.println("Status Code: "+statusCode);
                System.out.println("Response: "+call.request().url());
                NewsList news = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(news);
                System.out.println("Response Body to string "+json);
                newsList.clear();
                newsList.addAll(news.getResponse().getDocs());
                adapter.notifyDataSetChanged();
                System.out.println();
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
}
