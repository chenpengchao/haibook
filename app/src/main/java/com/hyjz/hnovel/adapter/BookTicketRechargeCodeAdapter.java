package com.hyjz.hnovel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.BookTicketRechargeBean;
import com.hyjz.hnovel.bean.MyBookTicketVidListBean;

public class BookTicketRechargeCodeAdapter extends BaseQuickAdapter<BookTicketRechargeBean,BaseViewHolder> {
    public BookTicketRechargeCodeAdapter() {
        super(R.layout.item_book_ticket_recharge_cede, null);
    }


    @Override
    protected void convert(BaseViewHolder helper,BookTicketRechargeBean item) {
        //书券数量
        if (item.getNote()!= null) {
            helper.setText(R.id.tv_recharge_name, item.getNote() );
        } else {
            helper.setText(R.id.tv_book_ticket, "充值记录");
        }
        //书券有效期
        if (item.getPayTime() != null) {
            helper.setText(R.id.tv_recharge_date, item.getPayTime());
        }
        //充值金额
        if (item.getGoodsNum() != null) {

            helper.setText(R.id.tv_recharge_money, "+" + item.getGoodsNum());
        } else {
            helper.setText(R.id.tv_recharge_money, "+0");
        }




    }
}
