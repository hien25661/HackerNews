package com.hackernew.api;

import com.hackernew.model.Comment;
import com.hackernew.model.Story;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Hien on 5/29/2017.
 */

public interface HackerNewsApi {
    @GET
    Call<String> getTopStory(@Url String url);

    @GET("item/{item-id}.json?print=pretty")
    Call<Story> getStoryItem(@Path("item-id") long item_id);

    @GET("item/{item-id}.json?print=pretty")
    Call<Comment> getComment(@Path("item-id") long item_id);

}
