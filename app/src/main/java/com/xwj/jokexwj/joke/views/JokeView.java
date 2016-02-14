package com.xwj.jokexwj.joke.views;

import android.content.Context;

import com.xwj.jokexwj.model.Joke;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-14.
 */
public interface JokeView {

    void showProgress();

    void hideProgress();

    void bindView(List<Joke> jokes);

    void bindMore(List<Joke> jokes);

    int getJokeCount();

    void clearList();

    Context gainContext();

}
