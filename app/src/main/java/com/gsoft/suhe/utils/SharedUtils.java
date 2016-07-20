package com.gsoft.suhe.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * D:SharedPreferences工具类
 * 2016/5/4
 */
public class SharedUtils {

    /**基本接口相关信息*/
    private static final String BASE_URL_SP_NAME = "baseurlsp" ;//基本地址url保存文件
    private static final String BASE_URL_JIEKOU_URL = "baseurljiekouurl" ;
    private static final String BASE_URL_WANGYE_URL = "baseurlwangyeurl" ;
    private static final String BASE_URL_UID = "baseurluid" ;
    private static final String BASE_URL_UNAME = "baseurluname" ;
    private static final String BASE_URL_NEEDRESET = "baseurlneedset" ;//是否需要重新设置信息

    /**当前城市相关信息*/
    private static final String SERVER_PREFER_FILE_NAME = "serverprefer" ;
    private static final String SERVER_PREFER_CURRENT_SHENG = "servercursheng" ;
    private static final String SERVER_PREFER_CURRENT_SHI = "servercurshi" ;

    /**登录相关信息，与BASE_URL_SP_NAME 同文件 */
    private static final String BASE_USER_NUBMER = "userloginnum" ;
    private static final String BASE_USER_PASS = "userloginpass" ;
    private static final String BASE_USER_GUID = "userloginguid" ;
    private static final String BASE_USER_BH = "userbh" ;
    private static final String BASE_USER_PHONE = "userphone" ;

    /**
     * 重置登录信息，包括 用户名、密码、guid、会员号
     * @param context 上下文
     */
    public static void resetLoginInfo(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSp.edit() ;
        editor.putString(BASE_USER_NUBMER , "") ;
        editor.putString(BASE_USER_PASS , "") ;
        editor.putString(BASE_USER_GUID , "") ;
        editor.putString(BASE_USER_BH , "") ;
        editor.commit() ;
    }

    /**
     * 保存用户登录信息
     * @param context context
     * @param userNumber 会员帐号
     * @param userPass 会员密码
     */
    public static void saveLoginUser(Context context , String userNumber
            , String userPass){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSp.edit() ;
        editor.putString(BASE_USER_NUBMER , userNumber) ;
        editor.putString(BASE_USER_PASS , userPass) ;
        editor.commit() ;
    }
    /**
     * 获取用户会员账号
     * @param context context
     * @return 会员账号
     */
    public static String getLoginUserNumber(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
       return mSp.getString(BASE_USER_NUBMER , "") ;
    }

    /***
     * 获取用户登录密码
     * @param context context
     * @return 登录密码
     */
    public static String getLoginUserPass(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_USER_PASS , "") ;
    }

    /**
     * 保存用户guid
     * @param context context
     * @param guid guid
     */
    public static void saveUserGuid(Context context , String guid){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSp.edit() ;
        editor.putString(BASE_USER_GUID , guid) ;
        editor.commit() ;
    }
    /***
     * 获取用户guid
     * @param context context
     * @return guid
     */
    public static String getUserGuid(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_USER_GUID , "") ;
    }

    /**
     * 保存用户phone
     * @param context context
     * @param phone phone
     */
    public static void saveUserPhone(Context context , String phone){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSp.edit() ;
        editor.putString(BASE_USER_PHONE , phone) ;
        editor.commit() ;
    }
    /***
     * phone
     * @param context context
     * @return phone
     */
    public static String getUserPhone(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_USER_PHONE , "") ;
    }

    /**
     * 保存用户会员号--注册成功后系统给的
     * @param context context
     * @param userbh userbh
     */
    public static void saveUserBh(Context context , String userbh){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSp.edit() ;
        editor.putString(BASE_USER_BH , userbh) ;
        editor.commit() ;
    }
    /***
     * 获取用户会员号
     * @param context context
     * @return userbh
     */
    public static String getUserBh(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_USER_BH , "") ;
    }


    /**
     * 重置接口信息
     * @param context 上下文
     * @param jiekouurl 接口url
     * @param wangyeurl 网页接口url
     * @param uid 商家（开发商）uid
     * @param uname 商家（开发商）名称
     */
    public static void resetBaseUrl(Context context , String jiekouurl
            , String wangyeurl , String uid , String uname){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSp.edit() ;
        editor.putString(BASE_URL_JIEKOU_URL,Util.convertNull(jiekouurl)) ;
        editor.putString(BASE_URL_WANGYE_URL,Util.convertNull(wangyeurl)) ;
        editor.putString(BASE_URL_UID,Util.convertNull(uid)) ;
        editor.putString(BASE_URL_UNAME,Util.convertNull(uname)) ;
        editor.putBoolean(BASE_URL_NEEDRESET,false) ;
        editor.commit() ;
    }

    /**
     * 获取接口tag 是否需要重新设置基本信息
     * @param context 上下文
     * @return 没有返回 true 表示需要设置
     */
    public static boolean getBaseUrlNeedreset(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME
                , Context.MODE_PRIVATE) ;
        return mSp.getBoolean(BASE_URL_NEEDRESET , true) ;
    }

    /**
     * 获取接口url
     * @param context 上下文
     * @return 没有返回""
     */
    public static String getBaseJiekouUrl(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_URL_JIEKOU_URL , "") ;
    }

    /**
     * 获取网页url
     * @param context 上下文
     * @return 没有返回""
     */
    public static String getBaseWangyeUrl(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_URL_WANGYE_URL , "") ;
    }

    /**
     * 获取uid
     * @param context 上下文
     * @return 没有返回""
     */
    public static String getBaseUid(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_URL_UID, "") ;
    }

    /**
     * 获取商家名称
     * @param context 上下文
     * @return 没有返回""
     */
    public static String getBaseName(Context context){
        SharedPreferences mSp = context.getSharedPreferences(BASE_URL_SP_NAME , Context.MODE_PRIVATE) ;
        return mSp.getString(BASE_URL_UNAME, "") ;
    }

    //TODO /*****************当前城市**********************/

    /**
     * 重置城市
     */
    public static void resetCurrentCity(Context context){
        SharedPreferences mPrefer = context.getSharedPreferences(SERVER_PREFER_FILE_NAME , Activity.MODE_PRIVATE) ;
        SharedPreferences.Editor edit = mPrefer.edit() ;
        edit.putString(SERVER_PREFER_CURRENT_SHENG, "河南") ;
        edit.putString(SERVER_PREFER_CURRENT_SHI, "郑州") ;
        edit.commit() ;
    }

    /**
     * 保存当前城市
     */
    public static void setCurrentCity(Context context , String sheng , String shi){
        SharedPreferences mPrefer = context.getSharedPreferences(SERVER_PREFER_FILE_NAME , Activity.MODE_PRIVATE) ;
        SharedPreferences.Editor edit = mPrefer.edit() ;
        edit.putString(SERVER_PREFER_CURRENT_SHENG, Util.convertNull(sheng)) ;
        edit.putString(SERVER_PREFER_CURRENT_SHI, Util.convertNull
                (shi)) ;
        edit.commit() ;
    }

    /**
     * 获取当前城市（默认是河南郑州）
     * @return List<String> 0 sheng ,1 shi
     */
    public static List<String> getCurrentCity(Context context){
        SharedPreferences mPrefer = context.getSharedPreferences(SERVER_PREFER_FILE_NAME , Activity.MODE_PRIVATE) ;
        String sheng = mPrefer.getString(SERVER_PREFER_CURRENT_SHENG, "河南") ;
        String shi = mPrefer.getString(SERVER_PREFER_CURRENT_SHI, "郑州") ;
        List<String> res = new ArrayList<>() ;
        res.add(sheng) ;
        res.add(shi) ;

        return res ;
    }

//TODO /********************融云相关******************************************************************/
    public static final String SP_RONGCLOUD = "GYWRongClouds";
    public static final String SP_RONG_CONNECT_STATE = "imconnectstate";
    public static final String SP_RONG_TOKEN_STATE = "imtokenstate";
    public static final String SP_RONG_TOKEN_TOKEN = "imtoken";

    /**
     * 设置融云连接状态
     */
    public static  void setRongConnState(Context mContext ,boolean isConnected){
        SharedPreferences sp = mContext.getSharedPreferences(SP_RONGCLOUD,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_RONG_CONNECT_STATE, isConnected);
        editor.commit();
    }
    /**
     * 获取融云连接状态
     */
    public static boolean getRongConnState(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_RONGCLOUD,
                Context.MODE_PRIVATE);
        return sp.getBoolean(SP_RONG_CONNECT_STATE, false) ;
    }
    /**
     * 设置融云连接的token状态
     */
    public static void setTokenState(Context mContext ,boolean isConnected){
        SharedPreferences sp = mContext.getSharedPreferences(SP_RONGCLOUD,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_RONG_TOKEN_STATE, isConnected);
        editor.commit();
    }
    /**
     * 获取融云连接的token状态
     */
    public static boolean getTokenState(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_RONGCLOUD,
                Context.MODE_PRIVATE);
        return sp.getBoolean(SP_RONG_TOKEN_STATE, false) ;
    }


    /**
     * 设置融云连接的token
     */
    public static void setUserToken(Context mContext ,String token){
        SharedPreferences sp = mContext.getSharedPreferences(SP_RONGCLOUD,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_RONG_TOKEN_TOKEN, token);
        editor.commit();
    }
    /**
     * 获取融云连接的token
     */
    public static String getUserToken(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_RONGCLOUD,
                Context.MODE_PRIVATE);
        return sp.getString(SP_RONG_TOKEN_TOKEN, "") ;
    }







}
