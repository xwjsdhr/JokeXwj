package com.xwj.jokexwj.adapter.presenter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.xwj.jokexwj.adapter.FooterView;
import com.xwj.jokexwj.api.NetClient;
import com.xwj.jokexwj.dao.JokesDao;
import com.xwj.jokexwj.main.MainView;
import com.xwj.jokexwj.model.JokeData;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by xwjsd on 2016-02-13.
 */
public class FooterPresenterImpl implements FooterPresenter {
    private FooterView mFooterView;
    private NetClient mNetClient;
    private MainView mMainView;
    private Activity activity;
    private JokesDao mDao;


    public FooterPresenterImpl(MainView mainView, FooterView footerView) {
        mFooterView = footerView;
        mMainView = mainView;
        activity = (Activity) mainView;
        mNetClient = new NetClient();
        mDao = new JokesDao((Context) mainView);
    }

    @Override
    public void onClick(View view) {
        mFooterView.showLoadingMore();
        mFooterView.unableClick();
        mNetClient.getMoreNewestJokes((mMainView.getJokeCount() / 20) + 1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                final JokeData data = gson.fromJson(response.body().string(), JokeData.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMainView.bindMore(data.result.data);
                        mFooterView.hideLoadingMore();
                        mFooterView.enableClick();
                    }
                });
                mDao.insertJokeList(data.result.data);
            }
        });
    }

}
