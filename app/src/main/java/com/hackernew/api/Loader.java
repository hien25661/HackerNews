package com.hackernew.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hackernew.BuildConfig;
import com.hackernew.model.Story;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hien on 5/29/2017.
 */

public class Loader {
    private static Loader instance;

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    public void getTopStory(final ResultCallBackApi callback) {
        HackerNewService.getService().getTopStory(BuildConfig.URL_GET_TOP_STORIES).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null && response.body().toString()!=null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Long>>() {
                    }.getType();
                    List<Long> listIdStory = gson.fromJson(response.body().toString(), type);
                    ArrayList<Story> stories = new ArrayList<Story>();
                    for (Long id : listIdStory) {
                        Story story = new Story();
                        story.setId(id);
                        stories.add(story);
                    }
                    callback.success(stories);
                }else callback.failed();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("getTopStory", "" + t.getMessage());
                callback.failed();
            }
        });
    }

    public void getInfoItem(long item_id, final ResultCallBackApi callback) {
        HackerNewService.getService().getInfoItem(item_id).enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                Log.e("getInfoItem", "" + response.body().toJson());
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                Log.e("getInfoItem", "" + t.getMessage());
            }
        });
    }
}
