package com.xwj.jokexwj.model;

/**
 * Created by xwjsd on 2016-02-12.
 */
public class Joke {
    public String content;
    public String hashId;
    public int unixtime;
    public String updatetime;
    public Joke(){}

    public Joke(String content, String hashId, int unixtime, String updatetime) {
        this.content = content;
        this.hashId = hashId;
        this.unixtime = unixtime;
        this.updatetime = updatetime;
    }
}
