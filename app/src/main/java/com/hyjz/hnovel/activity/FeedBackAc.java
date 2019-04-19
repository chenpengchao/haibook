package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.FeedBackPresenter;
import com.hyjz.hnovel.utils.ToastUtil;
import com.hyjz.hnovel.view.FeedBackView;
import com.hyjz.hnovel.weight.LoadingTip;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class FeedBackAc extends BaseActivity<FeedBackPresenter> implements FeedBackView {
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //意见反馈输入框
    @Bind(R.id.ac_feed_et_advice)
    EditText ac_feed_et_advice;
    //微信输入框
    @Bind(R.id.ac_feed_et_wechat)
    EditText ac_feed_et_wechat;
    //手机号输入框
    @Bind(R.id.ac_feed_et_phoneNum)
    EditText ac_feed_et_phoneNum;
    //提交按钮
    @Bind(R.id.ac_feed_tv_submit)
    TextView ac_feed_tv_submit;
    //网络加载控件
    @Bind(R.id.ac_feed_lt)
    LoadingTip ac_feed_lt;
    @Override
    public void initView() {

    }

    @Override
    protected FeedBackPresenter createPresenter() {
        return new FeedBackPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_feed_back;
    }

    @OnClick({R.id.back,R.id.ac_feed_tv_submit})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ac_feed_tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String et_advice = ac_feed_et_advice.getText().toString();
        String et_wechat = ac_feed_et_wechat.getText().toString();
        String et_phone = ac_feed_et_phoneNum.getText().toString();
        if (et_advice == null) {
            ToastUtil.showShort(mContext,"请输入您的建议");
            return;
        }
        if (et_wechat == null) {
            ToastUtil.showShort(mContext,"请输入您的微信号");
            return;
        }
        if (et_phone == null) {
            ToastUtil.showShort(mContext,"请输入您的手机号");
            return;
        }
        mPresenter.postFeedBack(et_advice, et_wechat, et_phone);

    }

    @Override
    public void showLoading(String title) {
        ac_feed_lt.setLoadingTip(LoadingTip.LoadStatus.loading);

    }

    @Override
    public void stopLoading() {
        ac_feed_lt.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        ac_feed_lt.setLoadingTip(LoadingTip.LoadStatus.error);
        ac_feed_lt.setTips(msg);
    }

    @Override
    public void onSuccess() {
        ToastUtil.showShort(mContext,"提交成功");
        finish();
    }

    @Override
    public void onFailue() {
        ToastUtil.showShort(mContext,"提交失败，请稍后重试");
    }
}
