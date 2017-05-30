package com.hackernew.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateUtils;

/**
 * Created by Hien on 5/29/2017.
 */

public class Utils {
    public static void openUrlBrowse(Context context, String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
    public static String getFormatTime(long timeMillis) {
        long time = Math.max(System.currentTimeMillis() - timeMillis, 0);
        if (time >= DateUtils.YEAR_IN_MILLIS) {
            return (time / DateUtils.YEAR_IN_MILLIS) + " years ago";
        }
        if (time >= DateUtils.WEEK_IN_MILLIS) {
            return (time / DateUtils.WEEK_IN_MILLIS) + " weeks ago";
        }
        if (time >= DateUtils.DAY_IN_MILLIS) {
            return (time / DateUtils.DAY_IN_MILLIS) + " days ago";
        }
        if (time >= DateUtils.HOUR_IN_MILLIS) {
            return (time / DateUtils.HOUR_IN_MILLIS) + " hours ago";
        }
        return (time / DateUtils.MINUTE_IN_MILLIS) + " minutes ago";
    }
}
