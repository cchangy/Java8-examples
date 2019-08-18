package com.chaytech.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface修饰，可以检查是否是函数式接口
 *
 * Java8四大内置核心函数式接口：
 *
 * Consumer<T>：消费型接口
 *      void accept(T t);
 *
 * Supplier<t>：供给型接口
 *      T get();
 *
 * Function<T, R>：函数型接口
 *      R apply(T t);
 *
 * Predicate<T>：断言型接口
 *      boolean test(T t);
 *
 *
 *
 * @author chaytech@163.com
 * @date 2019/08/17 10:58
 */
public class TestFunctionInterface {

    // 自定义函数式接口
    @Test
    public void test1() {
        Integer result = operation(100, (x) -> x * x);
        System.out.println("自定义函数式接口：" + result);

        Integer operation = operation(100, (x) -> x * 10 + 5);
        System.out.println("自定义函数式接口：" + operation);
    }

    // 运算方法
    private Integer operation(Integer integer, Calculation calculation) {
        return calculation.cal(integer);
    }

    /**
     * Consumer<T>：消费型接口
     *
     * 定义一个Lambda表达式，消费(输入)一个T类型的值，无返回值
     */
    @Test
    public void test2() {

        // 调用accept方法执行lambda体
        Consumer<Double> consumer = (e) -> System.out.println("消费" + e + "元");
        consumer.accept(100d);


        // 调用原consumer 再调用andThen方法中指定的Consumer
        Consumer<Double> consumer1 = (e) -> System.out.println("消费" + (e + 10) + "元");
        consumer.andThen(consumer1).accept(110d);
    }

    /**
     * Supplier<t>：供给型接口
     *
     * 定义一个Lambda表达式，无输入，生产(返回)一个T类型的值
     */
    @Test
    public void test3(){

        Supplier<Integer> supplier = () -> 10;
        System.out.println(supplier.get());
    }

    /**
     * Function<T, R>：函数型接口
     * 定义一个Lambda表达式，输入一个T类型的参数，返回一个R类型的值
     */
    @Test
    public void test4(){
        // 传入一个T类型参数，返回一个R类型结果
        Function<Integer, Double> function = (x) -> x + 1.1;
        System.out.println(function.apply(10));
        System.out.println("-------------------------------");


        Function<Integer, Integer> function1 = (x) -> x + 1;

        //compose 先执行compose中的函数，再把返回值当作参数执行原函数
        Double composeResult = function.compose(function1).apply(20);
        System.out.println(composeResult); //打印：11.1 22.1

        System.out.println("-------------------------------");

        Function<Double, Double> function2 = (x) -> x + 1d;
        //andThen 先执行原函数，再把原函数的返回值当作参数执行andThen中的函数
        Double andThenResult = function.andThen(function2).apply(2);
        System.out.println(andThenResult); //打印： 4.1

    }

    /**
     * Predicate<T>：断言型接口
     * 定义一个Lambda表达式，输入一个T类型的参数，返回一个true/false
     */
    @Test
    public void test5() {
        // test 测试输入的参数是否满足定义的lambda表达式
        Predicate<Integer> predicate1 = (e) -> e > 10;
        System.out.println(predicate1.test(20));
        System.out.println(predicate1.test(10));

        System.out.println("-------------------------------");

        // and 原Predicate接口和and方法中指定的Predicate接口要同时为true，结果才为true，同逻辑运算符&&一样
        Predicate<Integer> predicate2 = (e) -> e > 5;
        System.out.println(predicate1.and(predicate2).test(9));
        System.out.println(predicate1.and(predicate2).test(20));

        System.out.println("-------------------------------");

        //or 原Predicate接口和or方法中指定的Predicate接口有一个为true，结果就为true，同逻辑运算符||一样
        System.out.println(predicate1.or(predicate2).test(9));
        System.out.println(predicate1.or(predicate2).test(3));

        System.out.println("-------------------------------");

        //negate 对结果取反再输出
        System.out.println(predicate1.negate().test(3)); // 打印：false
    }

}
