package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.BookTicketVidAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.BookCoinRechargeCodePresenter;
import com.hyjz.hnovel.presenter.BookTicketRechargeCodePresenter;
import com.hyjz.hnovel.view.BookCoinRechargeCodeView;
import com.hyjz.hnovel.view.BookTicketRechargeCodeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.Bind;

/**
 * 书券充值记录
 */
public class BookTicketRechargeCode extends BaseActivity<BookTicketRechargeCodePresenter> implements BookCoinRechargeCodeView, BookTicketRechargeCodeView {

    //标题
    @Bind(R.id.title)
    TextView title;
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //adapter
    BookTicketVidAdapter mAdapter;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage = 1;
    private static final int PAGE_SIZE = 10;
    @Override
    public void initView() {
        mPresenter.getBookTicketRechargeCodeList(1);
    }

    @Override
    protected BookTicketRechargeCodePresenter createPresenter() {
        return new BookTicketRechargeCodePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_ticket_recharge_code;
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
    public void onSuccess() {

    }
}
