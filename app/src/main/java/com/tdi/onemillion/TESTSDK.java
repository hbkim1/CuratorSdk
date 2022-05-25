package com.tdi.onemillion;

import android.app.Activity;
import android.content.Intent;

import com.tdi.onemillion.activity.sdk_MainActivity;

public class TESTSDK {


    public static void startSDK(Activity activity) {

        Intent intent = new Intent(activity, sdk_MainActivity.class);
        activity.startActivity(intent);
    }


}
