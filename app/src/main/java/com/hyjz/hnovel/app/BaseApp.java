package com.hyjz.hnovel.app;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.hyjz.hnovel.utils.SharedPreferencesHelper;

public class BaseApp extends MultiDexApplication {

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler
    private static BaseApp instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //对全局属性赋值
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();

    }
    public static BaseApp getInstance() {
        return instance;
    }

    /**
     * 重启当前应用
     */
    public static void restart() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        BaseApp.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        BaseApp.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        BaseApp.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        BaseApp.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        BaseApp.mHandler = mHandler;
    }
    //存储昵称
    public void setNickName(String nickName) {
        SharedPreferencesHelper.saveString("nickName", nickName);
    }

    public String getNickName() {
        return SharedPreferencesHelper.getString("nickName");
    }
    //存储用户id
    public void setUserId(Long agentId) {
        SharedPreferencesHelper.saveLong("userId", agentId);
    }

    public Long getUserId() {
        return SharedPreferencesHelper.getLong("userId");
    }
    //存储头像
    public void setHeadImg(String headImg) {
        SharedPreferencesHelper.saveString("headImg", headImg);
    }

    public String getHeadImg() {
        return SharedPreferencesHelper.getString("headImg");
    }
    //token
    public void setToken(String sessionId) {
        SharedPreferencesHelper.saveString("sessionId", sessionId);
    }//
    public String getToken() {
        return SharedPreferencesHelper.getString("sessionId");
    }
    //存储手机号
    public void setPhoneNum(String phoneNum) {
        SharedPreferencesHelper.saveString("phoneNum", phoneNum);
    }

    public String getPhoneNum() {
        return SharedPreferencesHelper.getString("phoneNum");
    }
    //存储密码
    public void setPwd(String pwd) {
        SharedPreferencesHelper.saveString("pwd", pwd);
    }

    public String getPwd() {
        return SharedPreferencesHelper.getString("pwd");
    }
}
