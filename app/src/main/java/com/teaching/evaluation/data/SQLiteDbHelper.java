package com.teaching.evaluation.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teaching.evaluation.bean.Student;
import com.teaching.evaluation.constant.DataSourse;

import java.util.Random;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "database.db";
    //数据库版本号
    public static int DB_VERSION = 1;
    //学生表
    public static final String TAB_STUDENT = "student";
    //老师表
    public static final String TAB_TEACHER = "teacher";
    //用户表
    public static final String TAB_USER = "user";
    //学院表
    public static final String TAB_COLLEGE = "college";
    //课程表
    public static final String TAB_COURSE = "course";
    //评价表
    public static final String TAB_EVALUATE = "evaluate";
    //得分表
    public static final String TAB_SCORE = "score";
    //专业表
    public static final String TAB_MAJOR = "major";

    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createTableStudent(db);
        createTableTeacher(db);
        createTableUser(db);

        initData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //创建学生表
    public void createTableStudent(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_STUDENT +
                "(id integer primary key autoincrement, " +
                "stu_name varchar(60), " +
                "stu_number varchar(60), " +
                "stu_age integer," +
                "stu_sex integer, " +
                "college_name varchar(60), " +
                "marjor_name varchar(60)," +
                "time varchar(60))");
    }

    //创建老师表
    public void createTableTeacher(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_TEACHER +
                "(id integer primary key autoincrement, " +
                "tch_name varchar(60), " +
                "tch_number varchar(60), " +
                "tch_age integer," +
                "tch_sex integer, " +
                "college_name varchar(60), " +
                "time varchar(60))");
    }

    //创建用户表
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_USER +
                "(id integer primary key autoincrement, " +
                "user_name varchar(60), " +
                "user_number varchar(60), " +
                "user_pwd varchar(60))");
    }




    //------------------------------初始化数据--------------------------------------------------//
    public void initData( SQLiteDatabase database){
        //导入学生数据
        for (int i= 0;i<DataSourse.STUDENT.length;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("stu_name",DataSourse.STUDENT[i]);
            contentValues.put("stu_number","1000"+i);
            contentValues.put("stu_age",new Random().nextInt(30));
            contentValues.put("stu_sex",DataSourse.TIME[new Random().nextInt(1)]);
            contentValues.put("college_name",DataSourse.COLLEGE[new Random().nextInt(DataSourse.COLLEGE.length-1)]);
            contentValues.put("marjor_name",DataSourse.MAJOR[new Random().nextInt(DataSourse.MAJOR.length-1)]);
            contentValues.put("time",System.currentTimeMillis());
            database.insert(TAB_STUDENT,null,contentValues);
        }

    }
}
