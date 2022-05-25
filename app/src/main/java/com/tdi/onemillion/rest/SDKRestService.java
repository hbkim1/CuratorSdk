package com.tdi.onemillion.rest;

import android.content.Context;
import android.util.Log;

import com.tdi.onemillion.rest.model.sdk_articlesmodel;
import com.tdi.onemillion.rest.model.sdk_detaillistmodel;
import com.tdi.onemillion.rest.model.sdk_listdatamodel;
import com.tdi.onemillion.rest.model.sdk_listmodel;
import com.tdi.onemillion.rest.model.sdk_tokenmodel;
import com.tdi.onemillion.util.sdk_Preference;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdi.onemillion.sdk_Constant.*;

public class SDKRestService {

    sdk_Preference sdkPreference;


    public SDKRestService(Context context) {
        sdkPreference = new sdk_Preference(context, PREF_NAME);
    }

    public void getToken() {  //get access_token

        JSONObject obj = new JSONObject();
        try {
            obj.put("grant_type", GRANT_TYPE);
            obj.put("client_id", CLIENT_ID);
            obj.put("client_secret", CLIENT_SECRET);
            obj.put("scope", "");
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.toString());

        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), obj.toString());

        final SDKRestInterface service = SDKRestClient.getHttp();
        Call<sdk_tokenmodel> call = service.getToken(requestBody);

        call.enqueue(new Callback<sdk_tokenmodel>() {
            @Override
            public void onResponse(Call<sdk_tokenmodel> call, Response<sdk_tokenmodel> response) {
                if (response.code() == 200 && response.body() != null) {


                    sdkPreference.put("Access_token", response.body().getAccess_token());

                    Log.e(LOG_TAG, response.body().getToken_type());
                    Log.e(LOG_TAG, response.body().getExpires_in().toString());
                    Log.e(LOG_TAG, response.body().getAccess_token());
                } else {

                    Log.e(LOG_TAG, response.body().getError());
                    Log.e(LOG_TAG, response.body().getError_description());
                    Log.e(LOG_TAG, response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<sdk_tokenmodel> call, Throwable t) {
                Log.e(LOG_TAG, "getToken 통신 오류 : " + t.getMessage());
            }
        });
    }


    public ListListener listListener = null;
    public DetailListListener detaillistListener = null;

    public interface ListListener {
        void ListListener(
                sdk_listdatamodel data
        );

        void ErrorCode(int statusCode, String name, String message, int page, String code);
    }

    public interface DetailListListener {


        void DetailListListener(
                sdk_articlesmodel data
        );

        void DetailErrorCode(int statusCode,  String message);


    }

    public void getList(int user_id, int page, int per_page, String platform, String search) {
        Log.e(LOG_TAG, "pagepage : " + page);

        final SDKRestInterface service = SDKRestClient.getHttp();

//        Call<sdk_listmodel> call = service.getList("Bearer " + sdkPreference.getString("Access_token", ""),"C9", API_VERSION, MEDIA_ID, user_id, page, per_page, platform, search);
        Call<sdk_listmodel> call = service.getList(AUTHORIZATION, C9_KEY, API_VERSION, MEDIA_ID, user_id, page, per_page, platform, search);


        call.enqueue(new Callback<sdk_listmodel>() {
            @Override
            public void onResponse(Call<sdk_listmodel> call, Response<sdk_listmodel> response) {
                Log.e(LOG_TAG, response.toString());
                if (response.code() == 200 && response.body() != null) {

                    if (response.body().getStatusCode() == 200) {
                        listListener.ListListener(response.body().getData());
                        Log.e(LOG_TAG, "getList 성공 : " + response.body().getData().getTotalCount());


                    } else {
                        listListener.ErrorCode(response.body().getError().getStatusCode(), response.body().getError().getName(), response.body().getError().getMessage(), page, response.body().getError().getCode());
                        Log.e(LOG_TAG, "getList 실패 : " + response.body().getStatusCode());
                    }
                } else {

                    listListener.ErrorCode(response.code(), "", response.message(), page, "");

                    Log.e(LOG_TAG, "getList 실패 : " + response.code());

                }
            }

            @Override
            public void onFailure(Call<sdk_listmodel> call, Throwable t) {
                listListener.ErrorCode(-1, "", "getList 통신 오류", page, "");
                Log.e(LOG_TAG, "getList 통신 오류 : " + t.getMessage());

                t.printStackTrace();
            }
        });
    }


    public void getDetailList(Long article_id) {

        final SDKRestInterface service = SDKRestClient.getHttp();

        Call<sdk_detaillistmodel> call = service.getDetailList(AUTHORIZATION, C9_KEY, API_VERSION, article_id);


        call.enqueue(new Callback<sdk_detaillistmodel>() {
            @Override
            public void onResponse(Call<sdk_detaillistmodel> call, Response<sdk_detaillistmodel> response) {
                Log.e(LOG_TAG, response.toString());
                if (response.code() == 200 && response.body() != null) {

                    if (response.body().getStatusCode() == 200&&response.body().getData()!=null) {
                        detaillistListener.DetailListListener(response.body().getData());
                        Log.e(LOG_TAG, "getDetailList 성공 : " + response.body().getMessage());
                        Log.e(LOG_TAG, "getDetailList 성공 : " + response.body().getData().toString());


                    } else {
                        detaillistListener.DetailErrorCode(response.body().getStatusCode(), response.body().getMessage());
                        Log.e(LOG_TAG, "getDetailList 실패 : " + response.body().getStatusCode());
                    }
                } else {


                    detaillistListener.DetailErrorCode(response.code(),  response.message());
                    Log.e(LOG_TAG, "getDetailList 실패 : " + response.code());

                }
            }

            @Override
            public void onFailure(Call<sdk_detaillistmodel> call, Throwable t) {
                detaillistListener.DetailErrorCode(-1,  "getList 통신 오류");
                Log.e(LOG_TAG, "getDetailList 통신 오류 : " + t.getMessage());

                t.printStackTrace();
            }
        });
    }


}
