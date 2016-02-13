package com.xwj.jokexwj.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by xwjsd on 2016-02-13.
 */
public class HttpUtil {
    private static HttpUtil httpUtil;

    private Request.Builder mBuilder;
    private OkHttpClient mClient;

    private HttpUtil() {
        mBuilder = new Request.Builder();
        mClient = new OkHttpClient();
    }

    public static HttpUtil newInstance() {
        if (httpUtil == null) {
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }


    public void get(String url, HttpCallBack callBack) {
        call("GET", url, callBack);
    }

    public void post(String url, HttpCallBack callBack) {
        call("POST", url, callBack);
    }

    private void call(String method, String url, final HttpCallBack cb) {
        Request request = mBuilder
                .url(url)
                .method(method, method.equals("GET") ? null : new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                }).build();
        mClient.newCall(request).enqueue(new Callback() {
            Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cb.onFailed(call, e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cb.onSuccess(call, response);
                    }
                });
            }
        });

    }

   public interface HttpCallBack {
        void onFailed(Call call, IOException e);

        void onSuccess(Call call, Response response);
    }
}
