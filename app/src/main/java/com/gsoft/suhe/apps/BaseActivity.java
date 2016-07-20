package com.gsoft.suhe.apps;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.gsoft.suhe.utils.DialogUtil;
import com.gsoft.suhe.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * D:Activity基础类
 * 2016/6/27
 */
public abstract class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity" ;


    private List<String> mRequestTagList ;//Volley网络请求tag集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;

        DialogUtil.showLogI(TAG , "BaseActivity--onCreate---");

        mRequestTagList = new ArrayList<>() ;
        List<String> tagList = addRequestTags() ;
        if(tagList != null){
            mRequestTagList.addAll(tagList) ;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        DialogUtil.showLogI(TAG , "BaseActivity--onResume---");

        getDataInfo() ;
    }

    @Override
    protected void onStop() {
        super.onStop();

        DialogUtil.showLogI(TAG , "BaseActivity--onStop---");

        //取消网络请求
        if(mRequestTagList.size() != 0){
            for (String tag : mRequestTagList){
                if(!"".equals(Util.convertNull(tag))){
                    MyApplication.getHttpQueue().cancelAll(tag) ;
                }

                DialogUtil.showLogI(TAG , "BaseActivity--onStop--tag=" + Util.convertNull(tag));
            }
        }

    }

    /**
     * 获取数据
     */
    protected abstract void getDataInfo() ;

    /**
     * 添加requestTag到集合中
     */
    protected abstract List<String> addRequestTags() ;

}
