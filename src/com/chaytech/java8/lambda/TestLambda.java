package com.chaytech.java8.lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * lambda表达式
 *
 * @author chaytech@163.com
 * @date 2019/08/13 20:24
 */
public class TestLambda {

    // 原始匿名内部类
    @Test
    public void test1() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        runnable.run();
    }

    // 从匿名内部类到Lambda 表达式
    @Test
    public void test2() {
        Runnable runnable = () -> System.out.println("hello world");
        runnable.run();
    }

    List<User> userList = new ArrayList<>(Arrays.asList(
            new User("小明", "男", 20),
            new User("小红", "女", 30),
            new User("小张", "男", 40)
    ));

    // 需求：筛选用户
    @Test
    public void test3() {
        List<User> users = filterUserByGender(userList);
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("-------------------------------------");

        List<User> users1 = filterUserByAge(userList);
        for (User user : users1) {
            System.out.println(user);
        }
    }

    /**
     * 筛选出性别为男的用户
     *
     * @param users
     * @return
     */
    public List<User> filterUserByGender(List<User> users) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if ("男".equals(user.getGender())) {
                result.add(user);
            }
        }
        return result;
    }

    /**
     * 筛选年龄大于30的用户
     *
     * @param users
     * @return
     */
    public List<User> filterUserByAge(List<User> users) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getAge() > 30) {
                result.add(user);
            }
        }
        return result;
    }

    // 优化方式一：使用策略设计模式
    @Test
    public void test4() {
        List<User> users = filterUser(userList, new UserFilterByGener());
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("-------------------------------------");

        List<User> users1 = filterUser(userList, new UserFilterByAge());
        for (User user : users1) {
            System.out.println(user);
        }
    }

    public List<User> filterUser(List<User> users, Filter<User> filter) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (filter.test(user)) {
                result.add(user);
            }
        }
        return result;
    }

    // 优化方式二：匿名内部类
    @Test
    public void test5() {
        List<User> users = filterUser(userList, new Filter<User>() {
            @Override
            public boolean test(User user) {
                return user.getAge() > 30;
            }
        });

        for (User user : users) {
            System.out.println(user);
        }
    }


    // 优化方式三：lambda表达式
    @Test
    public void test6() {
        List<User> users = filterUser(userList, User -> User.getAge() > 30);
        // List<User> users = filterUser(userList, (e) -> e.getAge() > 30);

        users.forEach(System.out::println);
    }

    // 优化方式四：Stream API
    @Test
    public void test7() {

        userList.stream().filter(User -> User.getAge() > 30).forEach(System.out::println);

        System.out.println("-------------------------------------");

        List<User> users = userList.stream().filter(User -> "女".equals(User.getGender())).collect(Collectors.toList());
        users.forEach(System.out::println);
    }
}
