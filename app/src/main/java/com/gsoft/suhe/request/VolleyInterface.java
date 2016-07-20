package com.gsoft.suhe.request;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.content.Context;
import android.util.Log;

public abstract class VolleyInterface {
	public Context mContext ;
	public static Listener<String> mListener ;
	public static ErrorListener mErrorListener ;

	public VolleyInterface (Context context){
		this.mContext = context ;
	}

	public abstract void onMySuccess(String response) ;
	public abstract void onMyError(VolleyError error) ;

	/**
	 * 访问成功
	 * @return
	 */
	public Listener<String> loadingListener(){
		mListener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				onMySuccess(response);

				Log.i("VolleyRequest", "success=" + response ) ;
			}
		};
		return mListener ;
	}

	/**
	 * 访问出错
	 * @return
	 */
	public ErrorListener errorListener(){
		mErrorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				onMyError(error);

				Log.i("VolleyRequest", "error=" + error.toString() ) ;
			}
		};
		return mErrorListener ;
	}
}
