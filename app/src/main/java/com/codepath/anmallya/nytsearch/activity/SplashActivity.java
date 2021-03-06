package com.codepath.anmallya.nytsearch.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codepath.anmallya.nytsearch.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newActivityIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(newActivityIntent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}
