package com.xwj.jokexwj.main;

import android.view.MenuItem;
import android.view.View;

/**
 * Created by xwjsd on 2016-02-12.
 */
public interface MainPresenter {
    void onCreate();

    void onResume();

    void onDestroy();

    void onClick(View v);

    boolean onLongClick(View v);

    boolean onNavigationItemSelected(MenuItem item);

}
