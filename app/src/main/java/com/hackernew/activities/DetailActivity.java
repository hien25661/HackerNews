package com.hackernew.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.hackernew.R;
import com.hackernew.helper.Consts;
import com.hackernew.model.Story;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hien on 5/29/2017.
 */

public class DetailActivity extends BaseActivity {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvUrl)
    TextView tvUrl;
    @Bind(R.id.tvNumbComment)
    TextView tvNumbComment;
    @Bind(R.id.tvTimeAndAuthor)
    TextView tvTimeAndAuthor;

    @Bind(R.id.rcvComment)
    UltimateRecyclerView rcvTopStory;

    private Story story;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        getSupportActionBar().setTitle(getString(R.string.story));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setup Recycleview
        rcvTopStory.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTopStory.setLayoutManager(linearLayoutManager);
        rcvTopStory.mSwipeRefreshLayout.setEnabled(true);
        rcvTopStory.addItemDividerDecoration(this);
    }

    private void initData() {
        if (getIntent() != null) {
            story = getIntent().getExtras().getParcelable(Consts.PARAM_STORY_ITEM);
            if (story != null) {
                bindData(story);
            }
        }
    }

    private void bindData(Story story) {
        tvTitle.setText(story.getTitle());
        tvUrl.setText(story.getUrl());
        if (story.getKids() != null && story.getKids().size() >= 0) {
            tvNumbComment.setText(String.format(getString(R.string.numb_comment_format),
                    story.getKids().size()));
        }
        tvTimeAndAuthor.setText("" + story.getTime() + "-" + story.getBy());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
