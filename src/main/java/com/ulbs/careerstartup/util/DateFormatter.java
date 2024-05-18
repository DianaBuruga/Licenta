package com.ulbs.careerstartup.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static String formatDate(Instant instant) {
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Europe/Bucharest"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formatter);
    }
}
