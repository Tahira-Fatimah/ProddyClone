package com.assignment.proddy.Converters;

import androidx.room.TypeConverter;
import java.util.Date;
import java.util.TimeZone;

public class DateTypeConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {

        if (value == null) return null;

        TimeZone tz = TimeZone.getDefault();
        int offset = tz.getOffset(value);

        return new Date(value + offset);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

