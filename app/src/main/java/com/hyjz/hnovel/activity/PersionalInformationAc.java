package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.MyInfoBean;
import com.hyjz.hnovel.presenter.PersionInfoPresenter;
import com.hyjz.hnovel.view.PersionInfoView;

import butterknife.Bind;
import butterknife.OnClick;

public class PersionalInformationAc extends BaseActivity<PersionInfoPresenter> implements PersionInfoView {
    //真实姓名
    @Bind(R.id.tv_myinfo_name)
    TextView tv_myinfo_name;
    //手机号码
    @Bind(R.id.tv_myinfo_phonenum)
    TextView tv_myinfo_phonenum;
    //微信号
    @Bind(R.id.tv_myinfo_wechat)
    TextView tv_myinfo_wechat;
    //性别
    @Bind(R.id.tv_myinfo_gender)
    TextView tv_myinfo_gender;
    //地区
    @Bind(R.id.tv_myinfo_area)
    TextView tv_myinfo_area;

    //支付宝提现账号
    @Bind(R.id.tv_alipay_withdraw)
    TextView tv_alipay_withdraw;
    //切换账户
    @Bind(R.id.tv_qiehuan)
    TextView tv_qiehuan;
    @Override
    public void initView() {
        mPresenter.getInfo();
    }

    @Override
    protected PersionInfoPresenter createPresenter() {
        return new PersionInfoPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_persional_information;
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

    @Override
    public void onSucess(MyInfoBean bean) {
        if (bean != null) {
//            显示姓名
            if (bean.getRealName() != null) {
                tv_myinfo_name.setText(bean.getRealName() + "");
            } else {
                tv_myinfo_name.setText( "");
            }
//          显示手机号码
            if (bean.getPhoneNum() != null) {
                tv_myinfo_phonenum.setText(bean.getPhoneNum() + "");
            } else {
                tv_myinfo_phonenum.setText( "");
            }
//            微信号
            if (bean.getWx_account() != null) {
                tv_myinfo_wechat.setText(bean.getWx_account() + "");
            } else {
                tv_myinfo_wechat.setText( "");
            }
//            性别
            if (bean.getGender() != null) {
                tv_myinfo_gender.setText(bean.getGender() + "");

            } else {
                tv_myinfo_gender.setText("");
            }
//            地区
            if (bean.getArea() != null) {
                tv_myinfo_area.setText(bean.getArea());
            }else {
                tv_myinfo_area.setText("");
            }
//            支付宝提现账户
            if (bean.getAlipayAccount() != null) {
                tv_alipay_withdraw.setText(bean.getAlipayAccount() + "");
            } else {
                tv_alipay_withdraw.setText("");
            }
        }

    }
    //点击事件
    @OnClick({R.id.tv_qiehuan})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_qiehuan:
                Intent intent1 = new Intent(mContext, ShiftAc.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    @Override
    public void failue() {

    }
}
