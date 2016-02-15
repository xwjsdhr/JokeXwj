package com.xwj.jokexwj.funnypic.views;

import android.content.Context;

import com.xwj.jokexwj.model.funnypic.FunnyPic;
import com.xwj.jokexwj.views.BaseView;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public interface FunnyPicView extends BaseView<List<FunnyPic>> {
    Context gainContext();
}
