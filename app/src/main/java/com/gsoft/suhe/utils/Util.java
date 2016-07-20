package com.gsoft.suhe.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * D:
 * 2016/5/4
 */
public class Util {

    /**
     * 处理空字符；去掉首尾空格
     * @param temp
     * @return
     */
    public static String convertNull(String temp){
        if (null == temp){
            return "" ;
        }

        return temp.trim() ;
    }

    /**
     * 处理图片地址的转换--只用于大小图
     * @param str 原地址（ad/aa/123.png）
     * @param cStr 要添加的字符（_a 小图,_b 中图）
     * @return 处理过的str
     */
    public static String addStringToString(String str , String cStr){
        if(Util.convertNull(str).contains(cStr + ".")){//已经是想要改的图片了
            return str ;
        }
        if(str.contains(".")){
            StringBuffer buffer = new StringBuffer() ;
            buffer.append(str.substring(0, str.lastIndexOf(".")))
                    .append(cStr)
                    .append(str.substring(str.lastIndexOf("."), str.length())) ;
            return buffer.toString() ;
        }
        return str ;
    }

    /**
     * 对图片地址处理
     * @param context context
     * @param imgurl 图片地址
     * @return 处理好的图片地址
     */
    public static String convertImageUrl(Context context ,String imgurl ){
        if(imgurl == null || imgurl.equals("")){
            return "" ;
        }

        if(imgurl.startsWith("http") || imgurl.startsWith("file")){
            return imgurl ;
        }

        if(imgurl.startsWith("/")){
            return SharedUtils.getBaseJiekouUrl(context) + imgurl ;
        }

        return SharedUtils.getBaseJiekouUrl(context) + "/" +imgurl ;
    }

    /**
     * 网页地址格式化
     */
    public static String convertHtmlUrl(String htmlUrl){
        if(htmlUrl == null){
            return "" ;
        }
        StringBuffer url = new StringBuffer() ;
        if(htmlUrl.contains("\\")){
            url.append(htmlUrl.replace("\\", "/")) ;
        }else{
            url.append(htmlUrl) ;
        }

        if(htmlUrl.startsWith("http") || htmlUrl.startsWith("file")){
            return url.toString().trim() ;
        }else{
            return "http://" + url.toString().trim() ;
        }
    }

    /**
     * 提交网络参数 用URLEncoder 转 GBK
     */
    public static String convertURLEncoderGBK(String param){
        try {
            return URLEncoder.encode(convertNull(param), "GBK") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return convertNull(param) ;
        }
    }


    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 把String 转成 Int
     * @param temp
     * @return 出错为 0
     */
    public static int convertStringToInt(String temp){
        if(Util.convertNull(temp).equals("")){
            return 0 ;
        }
        int res = 0 ;
        try{
           res = Integer.valueOf(temp) ;
        }catch(Exception e){
            e.printStackTrace();
        }
        return res ;
    }

    /**
     * 转化成两位小数
     * @param ctd
     * @return
     */
    public static String convertTwoDecimal(double ctd){
        DecimalFormat format = new DecimalFormat("0.00") ;
        return format.format(ctd) ;
    }
    /**
     * 转化成两位小数
     * @param ctd
     * @return
     */
    public static String convertTwoDecimal(float ctd){
        DecimalFormat format = new DecimalFormat("0.00") ;
        return format.format(ctd) ;
    }
    /**
     * 转化成两位小数
     * @param ctd
     * @return
     */
    public static String convertTwoDecimal(String ctd){

        if(ctd == null || ctd.trim().equals("")){
            return "" ;
        }

        DecimalFormat format = new DecimalFormat("0.00") ;
        return format.format(Float.valueOf(ctd)) ;
    }
    /**
     * 转化成两位小数
     * @param ctdd
     * @return float
     */
    public static float convertTwoDecimalFloat(String ctdd){

        if(ctdd == null || ctdd.trim().equals("")){
            return 0 ;
        }

        DecimalFormat format = new DecimalFormat("0.00") ;
        return Float.valueOf(format.format(Float.valueOf(ctdd))) ;
    }
    /**
     * 转化成两位小数
     * @param ctdd
     * @return float
     */
    public static float convertTwoDecimalFloat(float ctdd){
        DecimalFormat format = new DecimalFormat("0.00") ;
        return Float.valueOf(format.format(ctdd)) ;
    }

    /**
     * 对html代码块转义--将">"这类标签转义
     * @param hStr
     * @return hStr
     */
    public static String convertHtmlTagPositive(String hStr){
        if(null == hStr){
            return "" ;
        }
        return hStr.replace(">", "&gt;")
                .replace("<", "&lt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;")
                .replace("&", "&amp;") ;
    }

    /**
     * 对html代码块转义--将">"这类标签转义回来
     * @param hStr
     * @return hStr
     */
    public static String convertHtmlTagNegative(String hStr){
        if(null == hStr){
            return "" ;
        }
        return hStr.replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&quot;", "\"")
                .replace("&apos;", "'")
                .replace("&amp;", "&") ;
    }


    /**
     * 处理html片段，带有img标签的，如果图片地址不正确，处理下
     * @param context context
     * @param hStr hStr
     * @return ms
     */
    public static String convertHtmlImage(Context context ,String hStr){
        if(null == hStr){
            return "" ;
        }

        String ms = convertHtmlTagNegative(hStr) ;
        if(ms.contains("src=\"")){
            if(!ms.contains("src=\"http") && !ms.contains("src=\"file")){
                ms = ms.replace("src=\"", "src=\"" + convertNull(SharedUtils.getBaseJiekouUrl(context))) ;
            }
        }

        return ms ;
    }

    /**
     * 格式化 日期格式如： 2014-08-21T10:31:37+08:00
     * @param nomalDate data
     * @return	2014-08-21
     */
    public static String formatNomalDate(String nomalDate){
        if(nomalDate == null || !nomalDate.contains("T")){
            return nomalDate ;
        }

        return nomalDate.split("T")[0] ;
    }
    /**
     * 格式化 日期格式如： 2014-08-21T10:31:37+08:00
     * @param nomalDate
     * @return	2014-08-21 10:31:37
     */
    public static String formatNomalDate2(String nomalDate){
        if(nomalDate == null || !nomalDate.contains("T") || !nomalDate.contains("+")){
            return nomalDate ;
        }

        String dateSec = nomalDate.split("\\+")[0] ;

        return dateSec.replace("T", " ");
    }
    /**
     * 格式化 日期
     * @param dateLong
     * @return	10:31:37
     */
    public static String formatNomalDateFromLong(long dateLong){
        if(dateLong == 0){
            return "0" ;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss") ;
        Date d = new Date(dateLong) ;
        String res = sdf.format(d) ;

        return res ;
    }

    /**
     * 格式化 日期格式如： 2014-08-21T10:31:37+08:00
     * @param nomalDate
     * @return	10:31:37
     */
    public static String formatNomalDateHour(String nomalDate){
        if(nomalDate == null || !nomalDate.contains("T") || !nomalDate.contains("+")){
            return nomalDate ;
        }

        String dateSec = nomalDate.split("\\+")[0] ;
        String nDate = dateSec.split("T")[1] ;
        if(nDate.contains(".")){
            return nDate.split("\\.")[0] ;
        }

        return nDate ;
    }

    /**
     * 距离换算
     * 将 米 转换成 公里
     */
    public static String convertMetersToKm(String meters){
        if(meters == null || meters.equals("") || meters.equals("0") ){
            return "0" ;
        }

        float mt = Float.valueOf(meters) ;
        float kmt = mt / 1000 ;

        DecimalFormat format = new DecimalFormat("0.00") ;

        return format.format(kmt) + "公里" ;
    }

    /**
     * 时间换算
     * 将 秒 转换成 时分
     */
    public static String convertSecToHour(float seconds){
        int hour = (int) (seconds / (60 * 60)) ;
        int mine = (int) ((seconds - hour* 3600) / 60) ;

        if(hour == 0){
            return mine + "分钟"  ;
        }else if(mine == 0){
            return hour + "小时" ;
        }

        return  hour + "小时" + mine + "分钟";
    }

    /**
     * 将str裁剪成原来的一半长度
     * 剩下的用endMark代替
     * @param str 要剪切的字符串
     * @param endMark 代替的字符
     */
    public static String cutStringToHalf(String str , String endMark){
        if(str != null){
            StringBuffer eStr = new StringBuffer();
            int length = str.length() ;

            if(length > 1){
                int halfLength = length / 2 ;
                if( length%2 == 0){//偶数
                    eStr.append(str.substring(0, halfLength)) ;
                    for(int x = 0 ; x < halfLength ; x ++){
                        eStr.append(endMark) ;
                    }
                }else{
                    halfLength += 1 ;
                    eStr.append(str.substring(0, halfLength)) ;
                    for(int x = 0 ; x < halfLength - 1 ; x ++){
                        eStr.append(endMark) ;
                    }
                }

                return eStr.toString() ;
            }else{
                return str ;
            }
        }else{
            return "" ;
        }
    }

    /**
     * 将 时间 转换成 long
     * @param secStr
     * @return
     */
    public static long convertTimeSecondStringToLong(String secStr){
        if(secStr == null || secStr.equals("")){
            return 0 ;
        }

        StringBuffer fBuf = new StringBuffer(secStr) ;
        if(getStringContainsCount(secStr , ":") == 1){
            fBuf.append(":00") ;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss") ;
        try {
            Date d = sdf.parse(fBuf.toString()) ;
            return d.getTime() ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0 ;
    }

    /**
     * 比较两个时间大小--HH:mm:ss
     * --如果是12:12这种，默认在后面加上 “ ：00 ”
     * @param firstTime
     * @param secendTime
     * @return firstTime > secendTime
     */
    public static boolean compairTimeMinute(String firstTime , String secendTime){
        if(firstTime == null || secendTime == null
                || firstTime.equals("") || secendTime.equals("")
                || !firstTime.contains(":") || !secendTime.contains(":") ){
            return false ;
        }
        StringBuffer fBuf = new StringBuffer(firstTime) ;
        if(getStringContainsCount(firstTime , ":") == 1){
            fBuf.append(":00") ;
        }
        StringBuffer sBuf = new StringBuffer(secendTime) ;
        if(getStringContainsCount(secendTime , ":") == 1){
            sBuf.append(":00") ;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss") ;
        try {
            Date fDa = sdf.parse(fBuf.toString()) ;
            Date sDa = sdf.parse(sBuf.toString()) ;
            return fDa.after(sDa) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false ;
    }

    /**
     * 获取 字符串 中 某个字符 出现的次数
     * @param pStr 字符串
     * @param cStr 某个字符
     * @return 次数、个数
     */
    public static int getStringContainsCount(String pStr , String cStr){
        if(pStr == null || cStr == null || pStr.equals("")) {
            return 0 ;
        }
        int size = pStr.length() ;
        int count = 0 ;
        for(int x = 0 ; x < size ; x ++){
            String nStr = pStr.substring(x, x+1) ;
            if(nStr.equals(cStr)){
                count ++ ;
            }
        }

        return count ;
    }

    /**
     * 冒泡法排序<br/>
     * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个。</li>
     * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。</li>
     * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>
     * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>
     *
     * @param numbers  需要排序的整型数组
     * @param desc  是否是降序排列 true 降序，false 升序
     */
    public static void bubbleSort(int[] numbers , boolean desc) {
        int temp; // 记录临时中间值
        int size = numbers.length; // 数组大小
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if(desc){
                    if (numbers[i] < numbers[j]) { // 交换两数的位置
                        temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }else{
                    if (numbers[i] > numbers[j]) { // 交换两数的位置
                        temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 对时间格式的string排序
     * @param stringTimes string类型时间： 12:12
     * @param desc
     */
    public static void bubbleSort(String[] stringTimes , boolean desc) {
        String temp; // 记录临时中间值
        int size = stringTimes.length; // 数组大小
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if(desc){
                    if (compairTimeMinute(stringTimes[j] ,stringTimes[i])) { // 交换两数的位置
                        temp = stringTimes[i];
                        stringTimes[i] = stringTimes[j];
                        stringTimes[j] = temp;
                    }
                }else{
                    if (compairTimeMinute(stringTimes[i] ,stringTimes[j])) { // 交换两数的位置
                        temp = stringTimes[i];
                        stringTimes[i] = stringTimes[j];
                        stringTimes[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 小数数字的乘法
     * @param value1 1
     * @param value2 2
     * @return double
     */
    public static double floatMultiply(Number value1, Number value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
        return b1.multiply(b2).doubleValue();

    }

    /**
     * Android上传文件到服务端
     *
     * @param file 需要上传的文件
     * @param RequestURL 请求的rul
     * @return 返回响应的内容
     */

    public static String uploadFile(File file, String RequestURL) {
        StringBuffer resStr = new StringBuffer() ;
        HttpURLConnection conn = null;
        DataOutputStream outStream = null;
        try{
            String MULTIPART_FORM_DATA ="multipart/form-data";

            URL url = new URL(RequestURL);
//		       URL url = new URL("http://116.255.129.41:8000/program/wap/wap.aspx?type=5&guid=2887a120-6b85-45a6-b930-19a8065f20be");

            FileInputStream fileIS = new FileInputStream(file);

            long contentLenght = file.length() ;

            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);        //允许输入
            conn.setDoOutput(true);        //允许输出
            conn.setUseCaches(false);    //不使用caches
            conn.setReadTimeout(10*1000);
            conn.setConnectTimeout( 10*1000 );

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8"); // 设置编码
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setRequestProperty("Content-Type",MULTIPART_FORM_DATA+";");
            conn.setRequestProperty("Content-Length",Long.toString(contentLenght));

            outStream = new DataOutputStream(conn.getOutputStream());

            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = fileIS.read(buffer)) != -1){
                outStream.write(buffer, 0, len);
            }

            fileIS.close();
            outStream.flush();

            int cah = conn.getResponseCode();

            DialogUtil.showLogI("testfileupload", "getResponseCode--" + cah) ;

            if(cah!=200){//
                System.out.println("上传失败");

                return "uperror";
            }

            InputStream is = conn.getInputStream();
            int ch;

            while((ch=is.read())!=-1){
                resStr.append((char)ch);
            }

            DialogUtil.showLogI("testfileupload", "result--" + resStr.toString()) ;
        } catch (IOException e) {
            e.printStackTrace();
            DialogUtil.showLogI("testfileupload", "IOException--" + e.toString()) ;

            return "uperror";
        } finally{
            try {
                if(outStream != null){
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(conn != null){
                conn.disconnect();
                conn = null;
            }
        }
        return resStr.toString().trim() ;
    }


    public static byte[] bmpToByteArray(final Bitmap bmp,
                                        final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



}
