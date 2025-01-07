package com.mss.identity.utils;

import java.util.Date;

public class DateUtils {

    private DateUtils() {}

    public static Date now() {
        return new Date();
    }

    public static Date toDate(long milliseconds) {
        return new Date(milliseconds);
    }
}
