package com.timechecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bhavesh on 08-07-2021.
 */
public class DateUtils {
    /**
     * Give time like 1
     * Return time 01:00 in String format
     */
    public static String createTimeFormatString_HH_mm(int hours24) {
        return ((hours24 >= 0 && hours24 < 10) ? "0" + hours24 : "" + hours24) + ":00";
    }

    /**
     * @param pattern E.g. dd/MM/yyyy hh:MM:ss
     * @return E.g. 31/12/2017 12:59:10
     */
    public static String getCurrentDateTime(String pattern) {
        return new SimpleDateFormat(pattern, Locale.US).format(new Date());
    }

    /**
     * @param date         E.g. 31/05/1991 18:30:00
     * @param inputPattern E.g. dd/MM/yyyy HH:mm:ss
     * @return Calendar object of given date
     */
    public static Calendar getCalendarObjectFromStringDate(String date, String inputPattern) {
        try {
            Date date1 = new Date();
            if (!date.isEmpty()) {
                date1 = new SimpleDateFormat(inputPattern, Locale.US).parse(date);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}