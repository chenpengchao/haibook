package com.hyjz.hnovel.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.MyBookTicketVidListBean;

import java.util.List;

public class BookTicketVidAdapter extends BaseQuickAdapter<MyBookTicketVidListBean.TicketVidBean,BaseViewHolder> {
    public BookTicketVidAdapter() {
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
