package com.xwj.jokexwj.funnypic.presenters;

import android.view.View;

import com.xwj.jokexwj.funnypic.views.FunnyPicView;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicsPresenterImpl implements FunnyPicsPresenter {

    private FunnyPicView mFunnyPicView;

    public FunnyPicsPresenterImpl(FunnyPicView funnyPicView) {
        this.mFunnyPicView = funnyPicView;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    @Override
    public void onRefresh() {

    }
}
