package com.xwj.jokexwj.funnypic.presenters;

import android.view.View;

import com.xwj.jokexwj.funnypic.model.FunnyPicsModel;
import com.xwj.jokexwj.funnypic.model.impl.FunnyPicsModelImpl;
import com.xwj.jokexwj.funnypic.views.FunnyPicView;
import com.xwj.jokexwj.joke.model.Listener;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicsPresenterImpl implements FunnyPicsPresenter {

    private FunnyPicView mFunnyPicView;

    private FunnyPicsModel mFunnyPicsModel;

    public FunnyPicsPresenterImpl(FunnyPicView funnyPicView) {
        this.mFunnyPicView = funnyPicView;
        mFunnyPicsModel = new FunnyPicsModelImpl(funnyPicView.gainContext());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    private void getNewestFunnyPics() {
        mFunnyPicsModel.getNewestFunnyPics(new Listener<List<FunnyPic>>() {
            @Override
            public void onSuccess(List<FunnyPic> list) {
                mFunnyPicView.bindView(list);
                mFunnyPicView.hideProgress();
            }

            @Override
            public void onFailed(List<FunnyPic> list, String msg) {
                mFunnyPicView.bindView(list);
                mFunnyPicView.hideProgress();
            }
        });
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
        this.getNewestFunnyPics();
    }

    @Override
    public void onViewCreated() {
        mFunnyPicView.showProgress();
        getNewestFunnyPics();
    }
}
