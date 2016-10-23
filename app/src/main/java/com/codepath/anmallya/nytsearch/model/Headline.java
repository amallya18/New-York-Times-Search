package com.codepath.anmallya.nytsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by anmallya on 10/21/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Headline {
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    private String main;
}
