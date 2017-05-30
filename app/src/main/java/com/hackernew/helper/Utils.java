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
            return (time / DateUtils.YEAR_IN_MILLIS) + " year ago";
        }
        if (time >= DateUtils.WEEK_IN_MILLIS) {
            return (time / DateUtils.WEEK_IN_MILLIS) + " week ago";
        }
        if (time >= DateUtils.DAY_IN_MILLIS) {
            return (time / DateUtils.DAY_IN_MILLIS) + " day ago";
        }
        if (time >= DateUtils.HOUR_IN_MILLIS) {
            return (time / DateUtils.HOUR_IN_MILLIS) + " hour ago";
        }
        return (time / DateUtils.MINUTE_IN_MILLIS) + " minute ago";
    }
}
