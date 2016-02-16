package com.xwj.jokexwj.funnypic.presenters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.funnypic.views.PicShowView;
import com.xwj.jokexwj.interactors.DownLoadListener;
import com.xwj.jokexwj.interactors.DownloadImageInteractor;
import com.xwj.jokexwj.interactors.impl.DownloadImageInteratorImpl;
import com.xwj.jokexwj.model.funnypic.FunnyPic;
import com.xwj.jokexwj.utils.Globals;

/**
 * Created by xwjsd on 2016-02-16.
 */
public class PicShowPresenterImpl implements PicShowPresenter {

    private Context mContext;
    private PicShowView mPicShowView;
    private DownloadImageInteractor mDownloadImageInteractor;

    public PicShowPresenterImpl(Context context, PicShowView picShowView) {
        mPicShowView = picShowView;
        mContext = context;
        mDownloadImageInteractor = new DownloadImageInteratorImpl(context);
    }

    @Override
    public void onCreate() {
        mPicShowView.showProgress();
        FunnyPic funnyPic = getFromIntent();
        if (funnyPic != null) {
            mPicShowView.bindView(funnyPic);
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab_download) {
            mDownloadImageInteractor.downLoadImage(getFromIntent(), new DownLoadListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(mContext, R.string.download_success, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed() {

                }
            });
        } else {
            mPicShowView.finishActivity();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    private FunnyPic getFromIntent() {
        return (FunnyPic) ((Activity) mContext).getIntent().getSerializableExtra(Globals.FUNNY_PIC);
    }
}
