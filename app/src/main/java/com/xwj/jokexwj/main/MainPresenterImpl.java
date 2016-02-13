package com.xwj.jokexwj.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xwj.jokexwj.api.NetClient;
import com.xwj.jokexwj.dao.JokesDao;
import com.xwj.jokexwj.model.Joke;
import com.xwj.jokexwj.model.JokeData;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private MainView mMainView;
    private Context mContext;
    private NetClient mNetClient;
    private Gson mGson;
    private JokesDao mJokesDao;
    MainActivity activity;

    public MainPresenterImpl(Context context, MainView mainView) {
        mContext = context;
        mMainView = mainView;
        mNetClient = new NetClient();
        mGson = new Gson();
        mJokesDao = new JokesDao(context);
        activity = (MainActivity) mainView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mMainView.showProgress();
//        mNetClient.getNewestJokes(
//                new HttpUtil.HttpCallBack() {
//                    @Override
//                    public void onFailed(Call call, IOException e) {
//                        final List<Joke> allJokes = mJokesDao.getAllJokes();
//                        if (allJokes != null && allJokes.size() > 0) {
//                            mMainView.bindView(allJokes);
//                        }
//                        mMainView.hideProgress();
//                    }
//
//                    @Override
//                    public void onSuccess(Call call, Response response) {
//                        final JokeData data;
//                        try {
//                            data = mGson.fromJson(response.body().string(), JokeData.class);
//                            mMainView.bindView(data.result.data);
//                            mJokesDao.insertJokeList(data.result.data);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        mMainView.hideProgress();
//                    }
//
//                }
//
//        );
        mNetClient.getNewestJokes(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                final List<Joke> allJokes = mJokesDao.getAllJokes();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (allJokes != null && allJokes.size() > 0) {
                            mMainView.bindView(allJokes);
                        }
                        mMainView.hideProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final JokeData data = mGson.fromJson(response.body().string(), JokeData.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMainView.bindView(data.result.data);
                        mMainView.hideProgress();
                    }
                });
                mJokesDao.insertJokeList(data.result.data);
            }
        });

    }

    @Override
    public void onResume() {

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
                        if (allJokes != null && allJokes.size() > 0) {
                            mMainView.bindView(allJokes);
                        }
                        mMainView.hideProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final JokeData data = mGson.fromJson(response.body().string(), JokeData.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMainView.bindView(data.result.data);
                        mMainView.hideProgress();
                    }
                });
                mJokesDao.insertJokeList(data.result.data);
            }
        });
    }
}
