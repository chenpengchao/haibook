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
 * 书券界面
 */
public class BookTicketAc extends BaseActivity {
    //返回
    @Bind(R.id.back)
    ImageView back;
    //标题
    @Bind(R.id.title)
    TextView title;
    //充值按钮
    @Bind(R.id.ac_ticket_book_ll_recharge)
    LinearLayout ac_ticket_book_ll_recharge;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_ticket;
    }
    @Override
    public void initView() {
        title.setText("书券");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.back,R.id.ac_ticket_book_ll_recharge})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
                //充值按钮
            case R.id.ac_ticket_book_ll_recharge:
                Intent intent_recharge = new Intent(mContext, RechargeAc.class);
                startActivity(intent_recharge);
                break;
        }
    }


}
