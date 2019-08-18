package com.chaytech.java8.date;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author chaytech@163.com
 * @date 2019/08/17 21:03
 */
public class TestSimpleDateFormat {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return format.parse("20190817");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Date>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            futureList.add(pool.submit(callable));
        }

        for (Future<Date> dateFuture : futureList) {
            System.out.println(dateFuture.get());
        }

        pool.shutdown();
    }

    private static final ThreadLocal<DateFormat> formate = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue(){
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String date)throws Exception{
        return formate.get().parse(date);
    }
}
