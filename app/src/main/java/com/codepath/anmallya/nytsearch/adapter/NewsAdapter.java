package com.codepath.anmallya.nytsearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.anmallya.nytsearch.R;
import com.codepath.anmallya.nytsearch.model.News;
import com.codepath.anmallya.nytsearch.model.NewsType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anmallya on 10/20/2016.
 */
public class NewsAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News> newsList = null;
    private Context mContext;

    public NewsAdapter(Context context, List<News> newsList) {
        this.newsList = newsList;
        this.mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public int getItemViewType(int position) {
        return newsList.get(position).getNewsType().getVal();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.layout_viewholder1, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.layout_viewholder2, parent, false);
                viewHolder = new ViewHolder2(v2);
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case 1:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
        }
    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        final News news = newsList.get(position);
        TextView textView = vh1.getTvNews();
        textView.setText(news.getHeadline().getMain());

        TextView textViewSource = vh1.getTvSource();
        textViewSource.setText(news.getSource());

        ImageButton ibShare = vh1.getIbShare();
        ibShare.setOnClickListener(v -> {
            shareUrl(news);
        });

        ImageView iv = vh1.getIvNews();
        if(news.getMultiMedia().size() > 0){
            //Picasso.with(getContext()).load(news.getMultiMedia().get(0).getUrl()).into(iv);
            Glide.with(getContext()).load(news.getMultiMedia().get(0).getUrl()).into(iv);
        }
    }

    private void configureViewHolder2(ViewHolder2 vh2, int position) {
        News news = newsList.get(position);
        vh2.getTvSnippet().setText(news.getSnippet());
        vh2.getTvNews().setText(news.getHeadline().getMain());
        ImageButton ibShare = vh2.getIbShare();
        ibShare.setOnClickListener(v -> {
            shareUrl(news);
        });
        TextView textViewSource = vh2.getTvSource();
        textViewSource.setText(news.getSource());
    }

    private void shareUrl(News news){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this link-"+news.getWebUrl()); // Simple text and URL to share
        sendIntent.setType("text/plain");
        getContext().startActivity(sendIntent);
    }
}