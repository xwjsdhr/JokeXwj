package com.xwj.jokexwj.funnypic;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by xwjsd on 2016-02-16.
 */
public class PicsDownLoader implements Downloader {
    private Context mContext;


    public PicsDownLoader(Context context) {
        mContext = context;
    }

    @Override
    public Response load(Uri uri, boolean localCacheOnly) throws IOException {
        Bitmap bitmap = Picasso.with(mContext).load(uri).get();
        Response response = new Response(bitmap, true, bitmap.getByteCount());
        return response;
    }
}
