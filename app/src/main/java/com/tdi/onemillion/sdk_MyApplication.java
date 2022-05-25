package com.tdi.onemillion;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class sdk_MyApplication extends MultiDexApplication {

    private static sdk_MyApplication instance = null;

    public static sdk_MyApplication getInstance() {
        if (instance == null)
            instance = new sdk_MyApplication();

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //MultiDex.install(this);


//        AppService.create(getApplicationContext(), false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}

