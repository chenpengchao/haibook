package com.hyjz.hnovel.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.BookTicketVidAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.MyBookTicketVidListBean;
import com.hyjz.hnovel.presenter.BookTicketVidPresenter;
import com.hyjz.hnovel.view.BookTicketVidView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import butterknife.Bind;

/**
 * 书券有效期页面
 */
public class BookTicketVidAc extends BaseActivity<BookTicketVidPresenter> implements BookTicketVidView {
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
    boolean isfresh = true;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_ticket_vid;
    }

    @Override
    public void initView() {
        title.setText("书券有效期");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(255,69,0));
        initRecycler();
        loadData();


        initRefresh();
//        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void loadData() {

        mPresenter.getBookTicketVidList(mPage);

    }

    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化上拉刷新, 下拉加载更多
     * versions: 1.0
     * 255,69,0
     */
    private void initRefresh() {

        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                loadData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
            }
        });

    }

    /**
     * time    : 2019/3/28 11:22
     * desc    : 刷新列表
     * versions: 1.0
     */
    private void refresh() {
        mPage = 1;
        mAdapter.getData().clear();
        loadData();
    }

    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化列表
     * versions: 1.0
     */
    private void initRecycler() {
        mAdapter = new BookTicketVidAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected BookTicketVidPresenter createPresenter() {
        return new BookTicketVidPresenter(this);
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showErrorTip(String msg) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void onBookTicketVidSuccess(MyBookTicketVidListBean b) {
        mRefreshLayout.setLoadmoreFinished(b.getList().size() < PAGE_SIZE);
        mAdapter.addData(b.getList());
    }


}
