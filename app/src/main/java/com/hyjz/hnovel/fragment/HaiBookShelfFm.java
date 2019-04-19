package com.hyjz.hnovel.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chaychan.uikit.powerfulrecyclerview.PowerfulRecyclerView;
import com.chaychan.uikit.refreshlayout.BGANormalRefreshViewHolder;
import com.chaychan.uikit.refreshlayout.BGARefreshLayout;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.BookShelfAdapter;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.presenter.BookShelfPresenter;
import com.hyjz.hnovel.utils.UIUtils;
import com.hyjz.hnovel.view.BookShelfView;
import com.hyjz.hnovel.weight.LoadingTip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaiBookShelfFm extends BaseFragment<BookShelfPresenter> implements BookShelfView, BGARefreshLayout.BGARefreshLayoutDelegate, BaseQuickAdapter.RequestLoadMoreListener {
    @Bind(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    //    @Bind(R.id.fl_content)
//    FrameLayout mFlContent;
    //加载页面内嵌提示
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.rv_news)
    PowerfulRecyclerView mRvNews;
    protected BaseQuickAdapter mNewsAdapter;
    //书架图书列表
    private List<BookRecommend> list = new ArrayList<>();

    @Override
    protected BookShelfPresenter createPresenter() {
        return new BookShelfPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_hai_book_shelf_fm;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();

//        mPresenter.getRecommed("male");

        mStateView.showContent();//显示内容
    }

    @Override
    public void onShowRecommendData(List<BookRecommend> data) {
        mRefreshLayout.endRefreshing();
        list.clear();
        if (data != null) {
            list.addAll(data);
            mNewsAdapter = new BookShelfAdapter(list);
            mRvNews.setAdapter(mNewsAdapter);
        }


    }

    @Override
    public void onError() {

    }

    @Override
    public void initView(View rootView) {
        mRefreshLayout.setDelegate(this);
        mRvNews.setLayoutManager(new GridLayoutManager(mActivity, 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, false);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText(UIUtils.getString(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(UIUtils.getString(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(UIUtils.getString(R.string.refresh_ing_text));//刷新中的提示文字


        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.shouldHandleRecyclerViewLoadingMore(mRvNews);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//        mPresenter.getRecommed("male");

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void showLoading(String title) {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
//        irc.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
    }
}
