package com.xwj.jokexwj.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.presenter.FooterPresenter;
import com.xwj.jokexwj.adapter.presenter.FooterPresenterImpl;
import com.xwj.jokexwj.joke.views.JokeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xwjsd on 2016-02-13.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder implements FooterView, View.OnClickListener {
    private static final String TAG = FooterViewHolder.class.getSimpleName();
    @InjectView(R.id.tv_isloading)
    public TextView mTvLoading;
    @InjectView(R.id.progress)
    public ProgressBar progressBar;
    @InjectView(R.id.ll_loading)
    public LinearLayout mLlLoading;
    @InjectView(R.id.ll_loading_more)
    public LinearLayout mLlLoadingMore;

    private FooterPresenter mFooterPresenter;

    public FooterViewHolder(JokeView jokeView, View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        mFooterPresenter = new FooterPresenterImpl(jokeView, this);
        itemView.setOnClickListener(this);
    }

    @Override
    public void showLoadingMore() {
        mLlLoadingMore.setVisibility(View.GONE);
        mLlLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingMore() {
        mLlLoadingMore.setVisibility(View.VISIBLE);
        mLlLoading.setVisibility(View.GONE);
    }

    @Override
    public void unableClick() {
        itemView.setClickable(false);
        itemView.setEnabled(false);
    }

    @Override
    public void enableClick() {
        itemView.setEnabled(true);
        itemView.setClickable(true);
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "ONCLICK");
        mFooterPresenter.onClick(v);
    }
}
