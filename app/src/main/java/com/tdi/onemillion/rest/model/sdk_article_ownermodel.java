package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_article_ownermodel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("platform")
    @Expose
    private String platform;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("storage_thumbnail_url")
    @Expose
    private String storage_thumbnail_url;

    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnail_url;

    @SerializedName("thumbnail_width")
    @Expose
    private int thumbnail_width;

    @SerializedName("thumbnail_height")
    @Expose
    private int thumbnail_height;

    public String getId() {
        return id;
    }

    public String getPlatform() {
        return platform;
    }

    public String getUrl() {
        return url;
    }

    public String getStorage_thumbnail_url() {
        return storage_thumbnail_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public int getThumbnail_width() {
        return thumbnail_width;
    }

    public int getThumbnail_height() {
        return thumbnail_height;
    }
}
