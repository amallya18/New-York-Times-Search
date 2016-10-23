package com.codepath.anmallya.nytsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by anmallya on 10/19/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Docs{
    public ArrayList<News> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<News> docs) {
        this.docs = docs;
    }

    private ArrayList<News> docs;
}