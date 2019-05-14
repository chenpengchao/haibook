package com.hyjz.hnovel.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.base.Constant;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.bean.ReadBean;
import com.hyjz.hnovel.manager.SettingManager;
import com.hyjz.hnovel.manager.ThemeManager;
import com.hyjz.hnovel.presenter.ReadPresenter;
import com.hyjz.hnovel.readview.BaseReadView;
import com.hyjz.hnovel.readview.NoAimWidget;
import com.hyjz.hnovel.readview.OnReadStateChangeListener;
import com.hyjz.hnovel.readview.OverlappedWidget;
import com.hyjz.hnovel.readview.PageWidget;
import com.hyjz.hnovel.utils.FileUtils;
import com.hyjz.hnovel.utils.LogUtil;
import com.hyjz.hnovel.utils.LogUtils;
import com.hyjz.hnovel.utils.ScreenUtils;
import com.hyjz.hnovel.utils.SharedPreferencesUtil;
import com.hyjz.hnovel.view.ReadView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

public class ReadAc extends BaseActivity<ReadPresenter> implements ReadView {
    private BaseReadView mPageWidget;
    @Bind(R.id.ivBack)
    ImageView mIvBack;
    @Bind(R.id.tvBookReadReading)
    TextView mTvBookReadReading;
    @Bind(R.id.flReadWidget)
    FrameLayout flReadWidget;
    @Bind(R.id.tvBookReadTocTitle)
    TextView mTvBookReadTocTitle;
    @Bind(R.id.llBookReadTop)
    LinearLayout mLlBookReadTop;
    @Bind(R.id.tvBookReadMode)
    TextView mTvBookReadMode;
    @Bind(R.id.tvBookReadSettings)
    TextView mTvBookReadSettings;
    @Bind(R.id.tvBookReadDownload)
    TextView mTvBookReadDownload;
    @Bind(R.id.tvBookReadToc)
    TextView mTvBookReadToc;
    @Bind(R.id.llBookReadBottom)
    LinearLayout mLlBookReadBottom;
    @Bind(R.id.rlBookReadRoot)
    RelativeLayout mRlBookReadRoot;
    @Bind(R.id.tvDownloadProgress)
    TextView mTvDownloadProgress;

    @Bind(R.id.rlReadAaSet)
    LinearLayout rlReadAaSet;
    @Bind(R.id.ivBrightnessMinus)
    ImageView ivBrightnessMinus;
    @Bind(R.id.seekbarLightness)
    SeekBar seekbarLightness;
    @Bind(R.id.ivBrightnessPlus)
    ImageView ivBrightnessPlus;
    @Bind(R.id.tvFontsizeMinus)
    TextView tvFontsizeMinus;
    @Bind(R.id.seekbarFontSize)
    SeekBar seekbarFontSize;
    @Bind(R.id.tvFontsizePlus)
    TextView tvFontsizePlus;

    @Bind(R.id.rlReadMark)
    LinearLayout rlReadMark;
    @Bind(R.id.tvAddMark)
    TextView tvAddMark;
    @Bind(R.id.lvMark)
    ListView lvMark;

    @Bind(R.id.cbVolume)
    CheckBox cbVolume;
    @Bind(R.id.cbAutoBrightness)
    CheckBox cbAutoBrightness;
    @Bind(R.id.gvTheme)
    GridView gvTheme;

    private View decodeView;


    private IntentFilter intentFilter = new IntentFilter();
    private BookRecommend.BookShelfList recommendBooks;
    private Long bookId;

    public static final String INTENT_BEAN = "recommendBooksBean";
    public static final String INTENT_SD = "isFromSD";
    private boolean isAutoLightness = false; // 记录其他页面是否自动调整亮度
    private boolean isFromSD = false;
    private Integer currentChapter = 1;
    private int curTheme = -1;
    Long chapterId;
    private Receiver receiver = new Receiver();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_read;
    }

    @Override
    public void initView() {
        bookId = getIntent().getLongExtra("bookId", 1l);
        chapterId = getIntent().getLongExtra("chapterId", 1l);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recommendBooks = (BookRecommend.BookShelfList) getIntent().getSerializableExtra(INTENT_BEAN);
//        bookId = recommendBooks.getBookId();
//        isFromSD = getIntent().getBooleanExtra(INTENT_SD, false);

        if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
            String filePath = Uri.decode(getIntent().getDataString().replace("file://", ""));
            String fileName;
            if (filePath.lastIndexOf(".") > filePath.lastIndexOf("/")) {
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
            } else {
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            }

//            CollectionsManager.getInstance().remove(fileName);
            // 转存
            File desc = FileUtils.createWifiTranfesFile(fileName);
            FileUtils.fileChannelCopy(new File(filePath), desc);
            // 建立
            recommendBooks = new BookRecommend.BookShelfList();
            recommendBooks.isFromSD =true;
            recommendBooks.bookId= Long.valueOf(fileName);
            recommendBooks.bookName = fileName;

            isFromSD = true;
        }
//        EventBus.getDefault().register(this);
        showDialog();

//        mTvBookReadTocTitle.setText(recommendBooks.bookName);

//        mTtsPlayer = TTSPlayerUtils.getTTSPlayer();
//        ttsConfig = TTSPlayerUtils.getTtsConfig();

        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);

//        CollectionsManager.getInstance().setRecentReadingTime(bookId);
//        Observable.timer(1000, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        //延迟1秒刷新书架
//                        EventManager.refreshCollectionList();
//                    }
//                });
        initPagerWidget();
        readCurrentChapter();
    }
    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mPageWidget != null) {
                if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                    int level = intent.getIntExtra("level", 0);
                    mPageWidget.setBattery(100 - level);
                } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                    mPageWidget.setTime(sdf.format(new Date()));
                }
            }
        }
    }
    private void initAASet() {
        curTheme = SettingManager.getInstance().getReadTheme();
        ThemeManager.setReaderTheme(curTheme, mRlBookReadRoot);

        seekbarFontSize.setMax(10);
        int fontSizePx = SettingManager.getInstance().getReadFontSize(bookId);
//        int fontSizePx = SettingManager.getInstance().getReadFontSize();
        int progress = (int) ((ScreenUtils.pxToDpInt(fontSizePx) - 12) / 1.7f);
        seekbarFontSize.setProgress(progress);
        seekbarFontSize.setOnSeekBarChangeListener(new SeekBarChangeListener());

        seekbarLightness.setMax(100);
        seekbarLightness.setOnSeekBarChangeListener(new SeekBarChangeListener());
        seekbarLightness.setProgress(ScreenUtils.getScreenBrightness());
        isAutoLightness = ScreenUtils.isAutoBrightness(this);


        this.getContentResolver().registerContentObserver(Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS), true, Brightness);

        if (SettingManager.getInstance().isAutoBrightness()) {
            startAutoLightness();
        } else {
            stopAutoLightness();
        }

        cbVolume.setChecked(SettingManager.getInstance().isVolumeFlipEnable());
        cbVolume.setOnCheckedChangeListener(new ChechBoxChangeListener());

        cbAutoBrightness.setChecked(SettingManager.getInstance().isAutoBrightness());
        cbAutoBrightness.setOnCheckedChangeListener(new ChechBoxChangeListener());

        gvAdapter = new ReadThemeAdapter(this, (themes = ThemeManager.getReaderThemeData(curTheme)), curTheme);
        gvTheme.setAdapter(gvAdapter);
        gvTheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < themes.size() - 1) {
                    changedMode(false, position);
                } else {
                    changedMode(true, position);
                }
            }
        });
    }
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == seekbarFontSize.getId() && fromUser) {
                calcFontSize(progress);
            } else if (seekBar.getId() == seekbarLightness.getId() && fromUser
                    && !SettingManager.getInstance().isAutoBrightness()) { // 非自动调节模式下 才可调整屏幕亮度
                ScreenUtils.saveScreenBrightnessInt100(progress, ReadActivity.this);
                //SettingManager.getInstance().saveReadBrightness(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    private void initPagerWidget() {
//        switch (SharedPreferencesUtil.getInstance().getInt(Constant.FLIP_STYLE, 0)) {
//            case 0:
                mPageWidget = new PageWidget(this, bookId, null, new ReadListener());
//                break;
//            case 1:
//                mPageWidget = new OverlappedWidget(this, bookId.toString(), null, new ReadListener());
//                break;
//            case 2:
//                mPageWidget = new NoAimWidget(this, bookId.toString(), null, new ReadListener());
//        }

        registerReceiver(receiver, intentFilter);
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
            mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.chapter_content_night),
                    ContextCompat.getColor(this, R.color.chapter_title_night));
        }
        flReadWidget.removeAllViews();
        flReadWidget.addView(mPageWidget);
    }
    private class ReadListener implements OnReadStateChangeListener {
        @Override
        public void onChapterChanged(int chapter) {
            LogUtil.i("onChapterChanged:" + chapter);
            currentChapter = chapter;
//            mTocListAdapter.setCurrentChapter(currentChapter);
            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3;
//                    && i <= mChapterList.size();
 i++
                    ) {
                if (i > 0 && i != chapter){
                    mPresenter.getChapterInfer(0, Long.valueOf(i));
                }
            }
        }

        @Override
        public void onPageChanged(int chapter, int page) {
            LogUtil.i("onPageChanged:" + chapter + "-" + page);
        }

        @Override
        public void onLoadChapterFailure(int chapter) {
            LogUtil.i("onLoadChapterFailure:" + chapter);
//            startRead = false;
//            if (CacheManager.getInstance().getChapterFile(bookId, chapter) == null)
//                mPresenter.getChapterRead(mChapterList.get(chapter - 1).link, chapter);
        }

        @Override
        public void onCenterClick() {
//            LogUtil.i("onCenterClick");
//            toggleReadBar();
        }

        @Override
        public void onFlip() {
//            hideReadBar();
        }
    }
//    private synchronized void hideReadBar() {
//        gone(mTvDownloadProgress, mLlBookReadBottom, mLlBookReadTop, rlReadAaSet, rlReadMark);
//        hideStatusBar();
//        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
//    }
//
//    private synchronized void showReadBar() { // 显示工具栏
//        visible(mLlBookReadBottom, mLlBookReadTop);
//        showStatusBar();
//        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//    }
//
//    private synchronized void toggleReadBar() { // 切换工具栏 隐藏/显示 状态
//        if (isVisible(mLlBookReadTop)) {
//            hideReadBar();
//        } else {
//            showReadBar();
//        }
//    }
    /**
     * 获取当前章节。章节文件存在则直接阅读，不存在则请求
     */
    public void readCurrentChapter() {
//        if (CacheManager.getInstance().getChapterFile(bookId, currentChapter) != null) {
//            showChapterRead(null, currentChapter);
//        } else {
            mPresenter.getChapterInfer(0, chapterId);
//        }
    }

    @Override
    protected ReadPresenter createPresenter() {
        return new ReadPresenter(this);
    }


    @Override
    public void showChapterInfo(ReadBean bean) {
//        if (bean != null) {
//            CacheManager.getInstance().saveChapterFile(bookId, chapter, data);
//        }

//        if (!startRead) {
//            startRead = true;
            currentChapter = bean.getChaperId().intValue();
            if (!mPageWidget.isPrepared) {
                mPageWidget.init(curTheme);
            } else {
                mPageWidget.jumpToChapter(currentChapter);
            }
            hideDialog();

    }

    @Override
    public void showChapterList() {

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
