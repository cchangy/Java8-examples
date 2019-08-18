package com.chaytech.java8.optional;

import com.chaytech.java8.lambda.User;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional 容器类
 *
 * @author chaytech@163.com
 * @date 2019/08/17 20:32
 */
public class TestOptional {

    /**
     * Optional 容器类常用方法：
     *
     * Optional.of(T t) : 创建一个Optional 实例
     * Optional.empty() : 创建一个空的Optional 实例
     * Optional.ofNullable(T t):若t 不为null,创建Optional 实例,否则创建空实例
     * isPresent() : 判断是否包含值
     * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
     * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值
     * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * flatMap(Function mapper):与map 类似，要求返回值必须是Optional
     */

    // Optional.of(T t) : 创建一个Optional 实例，不允许传入NULL
    @Test
    public void test1(){
        Optional<User> optional = Optional.of(new User());
        System.out.println(optional.get());
    }

    // Optional.empty() : 创建一个空的Optional 实例
    @Test
    public void test2(){
        Optional<User> empty = Optional.empty();
        System.out.println(empty.get());
    }

    // Optional.ofNullable(T t):若t 不为null,创建Optional 实例,否则创建空实例
    @Test
    public void test3(){
        Optional<User> user = Optional.ofNullable(new User());
        System.out.println(user.get());
    }

    // isPresent() : 判断是否包含值
    @Test
    public void test4(){
        Optional<User> empty = Optional.empty();
        System.out.println(empty.isPresent());
    }

    // orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
    @Test
    public void test5(){
        Optional<User> empty = Optional.empty();
        System.out.println(empty.orElse(new User()));
    }

    // orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值
    @Test
    public void test6(){
        Optional<User> empty = Optional.empty();
        User user = empty.orElseGet(() -> new User());
        System.out.println(user);
    }

    // map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
    @Test
    public void test7(){
        Optional<User> optional = Optional.of(new User("张三"));
        Optional<String> map = optional.map((e) -> e.getName());
        System.out.println(map.get());
    }

    // flatMap(Function mapper):与map 类似，要求返回值必须是Optional
    @Test
    public void test8(){
        Optional<User> optional = Optional.of(new User("张三"));
        Optional<String> flatMap = optional.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(flatMap.get());
    }

}
