package com.gsoft.suhe.apps;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * D: application
 * 1、Volley相关配置；
 * 2、融云相关配置；
 * 3、
 *
 *
 * 2016/6/27
 */
public class MyApplication extends Application {

    private static RequestQueue mReqQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        mReqQueue = Volley.newRequestQueue(getApplicationContext());

    }

    /**
     * 获取volley请求队列
     * @return RequestQueue
     */
    public static RequestQueue getHttpQueue(){
        return mReqQueue;
    }

}
