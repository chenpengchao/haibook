package com.hyjz.hnovel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.MyCommentBean;


public class MyCommentAdapter extends BaseQuickAdapter<MyCommentBean,BaseViewHolder> {

    public MyCommentAdapter() {
        super(R.layout.item_my_comment,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCommentBean item) {



    }
}
