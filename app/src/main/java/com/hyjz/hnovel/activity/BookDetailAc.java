package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BookDetailBean;
import com.hyjz.hnovel.presenter.BookDetailPresenter;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.view.BookDetailView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 小说详情页面
 */
public class BookDetailAc extends BaseActivity<BookDetailPresenter> implements BookDetailView {
    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.back)
    ImageView back;
    //书籍封面
    @Bind(R.id.iv_book_cover)
    ImageView iv_book_cover;
    //小说标题
    @Bind(R.id.tv_book_title)
    TextView tv_book_title;
    //小说作者
    @Bind(R.id.tv_author)
    TextView tv_author;
    //小说字数
    @Bind(R.id.tv_book_num)
    TextView tv_book_num;
    //小说是否完结
    @Bind(R.id.tv_is_end)
    TextView tv_is_end;
    //书籍标签
    @Bind(R.id.tv_book_tag)
    TextView tv_book_tag;
    @Bind(R.id.tv_reader)
    TextView tv_reader;


    Long bookId;
    Long chapterId;
    @Override
    public void initView() {
        title.setText("详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bookId = getIntent().getLongExtra("bookId", 0l);
        mPresenter.getbookdetail(bookId);

    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_detail;
    }

    @OnClick({R.id.tv_reader})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_reader:
                Intent intent = new Intent(mContext, ReadAc.class);
                intent.putExtra("bookId", bookId);
                intent.putExtra("chapterId", chapterId);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void success(BookDetailBean bean) {
        GlideUtils.load(mContext, bean.getBookInfo().getBookCover(), iv_book_cover);
        tv_book_title.setText(bean.getBookInfo().getBookName()+"");
        tv_author.setText(bean.getBookInfo().getAuthorName() + "");
        tv_book_num.setText(bean.getBookInfo().getWordNum()+"字");
        if (bean.getBookInfo().getBookStatus() == 1) {
            tv_is_end.setText("");
        }
        chapterId = bean.getBookInfo().getLastChapterId();

//        for (int i = 0; i < bean.getBookInfo().getTags().size(); i++) {
//            if (i<)
//        }


    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
