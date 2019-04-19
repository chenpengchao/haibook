package com.hyjz.hnovel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyjz.hnovel.adapter.hoder.RechargeBookCoinViewHolder;
import com.hyjz.hnovel.bean.IsCheckBean;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.RechargeBookCoinBean;
import com.hyjz.hnovel.presenter.ReChargeBookCoinPresenter;
import com.hyjz.hnovel.view.RechargeBookCoinView;

import org.greenrobot.eventbus.EventBus;

public class RechargeBookCoinAdapter extends BaseReclyerViewAdapter<RechargeBookCoinBean.RechageBookCoinItem> {
    private Context mContext;
    private ReChargeBookCoinPresenter mPresenter;
    public RechargeBookCoinAdapter(Context context, ReChargeBookCoinPresenter mPresenter) {
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
        return RechargeBookCoinViewHolder.create(mContext, viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(holder instanceof RechargeBookCoinViewHolder) {
            ((RechargeBookCoinViewHolder) holder).setData(mPresenter, get(position), position);
            ((RechargeBookCoinViewHolder) holder).setOnItemClickLitener(new RechargeBookCoinViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View view,  int pos) {
//                   view.setSelected(true);
//                   notifyItemChanged(pos);
                    IsCheckBean.setPos(pos);
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
