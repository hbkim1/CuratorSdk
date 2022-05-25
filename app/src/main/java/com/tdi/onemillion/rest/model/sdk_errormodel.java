package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_errormodel {

    @SerializedName("statusCode")
    @Expose
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }


    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    @SerializedName("code")
    @Expose
    private String code;

    public String getCode() {
        return code;
    }
}
