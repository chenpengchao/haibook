package com.hyjz.hnovel.utils;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;

public class TimeUtils {
	/**
	 * 天包含的毫秒
	 */
	final static long dayMs = 1000*60*60*24;
	/**
	 * 小时包含的毫秒
	 */
	final static long hourMs = 1000*60*60;
	/**
	 * 分钟包含的毫秒
	 */
	final static long minMs = 1000*60;
	/**
	 * 秒包含的毫秒
	 */
	final static long secMs = 1000;
	
	/**
	 * 获取指定毫秒数 包含的天数
	 * @param ms
	 * @return
	 */
	public static int getDay(long ms){
		return (int) (ms / dayMs);
	}
	
	/**
	 * 获取指定毫秒数 包含的(去掉天数以后的)小时
	 * @param ms
	 * @return
	 */
	public static int getHour(long ms){
		long myTotalMs = ms;
		int day = (int) (myTotalMs / dayMs);
		//如果超过了天，减去天
		myTotalMs -= dayMs*day;
		int hour = (int) (myTotalMs / hourMs);
		return hour;
	}
	
	/**
	 * 获取指定毫秒数 包含的(去掉天、小时以后的)分钟
	 * @param ms
	 * @return
	 */
	public static int getMin(long ms){
		long myTotalMs = ms;
		int day = (int) (myTotalMs / dayMs);
		//如果超过了天，减去天
		myTotalMs -= dayMs*day;
		int hour = (int) (myTotalMs / hourMs);
		myTotalMs -= hourMs*hour;
		int min = (int) (myTotalMs / minMs);
		return min;
	}
	
	/**
	 * 获取指定毫秒数 包含的(去掉天、小时、分钟以后的)秒
	 * @param ms
	 * @return
	 */
	public static int getMs(long ms){
		long myTotalMs = ms;
		int day = (int) (myTotalMs / dayMs);
//		//如果超过了天，减去天
//		myTotalMs -= dayMs*day;
//		//超过了小时减去小时
//		int hour = (int) (myTotalMs / hourMs);
//		myTotalMs -= hourMs*hour;
//		//超过了分钟减去分钟
//		int min = (int) (myTotalMs / minMs);
//		myTotalMs -= minMs*min;
		int sec = (int) (myTotalMs / secMs);
		return sec;
	}

	/**
	 * 得到仿微信日期格式输出
	 *
	 * @param msgTimeMillis
	 * @return
	 */
	public static String getMsgFormatTime(long msgTimeMillis) {

		DateTime nowTime = new DateTime();
//        LogUtils.sf("nowTime = " + nowTime);
		DateTime msgTime = new DateTime(msgTimeMillis);
//        LogUtils.sf("msgTime = " + msgTime);
		int days = Math.abs(Days.daysBetween(msgTime, nowTime).getDays());
//        LogUtils.sf("days = " + days);
		if (days < 1) {
			//早上、下午、晚上 1:40
			return getTime(msgTime);
		} else if (days == 1) {
			//昨天
			return "昨天 " + getTime(msgTime);
		} else if (days <= 7) {
			//星期
			switch (msgTime.getDayOfWeek()) {
				case DateTimeConstants.SUNDAY:
					return "周日 " + getTime(msgTime);
				case DateTimeConstants.MONDAY:
					return "周一 " + getTime(msgTime);
				case DateTimeConstants.TUESDAY:
					return "周二 " + getTime(msgTime);
				case DateTimeConstants.WEDNESDAY:
					return "周三 " + getTime(msgTime);
				case DateTimeConstants.THURSDAY:
					return "周四 " + getTime(msgTime);
				case DateTimeConstants.FRIDAY:
					return "周五 " + getTime(msgTime);
				case DateTimeConstants.SATURDAY:
					return "周六 " + getTime(msgTime);
			}
			return "";
		} else {
			//12月22日
			return msgTime.toString("MM月dd日 " + getTime(msgTime));
		}
	}

	@NonNull
	private static String getTime(DateTime msgTime) {
		int hourOfDay = msgTime.getHourOfDay();
		String when;
		if (hourOfDay >= 18) {//18-24
			when = "晚上";
		} else if (hourOfDay >= 13) {//13-18
			when = "下午";
		} else if (hourOfDay >= 11) {//11-13
			when = "中午";
		} else if (hourOfDay >= 5) {//5-11
			when = "早上";
		} else {//0-5
			when = "凌晨";
		}
		return when + " " + msgTime.toString("hh:mm");
	}

}
