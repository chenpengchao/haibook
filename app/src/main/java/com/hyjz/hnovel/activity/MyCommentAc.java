package com.hyjz.hnovel.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.MyCommentAdapter;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.MyCommentPresenter;
import com.hyjz.hnovel.view.MyCommentView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的评论
 */
public class MyCommentAc extends BaseActivity<MyCommentPresenter> implements MyCommentView {
    private int num = 1;
    //标题
    @Bind(R.id.title)
    TextView title;
    //返回键
    @Bind(R.id.back)
    ImageView back;
    //下拉刷新控件
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    //列表控件
    @Bind(R.id.rv_my_comment_list)
    RecyclerView rv_my_comment_list;
    //adapter
    MyCommentAdapter mAdapter;
    @Override
    public void initView() {
        title.setText("我的评论");
        mPresenter.myComment(num);
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_my_comment_list.setLayoutManager(new LinearLayoutManager(this));
        swipeLayout.setRefreshing(true);
        initAdapter();
        initRefreshLayout();

    }
    private void initAdapter() {
        mAdapter = new MyCommentAdapter();


        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        rv_my_comment_list.setAdapter(mAdapter);

        rv_my_comment_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(mContext, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initRefreshLayout() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        num = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.myComment(num);
//        new Request(mNextRequestPage, new RequestCallBack() {
//            @Override
//            public void success(List<Status> data) {
//                setData(true, data);
//                mAdapter.setEnableLoadMore(true);
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void fail(Exception e) {
//                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
//                mAdapter.setEnableLoadMore(true);
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        }).start();
    }
    private void loadMore() {
        num++;
        mPresenter.myComment(num);
    }
    @Override
    protected MyCommentPresenter createPresenter() {
        return new MyCommentPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_comment;
    }
    @OnClick({R.id.back})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
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
