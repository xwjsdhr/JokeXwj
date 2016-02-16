package com.xwj.jokexwj.funnypic.presenters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.xwj.jokexwj.funnypic.views.PicShowView;
import com.xwj.jokexwj.model.funnypic.FunnyPic;
import com.xwj.jokexwj.utils.Globals;

/**
 * Created by xwjsd on 2016-02-16.
 */
public class PicShowPresenterImpl implements PicShowPresenter {

    private Context mContext;
    private PicShowView mPicShowView;

    public PicShowPresenterImpl(Context context, PicShowView picShowView) {
        mPicShowView = picShowView;
        mContext = context;
    }

    @Override
    public void onCreate() {
        mPicShowView.showProgress();
        FunnyPic funnyPic = getFromIntent();
        if (funnyPic != null) {
            mPicShowView.bindView(funnyPic);
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View view) {
        mPicShowView.finishActivity();
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    private FunnyPic getFromIntent() {
        return (FunnyPic) ((Activity) mContext).getIntent().getSerializableExtra(Globals.FUNNY_PIC);
    }
}
