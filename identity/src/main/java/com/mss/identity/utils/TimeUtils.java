package com.mss.identity.utils;

import java.time.Instant;

public class TimeUtils {

    public static long currentTimeMillis() {
        return Instant.now().toEpochMilli();
    }
}
