package com.hyjz.hnovel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hyjz.hnovel.activity.LoginAc;
import com.hyjz.hnovel.activity.RegesterAc;
import com.hyjz.hnovel.app.AppConfig;
import com.hyjz.hnovel.app.AppConstant;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.LoginBean;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.TabEntity;
import com.hyjz.hnovel.fragment.BookCircleFm;
import com.hyjz.hnovel.fragment.FirstFm;
import com.hyjz.hnovel.fragment.HaiBookShelfFm;
import com.hyjz.hnovel.fragment.HiMoneyFm;
import com.hyjz.hnovel.fragment.MineFm;
import com.hyjz.hnovel.manager.ChangeModeController;
import com.hyjz.hnovel.presenter.LoginPresenter;
import com.hyjz.hnovel.presenter.MainAcPresenter;
import com.hyjz.hnovel.utils.LogUtils;
import com.hyjz.hnovel.utils.SharedPreferencesHelper;
import com.hyjz.hnovel.view.LoginView;
import com.hyjz.hnovel.view.MainAcView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import rx.functions.Action1;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles = {"嗨书架","首页", "嗨赚","嗨书圈","我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.bookshelf_normal,R.mipmap.home_normal,R.mipmap.himoney_normal,R.mipmap.circle_normal,R.mipmap.my_normal};
    private int[] mIconSelectIds = {
            R.mipmap.bookshelf_selected,R.mipmap.home_selected, R.mipmap.himoney_selected,R.mipmap.circle_selected,R.mipmap.my_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private HaiBookShelfFm haiBookShelfFm;
    private FirstFm firstFm;
    private HiMoneyFm hiMoneyFm;
    private BookCircleFm bookCircleFm;
    private MineFm mineFm;
    int position = 0;
    private static int tabLayoutHeight;
    Bundle savedInstance = null;
    public static MainActivity instance;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void message(MessageEvent event) {
        if (event.getMessage().equals("login_success")) {
            SwitchTo(0);
            tabLayout.setCurrentTab(0);
            EventBus.getDefault().post(new MessageEvent("refresh_fm_mine"));
        } else if (event.getMessage().equals("show_book_shelf")){
            SwitchTo(0);
            tabLayout.setCurrentTab(0);
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    public void initView() {
//        //此处填上在http://fir.im/注册账号后获得的API_TOKEN以及APP的应用ID
//        UpdateKey.API_TOKEN = AppConfig.API_FIRE_TOKEN;
//        UpdateKey.APP_ID = AppConfig.APP_FIRE_ID;
//        //如果你想通过Dialog来进行下载，可以如下设置
////        UpdateKey.DialogOrNotification=UpdateKey.WITH_DIALOG;
//        UpdateFunGO.init(this);
        //初始化菜单

            initTab();
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //切换daynight模式要立即变色的页面
        ChangeModeController.getInstance().init(this,R.attr.class);
        super.onCreate(savedInstanceState);
        savedInstance = savedInstanceState;
        instance = this;
        //初始化frament
        if (MyApp.getInstance().getPhoneNum() != null && MyApp.getInstance().getPwd() != null) {
            SharedPreferencesHelper.remove("token");
            mPresenter.login(MyApp.getInstance().getPhoneNum(), MyApp.getInstance().getPwd());
        } else {
            if (MyApp.getInstance().getToken() != null) {
                SharedPreferencesHelper.remove("token");
            }

        }
        initFragment(savedInstanceState);
        tabLayout.measure(0,0);
        tabLayoutHeight=tabLayout.getMeasuredHeight();
        //监听菜单显示或隐藏
        mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {

            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });
    }
    /**
     * 菜单显示隐藏动画
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide){
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if(!showOrHide){
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        }else{
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }
    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 1;
        if (savedInstanceState != null) {
            haiBookShelfFm = (HaiBookShelfFm) getSupportFragmentManager().findFragmentByTag("haiBookShelfFm");
            firstFm = (FirstFm) getSupportFragmentManager().findFragmentByTag("firstFm");
            hiMoneyFm = (HiMoneyFm) getSupportFragmentManager().findFragmentByTag("hiMoneyFm");
            bookCircleFm = (BookCircleFm) getSupportFragmentManager().findFragmentByTag("bookCircleFm");
            mineFm = (MineFm) getSupportFragmentManager().findFragmentByTag("mineFm");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            haiBookShelfFm = new HaiBookShelfFm();
            firstFm = new FirstFm();
            hiMoneyFm = new HiMoneyFm();
            bookCircleFm = new BookCircleFm();
            mineFm = new MineFm();

            transaction.add(R.id.fl_body, haiBookShelfFm, "haiBookShelfFm");
            transaction.add(R.id.fl_body, firstFm, "firstFm");
            transaction.add(R.id.fl_body, hiMoneyFm, "hiMoneyFm");
            transaction.add(R.id.fl_body, bookCircleFm, "bookCircleFm");
            transaction.add(R.id.fl_body, mineFm, "mineFm");
//            transaction.addToBackStack(null);
            transaction.commit();
        }

        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                position =0;
                transaction.hide(firstFm);
                transaction.hide(hiMoneyFm);
                transaction.hide(bookCircleFm);
                transaction.hide(mineFm);
                transaction.show(haiBookShelfFm);
                transaction.commitAllowingStateLoss();
                break;
            //美女
            case 1:
                position = 1;
                transaction.show(firstFm);
                transaction.hide(hiMoneyFm);
                transaction.hide(bookCircleFm);
                transaction.hide(mineFm);
                transaction.hide(haiBookShelfFm);
                transaction.commitAllowingStateLoss();
                break;
            //视频
            case 2:
                position = 2;
                transaction.hide(firstFm);
                transaction.show(hiMoneyFm);
                transaction.hide(bookCircleFm);
                transaction.hide(mineFm);
                transaction.hide(haiBookShelfFm);
                transaction.commitAllowingStateLoss();
                break;
            //关注
            case 3:
                position = 3;
                transaction.hide(firstFm);
                transaction.hide(hiMoneyFm);
                transaction.show(bookCircleFm);
                transaction.hide(mineFm);
                transaction.hide(haiBookShelfFm);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                position = 4;
                if (MyApp.getInstance().getToken() == null) {
                    startActivity(new Intent(this, LoginAc.class));
                } else {
                    transaction.hide(firstFm);
                    transaction.hide(hiMoneyFm);
                    transaction.hide(bookCircleFm);
                    transaction.show(mineFm);
                    transaction.hide(haiBookShelfFm);
                    transaction.commitAllowingStateLoss();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void success(LoginBean b) {
//        MyApp.getInstance().setPwd(password);
//        MyApp.getInstance().setPhoneNum(phone);
        MyApp.getInstance().setNickName(b.getNickName());
        MyApp.getInstance().setUserId(b.getUserId());
        if (b.getHeadImg() != null) {
            MyApp.getInstance().setHeadImg(b.getHeadImg());
        }
        Log.d("token", b.getSessionId().toString().trim());
        MyApp.getInstance().setToken(b.getSessionId().toString().trim());
        initFragment(savedInstanceState);
    }

    @Override
    public void error() {
        startActivity(new Intent(this, LoginAc.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(false);
//            return true;
//        }
        if (position == 1) {
            FirstFm.clickBack(keyCode, event);
            return true;
        }
//        else if (position == 2) {
//            HiMoneyFm.clickBack(keyCode, event);
//            return true;
//        } else if (position == 3) {
//            QcodeLoanAndFriendFm.clickBack(keyCode, event);
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }
}
