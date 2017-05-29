package com.hackernew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.hackernew.R;
import com.hackernew.adapter.TopStoryAdapter;
import com.hackernew.api.Loader;
import com.hackernew.api.ResultCallBackApi;
import com.hackernew.helper.Consts;
import com.hackernew.helper.event.ChooseStoryEvent;
import com.hackernew.model.Story;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hien on 5/29/2017.
 */

public class TopStoryActivity extends BaseActivity {
    @Bind(R.id.rcvTopStory)
    UltimateRecyclerView rcvTopStory;

    private TopStoryAdapter adapter;
    private Loader loader;
    private ArrayList<Story> mListStories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    private void initView() {
        //setup ActionBar
        getSupportActionBar().setTitle(getString(R.string.top_story));

        //Setup Recycleview
        rcvTopStory.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTopStory.setLayoutManager(linearLayoutManager);
        rcvTopStory.mSwipeRefreshLayout.setEnabled(true);
        rcvTopStory.setDefaultOnRefreshListener(pullToRefreshListener);
        rcvTopStory.addItemDividerDecoration(this);
    }

    private void loadData() {
        loader = Loader.getInstance();
        loader.getTopStory(new ResultCallBackApi() {
            @Override
            public void success(Object... params) {
                if (params[0] != null) {
                    mListStories = (ArrayList<Story>) params[0];
                    adapter = new TopStoryAdapter(mListStories);
                    rcvTopStory.setAdapter(adapter);
                }
            }

            @Override
            public void failed() {

            }
        });
    }

    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // TODO: Refresh Top Story here
            loadData();
        }
    };

    @Subscribe
    public void showDetailStory(ChooseStoryEvent event) {
        if(event!=null && event.getStory()!=null){
            Intent t = new Intent(TopStoryActivity.this, DetailActivity.class);
            t.putExtra(Consts.PARAM_STORY_ITEM,event.getStory());
            startActivity(t);
        }
    }

}
