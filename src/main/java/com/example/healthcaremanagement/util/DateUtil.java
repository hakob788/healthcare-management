package com.example.healthcaremanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String dateFormat = "dd/MM/yyyy";

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }
}