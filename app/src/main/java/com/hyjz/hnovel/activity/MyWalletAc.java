package com.hyjz.hnovel.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.MyWalletBean;
import com.hyjz.hnovel.presenter.MyWalletPresenter;
import com.hyjz.hnovel.view.MyWalletView;
import com.hyjz.hnovel.weight.ColorFlipPagerTitleView;
import com.hyjz.hnovel.weight.JudgeNestedScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的钱包页面
 */
public class MyWalletAc extends BaseActivity<MyWalletPresenter> implements MyWalletView {
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //标题
    @Bind(R.id.title)
    TextView title;
    //余额提现
    @Bind(R.id.ac_my_wallet_ll_withdraw)
    LinearLayout ac_my_wallet_ll_withdraw;
    //显示余额
    @Bind(R.id.tv_show_remain_money)
    TextView tv_show_remain_money;
    //当前金币
    @Bind(R.id.tv_recent_coin)
    TextView tv_recent_coin;
    //累计收益
    @Bind(R.id.tv_leiji_money)
    TextView tv_leiji_money;
    //排行榜按钮
    @Bind(R.id.tv_my_wallet_paihang)
    TextView tv_my_wallet_paihang;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @Bind(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @Bind(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[]{"金币获得记录", "金币兑换记录", "余额提现记录"};
    private List<String> mDataList = Arrays.asList(mTitles);
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_wallet;
    }
    @Override
    public void initView() {
        title.setText("我的钱包");
        mPresenter.getMyWallet();
    }

    @Override
    protected MyWalletPresenter createPresenter() {
        return new MyWalletPresenter(this);
    }

    @OnClick({R.id.ac_my_wallet_ll_withdraw,R.id.tv_my_wallet_paihang})
    public void onclick(View v) {
        switch (v.getId()) {
            //余额提现按钮
            case R.id.ac_my_wallet_ll_withdraw:
                Intent intent = new Intent(mContext, WithDrawCrashAc.class);
                startActivity(intent);
                break;
                //排行榜
            case R.id.tv_my_wallet_paihang:
                Intent intent1 = new Intent(mContext, PaihangAc.class);
                startActivity(intent1);
                break;
        }
    }


    @Override
    public void onSuccess(MyWalletBean b) {
        //显示余额
        if (b.getCash() != null) {
            tv_show_remain_money.setText(b.getCash() + "");
        } else {
            tv_show_remain_money.setText( "0");
        }
        if (b.getGoldCoin() != null) {
            tv_recent_coin.setText(b.getGoldCoin()+"");

        } else {
            tv_recent_coin.setText("0");
        }
        if (b.getProfit() != null) {
            tv_leiji_money.setText(b.getProfit() + "");
        } else {
            tv_leiji_money.setText("0");
        }


    }

    @Override
    public void onFailue() {

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
}
