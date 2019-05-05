package com.teaching.evaluation.bean;

import com.bin.david.form.annotation.SmartColumn;

/**
 * Created by lgx on 2019/4/22.
 */

public class Course {
    @SmartColumn(id = 1,name ="课程名称")
    public String name;
    @SmartColumn(id = 2,name ="学分")
    public String credit;
    @SmartColumn(id = 3,name ="学时")
    public String hour;
    @SmartColumn(id = 4,name ="绩效点")
    public String ach_point;
    @SmartColumn(id = 5,name ="上课地点")
    public String place;
    @SmartColumn(id = 6,name ="教师")
    public String tch_name;
    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }


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
