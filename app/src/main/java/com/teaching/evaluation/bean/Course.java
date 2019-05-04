package com.teaching.evaluation.bean;

/**
 * Created by lgx on 2019/4/22.
 */

public class Course {
    public String name;
    public String credit;
    public String hour;
    public String ach_point;
    public String place;

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String tch_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAch_point() {
        return ach_point;
    }

    public void setAch_point(String ach_point) {
        this.ach_point = ach_point;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String toString(){
        return name +" "+tch_name;
    }
}
