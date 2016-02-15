package com.xwj.jokexwj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwj.jokexwj.R;
import com.xwj.jokexwj.adapter.views.impl.FooterViewHolder;
import com.xwj.jokexwj.adapter.views.impl.FunnyPicViewHolder;
import com.xwj.jokexwj.adapter.views.impl.JokeViewHolder;
import com.xwj.jokexwj.funnypic.views.FunnyPicView;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<FunnyPic> mList;
    private FunnyPicView mFunnyPicView;

    public FunnyPicAdapter(FunnyPicView funnyPicView, Context context, List<FunnyPic> funnyPics) {
        this.mContext = context;
        this.mList = funnyPics;
        mInflater = LayoutInflater.from(context);
        mFunnyPicView = funnyPicView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            return new FooterViewHolder(mFunnyPicView, v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.funny_pic_item, parent, false);
            return new JokeViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;

        } else if (holder instanceof FunnyPicViewHolder) {
            ((FunnyPicViewHolder) holder).bindView(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
}
