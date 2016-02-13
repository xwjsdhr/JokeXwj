package com.xwj.jokexwj.dao;

import android.content.Context;

import com.xwj.jokexwj.model.Joke;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-13.
 */
public class JokesDao {

    private JokesDb db;

    public JokesDao(Context context) {
        db = new JokesDb(context, 1);
    }

    public void insertJokeList(List<Joke> list) {
        db.insertJokesIntoDB(list);
    }

    public List<Joke> getAllJokes() {
        return db.getAllJokes();
    }
}
