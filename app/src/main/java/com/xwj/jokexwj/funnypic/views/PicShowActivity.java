package com.xwj.jokexwj.funnypic.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
public class PicShowActivity extends AppCompatActivity implements PicShowView, View.OnClickListener {

    private PicShowPresenter mPicShowPresenter;
    @InjectView(R.id.gif_main)
    GifImageView mGifImageView;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.pb_pic_show)
    ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);

        initViews();
        mPicShowPresenter = new PicShowPresenterImpl(this, this);
        mPicShowPresenter.onCreate();
    }

    private void initViews() {
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(this);
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
//        mProgressBar.setVisibility(View.VISIBLE);
//        mGifImageView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
//        mProgressBar.setVisibility(View.GONE);
//        mGifImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        mPicShowPresenter.onClick(v);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }
}
