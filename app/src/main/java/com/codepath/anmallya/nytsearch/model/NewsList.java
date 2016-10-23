package com.codepath.anmallya.nytsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by anmallya on 10/19/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsList {

    public NewsList(){

    }

    public Docs getResponse() {
        return response;
    }

    public void setResponse(Docs response) {
        this.response = response;
    }

    private Docs response;
}
