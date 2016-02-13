package com.xwj.jokexwj.main;

import com.xwj.jokexwj.model.Joke;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-12.
 */
public interface MainView {
    void showProgress();

    void hideProgress();

    void bindView(List<Joke> jokes);

    void bindMore(List<Joke> jokes);

    int getJokeCount();

    void clearList();

}
