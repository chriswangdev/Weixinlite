package com.weixinlite.android.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by a on 2017/3/30 0030.
 */

public class Gettime {

    private static SimpleDateFormat simpleDateFormat;
    private static Date curDate;

    public static String getNowTime () {
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        curDate =  new Date(System.currentTimeMillis());
        return simpleDateFormat.format(curDate);
    }

    public static long getDiffer(String timeaffter, String timebefore) {
        return (getLongTime(timeaffter) - getLongTime(timebefore));
    }

    public static long getLongTime(String time) {
        long timelong = 0;
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        }
        try {
            Date date = simpleDateFormat.parse(time);
            timelong = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timelong;
    }

}
