package com.xwj.jokexwj.joke.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.JokeAdapter;
import com.xwj.jokexwj.joke.presenters.JokesPresenter;
import com.xwj.jokexwj.joke.presenters.impl.JokesPresenterImpl;
import com.xwj.jokexwj.model.Joke;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xwjsd on 2016-02-14.
 */
public class JokeFragment extends Fragment implements JokeView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = JokeFragment.class.getSimpleName();

    @InjectView(R.id.toolbar)
    public Toolbar mToolbar;

    @InjectView(R.id.rv_main_jokes)
    public RecyclerView mRecyclerView;

    @InjectView(R.id.swipe_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Joke> jokeList = new ArrayList<>();

    private JokeAdapter jokeAdapter;
    private JokesPresenter mJokesPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mJokesPresenter = new JokesPresenterImpl(context, this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

//        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_main_jokes);

        ((AppCompatActivity) this.getActivity()).setSupportActionBar(mToolbar);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setHasFixedSize(true);
        jokeAdapter = new JokeAdapter(this, this.getContext(), jokeList);
        mRecyclerView.setAdapter(jokeAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mJokesPresenter.onResume();
    }

    @Override
    public void onRefresh() {

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
    public Context gainContext() {
        return this.getContext();
    }
}
