package com.codepath.anmallya.nytsearch.model;

/**
 * Created by anmallya on 10/21/2016.
 */
public enum NewsType {
    NORMAL(0), TEXT_ONLY(1);

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    private int val;

    NewsType(int val){
        this.val = val;
    }
}
