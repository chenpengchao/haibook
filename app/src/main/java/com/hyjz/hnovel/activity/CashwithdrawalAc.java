package com.hyjz.hnovel.activity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Service;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.utils.AnimUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class CashwithdrawalAc extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    //余额显示
    @Bind(R.id.ac_cashwithdraw_remainder)
    TextView ac_cashwithdraw_remainder;
    //输入兑换金额
    @Bind(R.id.ac_cashwithdraw_et)
    EditText ac_cashwithdraw_et;
    //确认提现按钮
    @Bind(R.id.ac_cashwithdraw_tv_confirm)
    TextView ac_cashwithdraw_tv_confirm;
    //设置支付宝账号
    @Bind(R.id.ac_cashwithdraw_tv_setalipay)
    TextView ac_cashwithdraw_tv_setalipay;
    //填写支付宝账号的弹窗
    private PopupWindow popupWindow;
    //填写姓名
//    @Bind(R.id.et_pop_name)
    EditText et_pop_name;
    //填写支付宝账号
//    @Bind(R.id.et_alipay)
    EditText et_alipay;
    //再次填写支付宝账号
//    @Bind(R.id.et_alipay_second)
    EditText et_alipay_second;
    //取消按钮
//    @Bind(R.id.pop_tv_cancel)
    TextView pop_tv_cancel;
    //确认按钮
//    @Bind(R.id.pop_tv_confirm)
    TextView pop_tv_confirm;
    //弹出窗口相关
    InputMethodManager imm;
    //弹窗的动画
    private AnimUtil animUtil;
    //弹窗时页面的透明度
    private static final long DURATION = 500;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;

    private float bgAlpha = 1f;
    private boolean bright = false;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_cashwithdrawal;
    }
    @Override
    public void initView() {
        title.setText("提现");
        animUtil = new AnimUtil();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.back,R.id.ac_cashwithdraw_tv_setalipay})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ac_cashwithdraw_tv_setalipay:
                showPopup();
                toggleBright();
                break;

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_tv_cancel:
                popupWindow.dismiss();

                break;
            case R.id.pop_tv_confirm:
                popupWindow.dismiss();
                break;
        }
    }
    /**
     * 添加支付宝的方法
     * 弹出输入框
     */
    /**
     * 添加分组的方法
     */
    /**
     * show comment popupwindow（弹出评论的popupWindow）
     */
    @SuppressLint("WrongConstant")
    private void showPopup() {

        final View view = LayoutInflater.from(mContext).inflate(
                R.layout.pop_alipay_and_name, null);

////        cb_comment = (CheckBox) view.findViewById(R.id.cb_comment);
        et_pop_name = view.findViewById(R.id.et_pop_name);
        et_alipay = view.findViewById(R.id.et_alipay);
        et_alipay_second = view.findViewById(R.id.et_alipay_second);
        pop_tv_cancel = view.findViewById(R.id.pop_tv_cancel);
        pop_tv_confirm = view.findViewById(R.id.pop_tv_confirm);
        pop_tv_cancel.setOnClickListener(this);
        pop_tv_confirm.setOnClickListener(this);
//        tv_fenzu_confirm.setOnClickListener(this);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, false);

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
                    popupWindow.dismiss();
                return false;

            }
        });
//
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
//    popupWindow.setBackgroundDrawable(getResources().getDrawable(
//          R.drawable.popuwindow_white_bg));

        // 设置弹出窗体需要软键盘save
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//    WindowManager.LayoutParams params = getWindow().getAttributes();
//    params.alpha = 0.4f;
//    getWindow().setAttributes(params);
        // 设置popWindow的显示和消失动画
//    popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        popupWindow.update();
        popupInputMethodWindow1();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                toggleBright();
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }
    /**
     * 弹出输入法窗口
     */
    private void popupInputMethodWindow1() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                imm = (InputMethodManager)pop_tv_confirm .getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 400);
    }
    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator( START_ALPHA,END_ALPHA,DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }
    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


}
