package com.assignment.proddy.Utils;

import java.util.ArrayList;
import java.util.List;

public class IntegerUtils {
    public static List<Integer> getHourData(){
        List<Integer> hourData = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
            hourData.add(i);
        return hourData;
    }

    public static List<Integer> getMinData(){
        List<Integer> minData = new ArrayList<>();
        for (int i = 0; i <= 60; i++)
            minData.add(i);

        return minData;
    }
}
