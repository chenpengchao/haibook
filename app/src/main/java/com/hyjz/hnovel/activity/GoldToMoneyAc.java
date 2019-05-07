package com.hyjz.hnovel.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.GoldtoBookCoinInfoBean;
import com.hyjz.hnovel.bean.GoldtoMoneyInfoBean;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.presenter.GoldToMoneyPresenter;
import com.hyjz.hnovel.utils.ToastUtil;
import com.hyjz.hnovel.view.GoldtoMoneyView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 金币兑换现金页面
 */
public class GoldToMoneyAc extends BaseActivity<GoldToMoneyPresenter> implements GoldtoMoneyView {
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //标题
    @Bind(R.id.title)
    TextView title;
    //显示金币个数
    @Bind(R.id.tv_show_gold_coin)
    TextView tv_show_gold_coin;
    //四个选择按钮
    @Bind(R.id.ll_book_coin_1)
    LinearLayout ll_book_coin_1;
    @Bind(R.id.ll_book_coin_2)
    LinearLayout ll_book_coin_2;
    @Bind(R.id.ll_book_coin_3)
    LinearLayout ll_book_coin_3;
    @Bind(R.id.ll_book_coin_4)
    LinearLayout ll_book_coin_4;

    @Bind(R.id.tv_book_coin_1)
    TextView tv_book_coin_1;
    @Bind(R.id.tv_book_coin_2)
    TextView tv_book_coin_2;
    @Bind(R.id.tv_book_coin_3)
    TextView tv_book_coin_3;
    @Bind(R.id.tv_book_coin_4)
    TextView tv_book_coin_4;

    @Bind(R.id.tv_gold_coin_1)
    TextView tv_gold_coin_1;
    @Bind(R.id.tv_gold_coin_2)
    TextView tv_gold_coin_2;
    @Bind(R.id.tv_gold_coin_3)
    TextView tv_gold_coin_3;
    @Bind(R.id.tv_gold_coin_4)
    TextView tv_gold_coin_4;
    //确认兑换按钮
    @Bind(R.id.tv_gold_tobookcoin_confirm)
    TextView tv_gold_tobookcoin_confirm;
    //要兑换的钱
    Integer cash = 10;
    @Override
    public void initView() {
        title.setText("兑换书币");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter.goldtobookcoininfo(1);
        ll_book_coin_1.setBackgroundResource(R.drawable.circle_solid_red);
        ll_book_coin_2.setBackgroundResource(R.drawable.circle_stroke_eight_red);
        ll_book_coin_3.setBackgroundResource(R.drawable.circle_stroke_eight_red);
        ll_book_coin_4.setBackgroundResource(R.drawable.circle_stroke_eight_red);
        tv_book_coin_1.setTextColor(Color.WHITE);
        tv_book_coin_2.setTextColor(Color.parseColor("#F54337"));
        tv_book_coin_3.setTextColor(Color.parseColor("#F54337"));
        tv_book_coin_4.setTextColor(Color.parseColor("#F54337"));
        tv_gold_coin_1.setTextColor(Color.WHITE);
        tv_gold_coin_2.setTextColor(Color.parseColor("#343434"));
        tv_gold_coin_3.setTextColor(Color.parseColor("#343434"));
        tv_gold_coin_4.setTextColor(Color.parseColor("#343434"));
    }

    @Override
    protected GoldToMoneyPresenter createPresenter() {
        return new GoldToMoneyPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gold_to_money;
    }
    @OnClick({R.id.ll_book_coin_1,R.id.ll_book_coin_2,R.id.ll_book_coin_3,R.id.ll_book_coin_4,
            R.id.tv_gold_tobookcoin_confirm})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.ll_book_coin_1:
                ll_book_coin_1.setBackgroundResource(R.drawable.circle_solid_red);
                ll_book_coin_2.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_3.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_4.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                tv_book_coin_1.setTextColor(Color.WHITE);
                tv_book_coin_2.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_3.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_4.setTextColor(Color.parseColor("#F54337"));
                tv_gold_coin_1.setTextColor(Color.WHITE);
                tv_gold_coin_2.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_3.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_4.setTextColor(Color.parseColor("#343434"));
                cash = 10;
                break;
            case R.id.ll_book_coin_2:
                ll_book_coin_1.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_2.setBackgroundResource(R.drawable.circle_solid_red);
                ll_book_coin_3.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_4.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                tv_book_coin_1.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_2.setTextColor(Color.WHITE);
                tv_book_coin_3.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_4.setTextColor(Color.parseColor("#F54337"));
                tv_gold_coin_1.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_2.setTextColor(Color.WHITE);
                tv_gold_coin_3.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_4.setTextColor(Color.parseColor("#343434"));
                cash = 20;
                break;
            case R.id.ll_book_coin_3:
                ll_book_coin_1.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_2.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_3.setBackgroundResource(R.drawable.circle_solid_red);
                ll_book_coin_4.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                tv_book_coin_1.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_2.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_3.setTextColor(Color.WHITE);
                tv_book_coin_4.setTextColor(Color.parseColor("#F54337"));
                tv_gold_coin_1.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_2.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_3.setTextColor(Color.WHITE);
                tv_gold_coin_4.setTextColor(Color.parseColor("#343434"));
                cash = 50;
                break;
            case R.id.ll_book_coin_4:
                ll_book_coin_1.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_2.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_3.setBackgroundResource(R.drawable.circle_stroke_eight_red);
                ll_book_coin_4.setBackgroundResource(R.drawable.circle_solid_red);
                tv_book_coin_1.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_2.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_3.setTextColor(Color.parseColor("#F54337"));
                tv_book_coin_4.setTextColor(Color.WHITE);
                tv_gold_coin_1.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_2.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_3.setTextColor(Color.parseColor("#343434"));
                tv_gold_coin_4.setTextColor(Color.WHITE);
                cash = 100;
                break;
            case R.id.tv_gold_tobookcoin_confirm:
                mPresenter.goldtomoney(cash);
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
    public void onInfoSuccess(GoldtoMoneyInfoBean bean) {
        tv_show_gold_coin.setText(bean.getGoldCoin()+"金币");
    }

    @Override
    public void onGoldToMoneySuccess() {
        ToastUtil.showShort(mContext,"兑换成功");
        EventBus.getDefault().post(new MessageEvent("goldTobookcoinSuccess"));
        finish();
    }

    @Override
    public void onGoldToMoneyFailue() {
        ToastUtil.showShort(mContext,"金币不足");
    }
}
