package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_article_detailmodel {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("article_id")
    @Expose
    private Long article_id;

    @SerializedName("like")
    @Expose
    private int like;

    @SerializedName("dislike")
    @Expose
    private int dislike;

    @SerializedName("report")
    @Expose
    private int report;

    public int getId() {
        return id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public int getReport() {
        return report;
    }
}
