package com.hackernew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackernew.R;
import com.hackernew.api.Loader;
import com.hackernew.api.ResultCallBackApi;
import com.hackernew.helper.EventBusUtils;
import com.hackernew.helper.Utils;
import com.hackernew.helper.event.ChooseStoryEvent;
import com.hackernew.model.Story;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hien on 5/29/2017.
 */

public class TopStoryAdapter extends UltimateViewAdapter<TopStoryAdapter.StoryViewHolder> {
    private ArrayList<Story> mListStory = new ArrayList<>();
    private Loader loader;
    private Context mContext;
    public TopStoryAdapter(ArrayList<Story> list){
        this.mListStory = list;
    }
    @Override
    public StoryViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public StoryViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        StoryViewHolder vh = new StoryViewHolder(v);
        mContext = v.getContext();
        loader = Loader.getInstance();
        return vh;
    }

    @Override
    public int getAdapterItemCount() {
        return mListStory.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        if(position >=0 && position < mListStory.size()){
            final Story item = mListStory.get(position);
            if(item!=null) {
                if (!item.isLoaded()) {
                    loadInfoItem(holder, item, position);
                } else {
                    bindData(holder, item, position);
                }
                holder.imvGotoUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.openUrlBrowse(v.getContext(),item.getUrl());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBusUtils.getDefault().post(new ChooseStoryEvent(item));
                    }
                });
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    private void loadInfoItem(final StoryViewHolder holder, final Story story, final int position){
        loader.getInfoItem(story.getId(), new ResultCallBackApi() {
            @Override
            public void success(Object... params) {
                if(params[0]!=null){
                    Story mItemStory = (Story) params[0];
                    story.setObject(mItemStory);
                    story.setLoaded(true);
                    bindData(holder,story,position);
                }
            }

            @Override
            public void failed() {
            }
        });
    }

    private void bindData(StoryViewHolder holder, Story story, int position) {
        holder.tvNameStory.setText(story.getTitle());
        holder.tvAuthor.setText(story.getBy());
        holder.tvNumbStory.setText(""+(position+1));
        holder.tvScore.setText(String.format(mContext.getString(R.string.story_score_format),story.getScore()));
        if(story.getKids()!=null) {
            holder.tvNumbComment.setText("" + story.getKids().size());
        }
        holder.tvTime.setText(""+story.getTime());
    }

    class StoryViewHolder extends UltimateRecyclerviewViewHolder {
        @Bind(R.id.tvNameStory)
        TextView tvNameStory;
        @Bind(R.id.tvAuthor)
        TextView tvAuthor;
        @Bind(R.id.tvNumb)
        TextView tvNumbStory;
        @Bind(R.id.tvNumbComment)
        TextView tvNumbComment;
        @Bind(R.id.tvTime)
        TextView tvTime;
        @Bind(R.id.tvScore)
        TextView tvScore;
        @Bind(R.id.imvGotoUrl)
        ImageView imvGotoUrl;

        public StoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
