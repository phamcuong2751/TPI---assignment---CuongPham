package com.java.tpi.cuongpham.utils;

import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.exception.GlobalException;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String convertLocalTime(LocalDateTime dateTime) {
        try {
            if (dateTime == null) {
                dateTime = LocalDateTime.of(1970, 1, 1, 0, 0);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TIME);
            return dateTime.format(formatter); // Format LocalDateTime
        } catch (Exception e) {
            throw new GlobalException(HttpStatusCode.INTERNAL_SERVER_ERROR.code, e.getMessage());
        }

    }
}
