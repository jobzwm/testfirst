package com.gsoft.suhe.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.gsoft.suhe.R;

/**
 * D:应用首页--
 * 2016-6-27 12:00:55
 */
public class ActivityIndex extends FragmentActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);

    }



}
