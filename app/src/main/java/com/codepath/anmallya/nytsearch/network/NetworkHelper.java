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

        /*
        switch(type){
            case "HOME":
                call = apiService.getHomeNews(UrlUtils.getNytKey()); break;
            case "WORLD":
                call = apiService.getWorldNews(UrlUtils.getNytKey()); break;
            case "NATIONAL":
                call = apiService.getNationalNews(UrlUtils.getNytKey()); break;
            case "POLITICS":
                call = apiService.getPoliticsNews(UrlUtils.getNytKey()); break;
            case "BUSINESS":
                call = apiService.getBusinessNews(UrlUtils.getNytKey()); break;
            default:
                call = apiService.getHomeNews(UrlUtils.getNytKey());
        }
        */

        /*
        call.enqueue(new Callback<TopStories>() {
            @Override
            public void onResponse(Call<TopStories> call, Response<TopStories> response) {
                int statusCode = response.code();
                System.out.println("Status Code: "+statusCode);
                System.out.println("Response: "+call.request().url());
                TopStories news = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(news);
                System.out.println("Response Body to string "+json);
                newsList.clear();
                newsList.addAll(news.getResults());
                adapter.notifyDataSetChanged();
                System.out.println();
            }

            @Override
            public void onFailure(Call<TopStories> call, Throwable t) {
                System.out.println(t);
                if(t instanceof IOException){
                    Snackbar.make(parent, "No Internet Connection", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });*/
    }


    /*
    public void fetchMoviesAndroidAsyncHttp(int page, final View parent){

        //public static final String BASE_URL = "http://api.myservice.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndPointInterface apiService = retrofit.create(ApiEndPointInterface.class);

        Call<NewsList> call = apiService.getNews(KEY, "20100112", "oldest", "news_desk:(Education Health)", page);
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
                // Log error here since request failed
                if(t instanceof IOException){
                    Snackbar.make(parent, "No Internet Connection", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }


    private void fetchMoviesAndroidAsyncHttp1(int page){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL+page, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    NewsList newsListNew = (new ObjectMapper()).readValue(response.toString(), NewsList.class);
                    newsList.addAll(newsListNew.getResponse().getDocs());
                    adapter.notifyDataSetChanged();
                    for(News news:newsListNew.getResponse().getDocs()){
                        //System.out.println(news.getMultiMedia().get(0).getUrl());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable){
                super.onFailure(statusCode, headers, responseString, throwable);
                System.out.println(responseString+" "+statusCode);
            }
        });
    } */

    /*
    private void fetchMoviesAndroidAsyncHttp(int page, String url){
        AsyncHttpClient client = new AsyncHttpClient();

        System.out.println("Fetch called");
        String urlFinal = url+page;
        System.out.println("Url Final "+urlFinal);

        client.get(urlFinal, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    NewsList newsListNew = (new ObjectMapper()).readValue(response.toString(), NewsList.class);
                    newsList.addAll(newsListNew.getResponse().getDocs());
                    adapter.notifyDataSetChanged();
                    for(News news:newsListNew.getResponse().getDocs()){
                        //System.out.println(news.getMultiMedia().get(0).getUrl());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable){
                super.onFailure(statusCode, headers, responseString, throwable);
                System.out.println(responseString+" "+statusCode);
            }
        });
    }
    */

}
