package com.xwj.jokexwj.funnypic.model;

import com.xwj.jokexwj.joke.model.Listener;
import com.xwj.jokexwj.model.funnypic.FunnyPic;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public interface FunnyPicsModel {
    void getNewestFunnyPics(Listener<List<FunnyPic>> listener);
}
