package com.xwj.jokexwj.funnypic.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.xwj.jokexwj.R;
import com.xwj.jokexwj.funnypic.presenters.PicShowPresenter;
import com.xwj.jokexwj.funnypic.presenters.PicShowPresenterImpl;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by xwjsd on 2016-02-16.
 */
public class PicShowActivity extends AppCompatActivity implements PicShowView {

    private PicShowPresenter mPicShowPresenter;
    @InjectView(R.id.gif_main)
    GifImageView mGifImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);
        ButterKnife.inject(this);
        mPicShowPresenter = new PicShowPresenterImpl(this, this);
        mPicShowPresenter.onCreate();
    }

    @Override
    public void bindView(FunnyPic funnyPic) {
        Ion.with(mGifImageView).load(funnyPic.url).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView result) {
                hideProgress();
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
