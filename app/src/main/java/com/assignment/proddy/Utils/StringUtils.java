package com.assignment.proddy.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static List<String> getAllDays(){
        return new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
    }

    public static List<String> getTimeData(){
        List<String> timeData = new ArrayList<>();
        timeData.add("AM");
        timeData.add("PM");
        return timeData;
    }
}
