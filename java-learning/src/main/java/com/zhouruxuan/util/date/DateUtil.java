package com.zhouruxuan.util.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    @Test
    public void testWeekOfDate() {
        String dateStr = "2023-03-07";
        int weekDay = getWeekOfDate(dateStr, "yyyy-MM-dd");
        int weekDay2 = getWeekOfDateV2(dateStr, "yyyy-MM-dd");

        System.out.println("指定日期是星期" + weekDay);
        System.out.println("指定日期是星期" + weekDay2);

    }

    /**
     * 获取今天是星期几
     *
     * @param date
     * @param pattern
     * @return
     */
    public static int getWeekOfDate(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(String.valueOf(date));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date1);
        return aCalendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取今天是星期几
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getWeekOfDateV2(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(dateStr, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }
}
