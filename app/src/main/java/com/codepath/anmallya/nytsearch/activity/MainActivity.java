package com.codepath.anmallya.nytsearch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.anmallya.nytsearch.R;
import com.codepath.anmallya.nytsearch.adapter.NewsAdapter;
import com.codepath.anmallya.nytsearch.helper.Constants;
import com.codepath.anmallya.nytsearch.model.News;
import com.codepath.anmallya.nytsearch.network.NetworkHelper;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<News> newsList = null;
    private NewsAdapter adapter = null;
    private RecyclerView rvNews;
    private StaggeredGridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews(){
        setNavigationDrawerAndToolbar();
        setRecyclerView();
        fetchData("Home");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("Home");
            fetchData("HOME");
        } else if (id == R.id.nav_tech) {
            getSupportActionBar().setTitle("Technology");
            fetchData("TECH");
        } else if (id == R.id.nav_politics) {
            getSupportActionBar().setTitle("Politics");
            fetchData("POLITICS");
        } else if (id == R.id.nav_business) {
            getSupportActionBar().setTitle("Business");
            fetchData("BUSINESS");
        } else if (id == R.id.nav_national) {
            getSupportActionBar().setTitle("National");
            fetchData("NATIONAL");
        } else if (id == R.id.nav_health) {
            getSupportActionBar().setTitle("Health");
            fetchData("HEALTH");
        }else if (id == R.id.nav_education) {
            getSupportActionBar().setTitle("Education");
            fetchData("EDUCATION");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Toolbar toolbar = null;
    private void setNavigationDrawerAndToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setRecyclerView(){
        newsList = new ArrayList<>();
        gridLayoutManager =
                new StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        rvNews = (RecyclerView)findViewById(R.id.rvNewsItems);
        adapter = new NewsAdapter(this, newsList);
        rvNews.setAdapter(adapter);
        rvNews.setLayoutManager(gridLayoutManager);
    }

    private void fetchData(String type){
        (new NetworkHelper()).fetchTopStories(type, findViewById(R.id.root), newsList, adapter);
    }
}
