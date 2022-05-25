package com.tdi.onemillion.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ymjang on 2018-03-12.
 * MyPreference
 */

public class sdk_Preference {

    private Context context;
    private String prefKey;

    public sdk_Preference(Context context, String prefKey) {
        this.context = context;
        this.prefKey = prefKey;
    }

    private SharedPreferences getSharedPreference() {
        try {
            return context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        } catch (Exception e) {
            return null;
        }
    }

    public String getString(String key, String defValue) {
        try {
            if (getSharedPreference() == null) {
                return "";
            } else {
                return getSharedPreference().getString(key, defValue);
            }
        } catch (Exception e) {
            remove();
            return defValue;
        }
    }

    public int getInt(String key, int defValue) {
        if (getSharedPreference() == null) {
            return 1;
        } else {
            return getSharedPreference().getInt(key, defValue);
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        if (getSharedPreference() == null) {
            return true;
        } else {
            return getSharedPreference().getBoolean(key, defValue);
        }
    }

    public long getLong(String key, long defValue) {
        if (getSharedPreference() == null) {
            return 0;
        } else {
            return getSharedPreference().getLong(key, defValue);
        }
    }

    public void put(String key, String value) {
        try {
            SharedPreferences.Editor editor = getSharedPreference().edit();

            editor.putString(key, value);
            editor.apply();
        } catch (Exception e) {
        }
    }

    public boolean put(String key, int value, String sync) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.putInt(key, value);
        return editor.commit();
    }

    public boolean put(String key, String value, String sync) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.putString(key, value);
        return editor.commit();
    }

    public void put(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.putBoolean(key, value);
        editor.apply();
    }

    public void put(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.putInt(key, value);
        editor.apply();
    }


    public void put(String key, float value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.putFloat(key, value);
        editor.apply();
    }

    public void put(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.putLong(key, value);
        editor.apply();
    }


    public Map<String, ?> getAttributes() {
        return getSharedPreference().getAll();
    }

    public boolean remove(String key) {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.remove(key);
        return editor.commit();
    }

    public void remove() {
        SharedPreferences.Editor editor = getSharedPreference().edit();

        editor.clear();
        editor.apply();
    }


    public void putStringList(String key, List<String> dataList) {
        Set<String> taskSet = new HashSet<String>(dataList);
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putStringSet(key, taskSet);
        editor.apply();
    }

    public List<String> getStringList(String key) {
        if (getSharedPreference() == null) {
            return new ArrayList<String>();
        } else {
            Set<String> taskSet = getSharedPreference().getStringSet(key, new HashSet<String>());
            return new ArrayList<String>(taskSet);
        }
    }


}
