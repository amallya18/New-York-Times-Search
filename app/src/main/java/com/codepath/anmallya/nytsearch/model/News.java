package com.codepath.anmallya.nytsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.ArrayList;

/**
 * Created by anmallya on 10/19/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Parcel
public class News {


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @JsonProperty("lead_paragraph")
    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    @JsonProperty("source")
    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    @JsonProperty("news_desk")
    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    @JsonProperty("multimedia")
    public ArrayList<MultiMedia> getMultiMedia() {
        return multiMedia;
    }

    public void setMultiMedia(ArrayList<MultiMedia> multiMedia) {
        this.newsType = (multiMedia.size() == 0)? NewsType.TEXT_ONLY: NewsType.NORMAL;
        this.multiMedia = multiMedia;
    }

    @JsonProperty("section_name")
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    public NewsType getNewsType() {
        this.newsType = (multiMedia.size() == 0)? NewsType.TEXT_ONLY: NewsType.NORMAL;
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    @Transient
    private NewsType newsType;

    @SerializedName("web_url")
    public String webUrl;

    @Transient
    @SerializedName("snippet")
    private String snippet;

    @Transient
    @SerializedName("lead_paragraph")
    private String leadParagraph;

    @Transient
    @SerializedName("source")
    private String Source;

    @Transient
    @SerializedName("multimedia")
    private ArrayList<MultiMedia> multiMedia;

    @Transient
    @SerializedName("news_desk")
    private String newsDesk;

    @Transient
    @SerializedName("section_name")
    private String sectionName;

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    @Transient
    @SerializedName("headline")
    private Headline headline;

    public News(){

    }

}
