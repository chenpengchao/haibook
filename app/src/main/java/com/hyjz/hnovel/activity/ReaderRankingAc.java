package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.ReaderRankingPresenter;
import com.hyjz.hnovel.view.ReaderRankingView;

/**
 * 排行榜页面
 */
public class ReaderRankingAc extends BaseActivity<ReaderRankingPresenter> implements ReaderRankingView {
    @Override
    public void initView() {

    }

    @Override
    protected ReaderRankingPresenter createPresenter() {
        return new ReaderRankingPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_reader_ranking;
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
