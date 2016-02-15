package com.xwj.jokexwj;

import android.view.View;

/**
 * Created by xwjsd on 2016-02-14.
 */
public interface BasePresenter {

    void onCreate();

    void onResume();

    void onDestroy();

    void onClick(View view);

    boolean onLongClick(View view);
}
