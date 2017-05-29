package com.hackernew.api;

import com.hackernew.model.Story;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Hien on 5/29/2017.
 */

public interface HackerNewApi {
    @GET
    Call<String> getTopStory(@Url String url);

    @GET("item/{item-id}.json?print=pretty")
    Call<Story> getInfoItem(@Path("item-id") long item_id);

}
