package com.codepath.anmallya.nytsearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.anmallya.nytsearch.R;

/**
 * Created by anmallya on 10/21/2016.
 */
public class ViewHolder2 extends RecyclerView.ViewHolder {


    public TextView getTvSource() {
        return tvSource;
    }

    public void setTvSource(TextView tvSource) {
        this.tvSource = tvSource;
    }

    private TextView tvSource;
    public TextView getTvNews() {
        return tvNews;
    }

    public void setTvNews(TextView tvNews) {
        this.tvNews = tvNews;
    }

    private TextView tvNews;

    public TextView getTvSnippet() {
        return tvSnippet;
    }

    public void setTvSnippet(TextView tvSnippet) {
        this.tvSnippet = tvSnippet;
    }

    private TextView tvSnippet;

    public ViewHolder2(View v) {
        super(v);
        tvNews = (TextView) v.findViewById(R.id.item_name);
        tvSnippet = (TextView) v.findViewById(R.id.item_snippet);
        setTvSource((TextView) v.findViewById(R.id.item_source));
    }
}
