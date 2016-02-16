package com.xwj.jokexwj.funnypic.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.FunnyPicAdapter;
import com.xwj.jokexwj.funnypic.presenters.FunnyPicsPresenter;
import com.xwj.jokexwj.funnypic.presenters.FunnyPicsPresenterImpl;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicsFragment extends Fragment implements FunnyPicView, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.rv_main_funny_pics)
    RecyclerView mRecyclerView;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    private FunnyPicAdapter mFunnyPicAdapter;
    private List<FunnyPic> funnyPicList = new ArrayList<>();
    private FunnyPicsPresenter mFunnyPicsPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFunnyPicAdapter = new FunnyPicAdapter(this, context, funnyPicList);
        mFunnyPicsPresenter = new FunnyPicsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_funny_pic, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
        mRecyclerView.setAdapter(mFunnyPicAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mFunnyPicsPresenter.onResume();
    }

    @Override
    public void bindView(List<FunnyPic> funnyPics) {
        funnyPicList.clear();
        funnyPicList.addAll(funnyPics);
        mFunnyPicAdapter.notifyDataSetChanged();
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
    public Context gainContext() {
        return this.getContext();
    }

    @Override
    public void onRefresh() {
        mFunnyPicsPresenter.onRefresh();
    }
}
