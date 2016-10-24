package com.codepath.anmallya.nytsearch.model;

import java.util.ArrayList;

/**
 * Created by anmallya on 10/22/2016.
 */
public class Filter {

    private Filter(){
    }

    public String getFilterSearchQuery() {
        return filterSearchQuery;
    }

    public void setFilterSearchQuery(String filterSearchQuery) {
        this.filterSearchQuery = filterSearchQuery;
    }

    private String filterSearchQuery;
    private static Filter filter;
    private String beginDate;
    private ArrayList<String> newsDesk = new ArrayList<>();
    private String newsDeskString;
    private String sort = "newest";
    private String beginDateFormatted;

    public String getNewsDeskString() {
        return newsDeskString;
    }

    public String getBeginDateFormatted() {
        return beginDateFormatted;
    }

    public void setBeginDateFormatted(String beginDateFormatted) {
        this.beginDateFormatted = beginDateFormatted;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public ArrayList<String> getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(ArrayList<String> newsDesk) {
        this.newsDesk = newsDesk;
        StringBuilder sb = new StringBuilder();
        if(newsDesk.size()>0){
            sb.append("news_desk:(");
        }else{
            sb.append("news_desk:()");
            return;
        }
        for(String item:newsDesk){
            sb.append("\"");
            sb.append(item);
            sb.append("\"");
            sb.append(" ");
        }
        if(newsDesk.size()>0){
            sb.append(")");
        }
        newsDeskString = sb.toString();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public static Filter getInstance(){
        if(filter == null){
            filter = new Filter();
        }
        return filter;
    }

    public void resetFilter(){
        filterSearchQuery = null;
        beginDate = null;
        newsDesk = new ArrayList<>();
        newsDeskString = null;
        sort = "newest";
        beginDateFormatted = null;
    }

    @Override
    public String toString(){
        return getBeginDate()+" "+getSort()+" "+getNewsDesk().size()+" "+getFilterSearchQuery();
    }
}
