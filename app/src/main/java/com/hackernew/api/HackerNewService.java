package com.hackernew.api;

import com.hackernew.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Hien on 5/29/2017.
 */

public class HackerNewService {
    private static HackerNewApi instance;

    public static HackerNewApi getService() {
        if (instance == null) {
            instance = getRetrofit().create(HackerNewApi.class);
        }
        return instance;
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_HACKER_NEWS_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
