package com.xwj.jokexwj.joke.presenters;

import com.xwj.jokexwj.BasePresenter;

/**
 * Created by xwjsd on 2016-02-14.
 */
public interface JokesPresenter extends BasePresenter {
    void onCreate();

    void onResume();

    void onDestroy();

    void onRefresh();

}
