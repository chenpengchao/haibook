package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.RechargeBookCoinAdapter;
import com.hyjz.hnovel.anim.ScaleInAnimation;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.IsCheckBean;
import com.hyjz.hnovel.bean.RechargeBookCoinBean;
import com.hyjz.hnovel.presenter.ReChargeBookCoinPresenter;
import com.hyjz.hnovel.view.RechargeBookCoinView;
import com.hyjz.hnovel.weight.IRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 充值书券界面
 */
public class RechargeAc extends BaseActivity<ReChargeBookCoinPresenter> implements RechargeBookCoinView, View.OnClickListener {
    //标题
    @Bind(R.id.title)
    TextView title;
    //返回键
    @Bind(R.id.back)
    ImageView back;
    //充值项目
    @Bind(R.id.ac_recharge_rv)
    IRecyclerView ac_recharge_rv;
    //头部view
    View view;
    //网格gridview
    private GridLayoutManager grideLayoutManager;
    //adapter
    RechargeBookCoinAdapter mAdapter;
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
        title.setText("充值");
        view = LayoutInflater.from(mContext).inflate(R.layout.view_recharge_coin_head, null);
        recharge_footer = LayoutInflater.from(mContext).inflate(R.layout.view_recharge_book_coin_foot, null);
        ll_recharge_book_coin_wechat = recharge_footer.findViewById(R.id.ll_recharge_book_coin_wechat);
        ll_recharge_book_coin_alipay = recharge_footer.findViewById(R.id.ll_recharge_book_coin_alipay);
        tv_show_coin = view.findViewById(R.id.tv_show_coin);
        tv_show_ticket = view.findViewById(R.id.tv_show_ticket);

        mAdapter = new RechargeBookCoinAdapter(this, mPresenter);
        ac_recharge_rv.addHeaderView(view);
        ac_recharge_rv.addFooterView(recharge_footer);
        ac_recharge_rv.setRefreshEnabled(false);
//        mAdapter.openLoadAnimation(new ScaleInAnimation());
        ac_recharge_rv.setLayoutManager (new GridLayoutManager (mContext,2,GridLayoutManager.VERTICAL,false));
        ac_recharge_rv.setAdapter(mAdapter);
        IsCheckBean.setPos(0);
        loadData();
        mAdapter.setData(new RechargeBookCoinAdapter.OnSendDataListener() {
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

    private void loadData() {
        mPresenter.rechargeBookCoin();
    }

    @Override
    protected ReChargeBookCoinPresenter createPresenter() {
        return new ReChargeBookCoinPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_recharge;
    }

    @OnClick({R.id.back})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
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
    public void onSuccess(RechargeBookCoinBean bean) {

        mAdapter.clear();
        mAdapter.addAll(bean.getGoodsList());
        if (bean.getBookCoin() != null) {
            tv_show_coin.setText(bean.getBookCoin() + "");
        } else {
            tv_show_coin.setText("0");
        }
        if (bean.getBookCoupon() != null) {
            tv_show_ticket.setText(bean.getBookCoupon() + "");
        } else {
            tv_show_ticket.setText("0");
        }



    }

    @Override
    public void onFailue() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //微信支付
            case R.id.ll_recharge_book_coin_wechat:

                break;
                //支付宝支付
            case R.id.ll_recharge_book_coin_alipay:

                break;
        }
    }
}
