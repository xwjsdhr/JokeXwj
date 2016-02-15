package com.xwj.jokexwj.funnypic.model.impl;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.xwj.jokexwj.api.NetClient;
import com.xwj.jokexwj.dao.FunnyPicDao;
import com.xwj.jokexwj.funnypic.model.FunnyPicsModel;
import com.xwj.jokexwj.joke.model.Listener;
import com.xwj.jokexwj.model.funnypic.FunnyPic;
import com.xwj.jokexwj.model.funnypic.FunnyPicData;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicsModelImpl implements FunnyPicsModel {

    private NetClient mNetClient;
    private FunnyPicDao mFunnyPicDao;
    private Activity mActivity;
    private Gson mGson;

    public FunnyPicsModelImpl(Context context) {
        mNetClient = new NetClient();
        mFunnyPicDao = new FunnyPicDao(context);
        mActivity = (Activity) context;
        mGson = new Gson();
    }

    @Override
    public void getNewestFunnyPics(final Listener<List<FunnyPic>> listener) {
        mNetClient.getNewestFunnyPics(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final List<FunnyPic> allFunnyPics = mFunnyPicDao.getAllFunnyPics();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailed(allFunnyPics, e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final FunnyPicData data = mGson.fromJson(response.body().string(), FunnyPicData.class);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(data.result.data);
                    }
                });
                mFunnyPicDao.insertPicList(data.result.data);
            }
        });
    }
}
