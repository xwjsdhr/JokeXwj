package com.xwj.jokexwj.joke.presenters.impl;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.xwj.jokexwj.joke.model.JokeModel;
import com.xwj.jokexwj.joke.model.Listener;
import com.xwj.jokexwj.joke.model.impl.JokeModelImpl;
import com.xwj.jokexwj.joke.presenters.JokesPresenter;
import com.xwj.jokexwj.joke.views.JokeView;
import com.xwj.jokexwj.model.joke.Joke;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-14.
 */
public class JokesPresenterImpl implements JokesPresenter {

    private JokeView mJokeView;
    private Context mContext;
    Activity activity;
    private JokeModel mJokeModel;

    public JokesPresenterImpl(Context context, JokeView jokeView) {
        mContext = context;
        mJokeView = jokeView;
        mJokeModel = new JokeModelImpl(context);
        activity = (Activity) jokeView.gainContext();
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onResume() {
        mJokeView.showProgress();
        mJokeModel.getNewestJokes(new Listener<List<Joke>>() {
            @Override
            public void onSuccess(List<Joke> jokes) {
                mJokeView.bindView(jokes);
                mJokeView.hideProgress();
            }

            @Override
            public void onFailed(List<Joke> jokes, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                mJokeView.bindView(jokes);
                mJokeView.hideProgress();
            }
        });
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
    public void onRefresh() {
        mJokeModel.getNewestJokes(new Listener<List<Joke>>() {
            @Override
            public void onSuccess(List<Joke> jokes) {
                mJokeView.bindView(jokes);
                mJokeView.hideProgress();
            }

            @Override
            public void onFailed(List<Joke> jokes, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                mJokeView.bindView(jokes);
                mJokeView.hideProgress();
            }
        });
    }
}
