package com.teaching.evaluation.bean;

/**
 * Created by lgx on 2019/4/22.
 */

public class Student {
    public String name;
    public String number;
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return name+" "+number+" "+age;
    }
}
