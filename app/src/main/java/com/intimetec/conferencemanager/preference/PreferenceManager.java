package com.intimetec.conferencemanager.preference;

import android.content.Context;

import com.intimetec.conferencemanager.constant.PreferenceConstant;

/**
 * @author Swarn Singh.
 */

public class PreferenceManager {

    public static void saveObject(Context context, String key, Object object) {
        SharedPreference sharedPreference = new SharedPreference(context, PreferenceConstant.PREF_KEY);
        sharedPreference.saveObject(key, object);
    }

    public static <T> T getObject(Context context, String key, Class<T> tClass) {
        SharedPreference sharedPreference = new SharedPreference(context, PreferenceConstant.PREF_KEY);
        return sharedPreference.getObject(key,  tClass);
    }

    public static void setFlag(Context context, String key, boolean flag) {
        SharedPreference sharedPreference = new SharedPreference(context, PreferenceConstant.PREF_KEY);
        sharedPreference.setFlag(key, flag);
    }

    public static boolean getFlag(Context context, String key) {
        SharedPreference sharedPreference = new SharedPreference(context, PreferenceConstant.PREF_KEY);
        return sharedPreference.getFlag(key);
    }

    public static void clearPreference(Context context) {
        SharedPreference sharedPreference = new SharedPreference(context, PreferenceConstant.PREF_KEY);
        sharedPreference.clearObject();
    }

}
