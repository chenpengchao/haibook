package com.hyjz.hnovel.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.api.ApiConstants;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.utils.GlideUtils;

import java.util.List;

public class BookShelfAdapter extends BaseQuickAdapter<BookRecommend.BookShelfList,BaseViewHolder> {

    public BookShelfAdapter() {
        super(R.layout.item_book_detail_book_shelf_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookRecommend.BookShelfList item) {
        //小说封面
        GlideUtils.load(mContext, item.getBookCover(), helper.getView(R.id.ivBookListCover));
        //小说标题
        helper.setText(R.id.tvBookListTitle, item.getBookName());
        //是否在更新
        helper.setText(R.id.tv_is_lianzai_or_over, item.getLastChapterTitle() + "");
        //作者
        helper.setText(R.id.tvBookAuthor, item.getAuthorName()+ "");
        //更新时间
        helper.setText(R.id.tv_update_time, item.getUpdateTime());

    }
}
