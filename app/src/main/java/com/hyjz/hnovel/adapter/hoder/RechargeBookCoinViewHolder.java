package com.hyjz.hnovel.adapter.hoder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.IsCheckBean;
import com.hyjz.hnovel.bean.RechargeBookCoinBean;
import com.hyjz.hnovel.presenter.ReChargeBookCoinPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class RechargeBookCoinViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private View itemView;
    //整个item
    @Bind(R.id.item_recharge_book_coin_ll)
    LinearLayout item_recharge_book_coin_ll;
    //充值金额
    @Bind(R.id.item_recharge_book_coin_money)
    TextView item_recharge_book_coin_money;
    //兑换的书币
    @Bind(R.id.item_recharge_book_coin_tv_coin)
    TextView item_recharge_book_coin_tv_coin;
    //赠送的书券
    @Bind(R.id.item_recharge_book_coin_tv_ticket)
    TextView item_recharge_book_coin_tv_ticket;
    //ishot
    @Bind(R.id.item_rech_book_iv_ishot)
    ImageView item_rech_book_iv_ishot;
    //赠送标志
    @Bind(R.id.item_recharge_book_coin_iv_zengsong)
    ImageView item_recharge_book_coin_iv_zengsong;
    //首充
    @Bind(R.id.item_recharge_book_coin_first)
    ImageView item_recharge_book_coin_first;
    private ReChargeBookCoinPresenter mPresenter;
    private RechargeBookCoinBean.RechageBookCoinItem bean;
    private int position;
    private int selectedPosition = 0; //默认一个参数
    List<IsCheckBean> isCheck = new ArrayList<>();
    IsCheckBean b = null;
//    private Map<Integer, Boolean> isClicks = new HashMap<>();//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色

    public static RechargeBookCoinViewHolder create(Context context, int type) {
        RechargeBookCoinViewHolder imageViewHolder = new RechargeBookCoinViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recharge_book_coin, null), context);
        return imageViewHolder;
    }

    public RechargeBookCoinViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        initView();
    }

    private void initView() {

        item_recharge_book_coin_ll = itemView.findViewById(R.id.item_recharge_book_coin_ll);
        //充值金额

        item_recharge_book_coin_money = itemView.findViewById(R.id.item_recharge_book_coin_money);
        //兑换的书币
        item_recharge_book_coin_tv_coin = itemView.findViewById(R.id.item_recharge_book_coin_tv_coin);
        //赠送的书券
        item_recharge_book_coin_tv_ticket = itemView.findViewById(R.id.item_recharge_book_coin_tv_ticket);
        //hot标志
        item_rech_book_iv_ishot = itemView.findViewById(R.id.item_rech_book_iv_ishot);
        //赠送标志
        item_recharge_book_coin_iv_zengsong = itemView.findViewById(R.id.item_recharge_book_coin_iv_zengsong);
        //首充标之
        item_recharge_book_coin_first = itemView.findViewById(R.id.item_recharge_book_coin_first);
    }


    public void setData(ReChargeBookCoinPresenter mPresenter, RechargeBookCoinBean.RechageBookCoinItem rechargeBookCoinBean, int position) {
        //选择哪一个
        if (mPresenter == null || rechargeBookCoinBean == null) {
            selectedPosition = -5;
            return;
        }
        this.mPresenter = mPresenter;
        this.bean = rechargeBookCoinBean;
        this.position = position;
//            b = new IsCheckBean();
//            b.setaBoolean(false);
//            b.setPos(position);
//        isCheck.add(b);
        if (bean.getIsHot() == 1) {
            item_rech_book_iv_ishot.setVisibility(View.VISIBLE);
            item_rech_book_iv_ishot.setImageResource(R.mipmap.iv_book_coin_recharge_hot);

        } else {
            item_rech_book_iv_ishot.setVisibility(View.GONE);
        }


        item_recharge_book_coin_money.setText(bean.getCashAmount() + "");
        item_recharge_book_coin_tv_coin.setText(bean.getGoodsNum() + "");
        item_recharge_book_coin_tv_ticket.setText(bean.getRewardNum() + "");
//

        if (position == IsCheckBean.getPos()) {
            item_recharge_book_coin_ll.setBackgroundResource(R.drawable.circle_solid_red);
//            item_recharge_book_coin_ll.setBackgroundColor(Color.parseColor("#F54337"));
            item_recharge_book_coin_money.setTextColor(Color.parseColor("#FFFFFF"));
            item_recharge_book_coin_tv_coin.setTextColor(Color.parseColor("#F9CD34"));
            item_recharge_book_coin_tv_ticket.setTextColor(Color.parseColor("#FFFFFF"));
            item_recharge_book_coin_iv_zengsong.setImageResource(R.mipmap.present_selected);
        } else {
            item_recharge_book_coin_ll.setBackgroundResource(R.drawable.circle_recharge_book_coin_eight);
            item_recharge_book_coin_money.setTextColor(Color.parseColor("#333333"));
            item_recharge_book_coin_tv_coin.setTextColor(Color.parseColor("#F9AF34"));
            item_recharge_book_coin_tv_ticket.setTextColor(Color.parseColor("#343434"));
            item_recharge_book_coin_iv_zengsong.setImageResource(R.mipmap.present_ticket);
        }




        item_recharge_book_coin_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mOnItemClickListener.onItemClick(v,position);

            }
        });

//       if (itemView.isSelected())
//        item_recharge_book_coin_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item_recharge_book_coin_ll.setBackgroundColor(Color.parseColor("#F54337"));
//                item_recharge_book_coin_money.setTextColor(Color.parseColor("#FFFFFF"));
//                item_recharge_book_coin_tv_coin.setTextColor(Color.parseColor("#F9CD34"));
//                item_recharge_book_coin_tv_ticket.setTextColor(Color.parseColor("#FFFFFF"));
//            }
//        });

    }
    private OnItemClickListener mOnItemClickListener = null;
    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }
    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
