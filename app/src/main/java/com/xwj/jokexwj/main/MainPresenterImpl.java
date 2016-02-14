package com.xwj.jokexwj.main;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.xwj.jokexwj.R;
import com.xwj.jokexwj.api.NetClient;
import com.xwj.jokexwj.dao.JokesDao;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private MainView mMainView;
    private Context mContext;
    private NetClient mNetClient;
    private Gson mGson;
    private JokesDao mJokesDao;
    MainActivity activity;

    public MainPresenterImpl(Context context, MainView mainView) {
        mContext = context;
        mMainView = mainView;
        mNetClient = new NetClient();
        mGson = new Gson();
        mJokesDao = new JokesDao(context);
        activity = (MainActivity) mainView;
    }

    @Override
    public void onCreate() {
        mMainView.addJokesFragment();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jokes:
                mMainView.addJokesFragment();
                break;
            case R.id.funny_pics:
                mMainView.addFunnyPicsFragment();
                break;
        }
        return true;
    }

}
