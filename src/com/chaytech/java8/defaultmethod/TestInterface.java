package com.chaytech.java8.defaultmethod;

/**
 * 接口默认方法
 *
 * @author chaytech@163.com
 * @date 2019/08/18 12:24
 */
public interface TestInterface {

    default String getName(){
        return "java 8 default method";
    }

    static void show(){
        System.out.println("java 8 static method");
    }
}
