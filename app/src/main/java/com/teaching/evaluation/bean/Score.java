package com.teaching.evaluation.bean;

/**
 * Created by lgx on 2019/4/22.
 */

import com.bin.david.form.annotation.SmartColumn;

public class Score {
    @SmartColumn(id = 1,name ="课程名称")
    public String course_name;
    @SmartColumn(id = 2,name ="得分")
    public String score;
    @SmartColumn(id = 3,name ="教师")
    public String tch_name;
    @SmartColumn(id = 4,name ="年份")
    public String year;

    public String stu_number;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStu_number() {
        return stu_number;
    }

    public void setStu_number(String stu_number) {
        this.stu_number = stu_number;
    }

    public String toString(){
        return tch_name+" "+course_name+" "+score+" "+year;
    }
}
