package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.BookTicketVidPresenter;
import com.hyjz.hnovel.view.BookTicketVidView;

/**
 * 书券有效期页面
 */
public class BookTicketVidAc extends BaseActivity<BookTicketVidPresenter> implements BookTicketVidView {



    @Override
    public void initView() {

    }

    @Override
    protected BookTicketVidPresenter createPresenter() {
        return new BookTicketVidPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_ticket_vid;
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
