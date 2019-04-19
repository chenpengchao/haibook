package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.BookCoinConsumePresenter;
import com.hyjz.hnovel.view.BookCoinConsumeView;

/**
 * 书币消费记录
 */
public class BookCoinConsumeCodeAc extends BaseActivity<BookCoinConsumePresenter> implements BookCoinConsumeView {

    @Override
    public void initView() {

    }

    @Override
    protected BookCoinConsumePresenter createPresenter() {
        return new BookCoinConsumePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_coin_consume_code;
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
