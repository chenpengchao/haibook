package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.GoldToBookCoinPresenter;
import com.hyjz.hnovel.view.GoldToBookCoinView;

/**
 * 金币兑换书币页面
 */
public class GoldTOBookCoinAc extends BaseActivity<GoldToBookCoinPresenter> implements GoldToBookCoinView {


    @Override
    public void initView() {

    }

    @Override
    protected GoldToBookCoinPresenter createPresenter() {
        return new GoldToBookCoinPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gold_tobook_coin;
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
