package com.xwj.jokexwj.joke.model;

import com.xwj.jokexwj.model.joke.Joke;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public interface JokeModel {
    void getNewestJokes(Listener<List<Joke>> listener);
}
