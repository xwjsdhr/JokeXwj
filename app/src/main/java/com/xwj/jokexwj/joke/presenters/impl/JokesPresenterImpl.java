package com.xwj.jokexwj.joke.presenters.impl;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xwj.jokexwj.api.NetClient;
import com.xwj.jokexwj.dao.JokesDao;
import com.xwj.jokexwj.joke.presenters.JokesPresenter;
import com.xwj.jokexwj.joke.views.JokeView;
import com.xwj.jokexwj.model.Joke;
import com.xwj.jokexwj.model.JokeData;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by xwjsd on 2016-02-14.
 */
public class JokesPresenterImpl implements JokesPresenter {

    private JokeView mJokeView;
    private Context mContext;
    private NetClient mNetClient;
    private Gson mGson;
    private JokesDao mJokesDao;
    Activity activity;

    public JokesPresenterImpl(Context context, JokeView jokeView) {
        mContext = context;
        mJokeView = jokeView;
        mNetClient = new NetClient();
        mGson = new Gson();
        mJokesDao = new JokesDao(context);
        activity = (Activity) jokeView.gainContext();
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onResume() {
        mJokeView.showProgress();
        mNetClient.getNewestJokes(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                final List<Joke> allJokes = mJokesDao.getAllJokes();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (allJokes != null && allJokes.size() > 0) {
                            mJokeView.bindView(allJokes);
                        }
                        mJokeView.hideProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final JokeData data = mGson.fromJson(response.body().string(), JokeData.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mJokeView.bindView(data.result.data);
                        mJokeView.hideProgress();
                    }
                });
                mJokesDao.insertJokeList(data.result.data);
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
        mNetClient.getNewestJokes(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final List<Joke> allJokes = mJokesDao.getAllJokes();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "网络连接失败。", Toast.LENGTH_SHORT).show();
                        if (allJokes != null && allJokes.size() > 0) {
                            mJokeView.bindView(allJokes);
                        }
                        mJokeView.hideProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final JokeData data = mGson.fromJson(response.body().string(), JokeData.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mJokeView.bindView(data.result.data);
                        mJokeView.hideProgress();
                    }
                });
                mJokesDao.insertJokeList(data.result.data);
            }
        });
    }
}
