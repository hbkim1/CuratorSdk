package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_article_mediasmodel {

    @SerializedName("article_id")
    @Expose
    private Long article_id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("storage_url")
    @Expose
    private String storage_url;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("width")
    @Expose
    private int width;

    @SerializedName("height")
    @Expose
    private int height;


    @SerializedName("mime")
    @Expose
    private String mime;

    public Long getArticle_id() {
        return article_id;
    }

    public String getType() {
        return type;
    }

    public String getStorage_url() {
        return storage_url;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getMime() {
        return mime;
    }



}
