package com.xwj.jokexwj.adapter.views.impl;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.views.JokeItemView;
import com.xwj.jokexwj.model.joke.Joke;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class JokeViewHolder extends RecyclerView.ViewHolder implements JokeItemView {

    @InjectView(R.id.tv_content)
    public TextView mTvContent;

    @InjectView(R.id.tv_time)
    public TextView mTvTime;

    public JokeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bindView(Joke joke) {
        mTvContent.setText(joke.content);
        mTvTime.setText(joke.updatetime);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }
}
