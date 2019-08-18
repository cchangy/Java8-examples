package com.chaytech.java8.defaultmethod;

/**
 * @author chaytech@163.com
 * @date 2019/08/18 12:28
 */
public class Test implements TestInterface,GetNameInterface {
    @Override
    public String getName() {
        return GetNameInterface.super.getName();
    }
}
