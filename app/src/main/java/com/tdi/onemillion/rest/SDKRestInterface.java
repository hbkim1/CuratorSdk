package com.tdi.onemillion.rest;

import androidx.annotation.Keep;

import com.tdi.onemillion.rest.model.sdk_detaillistmodel;
import com.tdi.onemillion.rest.model.sdk_listmodel;
import com.tdi.onemillion.rest.model.sdk_tokenmodel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Keep
public interface SDKRestInterface {

    @POST("/oauth/token")
    Call<sdk_tokenmodel> getToken(
            @Body RequestBody body
    );


    @GET("/api/{version}/articles")
    Call<sdk_listmodel> getList(
            @Header("Authorization") String Authorization,
            @Header("C9") String C9,
            @Path("version") String version,
            @Query("media_id") int media_id,
            @Query("user_id") int user_id,
            @Query("page") int page,
            @Query("per_page") int per_page,
            @Query("platform") String platform,
            @Query("search") String search
    );


    @GET("/api/{version}/articles/{article_id}")
    Call<sdk_detaillistmodel> getDetailList(
            @Header("Authorization") String Authorization,
            @Header("C9") String C9,
            @Path("version") String version,
            @Path("article_id") Long article_id
    );

}
