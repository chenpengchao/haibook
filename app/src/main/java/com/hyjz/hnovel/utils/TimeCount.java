package com.hyjz.hnovel.utils;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

/*定义一个倒计时的类*/
public class TimeCount extends CountDownTimer {

	private TextView textView,textView2;
	private Context context;

	public TimeCount(long millisInFuture, long countDownInterval,
                     TextView textView, TextView textView2, Context context) {
		super(millisInFuture, countDownInterval);
		this.textView = textView;
		this.textView2 = textView2;
		this.context=context;
	}

	@Override
	public void onFinish() {
		textView.setClickable(true);
		textView.setText("获取验证码");
//		textView.setBackgroundColor(context.getResources().getColor(R.color.title_bar_background_gray));
	}

	@Override
	public void onTick(long millisUntilFinished) {
		// System.out.println("millisUntilFinished:"+millisUntilFinished);
		// 获取当前时间总秒数
		// first = millisUntilFinished / 1000;

		/**
		 * 转成小时分秒
		 */
		// textView.setText(TimeUtils.getDay(millisUntilFinished)+"天"+TimeUtils.getHour(millisUntilFinished)
		// +"小时"+TimeUtils.getMin(millisUntilFinished)+"分钟"+TimeUtils.getMs(millisUntilFinished)+"秒");
		textView.setText(TimeUtils.getMs(millisUntilFinished)+"秒");
//		textView.setBackgroundColor(context.getResources().getColor(R.color.title_bar_background_gray));
		textView.setClickable(false);
		textView2.setFocusable(true);
		textView2.setFocusableInTouchMode(true);

	}
}
