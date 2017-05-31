package com.hackernew.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

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
    private boolean isStagger = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        loadData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user) {
            if(!isStagger) {
                rcvTopStory.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                isStagger = true;
            }else {
                rcvTopStory.setLayoutManager(new LinearLayoutManager(this));
                isStagger = false;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        //setup ActionBar
        getSupportActionBar().setTitle(getString(R.string.top_story));

        //Setup Recycleview
        rcvTopStory.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTopStory.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rcvTopStory.mSwipeRefreshLayout.setEnabled(true);
        rcvTopStory.setDefaultOnRefreshListener(pullToRefreshListener);
       // rcvTopStory.addItemDividerDecoration(this);
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
