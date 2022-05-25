package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_listmodel {

    @SerializedName("result")
    @Expose
    private Boolean result;

    public Boolean getResult() {
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
    private sdk_listdatamodel data;

    public sdk_listdatamodel getData() {
        return data;
    }

    @SerializedName("error")
    @Expose
    private sdk_errormodel error;

    public sdk_errormodel getError() {
        return error;
    }
}


