package com.qingniu.qnble.demo.util;


import android.content.Context;

import com.qingniu.qnble.demo.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author lufoz
 */
public class DateUtils {

    public static Locale locale;

    public static final String FORMAT_SHORT = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "yyyy年MM月dd日 HH:mm";
    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_HOUR_MIN = "HH:mm";
    public static final String FORMAT_HOUR_MIN_SECOND = "HH:mm:ss";
    public static final String[] weekStrs = new String[]{"周日", "周一", "周二",
            "周三", "周四", "周五", "周六"};

    /**
     * 获取当前的locale
     *
     * @return
     */
    private static Locale getCurrentLocale() {
//        String language = SpHelper.getInstance().getString(SystemConst.SP_KEY_LANGUAGE, "", true);
        String language = "zh_CN";
        switch (language) {
            case "en":
                locale = Locale.US;
                break;
            case "zh_CN":
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case "zh_TW":
                locale = Locale.TRADITIONAL_CHINESE;
                break;
            case "ko":
                locale = Locale.KOREA;
                break;
            case "ja":
                locale = Locale.JAPAN;
                break;
            default:
                locale = Locale.US;
                break;
        }
        return locale;
    }

    /**
     * 获取String型系统日期 yyyy-MM-dd
     *
     * @return String格式时间
     */
    public static String currentDate() {
        return dateToString(new Date());
    }

    public static Date getCurrentDate() {
        return stringToDate(currentDate());
    }

    /**
     * 获取String型系统日期 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        return dateToString(new Date(), FORMAT_LONG);
    }

    public static Date getCurrentTime() {
        return stringToDate(dateToString(new Date(), FORMAT_LONG), FORMAT_LONG);
    }


    /**
     * 日期转字符串，HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToHourMinString(Date date) {
        return dateToString(date, FORMAT_HOUR_MIN_SECOND);
    }

    /**
     * 字符串转日期 yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        Locale systime = getCurrentLocale();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SHORT, systime);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String stringToTime(String time, String format) {
        //转换为毫秒
        Date date = new Date(Long.parseLong(time) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String d = sdf.format(date);
        return d;
    }

    /**
     * 获取当天零点的时间
     *
     * @return
     */
    public static Date getCurrentStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 字符转日期，针对任意格式
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date stringToDate(String dateStr, String format) {
        Locale systime = getCurrentLocale();
        SimpleDateFormat sdf = new SimpleDateFormat(format, systime);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转字符串，yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, FORMAT_SHORT);
    }

    /**
     * 日期转字符串，针对任意格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        Locale systime = getCurrentLocale();
        SimpleDateFormat sdf = new SimpleDateFormat(format, systime);
        return sdf.format(date);
    }

    /**
     * 日期系统时间格式(全格式)
     *
     * @param date
     * @return
     */
    public static String dateToDefault(Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();
        return dateFormat.format(date) + " " + timeFormat.format(date);
    }

    /**
     * 日期系统时间格式(精确到天)
     *
     * @param date
     * @return
     */
    public static String dateToDay(Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(date);
    }

    /**
     * 日期系统时间格式(精确到秒)
     *
     * @param date
     * @return
     */
    public static String dateToTime(Date date) {
        DateFormat timeFormat = DateFormat.getTimeInstance();
        return timeFormat.format(date);
    }

    /**
     * 通过本地化对象转换成当地时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateLocaleToString(Date date, String format) {
        String language = "zh_CN";
        Locale locale = new Locale(language);
        //DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
        // Locale systime = getCurrentLocale();
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(date);
    }

    /**
     * 时间戳转字符串
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String getDateTime(String timeStamp, String format) {
        long l = Long.valueOf(timeStamp);
        Date date = new Date(l);
        Locale systime = getCurrentLocale();
        SimpleDateFormat sdf = new SimpleDateFormat(format, systime);
        return sdf.format(date);
    }

    public static String dateToStringForTitle(Date date) {
        Date today = getCurrentDate();
        int differDay = getDaysDiff(today, date);
        switch (differDay) {
            case 0:
                return "今天";
            case 1:
                return "昨天";
            case 2:
                return "前天";
            default:
                return dateToString(date);
        }
    }

    public static int getAge(Date birthday) {
        if (birthday == null) {
            return 0;
        }
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int curYear = cal.get(Calendar.YEAR);
        cal.setTime(birthday);
        int birthYear = cal.get(Calendar.YEAR);
        return curYear - birthYear;
    }

    /**
     * 把日期精确到天
     *
     * @param date
     * @return
     */
    public static Date getShortDateFromLongDate(Date date) {
        return stringToDate(dateToString(date));
    }


    /**
     * 获取两个日期相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDaysDiff(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        diff = diff / 86400000;// 1000*60*60*24;
        return (int) diff;
    }

    @SuppressWarnings("deprecation")
    public static int getMonthsDiff(Date startDate, Date endDate) {
        int months = 0;// 相差月份
        int y1 = startDate.getYear();
        int y2 = endDate.getYear();
        months = endDate.getMonth() - startDate.getMonth() + (y2 - y1) * 12;
        return months;
    }

    public static Date getDate(Date date, int dateType, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(dateType, value);
        return cal.getTime();
    }

    /**
     * 获取与指定日期相差value天的日期,正数往后推，负数往前推
     *
     * @param date
     * @param value
     * @return
     */
    public static Date getDifferDay(Date date, int value) {
        return getDate(date, Calendar.DATE, value);
    }

    /**
     * 获取与指定日期相差value个月的日期,正数往后推，复数往前推
     *
     * @param date
     * @param value
     * @return
     */
    public static Date getDifferMonth(Date date, int value) {
        return getDate(date, Calendar.MONTH, value);
    }

    /**
     * 获取与指定日期相差value年的日期,正数往后推，复数往前推
     *
     * @param date
     * @param value
     * @return
     */
    public static Date getDifferYear(Date date, int value) {
        return getDate(date, Calendar.YEAR, value);
    }

    /**
     * 给定日期返回这个日期所处于的月份(返回这个月的第一天)
     *
     * @param date
     * @return
     */
    public static Date getMonthFromDay(Date date) {
        return stringToDate((date.getYear() + 1900) + "-"
                + (date.getMonth() + 1) + "-01");
    }

    /**
     * 给定日期返回这个日期所处于的季度（返回这个季度的第一天）
     *
     * @param date
     * @return
     */
    public static Date getSeasonFromDay(Date date) {
        String season = "01";
        int m = date.getMonth();
        if (m >= 3 && m < 6) {
            season = "04";
        } else if (m >= 6 && m < 9) {
            season = "07";
        } else if (m >= 9) {
            season = "10";
        }
        return stringToDate((date.getYear() + 1900) + "-" + season + "-01");
    }

    /**
     * 给定日期返回这个日期处于的年份（返回这个年份的第一天）
     *
     * @param date
     * @return
     */
    public static Date getYearFromDay(Date date) {
        return stringToDate((date.getYear() + 1900) + "-01-01");
    }

    /**
     * 把HH:mm转为成日期
     *
     * @param timeString
     * @return
     */
    public static long getClockTime(String timeString) {
        Calendar calendar = Calendar.getInstance();
        long time = 0;
        String[] strs = timeString.split(":");
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strs[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(strs[1]));
        // 将秒和毫秒设置为0
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        time = calendar.getTimeInMillis();
        if (time < System.currentTimeMillis()) {
            calendar.add(Calendar.DATE, 1);
        }
        time = calendar.getTimeInMillis();
        return time;
    }

    /**
     * 获取系统的时间：hh：mm
     *
     * @return
     */
    public static int[] getTimeData() {
        int[] time = new int[2];
        Calendar cal = Calendar.getInstance();
        time[0] = cal.get(Calendar.HOUR_OF_DAY);
        time[1] = cal.get(Calendar.MINUTE);
        return time;
    }

    public static String getTimeDataString() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return hour + ":" + minute;
    }


    public static String getHistoryTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_LONG);
        String[] time = sdf.format(date).split(" ");
        return time[1];

    }

    public static int[] getData(Date date) {
        String[] infos = DateUtils.dateToString(date).split("\\-");
        int[] dates = new int[infos.length];
        for (int i = 0; i < infos.length; i++) {
            dates[i] = Integer.parseInt(infos[i]);
        }

        return dates;
    }

    public static String getBirthdayString(Date birthday, Context context) {
        return birthday == null ? "" : DateUtils.dateToString(birthday,
                context.getResources().getString(R.string.date_format_day));
    }

    public static boolean isStandMaxMonth(Date brithday) {
        int[] brithdays = DateUtils.getData(brithday);
        int[] nows = DateUtils.getData(new Date());

        int month = (nows[0] - brithdays[0]) * 12 + nows[1] - brithdays[1];

        if (nows[2] >= brithdays[2]) {
            month = month + 1;
        } else {
            month = month - 1;
        }
        return month < 36;
    }

    @SuppressWarnings("deprecation")
    public static boolean isSameDay(Date date0, Date date1) {
        return date0.getYear() == date1.getYear()
                && date0.getMonth() == date1.getMonth()
                && date0.getDate() == date1.getDate();
    }

    public static boolean isSameMonth(Date date0, Date date1) {
        return date0.getYear() == date1.getYear()
                && date0.getMonth() == date1.getMonth();
    }

    @SuppressWarnings("deprecation")
    public static String getWeekString(Date date) {
        return weekStrs[date.getDay()];

    }

    public static String getTimestampString(Context context, Date messageDate) {
        Locale curLocale = context.getResources().getConfiguration().locale;
        Date today = new Date();
        String languageCode = curLocale.getLanguage();

        boolean isChinese = languageCode.contains("zh");

        String format;

        if (isSameDay(today, messageDate)) {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(messageDate);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            format = "HH:mm";

            if (hour > 17) {
                if (isChinese) {
                    format = "晚上 hh:mm";
                }

            } else if (hour >= 0 && hour <= 6) {
                if (isChinese) {
                    format = "凌晨 hh:mm";
                }
            } else if (hour > 11 && hour <= 17) {
                if (isChinese) {
                    format = "下午 hh:mm";
                }

            } else {
                if (isChinese) {
                    format = "上午 hh:mm";
                }
            }
        } else if (isSameDay(DateUtils.getDate(today, Calendar.DATE, -1), messageDate)) {
            if (isChinese) {
                format = "昨天 HH:mm";
            } else {
                format = "MM-dd HH:mm";
            }
        } else {
            if (isChinese) {
                format = "M月d日 HH:mm";
            } else {
                format = "MM-dd HH:mm";
            }
        }

        if (isChinese) {
            return new SimpleDateFormat(format, Locale.CHINA).format(messageDate);
        } else {
            return new SimpleDateFormat(format, Locale.US).format(messageDate);
        }
    }

    /**
     * 根据传入的三个参数年月日来转成一个(year-month-day)格式的字符串
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String formatDateString(int year, int month, int day) {
        return year + "-" + NumberUtils.formatIntegerTo2(month) + "-"
                + NumberUtils.formatIntegerTo2(day);
    }

    public static Date getDate(int year, int month, int day) {
        return stringToDate(year + "-" + NumberUtils.formatIntegerTo2(month) + "-"
                + NumberUtils.formatIntegerTo2(day));
    }

    public static String getTimeZoneString() {
        long hour = TimeZone.getDefault().getRawOffset() / (3600000);//1000*60*60
        StringBuilder sb = new StringBuilder();
        if (hour >= 0) {
            sb.append("+");
        } else {
            sb.append("-");
        }
        if (hour > -10 && hour < 10) {
            sb.append(0);
        }
        sb.append(Math.abs(hour));
        sb.append(":00");
        return sb.toString();
    }

    /**
     * 获取时区,正数表示东时区,负数表示西时区
     */
    public static int getTimeZone() {

        TimeZone timeZone = TimeZone.getDefault();
        int millSecond = timeZone.getRawOffset();
        return millSecond / (1000 * 60 * 60);
    }


    /**
     * 对比数据分享的时候，获取两个日期相差的天数、小时数、分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getTimeDiff(Date startDate, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数

        long diff = endDate.getTime() - startDate.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date1 = dateFormat.parse(dateToString(startDate));
            Date date2 = dateFormat.parse(dateToString(endDate));
            day = (date2.getTime() - date1.getTime()) / (24 * 3600 * 1000);//计算差多少天
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // TODO: 2017/11/13 这里返回的差值一直只使用了天的差值，如果需要小时和分钟的差值，需要改变方法
        return (int) day;
       /* long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟


        if (day > 0) {
            return (int) day;
        } else if (hour > 0) {
            return (int) hour;
        } else {
            return (int) min;
        }*/
    }

}
