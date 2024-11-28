package com.assignment.proddy.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        return calendar.getTime();
    }

    public static Date getYesterdayForDB(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
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

        Date today = getDateOnly(calendar.getTime());
        latestSevenDates.add(today);
        for (int i = 1; i <= 6; i++) {
            calendar.add(Calendar.DATE, -1);
            latestSevenDates.add(getDateOnly(calendar.getTime()));
        }
        Collections.reverse(latestSevenDates);

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
        return monthDates;
    }

    public static String getDayOfWeek(Date date){
        switch (date.getDay()){
            case 0: return "Sunday";
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
        }
        return "Sunday";
    }


    public static int findDateIndex(Date inputDate) {
        Date today = getDateOnly(getToday());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date startDate = getDateOnly(calendar.getTime());


        if (!inputDate.before(startDate) && !inputDate.after(today)) {
            long diffInMillies = inputDate.getTime() - startDate.getTime();
            int position = (int) (diffInMillies / (1000 * 60 * 60 * 24)) + 1;
            return position;
        } else {
            return -1;
        }
    }

    public static Date getDateOnlyForToday(){
        return getDateOnly(getToday());
    }


}
