package com.hackernew.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.hackernew.R;
import com.hackernew.adapter.CommentAdapter;
import com.hackernew.helper.Consts;
import com.hackernew.helper.Utils;
import com.hackernew.model.Comment;
import com.hackernew.model.Story;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;

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
    UltimateRecyclerView rcvComment;

    private Story story;
    private ArrayList<Long> kids = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private CommentAdapter adapter;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setup Recycleview
        rcvComment.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvComment.setLayoutManager(linearLayoutManager);
        rcvComment.mSwipeRefreshLayout.setEnabled(false);
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
            kids = (ArrayList<Long>) story.getKids();
        }
        tvTimeAndAuthor.setText("" + Utils.getFormatTime(story.getTime() * 1000) + "-" + story.getBy());
        tvNumbComment.setText(String.format(getString(R.string.numb_comment_format),
                kids.size()));
        //get 10 Latest comment
        if(kids.size() > 0) {
            comments = getTop10CommentList(kids);
            adapter = new CommentAdapter(comments);
            rcvComment.setAdapter(adapter);
        }
    }
    private ArrayList<Comment> getTop10CommentList(ArrayList<Long> kids){
        ArrayList<Comment> list = new ArrayList<>();
        for (long id_kid : kids){
            if(list.size() > 10) break;
            Comment comment = new Comment();
            comment.setId(id_kid);
            list.add(comment);
        }
        return list;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
