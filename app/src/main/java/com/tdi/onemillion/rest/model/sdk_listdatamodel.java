package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class sdk_listdatamodel {


    @SerializedName("totalCount")
    @Expose
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    @SerializedName("articles")
    @Expose
    private ArrayList<sdk_articlesmodel> articles;

    public ArrayList<sdk_articlesmodel> getArticles() {
        return articles;
    }
}
