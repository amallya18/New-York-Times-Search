package com.codepath.anmallya.nytsearch.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.codepath.anmallya.nytsearch.R;
import com.codepath.anmallya.nytsearch.adapter.NewsAdapter;
import com.codepath.anmallya.nytsearch.databinding.ActivitySearchBinding;
import com.codepath.anmallya.nytsearch.fragment.FilterFragment;
import com.codepath.anmallya.nytsearch.helper.Constants;
import com.codepath.anmallya.nytsearch.helper.EndlessRecyclerViewScrollListener;
import com.codepath.anmallya.nytsearch.helper.ItemClickSupport;
import com.codepath.anmallya.nytsearch.helper.UrlUtils;
import com.codepath.anmallya.nytsearch.model.Filter;
import com.codepath.anmallya.nytsearch.model.News;
import com.codepath.anmallya.nytsearch.model.NewsList;
import com.codepath.anmallya.nytsearch.network.NetworkHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<News> newsList = null;
    private NewsAdapter adapter = null;
    private String query = null;
    private RecyclerView rvNews;
    private StaggeredGridLayoutManager gridLayoutManager;
    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        Filter.getInstance().resetFilter();
        setRecyclerView();
        setInfiniteScroll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.expandActionView();
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchItems(query);
                searchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showSettingsDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setRecyclerView(){
        newsList = new ArrayList<>();
        gridLayoutManager =
                new StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        //rvNews = (RecyclerView)findViewById(R.id.rvNewsSearchItems);
        rvNews = binding.rvNewsSearchItems;
        adapter = new NewsAdapter(this, newsList);
        rvNews.setAdapter(adapter);
        rvNews.setLayoutManager(gridLayoutManager);
        rvNews.setItemAnimator(new SlideInUpAnimator());
        ItemClickSupport.addTo(rvNews).setOnItemClickListener(
                (recyclerView, position, v) -> {
                    Intent intent = new Intent(SearchActivity.this, WebViewActivity.class);
                    intent.putExtra("news", Parcels.wrap(newsList.get(position)));
                    startActivity(intent);
                }
        );
    }

    private void getSearchItems(String query){
        newsList.clear();
        adapter.notifyDataSetChanged();
        SearchActivity.this.query = query;
        Filter.getInstance().setFilterSearchQuery(query);
        (new NetworkHelper()).fetchNewsList(1, findViewById(R.id.root), Filter.getInstance(), newsList, adapter);
    }

    private void setInfiniteScroll(){
        rvNews.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Filter.getInstance().setFilterSearchQuery(SearchActivity.this.query);
                (new NetworkHelper()).fetchNewsList(page+1, binding.root, Filter.getInstance(), newsList, adapter);
            }
        });
    }

    private void showSettingsDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FilterFragment editNameDialogFragment = FilterFragment.newInstance("Settings Dialog");
        editNameDialogFragment.show(fm, "fragment_settings_name");
    }

}
