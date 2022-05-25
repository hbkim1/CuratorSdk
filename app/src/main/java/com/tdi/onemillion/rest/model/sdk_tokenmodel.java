package com.tdi.onemillion.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sdk_tokenmodel {

    @SerializedName("token_type")
    @Expose
    private String token_type;

    public String getToken_type() {
        return token_type;
    }


    @SerializedName("expires_in")
    @Expose
    private Long expires_in;

    public Long getExpires_in() {
        return expires_in;
    }


    @SerializedName("access_token")
    @Expose
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }



    //Error

    @SerializedName("error")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    @SerializedName("error_description")
    @Expose
    private String error_description;

    public String getError_description() {
        return error_description;
    }


    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

}
