package com.intimetec.conferencemanager.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author Swarn Singh.
 */

public class SharedPreference {

    private Gson mGson = new Gson();
    private Type mTypeObject = new TypeToken<Object>() {}.getType();
    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedPreference(Context context, String prefKey) {
        mPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public void saveObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is empty or null");
        }

        mEditor.putString(key, mGson.toJson(object));
        mEditor.commit();
    }

    public <T> T getObject(String key, Class<T> tClass) {

        String gson = mPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return mGson.fromJson(gson, tClass);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key " + key + " is instanceof other class");
            }
        }
    }

    public void setFlag(String key, boolean flag) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is empty or null");
        }

        mEditor.putBoolean(key, flag);
        mEditor.commit();
    }

    public boolean getFlag(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public void clearObject() {
        mEditor.clear();
        mEditor.commit();
    }
}
