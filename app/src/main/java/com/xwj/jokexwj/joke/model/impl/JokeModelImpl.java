package com.xwj.jokexwj.joke.model.impl;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.xwj.jokexwj.api.NetClient;
import com.xwj.jokexwj.dao.JokesDao;
import com.xwj.jokexwj.joke.model.JokeModel;
import com.xwj.jokexwj.joke.model.Listener;
import com.xwj.jokexwj.model.joke.Joke;
import com.xwj.jokexwj.model.joke.JokeData;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class JokeModelImpl implements JokeModel {

    private NetClient mNetClient;
    private Gson mGson;
    private JokesDao mJokesDao;
    private Activity mActivity;

    public JokeModelImpl(Context context) {
        mNetClient = new NetClient();
        mGson = new Gson();
        mJokesDao = new JokesDao(context);
        mActivity = (Activity) context;
    }


    @Override
    public void getNewestJokes(final Listener<List<Joke>> listener) {
        mNetClient.getNewestJokes(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final List<Joke> allJokes = mJokesDao.getAllJokes();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailed(allJokes, e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final JokeData data = mGson.fromJson(response.body().string(), JokeData.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(data.result.data);
                    }
                });
                mJokesDao.insertJokeList(data.result.data);
            }
        });
    }
}
