package com.codepath.anmallya.nytsearch.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.codepath.anmallya.nytsearch.R;
import com.codepath.anmallya.nytsearch.model.News;

import org.parceler.Parcels;

public class WebViewActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        News news = (News) Parcels.unwrap(getIntent().getParcelableExtra("news"));
        openCustomToolbar(news.getWebUrl());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
            //webView.clearHistory(); // clear history
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void openCustomToolbar(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "http://www.codepath.com");

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setActionButton(bitmap, "Share Link", pendingIntent, true);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}
