package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class sdk_articlesmodel {



    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("media_id")
    @Expose
    private int media_id;

    @SerializedName("platform")
    @Expose
    private String platform;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("keyword")
    @Expose
    private String keyword;

    @SerializedName("channel")
    @Expose
    private String channel;

    @SerializedName("article_owner_id")
    @Expose
    private String article_owner_id;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("contents")
    @Expose
    private String contents;

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


    @SerializedName("mime")
    @Expose
    private String mime;


    @SerializedName("hashtag")
    @Expose
    private String hashtag;


    @SerializedName("state")
    @Expose
    private int state;

    @SerializedName("date")
    @Expose
    private String date;


    @SerializedName("has_media")
    @Expose
    private int has_media;

    @SerializedName("is_favorite")
    @Expose
    private Boolean is_favorite;

    @SerializedName("is_like")
    @Expose
    private Boolean is_like;

    @SerializedName("article_medias")
    @Expose
    private ArrayList<sdk_article_mediasmodel> articles;


    @SerializedName("article_owner")
    @Expose
    private sdk_article_ownermodel article_owner;

    @SerializedName("article_detail")
    @Expose
    private sdk_article_detailmodel article_detail;

    public Long getId() {
        return id;
    }

    public int getMedia_id() {
        return media_id;
    }

    public String getPlatform() {
        return platform;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getChannel() {
        return channel;
    }

    public String getArticle_owner_id() {
        return article_owner_id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
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

    public String getMime() {
        return mime;
    }

    public String getHashtag() {
        return hashtag;
    }

    public int getState() {
        return state;
    }

    public String getDate() {
        return date;
    }

    public int getHas_media() {
        return has_media;
    }

    public Boolean getIs_favorite() {
        return is_favorite;
    }

    public Boolean getIs_like() {
        return is_like;
    }

    public ArrayList<sdk_article_mediasmodel> getarticles() {
        return articles;
    }

    public sdk_article_ownermodel getArticle_owner() {
        return article_owner;
    }

    public sdk_article_detailmodel getArticle_detail() {
        return article_detail;
    }
}
