package com.hyjz.hnovel.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.activity.AboutHNovelAc;
import com.hyjz.hnovel.activity.BeVipAc;
import com.hyjz.hnovel.activity.BookTicketAc;
import com.hyjz.hnovel.activity.CashwithdrawalAc;
import com.hyjz.hnovel.activity.FeedBackAc;
import com.hyjz.hnovel.activity.MyCommentAc;
import com.hyjz.hnovel.activity.MyMessageAc;
import com.hyjz.hnovel.activity.MyWalletAc;
import com.hyjz.hnovel.activity.PersionalInformationAc;
import com.hyjz.hnovel.activity.RechargeAc;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.MineBean;
import com.hyjz.hnovel.presenter.MinePresenter;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.view.MineView;
import com.hyjz.hnovel.weight.CircleImageView;
import com.hyjz.hnovel.weight.LoadingTip;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFm extends BaseFragment<MinePresenter> implements MineView {
    //网络加载进度控件
//    @Bind(R.id.fm_mine_lp)
//    LoadingTip fm_mine_lp;
    //圆形头像
    @Bind(R.id.fm_mine_iv)
    ImageView fm_mine_iv;
    //昵称
    @Bind(R.id.fm_mine_tv_nikename)
    TextView fm_mine_tv_nikename;
    //书币
    @Bind(R.id.fm_mine_tv_book_coin)
    TextView fm_mine_tv_book_coin;
    //金币
    @Bind(R.id.fm_mine_tv_gold_coin)
    TextView fm_mine_tv_gold_coin;
    //会员显示界面
    @Bind(R.id.ll_vip_show)
    LinearLayout ll_vip_show;
    @Bind(R.id.ll_no_vip_show)
    LinearLayout ll_no_vip_show;
    //我的钱包
    @Bind(R.id.ll_my_wallet)
    LinearLayout ll_my_wallet;
    //意见反馈
    @Bind(R.id.ll_mine_feedback)
    LinearLayout ll_mine_feedback;
    //个人信息
    @Bind(R.id.ll_persional_information)
    LinearLayout ll_persional_information;
    //充值
    @Bind(R.id.tv_mine_recharge)
    TextView tv_mine_recharge;
    //提现按钮
    @Bind(R.id.fm_mine_tv_cashwithdraw)
    TextView fm_mine_tv_cashwithdraw;
    //跳转我的书架按钮
    @Bind(R.id.fm_mine_ll_my_book_shelf)
    LinearLayout fm_mine_ll_my_book_shelf;
    //跳转我的消息界面
    @Bind(R.id.fm_mine_ll_my_message)
    LinearLayout fm_mine_ll_my_message;
    //跳转我的评论界面
    @Bind(R.id.fm_mine_ll_my_comment)
    LinearLayout fm_mine_ll_my_comment;
    //关于嗨小说
    @Bind(R.id.fm_mine_ll_about_hi)
    LinearLayout fm_mine_ll_about_hi;



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(MessageEvent event) {
        if (event.getMessage().equals("refresh_fm_mine")) {
           mPresenter.getMineData();
        }
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void refresh(MessageEvent event) {
//        if (event.getMessage().equals("login_success")) {
//            SwitchTo(0);
//            tabLayout.setCurrentTab(0);
//            EventBus.getDefault().post("refresh_fm_mine");
//        }
//    }
    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine_fm;
    }

    @Override
    protected void loadData() {
//        Log.d("mine_token", MyApp.getInstance().getToken());
        if (MyApp.getInstance().getToken() != null) {
            mPresenter.getMineData();
        }

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }


    @OnClick({R.id.ll_mine_feedback, R.id.ll_persional_information, R.id.tv_mine_recharge,
            R.id.fm_mine_tv_cashwithdraw, R.id.ll_my_wallet, R.id.fm_mine_ll_my_book_shelf,
            R.id.fm_mine_ll_my_message, R.id.fm_mine_ll_my_comment,R.id.fm_mine_ll_about_hi,
            R.id.ll_vip_show
    })

    public void onclick(View v) {
        switch (v.getId()) {
//            意见反馈
            case R.id.ll_mine_feedback:
                Intent intent = new Intent(mContext, FeedBackAc.class);
                startActivity(intent);
                break;
                //个人信息
            case R.id.ll_persional_information:
                Intent intentpersion = new Intent(mContext, PersionalInformationAc.class);
                startActivity(intentpersion);
                break;
                //充值
            case R.id.tv_mine_recharge:
                Intent intentRecharge = new Intent(mContext, BookTicketAc.class);
                startActivity(intentRecharge);
                break;
                //提现
            case R.id.fm_mine_tv_cashwithdraw:
                Intent intentCashWithDraw = new Intent(mContext, MyWalletAc.class);
                startActivity(intentCashWithDraw);
                break;
                //我的钱包
            case R.id.ll_my_wallet:
                Intent intentmyWallet = new Intent(mContext, MyWalletAc.class);
                startActivity(intentmyWallet);
                break;
                //我的书架
            case R.id.fm_mine_ll_my_book_shelf:
                EventBus.getDefault().post(new MessageEvent("show_book_shelf"));
                break;
                //我的消息
            case R.id.fm_mine_ll_my_message:
                Intent intentmyMessage = new Intent(mContext,MyMessageAc.class);
                startActivity(intentmyMessage);
                break;
                //我的评论
            case R.id.fm_mine_ll_my_comment:
                Intent intentmyComment = new Intent(mContext,MyCommentAc.class);
                startActivity(intentmyComment);
                break;
                //关于嗨小说
            case R.id.fm_mine_ll_about_hi:
                Intent intentAboutHi = new Intent(mContext,AboutHNovelAc.class);
                startActivity(intentAboutHi);
                break;
                //是会员的时候的显示
            case R.id.ll_vip_show:
                Intent intentVipShow = new Intent(mContext,BeVipAc.class);
                startActivity(intentVipShow);
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
    public void onSuccess(MineBean bean) {
        if (bean != null) {
            if (bean.getHeadImg() != null) {
                GlideUtils.loadRound(mContext, bean.getHeadImg(), fm_mine_iv);
            }
            if (bean.getNickName() != null) {
                fm_mine_tv_nikename.setText(bean.getNickName());
            } else {
                fm_mine_tv_nikename.setText("");
            }
            if (bean.getBookCoin() != null) {
                fm_mine_tv_book_coin.setText(bean.getBookCoin() + "");

            } else {
                fm_mine_tv_book_coin.setText("0");
            }
            if (bean.getGoldCoin() != null) {
                fm_mine_tv_gold_coin.setText(bean.getGoldCoin() + "");

            } else {
                fm_mine_tv_gold_coin.setText("0");
            }
            if (bean.getIsVip() != null) {
                if (bean.getIsVip() == 0) {
                    ll_no_vip_show.setVisibility(View.VISIBLE);
                    ll_vip_show.setVisibility(View.GONE);
                } else {
                    ll_no_vip_show.setVisibility(View.GONE);
                    ll_vip_show.setVisibility(View.VISIBLE);
                }
            } else {
                ll_no_vip_show.setVisibility(View.VISIBLE);
                ll_vip_show.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onFailure() {

    }
}
