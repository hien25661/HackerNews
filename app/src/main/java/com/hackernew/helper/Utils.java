package com.hackernew.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Hien on 5/29/2017.
 */

public class Utils {
    public static void openUrlBrowse(Context context, String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
}
