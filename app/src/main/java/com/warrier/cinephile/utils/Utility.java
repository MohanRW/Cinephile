package com.warrier.cinephile.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeIntents;
import com.warrier.cinephile.R;

/**
 * Created by Galahad on 17/11/2017.
 */

public class Utility {

    public static boolean hasNetworkConnection(Context context) {
        boolean hasConnectedWifi = false;
        boolean hasConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    hasConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    hasConnectedMobile = true;
        }
        return hasConnectedWifi || hasConnectedMobile;
    }

    public static void playVideo(Activity mActivity, String source) {
        if (mActivity != null) {
            String version = YouTubeIntents.getInstalledYouTubeVersionName(mActivity);
            if (version == null) {
                Toast.makeText(mActivity, R.string.youtube_not_installed, Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = YouTubeIntents.createPlayVideoIntentWithOptions(mActivity, source, true, false);
                mActivity.startActivity(intent);
            }
        }
    }

}
