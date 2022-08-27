package com.ffs.fortunefundadmin.utils;

import com.ffs.fortunefundadmin.FortuneFundApp;
import com.google.gson.Gson;

public class SharedPrefUtils {


    public static void putString(String key , String value)
    {
        FortuneFundApp.getInstance().getSharedPreferences().edit().putString(key,value).commit();
    }

    public static String getString(String key)
    {
       return FortuneFundApp.getInstance().getSharedPreferences().getString(key,"");

    }

    public static void putObject(String key, Object obj) {
        checkForNullKey(key);
        Gson gson = new Gson();
        putString(key, gson.toJson(obj));
    }

    public static Object getObject(String key, Class<?> classOfT) {

        String json = getString(key);
        Object value = new Gson().fromJson(json, classOfT);
        if (value == null) {
            value = "";
            // throw new NullPointerException();
        }
        return value;
    }

    public static void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    public static void clear() {
        FortuneFundApp.getInstance().getSharedPreferences().edit().clear().apply();
    }

    public static void remove(String key) {
        FortuneFundApp.getInstance().getSharedPreferences().edit().remove(key).apply();
    }



    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}

