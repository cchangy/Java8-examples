package com.chaytech.java8.defaultmethod;

/**
 * @author chaytech@163.com
 * @date 2019/08/18 12:27
 */
public interface GetNameInterface {
    default String getName(){
        return "get name default method";
    }
}
