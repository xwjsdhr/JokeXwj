package com.xwj.jokexwj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.views.impl.FooterViewHolder;
import com.xwj.jokexwj.adapter.views.impl.JokeViewHolder;
import com.xwj.jokexwj.joke.views.JokeView;
import com.xwj.jokexwj.model.joke.Joke;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private Context mContext;
    private List<Joke> mJokes;
    private LayoutInflater mInflater;
    private JokeView mJokeView;

    public JokeAdapter(JokeView jokeView, Context context, List<Joke> jokes) {
        mContext = context;
        mJokes = jokes;
        mJokeView = jokeView;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            return new FooterViewHolder(mJokeView, v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false);
            return new JokeViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;

        } else if (holder instanceof JokeViewHolder) {
            ((JokeViewHolder) holder).bindView(mJokes.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mJokes.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mJokes.size() + 1;
    }
}
