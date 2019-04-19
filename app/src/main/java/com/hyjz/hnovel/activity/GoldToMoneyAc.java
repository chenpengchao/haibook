package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.GoldToMoneyPresenter;
import com.hyjz.hnovel.view.GoldtoMoneyView;

/**
 * 金币兑换现金页面
 */
public class GoldToMoneyAc extends BaseActivity<GoldToMoneyPresenter> implements GoldtoMoneyView {


    @Override
    public void initView() {

    }

    @Override
    protected GoldToMoneyPresenter createPresenter() {
        return new GoldToMoneyPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gold_to_money;
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
