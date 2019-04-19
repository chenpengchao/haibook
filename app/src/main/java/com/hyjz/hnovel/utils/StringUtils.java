package com.hyjz.hnovel.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by squirrel on 2016/5/13.
 */
public class StringUtils {
    public static String[] returnImageUrlsFromHtml(String data) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(data);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            Log.e("imageSrcList","资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern IMG_URL = Pattern
            .compile(".*?(gif|jpeg|png|jpg|bmp)");

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    //将手机号码中间四位转换成*号
    public static String toStarString(String str){
        if(!TextUtils.isEmpty(str) && str.length() > 10 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            return sb+"" ;
        }
        return  -1+"" ;
    }

    /**
     * 是否挂载SD卡
     *
     * @return
     */
    public static boolean hasSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }




    public static boolean isHttp(String str) {
        if (str != null && str.length() > 0) {
            if (str.startsWith("http") || str.startsWith("https")) {
                return true;
            }
        }
        return false;
    }

    /**
     * �?��邮箱地址是否合法
     *
     * @param email
     * @return true合法 false不合�?
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //�?��匹配
        Pattern p = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
    /**
     * �?��邮箱地址是否合法
     *
     * @param email
     * @return true合法 false不合�?
     */
    public static boolean isPhoto(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //�?��匹配
//        Pattern p = Pattern
//                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = IMG_URL.matcher(email);
        return m.matches();
    }
    /**
     * 手机校验
     *
     * @param phone
     * @return true合法 false不合�?
     */
    public static boolean isPhone(String phone) {
        if (null == phone || "".equals(phone))
            return false;
        return isChinaPhoneLegal(phone) || isHKPhoneLegal(phone);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 密码格式校验
     *
     * @param phone
     * @return true合法 false不合�?
     */
    public static boolean isPassword(String phone) {
        if (null == phone || "".equals(phone)||phone.length()<6)
            return false;
        Pattern p = Pattern
                .compile("^.*[A-Za-z0-9\\w_-]+.*$");// 复杂匹配
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    /**
     * 时间转换成时间戳
     * @param str_time
     * @return
     */
    public static String getTime(String str_time){
        String re_time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(str_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return re_time;

    }

    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /**
     * 判断是否是文件
     * @param value
     * @return
     */
    public static boolean isFile(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }

//		if(StringUtils.isHttp(value)){
//			return false;
//		}

        File file = new File(value);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }
//    public static String listToString(List<UploadBean> list){
//        StringBuilder str = new StringBuilder();
//        //把集合中的recordId转为字符串
//        for (int i = 0; i < list.size(); i++) {
//            if (i == 0) {
//                str.append(list.get(i).getNetName());
//            } else {
//                str.append(","+ list.get(i).getNetName());
//            }
//        }
//        return str.toString();
//    }
}
