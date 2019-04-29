package com.hyjz.hnovel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.BookTicketConsumBean;
import com.hyjz.hnovel.bean.BookTicketConsumListBean;
import com.hyjz.hnovel.bean.BookTicketRechargeBean;

public class BookTicketConsumCodeAdapter extends BaseQuickAdapter<BookTicketConsumBean,BaseViewHolder> {
    public BookTicketConsumCodeAdapter() {
        super(R.layout.item_book_ticket_recharge_cede, null);
    }


    @Override
    protected void convert(BaseViewHolder helper,BookTicketConsumBean item) {

        if (item.getNote()!= null) {
            helper.setText(R.id.tv_recharge_name, item.getNote() );
        } else {
            helper.setText(R.id.tv_book_ticket, "充值记录");
        }

        if (item.getConsumTime() != null) {
            helper.setText(R.id.tv_recharge_date, item.getConsumTime());
        }

        if (item.getBookCoin() != null) {

            helper.setText(R.id.tv_recharge_money, "-" + item.getBookCoin());
        } else {
            helper.setText(R.id.tv_recharge_money, "-0");
        }




    }
}
