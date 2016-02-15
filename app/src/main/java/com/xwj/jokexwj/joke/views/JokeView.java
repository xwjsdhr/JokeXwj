package com.xwj.jokexwj.joke.views;

import android.content.Context;

import com.xwj.jokexwj.model.joke.Joke;
import com.xwj.jokexwj.views.BaseView;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-14.
 */
public interface JokeView extends BaseView<List<Joke>> {

    void bindMore(List<Joke> jokes);

    int getJokeCount();

    void clearList();

    Context gainContext();

}
