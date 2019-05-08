package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.BeVipRechargeAdapter;
import com.hyjz.hnovel.anim.ScaleInAnimation;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.IsCheckBean;
import com.hyjz.hnovel.bean.IsCheckBeanBeVip;
import com.hyjz.hnovel.bean.ReadVipBean;
import com.hyjz.hnovel.presenter.BeVipPresenter;
import com.hyjz.hnovel.view.BeVipView;
import com.hyjz.hnovel.weight.IRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

public class BeVipAc extends BaseActivity<BeVipPresenter> implements BeVipView, View.OnClickListener {
    //标题
    @Bind(R.id.title)
    TextView title;
    //返回键
    @Bind(R.id.back)
    ImageView back;
    //充值项目
    @Bind(R.id.ac_recharge_rv)
    IRecyclerView ac_recharge_rv;
    //充值记录
    @Bind(R.id.tv_recharge_code)
    TextView tv_recharge_code;
    //显示是否是vip和vip有效期
    @Bind(R.id.tv_show_is_vip)
    TextView tv_show_is_vip;
    //adapter
    BeVipRechargeAdapter mAdapter;
    int position = 0;
    //显示书币
    TextView tv_show_coin;
    //显示书券
    TextView tv_show_ticket;
    //微信或者支付宝充值footer
    View recharge_footer;
    //微信充值
    LinearLayout ll_recharge_book_coin_wechat;
    //支付宝充值
    LinearLayout ll_recharge_book_coin_alipay;
    @Override
    public void initView() {
        title.setText("充值嗨读VIP");
//        view = LayoutInflater.from(mContext).inflate(R.layout.view_recharge_coin_head, null);
        recharge_footer = LayoutInflater.from(mContext).inflate(R.layout.view_recharge_book_coin_foot, null);
        ll_recharge_book_coin_wechat = recharge_footer.findViewById(R.id.ll_recharge_book_coin_wechat);
        ll_recharge_book_coin_alipay = recharge_footer.findViewById(R.id.ll_recharge_book_coin_alipay);
//        tv_show_coin = view.findViewById(R.id.tv_show_coin);
//        tv_show_ticket = view.findViewById(R.id.tv_show_ticket);

        mAdapter = new BeVipRechargeAdapter(this, mPresenter);
//        ac_recharge_rv.addHeaderView(view);
//        ac_recharge_rv.addFooterView(recharge_footer);
        ac_recharge_rv.setRefreshEnabled(false);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        ac_recharge_rv.setLayoutManager (new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false));
        ac_recharge_rv.setAdapter(mAdapter);
        IsCheckBeanBeVip.setPos(0);
        mPresenter.showVipInfo();
        mAdapter.setData(new BeVipRechargeAdapter.OnSendDataListener() {
            @Override
            public void send(Integer pos) {
                position = pos;
            }
        });
        //微信支付
        ll_recharge_book_coin_wechat.setOnClickListener(this);
        //支付宝支付
        ll_recharge_book_coin_alipay.setOnClickListener(this);

    }

    @Override
    protected BeVipPresenter createPresenter() {
        return new BeVipPresenter(this);
    }

    @OnClick({R.id.back, R.id.tv_recharge_code})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            //充值记录
            case R.id.tv_recharge_code:
                Intent intent = new Intent(mContext, RechargeCodeAc.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_be_vip;
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
    public void onReadVipInfo(ReadVipBean bean) {
        mAdapter.clear();
        mAdapter.addAll(bean.getGoodsList());
    }

    @Override
    public void onClick(View v) {

    }
}
