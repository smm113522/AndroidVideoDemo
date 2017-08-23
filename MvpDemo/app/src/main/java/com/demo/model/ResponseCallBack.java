package com.demo.model;



/**
 * Created by Administrator on 2017/5/18 0018.
 */

public interface ResponseCallBack {
    void OnResponseSucess(int states, String common);
    void OnResponseFaile(int states, String common);
}
