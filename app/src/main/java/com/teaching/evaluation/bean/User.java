package com.teaching.evaluation.bean;

/**
 * Created by lgx on 2019/4/24.
 */

public class User {
    private String name;
    private String pwd;
    private String number;
    private String role;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString(){
        return name+" "+number+" "+pwd+" "+role;
    }

}
