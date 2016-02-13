package com.xwj.jokexwj.main;

import android.os.Bundle;
import android.view.View;

/**
 * Created by xwjsd on 2016-02-12.
 */
public interface MainPresenter {
    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onDestroy();

    void onClick(View v);

    boolean onLongClick(View v);

    void onRefresh();
}
