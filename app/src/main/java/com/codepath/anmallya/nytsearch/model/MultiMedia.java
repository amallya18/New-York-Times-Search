package com.codepath.anmallya.nytsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by anmallya on 10/19/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultiMedia{
    public String getUrl() {
        return "http://nytimes.com/"+url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public MultiMedia(){

    }
}
