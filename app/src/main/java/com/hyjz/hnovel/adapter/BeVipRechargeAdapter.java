package com.hyjz.hnovel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hyjz.hnovel.adapter.hoder.BeVipRechargeViewHolder;
import com.hyjz.hnovel.adapter.hoder.RechargeBookCoinViewHolder;
import com.hyjz.hnovel.bean.IsCheckBean;
import com.hyjz.hnovel.bean.IsCheckBeanBeVip;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.ReadVipBean;
import com.hyjz.hnovel.bean.RechargeBookCoinBean;
import com.hyjz.hnovel.presenter.BeVipPresenter;
import com.hyjz.hnovel.presenter.ReChargeBookCoinPresenter;

import org.greenrobot.eventbus.EventBus;

public class BeVipRechargeAdapter extends BaseReclyerViewAdapter<ReadVipBean.GoodsListBean> {
    private Context mContext;
    private BeVipPresenter mPresenter;
    public BeVipRechargeAdapter(Context context, BeVipPresenter mPresenter) {
        super(context);
        this.mContext = context;
        this.mPresenter = mPresenter;
    }

////    @Override
//    public int getItemViewType(int position) {
//        return getData().get(position).getVipDiscount();
//    }


//    @Override
//    public int getItemCount() {
//        return getItemCount();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BeVipRechargeViewHolder.create(mContext, viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(holder instanceof BeVipRechargeViewHolder) {
            ((BeVipRechargeViewHolder) holder).setData(mPresenter, get(position), position);
            ((BeVipRechargeViewHolder) holder).setOnItemClickLitener(new BeVipRechargeViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View view,  int pos) {
//                   view.setSelected(true);
//                   notifyItemChanged(pos);
                    IsCheckBeanBeVip.setPos(pos);
                    listener.send(pos);

                    EventBus.getDefault().post(new MessageEvent("coin_shift"));
                   notifyDataSetChanged();
//                   holder.itemView.setSelected(true);
//                   notifyDataSetChanged();
                }
            });

        }

    }

    OnSendDataListener listener;
    public void setData(OnSendDataListener listener) {
        this.listener = listener;
    }
    public  interface OnSendDataListener {
        void send(Integer pos);
    }
}
