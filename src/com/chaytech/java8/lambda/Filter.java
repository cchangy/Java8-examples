package com.chaytech.java8.lambda;

/**
 * @author chaytech@163.com
 * @date 2019/08/14 20:33
 */
public interface Filter<T> {
    boolean test(T t);
}
