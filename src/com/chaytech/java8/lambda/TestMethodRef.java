package com.chaytech.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若lambda体中的内容有方法已经实现了，我们可以使用"方法引用"（可以理解为方法引用是lambda表达式的另外一种表现形式）
 *
 * 语法格式：
 *      1、对象::实例方法名
 *      2、类::静态方法名
 *      3、类::实例方法名
 *
 * 注意事项：
 *      1、lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致；
 *      2、如果lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 *
 * 二、构造器引用
 * 语法格式：ClassName::method
 * 注意事项：需要调用的构造器的参数列表需要与函数式接口中的抽象方法的参数列表保持一致
 *
 *
 * @author chaytech@163.com
 * @date 2019/08/17 15:40
 */
public class TestMethodRef {

    // 对象::实例方法名
    @Test
    public void test1(){
        // 使用前
        Consumer<String> consumer1 = e -> System.out.println(e);
        consumer1.accept("aaaaaaa");

        System.out.println("-----------------------");

        // 使用后
        Consumer<String> consumer = System.out::println;
        consumer.accept("bbbbbbb");

        System.out.println("-----------------------");

        User user = new User();
        Supplier<String> supplier2 = user::getName;
        System.out.println(supplier2.get());
    }

    // 类::静态方法名
    @Test
    public void test2(){
        // 使用前
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        System.out.println(comparator.compare(1,2));

        // 使用后
        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(1,1));
    }

    // 类::实例方法名
    @Test
    public void test3(){
        BiPredicate<String,String> predicate = (x,y) -> x.equals(y);
        System.out.println(predicate.test("a","a"));

        BiPredicate<String,String> predicate1 = String::equals;
        System.out.println(predicate1.test("a","b"));

        BiPredicate<String,String> predicate2 = Objects::equals;
        System.out.println(predicate2.test("a","c"));
    }

    // 构造器引用
    @Test
    public void test4(){
        // 使用前
        Supplier<User> supplier = () -> new User();
        System.out.println(supplier.get());

        System.out.println("-----------------------");

        // 使用后
        Supplier<User> supplier1 = User::new;
        System.out.println(supplier1.get());


        System.out.println("-----------------------");

        Function<String, User> function = User::new;
        System.out.println(function.apply("小明"));
    }
}
