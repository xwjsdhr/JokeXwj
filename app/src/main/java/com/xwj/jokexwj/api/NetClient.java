package com.xwj.jokexwj.api;

import com.xwj.jokexwj.utils.Globals;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class NetClient {

    private OkHttpClient client;

//    private HttpUtil httpUtil;

    public NetClient() {
        client = new OkHttpClient();

//        httpUtil = HttpUtil.newInstance();
    }

    public void getNewestJokes(final Callback callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = HttpUrl.parse(Globals.BASE_URL + "content/text.from").newBuilder()
                        .addQueryParameter("page", "1")
                        .addQueryParameter("pagesize", "20")
                        .addQueryParameter("key", Globals.APP_KEY)
                        .build();
                Request request = new Request.Builder().url(url).build();
                Call call = client.newCall(request);
                call.enqueue(callBack);
            }
        }).start();
        //httpUtil.get(Globals.NEWEST_JOKES, callBack);

    }

    public void getMoreNewestJokes(final int page, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = HttpUrl.parse(Globals.BASE_URL + "content/text.from").newBuilder()
                        .addQueryParameter("page", String.valueOf(page))
                        .addQueryParameter("pagesize", "20")
                        .addQueryParameter("key", Globals.APP_KEY)
                        .build();
                Request request = new Request.Builder().url(url).build();
                Call call = client.newCall(request);
                call.enqueue(callback);
            }
        }).start();
    }
}
