package com.assignment.proddy.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtils {

    public static Date getDateOnly(Date date){
        return new Date(date.getYear(),date.getMonth(),date.getDate());
    }

    public static Date getToday(){
        return Calendar.getInstance().getTime();
    }

    public static Date getTodayForInsertDB(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getToday());
        calendar.add(Calendar.HOUR, +5);
        return calendar.getTime();
    }

    public static Date getYesterdayForDB(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        calendar.add(Calendar.HOUR, +5);
        return calendar.getTime();
    }

    public static Date getDateForMatchDB(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, +5);
        return calendar.getTime();
    }

    public static Date getCurrentMonthStart(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getCurrentMonthEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    public static Date getCurrentWeekStart(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    public static Date getCurrentWeekEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        return calendar.getTime();
    }


    public static List<Date> getLatestWeek(){
        List<Date> latestSevenDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, +5);

        Date today = getDateOnly(calendar.getTime());
        latestSevenDates.add(today);
        for (int i = 1; i <= 6; i++) {
            calendar.add(Calendar.DATE, -1);
            latestSevenDates.add(getDateOnly(calendar.getTime()));
        }
        Collections.reverse(latestSevenDates);

        for (int i = 0; i <= 6; i++) {
            Date date = latestSevenDates.get(i);
            date.setHours(latestSevenDates.get(i).getHours()+5);
            latestSevenDates.set(i,date);
        }

        return latestSevenDates;
    }

    public static List<Date> getMonth(){
        List<Date> monthDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int max = calendar.getTime().getDate();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date today = getDateOnly(calendar.getTime());
        monthDates.add(today);
        for (int i = 1; i <= max; i++) {
            calendar.add(Calendar.DATE, 1);
            monthDates.add(getDateOnly(calendar.getTime()));
        }

        for (int i = 0; i <= max; i++) {
            Date date = monthDates.get(i);
            date.setHours(monthDates.get(i).getHours()+5);
            monthDates.set(i,date);
        }
        return monthDates;
    }

    public static Date utcToSystemTime(Date utcDate) {
        if (utcDate == null) return null;

        long timeInMillis = utcDate.getTime();
        TimeZone tz = TimeZone.getDefault();
        int offset = tz.getOffset(timeInMillis);

        return new Date(timeInMillis + offset); // Adjust for system time zone
    }

    public static int findDateIndex(Date inputDate) {
        Date today = getDateOnly(getToday());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date startDate = getDateOnly(calendar.getTime());

        Log.d("Start Date ", startDate.toString() + " Today  " + today.toString() + " input " + inputDate.toString());

        if (!inputDate.before(startDate) && !inputDate.after(today)) {
            long diffInMillies = inputDate.getTime() - startDate.getTime();
            int position = (int) (diffInMillies / (1000 * 60 * 60 * 24)) + 1; // +1 for 1-based index
            Log.d("Position ", String.valueOf(position));
            return position;
        } else {
            // Return -1 for out-of-range dates
            return -1;
        }
    }


}
