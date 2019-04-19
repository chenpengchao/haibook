package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.BookCoinRechargeCodePresenter;
import com.hyjz.hnovel.view.BookCoinRechargeCodeView;

/**
 * 书币充值记录
 */
public class BookCoinRechargeCode extends BaseActivity<BookCoinRechargeCodePresenter> implements BookCoinRechargeCodeView {


    @Override
    public void initView() {

    }

    @Override
    protected BookCoinRechargeCodePresenter createPresenter() {
        return new BookCoinRechargeCodePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_coin_recharge_code;
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
