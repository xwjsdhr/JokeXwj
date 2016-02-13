package com.xwj.jokexwj.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.JokeAdapter;
import com.xwj.jokexwj.model.Joke;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.toolbar)
    public Toolbar mToolbar;

    @InjectView(R.id.rv_main_jokes)
    public RecyclerView mRecyclerView;

    @InjectView(R.id.swipe_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    private MainPresenter mMainPresenter;

    private List<Joke> jokeList = new ArrayList<>();

    private JokeAdapter jokeAdapter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mMainPresenter = new MainPresenterImpl(this, this);
        setSupportActionBar(mToolbar);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null ||
                                recyclerView.getChildCount() == 0) ? 0
                                : recyclerView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        jokeAdapter = new JokeAdapter(this, jokeList);
        mRecyclerView.setAdapter(jokeAdapter);
        mMainPresenter.onCreate(savedInstanceState);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void bindView(List<Joke> jokes) {
        Log.e(TAG, "bind view" + Thread.currentThread().toString());
        jokeList.clear();
        jokeList.addAll(jokes);
        jokeAdapter.notifyDataSetChanged();
    }

    @Override
    public void bindMore(List<Joke> jokes) {
        jokeList.addAll(jokes);
        jokeAdapter.notifyDataSetChanged();
    }

    @Override
    public int getJokeCount() {
        return jokeList.size();
    }

    @Override
    public void clearList() {
        jokeList.clear();
    }


    @Override
    public void onRefresh() {
        mMainPresenter.onRefresh();
    }
}
