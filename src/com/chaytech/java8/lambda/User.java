package com.chaytech.java8.lambda;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author chaytech@163.com
 * @date 2019/08/14 20:12
 */
public class User{
    private String name; // 名称
    private String gender; // 性别
    private int age; // 年龄

    public User(){

    }

    public User(String name){
        this.name = name;
    }

    public User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age);
    }
}
