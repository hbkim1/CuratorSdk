package com.tdi.onemillion.rest;



import com.tdi.onemillion.sdk_Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SDKRestClient {

    private static SDKRestInterface SDKRestInterface;

    public static SDKRestInterface getHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS);

        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request);
            }
        });

/*        if (Constant.isDebug) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }*/

        OkHttpClient okclient = httpClient.build();

        if (SDKRestInterface == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(sdk_Constant.DEV_BASE_URL)
                    .client(okclient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            SDKRestInterface = client.create(SDKRestInterface.class);
        }

        return SDKRestInterface;
    }
}
