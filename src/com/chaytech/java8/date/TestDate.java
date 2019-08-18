package com.chaytech.java8.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author chaytech@163.com
 * @date 2019/08/17 20:54
 */
public class TestDate {

    // now() 静态方法，根据当前时间创建对象
    @Test
    public void test1(){
        // LocalDate 当前日期
        LocalDate localDate = LocalDate.now();
        // LocalTime 当前时间
        LocalTime localTime = LocalTime.now();
        // LocalDateTime当前年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
    }

    // of() 静态方法，根据指定日期/时间创建对象
    @Test
    public void test2(){
        LocalDate localDate = LocalDate.of(2019, 8, 17);
        LocalTime localTime = LocalTime.of(22, 22, 56);
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 17, 22, 22, 56);
    }


    // plusDays,plusWeeks,plusMonths,plusYears
    // 向当前LocalDate对象添加几天、几周、几个月、几年
    @Test
    public void test3(){
        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.plusDays(1);
        localDateTime.plusWeeks(1);
        localDateTime.plusMonths(1);
        localDateTime.plusYears(1);
    }

    // minusDays,minusWeeks,minusMonths,minusYears
    // 从当前LocalDate对象减去几天、几周、几个月、几年
    @Test
    public void test4(){

        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.minusDays(1);
        localDateTime.minusWeeks(1);
        localDateTime.minusMonths(1);
        localDateTime.minusYears(1);
    }

    // withDayOfMonth,withDayOfYear,withMonth,withYear
    // 将月份天数、年份天数、月份、年份修改为指定的值并返回新的LocalDate对象
    @Test
    public void test5(){
        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.withDayOfMonth(1);
        localDateTime.withDayOfYear(6);
        localDateTime.withMonth(3);
        localDateTime.withYear(2018);
    }

    // getDayOfMonth  获得月份天数(1-31)
    @Test
    public void test6(){
        LocalDate localDate = LocalDate.now();

        localDate.getDayOfMonth();
    }

    // getDayOfYear  获得年份天数(1-366)
    @Test
    public void test7(){
        LocalDate localDate = LocalDate.now();

        localDate.getDayOfYear();
    }

    // getDayOfWeek  获得星期几(返回一个DayOfWeek枚举值)
    @Test
    public void test8(){
        LocalDate localDate = LocalDate.now();

        localDate.getDayOfWeek();
    }

    // getMonth  获得月份,返回一个Month枚举值
    @Test
    public void test9(){
        LocalDate localDate = LocalDate.now();

        Month month = localDate.getMonth();
    }

    // getMonthValue  获得月份(1-12)
    @Test
    public void test10(){
        LocalDate localDate = LocalDate.now();

        localDate.getMonthValue();
    }


    // getYear  获得年份
    @Test
    public void test11(){
        LocalDate localDate = LocalDate.now();

        localDate.getYear();
    }

    // Duration: 计算两个时间的间隔
    // LocalDate：计算两个日期之间的间隔
    @Test
    public void test12(){
        LocalDateTime d1 = LocalDateTime.of(2018,10,10,12,12,30);
        LocalDateTime now = LocalDateTime.now();

        Duration between = Duration.between(d1, now);
        System.out.println(between.getSeconds());


        LocalDate d2 = LocalDate.of(2018,10,10);
        Period period = Period.between(LocalDate.now(), d2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    // isBefore,isAfter 比较两个LocalDate
    @Test
    public void test13(){
        LocalDate localDate = LocalDate.now();

        localDate.isAfter(LocalDate.now());
        localDate.isBefore(LocalDate.now());
    }

    // isLeapYear  判断是否是闰年
    @Test
    public void test14(){
        LocalDate localDate = LocalDate.now();
        localDate.isLeapYear();
    }

    // Instant 时间戳
    @Test
    public void test15(){
        Instant now = Instant.now(); // 默认获取 UTC 时区
        System.out.println(now);

        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8)); // 偏移量
        System.out.println(offsetDateTime);

        System.out.println(now.toEpochMilli()); // 时间戳
    }

    // 时间矫正器 TemporalAdjuster
    @Test
    public void test16(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);


        LocalDateTime localDateTime = now.withDayOfMonth(25);
        System.out.println(localDateTime);

        // 下一个星期一
        LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(with);


        // 自定义，下一个工作日
        LocalDateTime with1 = now.with((e) -> {
            LocalDateTime localDateTime1 = (LocalDateTime) e;
            DayOfWeek dayOfWeek = localDateTime1.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDateTime1.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return localDateTime1.plusDays(2);
            } else {
                return localDateTime1.plusDays(1);
            }
        });

        System.out.println(with1);
    }


    // 格式化日期时间 DateTimeFormatter
    @Test
    public void test17(){

        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(isoDateTime));

        // 格式化
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = localDateTime.format(yyyyMMdd);
        System.out.println(format);

        // 反格式化
        LocalDate parse = LocalDate.parse(format, yyyyMMdd);
        System.out.println(parse);
    }

    // 时区处理
    @Test
    public void test18(){
        // 支持的时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);

        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }
}
