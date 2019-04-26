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
import com.hyjz.hnovel.bean.MyBookTicketBean;
import com.hyjz.hnovel.presenter.MyBookTicketPresenter;
import com.hyjz.hnovel.view.MyBookTicketView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 书券界面
 */
public class BookTicketAc extends BaseActivity<MyBookTicketPresenter> implements MyBookTicketView {
    //返回
    @Bind(R.id.back)
    ImageView back;
    //标题
    @Bind(R.id.title)
    TextView title;
    //充值按钮
    @Bind(R.id.ac_ticket_book_ll_recharge)
    LinearLayout ac_ticket_book_ll_recharge;
    //显示书币
    @Bind(R.id.tv_book_coin)
    TextView tv_book_coin;
    //书券显示
    @Bind(R.id.tv_book_ticket)
    TextView tv_book_ticket;
    //书券有效期按钮
    @Bind(R.id.ll_book_ticket_vid)
    LinearLayout ll_book_ticket_vid;
    //充值记录
    @Bind(R.id.ll_book_ticket_recharge_code)
    LinearLayout ll_book_ticket_recharge_code;
    //消费记录
    @Bind(R.id.ll_book_ticket_consum_code)
    LinearLayout ll_book_ticket_consum_code;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_ticket;
    }
    @Override
    public void initView() {
        title.setText("书券");
        mPresenter.getBookTicketInfo();
    }

    @Override
    protected MyBookTicketPresenter createPresenter() {
        return new MyBookTicketPresenter(this);
    }

    @OnClick({R.id.back,R.id.ac_ticket_book_ll_recharge,R.id.ll_book_ticket_vid,
            R.id.ll_book_ticket_recharge_code,R.id.ll_book_ticket_consum_code})
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
                //书券有效期列表
            case R.id.ll_book_ticket_vid:
                Intent intent_bookticket_vid = new Intent(mContext, BookTicketVidAc.class);
                startActivity(intent_bookticket_vid);
                break;
                //书券充值记录
            case R.id.ll_book_ticket_recharge_code:
                Intent intent_book_ticket_recharge_code = new Intent(mContext, BookTicketRechargeCode.class);
                startActivity(intent_book_ticket_recharge_code);
                break;
                //书券消费记录
            case R.id.ll_book_ticket_consum_code:

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

    @Override
    public void onGetTicketDataSuccess(MyBookTicketBean bean) {
        if (bean.getBookCoin() == null) {
            tv_book_coin.setText("0");
        } else {
            tv_book_coin.setText(bean.getBookCoin()+"");
        }
        if (bean.getBookCoupon() != null) {
            tv_book_ticket.setText(bean.getBookCoupon()+"");
        } else {
            tv_book_ticket.setText("0");
        }

    }
}
