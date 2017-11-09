package com.intimetec.conferencemanager.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.intimetec.conferencemanager.ConferenceManagerApplication;


/**
 * @author Swarn Singh
 */

public class NetworkUtil {

    public static boolean hasConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) ConferenceManagerApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
