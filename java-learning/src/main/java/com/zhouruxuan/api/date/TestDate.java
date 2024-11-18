package com.zhouruxuan.api.date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TestDate {

    @org.junit.Test
    public void toEpochMilli_test(){
        System.out.println(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(LocalDate.now().minusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(LocalDate.now().plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    /**
     * 日期转换
     */
    @org.junit.Test
    public void convertTime_test() {
        // 解析时间字符串
        LocalTime localTime = null;

        try {
            localTime = LocalTime.parse("8:00");
        } catch (Exception e) {
        }
        Assert.assertNull(localTime);

        localTime = LocalTime.parse("08:00");
        Assert.assertNotNull(localTime);

        // 格式化为时分格式的时间字符串
        String timeWithoutSeconds;
        timeWithoutSeconds = LocalTime.parse("1:30:00", DateTimeFormatter.ofPattern("H:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm"));
        Assert.assertEquals("01:30", timeWithoutSeconds);
    }

    @org.junit.Test
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

    /**
     * YY和yy的区别
     * <p>
     * 用YYYY格式化代码
     * 12/29/2019 将会格式化到2019年 这一周还属于2019年
     * 12/30/2019 将会格式化到2020年 这一周已经属于2020年
     */
    @Test
    public void testYYyy() {
        Calendar calendar = Calendar.getInstance();
        // 2019-12-31
        calendar.set(2019, Calendar.DECEMBER, 31);
        Date strDate1 = calendar.getTime();
        // 2020-01-01
        calendar.set(2020, Calendar.JANUARY, 1);
        Date strDate2 = calendar.getTime();
        // 大写 YYYY
        SimpleDateFormat formatYYYY = new SimpleDateFormat("YYYY/MM/dd");
        System.out.println("2019-12-31 转 YYYY/MM/dd 格式: " + formatYYYY.format(strDate1));
        System.out.println("2020-01-01 转 YYYY/MM/dd 格式: " + formatYYYY.format(strDate2));
        // 小写 YYYY
        SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("2019-12-31 转 yyyy/MM/dd 格式: " + formatyyyy.format(strDate1));
        System.out.println("2020-01-01 转 yyyy/MM/dd 格式: " + formatyyyy.format(strDate2));
    }

    /**
     * 大写的DD代表的是处于这一年中那一天，不是处于这个月的那一天。
     */
    @Test
    public void testDD() {
        tryit("2020-03-17", "YYYY/MM/DD");
        tryit("2020-03-18", "YYYY/MM/DD");
    }

    private void tryit(String dateStr, String pat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取年、月、日
        int Y = calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH) + 1;
        int D = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat fmt = new SimpleDateFormat(pat);
        String str = fmt.format(date);
        System.out.printf("%s 转 %s 格式: %s\n", dateStr, pat, str);
    }

}
