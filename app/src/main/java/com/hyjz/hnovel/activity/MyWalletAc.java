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
        init();
    }
        private void init () {
//            StatusBarUtil.immersive(this);
//            StatusBarUtil.setPaddingSmart(this, toolbar);
            refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
                @Override
                public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                    mOffset = offset / 2;
//                    ivHeader.setTranslationY(mOffset - mScrollY);
//                    toolbar.setAlpha(1 - Math.min(percent, 1));
                }

                @Override
                public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                    mOffset = offset / 2;
//                    ivHeader.setTranslationY(mOffset - mScrollY);
//                    toolbar.setAlpha(1 - Math.min(percent, 1));
                }
            });

            //判断是否是华为手机并且是否有虚拟导航键
//        if (DeviceUtil.isHUAWEI() && DeviceUtil.checkDeviceHasNavigationBar(this.getApplicationContext())) {
//            getContentResolver().registerContentObserver(Settings.System.getUriFor
//                    ("navigationbar_is_min"), true, mNavigationStatusObserver);
//        }
            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    dealWithViewPager();
                }
            });
            scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                int lastScrollY = 0;
                int h = DensityUtil.dp2px(170);
                int color = ContextCompat.getColor(getApplicationContext(), R.color.white) & 0x00ffffff;

                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int[] location = new int[2];
                    magicIndicator.getLocationOnScreen(location);
                    int yPosition = location[1];
                    if (yPosition < toolBarPositionY) {
                        magicIndicatorTitle.setVisibility(View.VISIBLE);
                        scrollView.setNeedScroll(false);
                    } else {
                        magicIndicatorTitle.setVisibility(View.GONE);
                        scrollView.setNeedScroll(true);

                    }

//                    if (lastScrollY < h) {
//                        scrollY = Math.min(h, scrollY);
//                        mScrollY = scrollY > h ? h : scrollY;
//                        buttonBarLayout.setAlpha(1f * mScrollY / h);
//                        toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
//                        ivHeader.setTranslationY(mOffset - mScrollY);
//                    }
//                    if (scrollY == 0) {
//                        ivBack.setImageResource(R.drawable.back_white);
//                        ivMenu.setImageResource(R.drawable.icon_menu_white);
//                    } else {
//                        ivBack.setImageResource(R.drawable.back_black);
//                        ivMenu.setImageResource(R.drawable.icon_menu_black);
//                    }

                    lastScrollY = scrollY;
                }
            });
//            buttonBarLayout.setAlpha(0);
//            toolbar.setBackgroundColor(0);


            viewPager.setAdapter(new ComFragmentAdapter(getSupportFragmentManager(), getFragments()));
            viewPager.setOffscreenPageLimit(10);
            initMagicIndicator();
            initMagicIndicatorTitle();
        }

        private void dealWithViewPager () {
            toolBarPositionY = toolbar.getHeight();
            ViewGroup.LayoutParams params = viewPager.getLayoutParams();
            params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - magicIndicator.getHeight() + 1;
            viewPager.setLayoutParams(params);
        }

        private List<Fragment> getFragments () {
            List<Fragment> fragments = new ArrayList<>();
            fragments.add(DynamicFragment.getInstance());
            fragments.add(ArticleFragment.getInstance());
            fragments.add(QuestionFragment.getInstance());
            return fragments;
        }

        private void initMagicIndicator () {
            CommonNavigator commonNavigator = new CommonNavigator(this);
            commonNavigator.setScrollPivotX(0.65f);
            commonNavigator.setAdjustMode(true);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return mDataList == null ? 0 : mDataList.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                    simplePagerTitleView.setText(mDataList.get(index));
                    simplePagerTitleView.setNormalColor(ContextCompat.getColor(MyWalletAc.this, R.color.main_color));
                    simplePagerTitleView.setSelectedColor(ContextCompat.getColor(MyWalletAc.this, R.color.main_color));
                    simplePagerTitleView.setTextSize(16);
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(index, false);
                        }
                    });
                    return simplePagerTitleView;
                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                    indicator.setLineHeight(UIUtil.dip2px(context, 2));
                    indicator.setLineWidth(UIUtil.dip2px(context, 20));
                    indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                    indicator.setStartInterpolator(new AccelerateInterpolator());
                    indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                    indicator.setColors(ContextCompat.getColor(MyWalletAc.this, R.color.main_color));
                    return indicator;
                }
            });
            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, viewPager);
        }

        private void initMagicIndicatorTitle () {
            CommonNavigator commonNavigator = new CommonNavigator(this);
            commonNavigator.setScrollPivotX(0.65f);
            commonNavigator.setAdjustMode(true);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return mDataList == null ? 0 : mDataList.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                    simplePagerTitleView.setText(mDataList.get(index));
                    simplePagerTitleView.setNormalColor(ContextCompat.getColor(MyWalletAc.this, R.color.main_color));
                    simplePagerTitleView.setSelectedColor(ContextCompat.getColor(MyWalletAc.this, R.color.main_color));
                    simplePagerTitleView.setTextSize(16);
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(index, false);
                        }
                    });
                    return simplePagerTitleView;
                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                    indicator.setLineHeight(UIUtil.dip2px(context, 2));
                    indicator.setLineWidth(UIUtil.dip2px(context, 20));
                    indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                    indicator.setStartInterpolator(new AccelerateInterpolator());
                    indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                    indicator.setColors(ContextCompat.getColor(MyWalletAc.this, R.color.main_color));
                    return indicator;
                }
            });
            magicIndicatorTitle.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicatorTitle, viewPager);

    }
    @Override
    protected MyWalletPresenter createPresenter() {
        return new MyWalletPresenter(this);
    }

    @OnClick({R.id.ac_my_wallet_ll_withdraw})
    public void onclick(View v) {
        switch (v.getId()) {
            //余额提现按钮
            case R.id.ac_my_wallet_ll_withdraw:
                Intent intent = new Intent(mContext, WithDrawCrashAc.class);
                startActivity(intent);
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
