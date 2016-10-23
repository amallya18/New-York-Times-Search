package com.codepath.anmallya.nytsearch.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Transient;

import java.util.ArrayList;

/**
 * Created by anmallya on 10/23/2016.
 */
public class Stories {

    @Transient
    @SerializedName("multimedia")
    private String title;

    @Transient
    @SerializedName("multimedia")
    private String abstracts;

    @Transient
    @SerializedName("multimedia")
    private ArrayList<MultiMedia> multiMedia;

    private String url;
}
