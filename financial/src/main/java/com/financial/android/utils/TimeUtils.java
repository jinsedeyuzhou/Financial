package com.financial.android.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

/**
 * Created by Administrator on 2017/11/11.
 */

public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis timeInMillis
     * @param dateFormat   dateFormat
     * @return String
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis time
     * @return String
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return long
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return  String
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @param dateFormat    dateFormat
     * @return  String
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {

        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    //获得当天0点时间
    public static int getTimesmorning() {
        Calendar cal = getInstance();
        cal.set(HOUR_OF_DAY, 0);
        cal.set(SECOND, 0);
        cal.set(MINUTE, 0);
        cal.set(MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得当天24点时间
    public static int getTimesnight() {
        Calendar cal = getInstance();
        cal.set(HOUR_OF_DAY, 24);
        cal.set(SECOND, 0);
        cal.set(MINUTE, 0);
        cal.set(MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

}
