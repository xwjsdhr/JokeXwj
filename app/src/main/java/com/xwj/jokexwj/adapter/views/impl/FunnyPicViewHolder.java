package com.xwj.jokexwj.adapter.views.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.presenter.FunnyPicItemPresenter;
import com.xwj.jokexwj.adapter.presenter.FunnyPicItemPresenterImpl;
import com.xwj.jokexwj.adapter.views.FunnyPicItemView;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicViewHolder extends RecyclerView.ViewHolder implements FunnyPicItemView, View.OnClickListener {

    @InjectView(R.id.iv_item_funny_pic)
    public GifImageView mIvItem;

    @InjectView(R.id.tv_item_pic_content)
    TextView mTvContent;
    private Context mContext;
    private FunnyPicItemPresenter mFunnyPicItemPresenter;
    private FunnyPic mFunnyPic;

    public FunnyPicViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        mContext = context;
        mFunnyPicItemPresenter = new FunnyPicItemPresenterImpl(context, this);
        itemView.setOnClickListener(this);
    }

    @Override
    public void bindView(FunnyPic funnyPic) {
        mFunnyPic = funnyPic;
        Picasso.with(mContext)
                .load(funnyPic.url)
                .placeholder(R.drawable.placeholder)
                .into(mIvItem);
        mTvContent.setText(funnyPic.content);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick(View v) {
        mFunnyPicItemPresenter.onClick(v);
    }

    @Override
    public FunnyPic getFunnyPic() {
        return mFunnyPic;
    }
}
