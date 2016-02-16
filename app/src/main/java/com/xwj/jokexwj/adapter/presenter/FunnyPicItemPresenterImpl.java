package com.xwj.jokexwj.adapter.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xwj.jokexwj.adapter.views.FunnyPicItemView;
import com.xwj.jokexwj.funnypic.views.PicShowActivity;
import com.xwj.jokexwj.utils.Globals;

/**
 * Created by xwjsd on 2016-02-16.
 */
public class FunnyPicItemPresenterImpl implements FunnyPicItemPresenter {
    private Context mContext;
    private FunnyPicItemView mFunnyPicItemView;


    public FunnyPicItemPresenterImpl(Context context, FunnyPicItemView funnyPicItemView) {

        mContext = context;
        mFunnyPicItemView = funnyPicItemView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, PicShowActivity.class);
        intent.putExtra(Globals.FUNNY_PIC, mFunnyPicItemView.getFunnyPic());
        mContext.startActivity(intent);
    }
}
