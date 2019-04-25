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
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private int mPage=1;
    private static final int PAGE_SIZE = 10;
    boolean isfresh=false ;
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
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(255,69,0));
        initRecycler();
        loadData();


        initRefresh();
        mSwipeRefreshLayout.setRefreshing(true);
    }
    public void loadData() {

        mPresenter.getBookTicketVidList(mPage);

    }
    /**
     * time    : 2019/3/14 11:24
     * desc    : 初始化上拉刷新, 下拉加载更多
     * versions: 1.0
     * 	255,69,0
     */
    private void initRefresh() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isfresh = true;
                refresh();
            }
        });

    }
    /**
     * time    : 2019/3/28 11:22
     * desc    : 刷新列表
     * versions: 1.0
     */
    private void refresh(){
        mPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
//        mAdapter.getData().clear();
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
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPage++;
                isfresh = false;
                loadData();
            }
        });
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

    }

    @Override
    public void showErrorTip(String msg) {
        if (isfresh == true) {
            Toast.makeText(mContext, "网络错误", Toast.LENGTH_LONG).show();
            mAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mAdapter.loadMoreFail();
            Toast.makeText(mContext, "网络错误", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBookTicketVidSuccess(MyBookTicketVidListBean b) {
//        mRefreshLayout.setLoadmoreFinished(b.getList() .size()< 10);

        if (isfresh == true) {
            setData(true, b.getList());
            mAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
        } else if (isfresh == false) {
//            boolean isRefresh =mPage ==1;
            setData(isfresh, b.getList());
        }
    }
    private void setData(boolean isRefresh, List data) {
//        mPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
