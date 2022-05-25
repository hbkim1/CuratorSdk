package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_detaillistmodel {
    @SerializedName("result")
    @Expose
    private boolean result;

    public boolean isResult() {
        return result;
    }

    @SerializedName("statusCode")
    @Expose
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    @SerializedName("data")
    @Expose
    private sdk_articlesmodel data;

    public sdk_articlesmodel getData() {
        return data;
    }
}
