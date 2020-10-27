package com.ceiba.adnparquedero.domain.common.util;

import com.google.common.base.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarOperatorUtil {

    public static String parseCalendarToString(Calendar calendar, String format) {
        if (calendar == null || Strings.isNullOrEmpty(format)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }

    public static Calendar parseStringToCalendar(String stringCalendar, String format) {
        if (stringCalendar == null || Strings.isNullOrEmpty(format)) {
            return null;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(stringCalendar));
            return calendar;
        } catch (ParseException pe) {
            return null;
        }
    }

    public static long obtainHourDifference(Calendar start, Calendar end) {
        long different = end.getTime().getTime() - start.getTime().getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        return different / hoursInMilli;
    }
}
