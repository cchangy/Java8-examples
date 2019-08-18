package com.chaytech.java8.lambda;

/**
 * @author chaytech@163.com
 * @date 2019/08/14 20:38
 */
public class UserFilterByGener implements Filter<User> {

    @Override
    public boolean test(User user) {
        if("男".equals(user.getGender())){
            return true;
        }
        return false;
    }
}
