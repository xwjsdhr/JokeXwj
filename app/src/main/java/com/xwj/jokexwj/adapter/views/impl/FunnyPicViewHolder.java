package com.xwj.jokexwj.adapter.views.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.views.FunnyPicItemView;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicViewHolder extends RecyclerView.ViewHolder implements FunnyPicItemView {

    @InjectView(R.id.iv_item_funny_pic)
    public GifImageView mIvItem;

    private Context mContext;

    public FunnyPicViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        mContext = context;
    }

    @Override
    public void bindView(FunnyPic funnyPic) {
        Picasso.with(mContext).load(funnyPic.url).into(mIvItem, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
