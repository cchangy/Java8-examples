package com.chaytech.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、lambda表达式的基础语法：Java8中引入了一个新的操作符 '->' 该操作符称为箭头操作符或lambda操作符
 * 箭头操作符将lambda表达式拆分成两部分：
 *      左侧：lambda表达式的参数列表
 *      右侧：lambda表达式中所需要执行的功能，即lambda体
 *
 * 二、语法格式：
 *      1、无参数，无返回值：() -> System.out.println("Hello Lambda");
 *      2、一个参数，无返回值：(e) -> System.out.println(e);
 *      3、一个参数，小括号可以省略不写：e -> System.out.println(e);
 *      4、多个参数，有返回值，lambda体中有多条语句：
 *          Comparator<Integer> comparable = (x, y) -> {
 *             System.out.println("多个参数，有返回值，lambda体中有多条语句");
 *             return Integer.compare(x,y);
 *          };
 *      5、lambda体中有一条语句，大括号和return可以不写：Comparator<Integer> comparable = (x, y) -> Integer.compare(x,y);
 *
 * 三：类型推断：lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器会通过上下文推断出数据类型，通常称之为"类型推断"
 *
 *
 * @author chaytech@163.com
 * @date 2019/08/17 09:57
 */
public class TestLambdaGrammar {

    // 语法格式一：无参数，无返回值：() -> System.out.println("Hello Lambda");
    @Test
    public void test1(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        runnable.run();

        System.out.println("--------------------------------");

        Runnable runnable1 = () -> System.out.println("Hello Lambda");
        runnable1.run();
    }

    // 语法格式二：有一个参数，无返回值：(e) -> System.out.println(e);
    @Test
    public void test2(){
        Consumer<String> consumer = (e) -> System.out.println(e);
        consumer.accept("语法格式二：有一个参数，无返回值");
    }

    // 语法格式三：有一个参数时，小括号可以省略不写：e -> System.out.println(e);
    @Test
    public void test3(){
        Consumer<String> consumer = e -> System.out.println(e);
        consumer.accept("语法格式二：有一个参数时，小括号可以省略不写");
    }

    // 语法格式四：多个参数，有返回值，lambda体中有多条语句
    @Test
    public void test4(){
        Comparator<Integer> comparable = (x, y) -> {
            System.out.println("多个参数，有返回值，lambda体中有多条语句");
            return Integer.compare(x,y);
        };
    }

    // 语法格式五：多个参数，有返回值，lambda体中有一条语句，打括号和return可以不写
    @Test
    public void test5(){
        Comparator<Integer> comparable = (x, y) -> Integer.compare(x,y);
    }
}
