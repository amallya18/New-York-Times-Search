package com.codepath.anmallya.nytsearch.model;

import java.util.ArrayList;

/**
 * Created by anmallya on 10/23/2016.
 */
public class TopStories {
    public ArrayList<News> getResults() {
        return results;
    }

    public void setResults(ArrayList<News> results) {
        this.results = results;
    }

    private ArrayList<News> results;
}
