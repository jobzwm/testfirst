package com.gsoft.suhe.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.gsoft.suhe.apps.MyApplication;
import com.gsoft.suhe.utils.DialogUtil;

import java.util.Map;

public class VolleyRequest {
	/**
	 * String---GET 请求
	 * @param context
	 * @param url
	 * @param tag
	 * @param vif
	 */
	public static void StringRequestGet(Context context , String url , String tag , VolleyInterface vif){

		DialogUtil.showLogI("VolleyRequest", "url=" + url ); ;

		MyApplication.getHttpQueue().cancelAll(tag);
		StringRequest stringRequest = new StringRequest(Method.GET, url, vif.loadingListener(), vif.errorListener()) ;
		stringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(stringRequest) ;
		MyApplication.getHttpQueue().start();
	}

	/**
	 * String---post 请求
	 * @param context
	 * @param url
	 * @param tag
	 * @param parmas
	 * @param vif
	 */
	public static void StringRequestPost(Context context , String url , String tag ,
										 final Map<String, String> parmas , VolleyInterface vif ){
		MyApplication.getHttpQueue().cancelAll(tag);
		StringRequest stringRequest = new StringRequest(Request.Method.POST,url, vif.loadingListener(), vif.errorListener()){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return parmas;
			}
		} ;
		stringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(stringRequest) ;
		MyApplication.getHttpQueue().start();
	}
}
