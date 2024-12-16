package com.mss.identity.utils;

import java.util.Date;

public class DateUtils {
    public static Date now() {
        return new Date();
    }

    // current time + ttl
    public static Date toDate(long milliseconds) {
        return new Date(milliseconds);
    }
}
