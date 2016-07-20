package com.gsoft.suhe.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gsoft.suhe.view.MyCustomDialog;

/**
 * D:弹出框、toast、log等util
 * 2016/5/5
 */
public class DialogUtil {
    /**
     * 是否显示log
     */
    private static final boolean ISSHOWLOG = true ;

    /**
     * 显示log信息--i
     * @param tag filter
     * @param logs 要输出的内容
     */
    public static void showLogI(String tag , String logs){
        if (ISSHOWLOG)
            Log.i(tag , logs) ;
    }

    /**
     * 显示log信息--e
     * @param tag filter
     * @param logs 要输出的内容
     */
    public static void showLogE(String tag , String logs){
        if (ISSHOWLOG)
            Log.e(tag , logs) ;
    }

    /**
     * 显示toast消息，short
     * @param context context
     * @param message toast消息
     */
    public static void showToastShort(Context context , String message){
        Toast.makeText(context , message , Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示toast消息，long
     * @param context context
     * @param message toast消息
     */
    public static void showToastLong(Context context , String message){
        Toast.makeText(context , message , Toast.LENGTH_LONG).show();
    }

    /**
     * 显示Toast消息
     * @param context context
     * @param message toast消息
     * @param duration duration
     */
    public static void showToast(Context context , String message , int duration ){
        Toast.makeText(context, message, duration).show(); ;
    }
    /**
     * 自定义dialog
     * @param context context
     * @param title title
     * @param message message
     * @param contentView if != null 显示这个view，否则就显示message
     * @param posiText 按钮1名称
     * @param listener1 按钮1监听
     * @param nageText 按钮2名称
     * @param listener2 按钮2监听
     * @param neutText 按钮3名称
     * @param listener3 按钮3监听
     */
    public static void showCustomViewDialog(Context context , String title , String message
            , View contentView , String posiText , DialogInterface.OnClickListener listener1
            , String nageText , DialogInterface.OnClickListener listener2
            , String neutText , DialogInterface.OnClickListener listener3){

        MyCustomDialog.Builder builder = new MyCustomDialog.Builder(context) ;
        if(title != null){
            builder.setTitle(title) ;
        }
        if(message != null){
            builder.setMessage(message) ;
        }
        if(contentView != null){
            builder.setContentView(contentView) ;
        }
        builder.setPositiveButton(posiText, listener1) ;
        builder.setNegativeButton(nageText, listener2) ;
        builder.setNeutralButton(neutText, listener3) ;
        builder.create().show();
    }
    /**
     * 自定义dialog
     * @param context context
     * @param title title
     * @param message message
     * @param contentView if != null 显示这个view，否则就显示message
     * @param posiText 按钮1名称
     * @param listener1 按钮1监听
     * @param nageText 按钮2名称
     * @param listener2 按钮2监听
     */
    public static void showCustomViewDialog(Context context , String title , String message
            , View contentView , String posiText , DialogInterface.OnClickListener listener1 , String nageText , DialogInterface.OnClickListener listener2){

        MyCustomDialog.Builder builder = new MyCustomDialog.Builder(context) ;
        if(title != null){
            builder.setTitle(title) ;
        }
        if(message != null){
            builder.setMessage(message) ;
        }
        if(contentView != null){
            builder.setContentView(contentView) ;
        }
        builder.setPositiveButton(posiText, listener1) ;
        builder.setNegativeButton(nageText, listener2) ;
        builder.create().show();
    }
    /**
     * 自定义dialog
     * @param context context
     * @param title title
     * @param message message
     * @param contentView if != null 显示这个view，否则就显示message
     * @param posiText 按钮1名称
     * @param listener1 按钮1监听
     */
    public static void showCustomViewDialog(Context context , String title , String message
            , View contentView , String posiText , DialogInterface.OnClickListener listener1){

        MyCustomDialog.Builder builder = new MyCustomDialog.Builder(context) ;
        if(title != null){
            builder.setTitle(title) ;
        }
        if(message != null){
            builder.setMessage(message) ;
        }
        if(contentView != null){
            builder.setContentView(contentView) ;
        }
        builder.setPositiveButton(posiText, listener1) ;
        builder.setNegativeButton(null, null) ;
        builder.create().show();
    }



    /**
     * 隐藏输入法
     * @param view
     */
    public static void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示输入法
     * @param view
     */
    public static void showKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        view.requestFocus();
    }

}
