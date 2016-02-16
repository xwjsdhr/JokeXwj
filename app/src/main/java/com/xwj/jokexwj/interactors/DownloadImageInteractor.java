package com.xwj.jokexwj.interactors;

import com.xwj.jokexwj.model.funnypic.FunnyPic;

/**
 * Created by xwjsd on 2016-02-16.
 */
public interface DownloadImageInteractor {
    void downLoadImage(FunnyPic funnyPic, DownLoadListener Listener);
}

