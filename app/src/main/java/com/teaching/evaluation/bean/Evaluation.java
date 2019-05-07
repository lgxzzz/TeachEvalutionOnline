package com.teaching.evaluation.bean;

public class Evaluation {
    String content;
    String tch_name;
    String user_number;
    String course_name;
    String eva_score;

    public String getEva_score() {
        return eva_score;
    }

    public void setEva_score(String eva_score) {
        this.eva_score = eva_score;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String toString(){
        return course_name +" "+tch_name+" "+content;
    }
}
