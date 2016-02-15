package com.xwj.jokexwj.dao;

import android.content.Context;

import com.xwj.jokexwj.model.funnypic.FunnyPic;

import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicDao {
    private FunnyPicDb mFunnyPicDb;

    public FunnyPicDao(Context context) {
        mFunnyPicDb = new FunnyPicDb(context, 1);
    }

    public void insertPicList(List<FunnyPic> list) {
        mFunnyPicDb.insertPicsIntoDB(list);
    }

    public List<FunnyPic> getAllFunnyPics() {
        return mFunnyPicDb.getAllFunnyPics();
    }

}
