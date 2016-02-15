package com.xwj.jokexwj.views;

/**
 * Created by xwjsd on 2016-02-15.
 */
public interface BaseView<T> {
    void bindView(T t);

    void showProgress();

    void hideProgress();
}
