package com.kesun.main.bean;

import com.jia.jsplayer.bean.IVideoInfo;

/**
 * Created by Administrator on 2018/2/14 0014.
 */
public class VideoInfo implements IVideoInfo {

    private String title;

    private String path;

    public VideoInfo(String title, String path) {
        this.title = title;
        this.path = path;
    }

    @Override
    public String getVideoTitle() {
        return title;
    }

    @Override
    public String getVideoPath() {
        return path;
    }
}
