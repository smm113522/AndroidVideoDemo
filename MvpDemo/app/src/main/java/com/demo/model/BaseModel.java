package com.demo.model;

import android.content.Context;


import java.util.ArrayList;


/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class BaseModel {
//public class BaseModel implements ResponseCallBack {

    public Context mContext;

//    protected ArrayList<ResponseCallBack> businessResponseArrayList = new ArrayList<ResponseCallBack>();

    public BaseModel(Context context) {
        this.mContext = context;
    }

//    public void addResponseListener(ResponseCallBack listener) {
//        if (!businessResponseArrayList.contains(listener)) {
//            businessResponseArrayList.add(listener);
//        }
//    }
//
//    public void removeResponseListener(ResponseCallBack listener) {
//        businessResponseArrayList.remove(listener);
//    }
//
//    @Override
//    public void OnResponseSucess(int states, String common) {
//        for (ResponseCallBack iterable_element : businessResponseArrayList) {
//            iterable_element.OnResponseSucess(states, common);
//        }
//    }
//
//    @Override
//    public void OnResponseFaile(int states, String common) {
//        for (ResponseCallBack iterable_element : businessResponseArrayList) {
//            iterable_element.OnResponseSucess(states, common);
//        }
//    }
}
