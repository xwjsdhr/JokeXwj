package com.xwj.jokexwj.model.funnypic;

import java.io.Serializable;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPic implements Serializable {
    public String content;
    public String hashId;
    public int unixtime;
    public String updatetime;
    public String url;

    public FunnyPic() {
    }

    public FunnyPic(String content, String hashId, int unixtime, String updatetime, String url) {
        this.content = content;
        this.hashId = hashId;
        this.unixtime = unixtime;
        this.updatetime = updatetime;
        this.url = url;
    }
}
