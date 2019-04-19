package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class BeVipAc extends BaseActivity {
    //标题
    @Bind(R.id.title)
    TextView title;
    //返回键
    @Bind(R.id.back)
    ImageView back;
    //充值记录
    @Bind(R.id.tv_recharge_code)
    TextView tv_recharge_code;

    @Override
    public void initView() {
        title.setText("充值嗨读VIP");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.back, R.id.tv_recharge_code})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            //充值记录
            case R.id.tv_recharge_code:
                Intent intent = new Intent(mContext, RechargeCodeAc.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_be_vip;
    }
}
