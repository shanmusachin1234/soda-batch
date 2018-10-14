package com.soda.nse.batch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDate {
    public static void main(String a[]) {
        LocalDateTime localDate = LocalDateTime.parse("2015-09-07T05:00:00");

        localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        System.out.println(localDate);
    }
}
