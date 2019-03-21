package com.netofthing.utils;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.netofthing.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @创建者 gqf
 * @描述 时间工具
 * <p>
 * 时间工具调用统一入口
 */
public class TimeUtils {

    public static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    /**
     * 显示日期的格式,yyyy-MM-dd HH:mm:ss
     */
    public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 显示日期的格式,yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_HOUR_FORMAT2 = "yyyy/MM/dd HH:mm";
    public static final String DATE_HOUR_FORMAT3 = "MM/dd HH:mm";

    public static String toYearMonthsDay(long data) {
        return com.blankj.utilcode.util.TimeUtils.millis2String(data, DEFAULT_FORMAT);

    }
    public static String toYearMonthsDayHMS(long data) {
        return com.blankj.utilcode.util.TimeUtils.millis2String(data, new SimpleDateFormat(DATE_HOUR_FORMAT2));

    }
    public static DateFormat getInstance(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format;
    }


    /**
     * 日期转换为 剩余时间 (如: 10sec ago ; 12min ago)
     *
     * @return
     * @date 2018/1/15
     * @author hgl
     * @see
     */
    public static String getDateToLeftTime(long time) {
        Date date = new Date(time);
        return getDateToLeftTime(date);
    }

    public static String getDateToLeftTime(Date date) {
        String leftTime = "";
        if (date == null) {
            return leftTime;
        }

        String postfix = CommonUtils.getString(R.string.str_ago);
        String sec = CommonUtils.getString(R.string.str_sec);
        String min = CommonUtils.getString(R.string.str_min);
        String hour = CommonUtils.getString(R.string.str_hrs);
        String yesterday = CommonUtils.getString(R.string.str_yesterday);
        String today = CommonUtils.getString(R.string.str_today);
        long currentMs = new Date().getTime();
        long dateMs = date.getTime();
        long letfSec = (currentMs - dateMs) / 1000;
        //不足一分钟
        if (letfSec < 60) {
            letfSec = letfSec < 1 ? 1 : letfSec;
            leftTime = letfSec + sec + postfix;
            return leftTime;
        }
        //不足一小时
        if (letfSec >= 60 && letfSec < 60 * 60) {

            leftTime = letfSec / 60 + min + postfix;
            return leftTime;
        }
        //昨天
        //昨天 最后时刻
        long yesterdayLastTime = 0L;
        String yesterdayLastTimeStr = getYesterdayDateStr() + " 23:59:59";
        long yesterdayFirstTime = 0L;
        String yesterdayFirstTimeStr = getYesterdayDateStr() + " 00:00:00";
        try {
            yesterdayLastTime = convertSimpleStringToDateTime(yesterdayLastTimeStr).getTime();
            yesterdayFirstTime = convertSimpleStringToDateTime(yesterdayFirstTimeStr).getTime();
        } catch (Exception e) {

        }

        if (dateMs >= yesterdayFirstTime && dateMs <= yesterdayLastTime) {
            leftTime = yesterday + getDateToStringByFormat(date, "HH:mm");
            return leftTime;
        }

        //显示今天 超过10个小时 且不足一天
        if (letfSec > 60 * 60 * 10 && letfSec < 60 * 60 * 24) {
            leftTime = today + getDateToStringByFormat(date, "HH:mm");
            return leftTime;
        }

        //不足一天(不足10个小时)
        if (letfSec >= 60 * 60 && letfSec <= 60 * 60 * 10) {

            leftTime = letfSec / (60 * 60) + hour + postfix;
            return leftTime;
        }
        //大于一天
        if (letfSec >= 60 * 60 * 24) {
            leftTime = com.blankj.utilcode.util.TimeUtils.date2String(date, DEFAULT_FORMAT);// DateUtil.getDateToStringByFormat(date,DATE_HOUR_FORMAT2);
            return leftTime;
        }

        return leftTime;

    }

    /**
     * 自定义日期格式  日期转为字符串
     *
     * @param date  要转换的日期
     * @param fomat 日期格式 如: "yyyy-MM-dd HH:mm:ss"
     * @return
     * @date 2018/1/13
     * @author hgl
     * @see
     */
    public static String getDateToStringByFormat(Date date, String fomat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(fomat);
        return format.format(date);
    }

    /**
     * 时间字符串转换成日期时间的形式
     *
     * @param date yy-MM-dd HH:mm:ss形式的日期字符串
     * @return Date对象
     * @throws ParseException
     */
    public static Date convertSimpleStringToDateTime(String date) throws ParseException {
        return getInstance(TIMEF_FORMAT).parse(date);
    }

    /**
     * 获取昨日日期 返回格式：yyyy-MM-dd
     */
    public static String getYesterdayDateStr() {
        // 获取昨日的日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = cal.getTime();
        return getInstance(DATE_FORMAT).format(yesterday);
    }
}
