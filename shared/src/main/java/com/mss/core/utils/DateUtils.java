package com.mss.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static Date now() {
        return new Date();
    }

    public static Date toDate(long millis) {
        return new Date(millis);
    }

    public static long toMillis(@NonNull Date date) {
        return date.toInstant().toEpochMilli();
    }

    public static Date plusMinutes(Date date, int minutes) {
        var instant = date.toInstant().plus(minutes, ChronoUnit.MINUTES);
        return Date.from(instant);
    }
}
