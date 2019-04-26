package com.hyjz.hnovel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.MyBookTicketVidListBean;

public class BookTicketRechargeCodeAdapter extends BaseQuickAdapter<MyBookTicketVidListBean.TicketVidBean,BaseViewHolder> {
    public BookTicketRechargeCodeAdapter() {
        super(R.layout.item_book_ticket_vid, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, MyBookTicketVidListBean.TicketVidBean item) {
        //书券数量
        if (item.getTotalNum() != null) {
            helper.setText(R.id.tv_book_ticket, item.getTotalNum() + "书券");
        } else {
            helper.setText(R.id.tv_book_ticket, "0书券");
        }
        //书券有效期
        if (item.getValidDays() != null) {
            helper.setText(R.id.tv_book_ticket_vid, item.getValidDays() + "天后过期");
        }




    }
}
