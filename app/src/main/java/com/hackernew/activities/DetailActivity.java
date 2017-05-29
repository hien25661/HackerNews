package com.hackernew.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hackernew.R;

import butterknife.ButterKnife;

/**
 * Created by Hien on 5/29/2017.
 */

public class DetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }
}
