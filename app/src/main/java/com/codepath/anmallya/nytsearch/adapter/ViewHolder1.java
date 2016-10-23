package com.codepath.anmallya.nytsearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.anmallya.nytsearch.R;

/**
 * Created by anmallya on 10/21/2016.
 */
public class ViewHolder1 extends RecyclerView.ViewHolder {

    private ImageView ivNews;
    private TextView tvNews;

    public ImageButton getIbShare() {
        return ibShare;
    }

    public void setIbShare(ImageButton ibShare) {
        this.ibShare = ibShare;
    }

    private ImageButton ibShare;

    public TextView getTvSource() {
        return tvSource;
    }

    public void setTvSource(TextView tvSource) {
        this.tvSource = tvSource;
    }

    private TextView tvSource;

    public ViewHolder1(View v) {
        super(v);
        setIvNews((ImageView) v.findViewById(R.id.news_image));
        setTvNews((TextView) v.findViewById(R.id.item_name));
        setTvSource((TextView) v.findViewById(R.id.item_source));
        setIbShare((ImageButton) v.findViewById(R.id.news_share));
    }


    public ImageView getIvNews() {
        return ivNews;
    }

    public void setIvNews(ImageView ivNews) {
        this.ivNews = ivNews;
    }

    public TextView getTvNews() {
        return tvNews;
    }

    public void setTvNews(TextView tvNews) {
        this.tvNews = tvNews;
    }
}
