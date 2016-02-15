package com.xwj.jokexwj.joke.model;

/**
 * Created by xwjsd on 2016-02-15.
 */
public interface Listener<T> {
    void onSuccess(T t);

    void onFailed(T t, String msg);
}
