package com.javanorth.spring.springbootrabbitmq.util;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return dateTimeFormatter.format(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.of("Asia/Shanghai")));
    }
}
