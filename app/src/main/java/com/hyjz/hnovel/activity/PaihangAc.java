package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.BookTicketConsumCodeAdapter;
import com.hyjz.hnovel.adapter.PaihangAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.MyWalletBean;
import com.hyjz.hnovel.bean.MyWalletPaihangListBean;
import com.hyjz.hnovel.presenter.MyWalletPresenter;
import com.hyjz.hnovel.presenter.PaihangPresenter;
import com.hyjz.hnovel.view.MyWalletView;
import com.hyjz.hnovel.view.PaihangView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class PaihangAc extends BaseActivity<PaihangPresenter> implements PaihangView {

    //标题
    @Bind(R.id.title)
    TextView title;
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //adapter
    PaihangAdapter mAdapter;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage = 1;
    private static final int PAGE_SIZE = 15;
    //时间选择器
    TimePickerView pvTime;
    int year;
    int month;
    Calendar cal;
    @Override
    public void initView() {
        title.setText("排行榜");
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
    }




    public void loadData() {


        mPresenter.getBookTicketConsumCodeList(mPage);

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
        mAdapter = new PaihangAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected PaihangPresenter createPresenter() {
        return new PaihangPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_paihang;
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
    public void onSuccess(MyWalletPaihangListBean b) {
        mRefreshLayout.setLoadmoreFinished(b.getList().size() < PAGE_SIZE);
        mAdapter.addData(b.getList());
    }
}
