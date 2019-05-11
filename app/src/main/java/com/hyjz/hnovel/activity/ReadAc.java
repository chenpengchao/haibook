package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;

public class ReadAc extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_read;
    }
}
