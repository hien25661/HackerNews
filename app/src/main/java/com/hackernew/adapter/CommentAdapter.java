package com.hackernew.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackernew.R;
import com.hackernew.api.Loader;
import com.hackernew.api.ResultCallBackApi;
import com.hackernew.helper.Utils;
import com.hackernew.model.Comment;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hien on 5/29/2017.
 */

public class CommentAdapter extends UltimateViewAdapter<CommentAdapter.CommentHolder> {
    private Loader loader;
    private Context mContext;
    private ArrayList<Comment> comments;

    public CommentAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public CommentHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public CommentHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        CommentHolder vh = new CommentHolder(v);
        mContext = v.getContext();
        loader = Loader.getInstance();
        return vh;
    }

    @Override
    public int getAdapterItemCount() {
        return comments.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        if (position >= 0 && position < comments.size()) {
            Comment comment = comments.get(position);
            if (!comment.isLoaded()) {
                loadComment(holder, comment, position);
            } else {
                bindData(holder, comment, false);
            }
        }
    }

    private void loadComment(final CommentHolder holder, final Comment comment, final int position) {
        loader.getCommentItem(comment.getId(), new ResultCallBackApi() {
            @Override
            public void success(Object... params) {
                if (params[0] != null) {
                    Comment mItem = (Comment) params[0];
                    comment.setObject(mItem);
                    bindData(holder, comment, false);
                }
            }

            @Override
            public void failed() {

            }
        });
    }

    private void bindData(CommentHolder holder, Comment comment, boolean isReply) {
        String textComment = comment.getText();
        String timeAndAuthor = Utils.getFormatTime(comment.getTime() * 1000) + "-" + comment.getBy();
        holder.tvReply.setVisibility(View.GONE);
        holder.tvHeaderReply.setVisibility(View.GONE);
        if (textComment != null) {
            if (!isReply) {
                holder.tvComment.setText(Html.fromHtml(textComment));
            } else {
                holder.tvReply.setVisibility(View.VISIBLE);
                holder.tvReply.setText(Html.fromHtml(textComment));
            }
        }
        if (!isReply) {
            holder.tvHeaderComment.setText(timeAndAuthor);
        } else {
            holder.tvHeaderReply.setVisibility(View.VISIBLE);
            holder.tvHeaderReply.setText(timeAndAuthor);
        }
        /*if(comment.getKids()!=null && comment.getKids().size()>0 && !isReply){
            holder.replyView.setVisibility(View.GONE);
            loadLatestReply(holder,comment);
        }else {
            holder.replyView.setVisibility(View.VISIBLE);
        }*/
        if (!isReply) {
            loadLatestReply(holder, comment);
        }
    }

    private void loadLatestReply(final CommentHolder holder, final Comment comment) {
        if (comment.getKids() != null && comment.getKids().size() > 0 && !comment.isLoaded()) {
            long latestIdReply = comment.getKids().get(0);
            loader.getCommentItem(latestIdReply, new ResultCallBackApi() {
                @Override
                public void success(Object... params) {
                    if (params[0] != null) {
                        comment.setLoaded(true);
                        Comment replyItem = (Comment) params[0];
                        bindData(holder, replyItem, true);
                    }
                }

                @Override
                public void failed() {
                    comment.setLoaded(true);
                }
            });
        }else {
            comment.setLoaded(true);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class CommentHolder extends UltimateRecyclerviewViewHolder {
        @Bind(R.id.tvHeaderComment)
        TextView tvHeaderComment;
        @Bind(R.id.tvComment)
        TextView tvComment;

        @Bind(R.id.tvHeaderReply)
        TextView tvHeaderReply;
        @Bind(R.id.tvReply)
        TextView tvReply;
        @Bind(R.id.replyView)
        View replyView;

        public CommentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
