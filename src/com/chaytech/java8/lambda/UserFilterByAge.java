package com.chaytech.java8.lambda;

/**
 * @author chaytech@163.com
 * @date 2019/08/14 20:39
 */
public class UserFilterByAge implements Filter<User> {
    @Override
    public boolean test(User user) {

        if(user.getAge() > 30){
            return true;
        }
        return false;
    }
}
