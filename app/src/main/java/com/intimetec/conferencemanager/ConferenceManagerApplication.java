package com.intimetec.conferencemanager;

import android.app.Application;
import android.content.Context;

/**
 * @author Swarn Singh
 */

public class ConferenceManagerApplication extends Application {

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
