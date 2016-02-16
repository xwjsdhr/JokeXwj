package com.xwj.jokexwj.interactors.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;
import com.xwj.jokexwj.interactors.DownLoadListener;
import com.xwj.jokexwj.interactors.DownloadImageInteractor;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xwjsd on 2016-02-16.
 */
public class DownloadImageInteratorImpl implements DownloadImageInteractor {
    private Context mContext;

    public DownloadImageInteratorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void downLoadImage(final FunnyPic funnyPic, final DownLoadListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(mContext).load(funnyPic.url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File filesDir = mContext.getFilesDir();
                File file = new File(filesDir, funnyPic.hashId);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess();
                        }
                    });
                } else {
                    listener.onFailed();
                }
            }
        }).start();
    }

}
