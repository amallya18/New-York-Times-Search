package com.codepath.anmallya.nytsearch.helper;

import com.codepath.anmallya.nytsearch.model.Filter;

/**
 * Created by anmallya on 10/22/2016.
 */
public class UrlUtils {


    public String TEST_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=20100112&sort=oldest&fq=news_desk:(%22Education%22%20%22Health%22)&api-key=227c750bb7714fc39ef1559ef1bd8329&page=";

    public static String getNytUrl() {
        return NYT_URL;
    }

    public static String getNytKey() {
        return NYT_KEY;
    }

    private static final String NYT_KEY = "7233e0d4e0de4dd8a74892f0eca04ba1";

    private static final String NYT_URL = "https://api.nytimes.com";

    private static final String NYT__TOP_STORIES_URL = "https://api.nytimes.com";

    private static final String SEARCH_URL = "/svc/search/v2/articlesearch.json?";

    private static final String API_KEY = "api-key=227c750bb7714fc39ef1559ef1bd8329&page=";

    public static String generateUrlFromFilter(Filter filter, String searchQuery){
        StringBuilder url = new StringBuilder(NYT_URL+SEARCH_URL);

        url.append("fq="+searchQuery);
        url.append("&");

        if(filter.getBeginDate()!=null){
            url.append("begin_date="+filter.getBeginDate());
            url.append("&");
        }

        url.append("sort="+filter.getSort().toLowerCase());
        url.append("&");

        if(filter.getNewsDesk().size() > 0){
            url.append("fq=news_desk:(");
            for(String name:filter.getNewsDesk()) {
                url.append("%22");
                url.append(name);
                url.append("%22");
                url.append("%20");
            }
            url.append(")");
            url.append("&");
        }
        url.append(API_KEY);

        System.out.println("Url is: "+url.toString());
        return url.toString();
    }
}
