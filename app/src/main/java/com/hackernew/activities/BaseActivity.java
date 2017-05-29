package com.hackernew.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hackernew.R;
import com.hackernew.helper.EventBusUtils;

/**
 * Created by Hien on 5/29/2017.
 */

public class BaseActivity extends AppCompatActivity {
    protected EventBusUtils.Holder eventBus = EventBusUtils.getDefault();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int colorActionBar;
        try {
            colorActionBar = ContextCompat.getColor(this, R.color.color_action_bar);
        }
        catch(NullPointerException e) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                colorActionBar = this.getColor(R.color.color_action_bar);
            }
            else {
                colorActionBar = getResources().getColor(R.color.color_action_bar);
            }
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colorActionBar));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(eventBus, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusUtils.register(eventBus, this);
    }
}
