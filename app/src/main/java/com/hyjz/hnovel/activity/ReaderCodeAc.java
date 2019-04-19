package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.ReaderCodePresenter;
import com.hyjz.hnovel.view.ReaderCodeView;

/**
 * 阅读记录页面
 */
public class ReaderCodeAc extends BaseActivity <ReaderCodePresenter> implements ReaderCodeView {

    @Override
    public void initView() {

    }

    @Override
    protected ReaderCodePresenter createPresenter() {
        return new ReaderCodePresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_reader_code;
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
