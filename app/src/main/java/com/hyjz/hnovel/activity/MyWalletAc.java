package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的钱包页面
 */
public class MyWalletAc extends BaseActivity {
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //标题
    @Bind(R.id.title)
    TextView title;
    //余额提现
    @Bind(R.id.ac_my_wallet_ll_withdraw)
    LinearLayout ac_my_wallet_ll_withdraw;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_wallet;
    }
    @Override
    public void initView() {
        title.setText("我的钱包");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.ac_my_wallet_ll_withdraw})
    public void onclick(View v) {
        switch (v.getId()) {
            //余额提现按钮
            case R.id.ac_my_wallet_ll_withdraw:
                Intent intent = new Intent(mContext, WithDrawCrashAc.class);
                startActivity(intent);
                break;
        }
    }


}
