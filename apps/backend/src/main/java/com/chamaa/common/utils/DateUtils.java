package com.chamaa.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public static String formatToISO(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(ISO_FORMATTER) : null;
    }

    public static LocalDateTime parseISO(String dateStr) {
        return dateStr != null ? LocalDateTime.parse(dateStr, ISO_FORMATTER) : null;
    }
}
