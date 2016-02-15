package com.xwj.jokexwj.adapter.views.impl;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.views.FunnyPicItemView;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicViewHolder extends RecyclerView.ViewHolder implements FunnyPicItemView {

    @InjectView(R.id.iv_item_funny_pic)
    public ImageView mIvItem;

    public FunnyPicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bindView(FunnyPic funnyPic) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
