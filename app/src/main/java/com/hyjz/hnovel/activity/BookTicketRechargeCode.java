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
import com.hyjz.hnovel.adapter.BookTicketRechargeCodeAdapter;
import com.hyjz.hnovel.adapter.BookTicketVidAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BookTicketRechargeListBean;
import com.hyjz.hnovel.presenter.BookCoinRechargeCodePresenter;
import com.hyjz.hnovel.presenter.BookTicketRechargeCodePresenter;
import com.hyjz.hnovel.view.BookCoinRechargeCodeView;
import com.hyjz.hnovel.view.BookTicketRechargeCodeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

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
    BookTicketRechargeCodeAdapter mAdapter;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage = 1;
    private static final int PAGE_SIZE = 10;
    //选择日期
    @Bind(R.id.tv_select_date)
    TextView tv_select_date;

    @Bind(R.id.iv_select_date)
    ImageView iv_select_date;
    //时间选择器
    TimePickerView pvTime;
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
    }

    @OnClick({R.id.iv_select_date})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_select_date:
                showTimeAllDay();
                break;
        }
    }
    //全天选择时间
    private void showTimeAllDay() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                tv_select_date.setText(getTimeAllday(date));
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);//获取年份
                int month=cal.get(Calendar.MONTH);//获取月份
                int day=cal.get(Calendar.DATE);//获取日
                mPage = 1;
                mAdapter.getData().clear();
                mPresenter.getBookTicketRechargeCodeList(mPage,year,month);
            }
        })
                .setType(new boolean[]{true, true, true,false,false,false})// 默认全部显示
//                .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setGravity(Gravity.CENTER)
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }

    private String getTimeAllday(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }
    public void loadData() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
        tv_select_date.setText(year+"年"+(month+1)+"月");
        mPresenter.getBookTicketRechargeCodeList(mPage,year,month);

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
        mAdapter = new BookTicketRechargeCodeAdapter();
        recyclerView.setAdapter(mAdapter);
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
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showErrorTip(String msg) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void onSuccess(BookTicketRechargeListBean b) {
        mRefreshLayout.setLoadmoreFinished(b.getList().size() < PAGE_SIZE);
        mAdapter.addData(b.getList());
    }
}
