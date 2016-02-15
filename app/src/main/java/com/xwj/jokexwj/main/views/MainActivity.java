package com.xwj.jokexwj.main.views;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.funnypic.views.FunnyPicsFragment;
import com.xwj.jokexwj.joke.views.JokeFragment;
import com.xwj.jokexwj.main.presenters.MainPresenter;
import com.xwj.jokexwj.main.presenters.impl.MainPresenterImpl;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.nav_main)
    NavigationView mNavigationView;
    @InjectView(R.id.container_main)
    FrameLayout mContainer;
    @InjectView(R.id.drawer)
    DrawerLayout mDrawer;

    private MainPresenter mMainPresenter;
    private FragmentManager mManager;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initOther();
        initPresenter();

    }

    private void initOther() {
        mManager = this.getSupportFragmentManager();
    }

    private void initPresenter() {
        mMainPresenter = new MainPresenterImpl(this, this);
        mMainPresenter.onCreate();
    }

    private void initViews() {
        ButterKnife.inject(this);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return mMainPresenter.onNavigationItemSelected(item);
    }

    @Override
    public void addJokesFragment() {
        mManager.beginTransaction()
                .replace(R.id.container_main,
                        new JokeFragment(),
                        JokeFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void addFunnyPicsFragment() {
        mManager.beginTransaction()
                .replace(R.id.container_main,
                        new FunnyPicsFragment(),
                        FunnyPicsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void closeDrawer() {
        mDrawer.closeDrawers();
    }
}
