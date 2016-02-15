package com.xwj.jokexwj.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class ImageUtils {
    public static ImageUtils imageUtils;

    private Context mContext;

    private ImageUtils(Context context) {
        mContext = context;
    }

    public ImageUtils get(Context context) {
        if (imageUtils == null) {
            imageUtils = new ImageUtils(context);
        }
        return imageUtils;
    }

    public void into(String url, ImageView imageView) {
        Bitmap bitmap = this.loadBitmapFromDisk(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            bitmap = this.loadBitmapFromNet(url);
            imageView.setImageBitmap(bitmap);
        }

    }

    public Bitmap loadBitmapFromNet(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(mContext).load(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.saveBitmap2Disk(url, bitmap);
        return bitmap;
    }

    private void saveBitmap2Disk(String url, Bitmap bitmap) {
        File cacheDir = mContext.getExternalCacheDir();
        File file = new File(cacheDir, url);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
    }

    private Bitmap loadBitmapFromDisk(String url) {
        File cacheDir = mContext.getExternalCacheDir();
        File file = new File(cacheDir, url);
        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(mContext).load(file).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            return bitmap;
        }
        return null;
    }
}
