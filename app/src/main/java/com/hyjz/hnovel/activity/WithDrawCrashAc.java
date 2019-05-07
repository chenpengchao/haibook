package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.WithDrawInfoBean;
import com.hyjz.hnovel.presenter.WithDrawCashPresenter;
import com.hyjz.hnovel.view.WithDrawCashView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 提现页面
 */
public class WithDrawCrashAc extends BaseActivity<WithDrawCashPresenter> implements WithDrawCashView {
    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.ll_withdraw_crash_record)
    LinearLayout ll_withdraw_crash_record;
    //提现按钮
    @Bind(R.id.ac_with_draw_crash_ll)
    LinearLayout ac_with_draw_crash_ll;
    //已经提现的金额
    @Bind(R.id.tv_already_withdraw)
    TextView tv_already_withdraw;
    //可以提现的金额
    @Bind(R.id.tv_can_withdraw)
    TextView tv_can_withdraw;
    @Override
    protected WithDrawCashPresenter createPresenter() {
        return new WithDrawCashPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_with_draw_crash;
    }

    @Override
    public void initView() {
        title.setText("余额提现");
        mPresenter.withdraw();
    }

    @OnClick({R.id.ll_withdraw_crash_record,R.id.ac_with_draw_crash_ll})
    public void onclick(View v) {
        switch (v.getId()) {
            //提现记录
            case R.id.ll_withdraw_crash_record:
                Intent intent = new Intent(mContext, WithDrawCrashRecodeAc.class);
                startActivity(intent);
                break;
                //提现按钮
            case R.id.ac_with_draw_crash_ll:
                Intent intent_with_draw = new Intent(mContext, CashwithdrawalAc.class);
                startActivity(intent_with_draw);
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
    public void onInfoSuccess(WithDrawInfoBean bean) {
        tv_already_withdraw.setText(bean.getHasWithdrawedCash()+"元");
        tv_can_withdraw.setText(bean.getCanWithdrawCash()+"元");
    }
}
