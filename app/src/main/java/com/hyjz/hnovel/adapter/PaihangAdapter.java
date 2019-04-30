package com.hyjz.hnovel.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.MyWalletPaihangBean;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.weight.CircleImageView;

public class PaihangAdapter extends BaseQuickAdapter<MyWalletPaihangBean,BaseViewHolder> {

    public PaihangAdapter() {
        super(R.layout.item_my_wallet_paihang, null);
    }


    @Override
    protected void convert(BaseViewHolder helper,MyWalletPaihangBean item) {
        Integer position = helper.getAdapterPosition();

        ImageView iv_top = helper.getView(R.id.iv_top);
        TextView tv_top = helper.getView(R.id.tv_top);
        if (position == 0) {
            iv_top.setVisibility(View.VISIBLE);
           tv_top.setVisibility(View.GONE);
            helper.setImageResource(R.id.iv_top, R.mipmap.medal_gold);
        }else  if (position == 1) {
           iv_top.setVisibility(View.VISIBLE);
           tv_top.setVisibility(View.GONE);
            helper.setImageResource(R.id.iv_top, R.mipmap.medal_silver);
        } else if (position == 2) {
          iv_top.setVisibility(View.VISIBLE);
           tv_top.setVisibility(View.GONE);
            helper.setImageResource(R.id.iv_top, R.mipmap.medal_bronze);

        } else {
          iv_top.setVisibility(View.GONE);
          tv_top.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_top, position + 1 + "");
        }
        CircleImageView iv = helper.getView(R.id.cv_top_head);
        if (item.getHeadImg()!= null) {

            GlideUtils.loadRound(mContext,item.getHeadImg(),iv);
        } else {

        }

        if (item.getNickName() != null) {
            helper.setText(R.id.tv_top_name, item.getNickName()+"");
        }

        if (item.getProfit() != null) {
            helper.setText(R.id.tv_top_money,  item.getProfit()+"元");
        } else {
            helper.setText(R.id.tv_top_money, "0元");
        }




    }
}
