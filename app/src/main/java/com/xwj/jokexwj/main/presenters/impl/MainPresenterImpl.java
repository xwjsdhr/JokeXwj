package com.xwj.jokexwj.main.presenters.impl;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.main.views.MainActivity;
import com.xwj.jokexwj.main.presenters.MainPresenter;
import com.xwj.jokexwj.main.views.MainView;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private MainView mMainView;
    MainActivity activity;

    public MainPresenterImpl(Context context, MainView mainView) {
        mMainView = mainView;
        activity = (MainActivity) mainView;
    }

    @Override
    public void onCreate() {
        mMainView.addJokesFragment();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jokes:
                mMainView.addJokesFragment();
                break;
            case R.id.funny_pics:
                mMainView.addFunnyPicsFragment();
                break;
        }
        return true;
    }

}
