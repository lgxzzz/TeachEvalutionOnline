package com.teaching.evaluation.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teaching.evaluation.bean.Student;
import com.teaching.evaluation.constant.DataSourse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "database.db";
    //数据库版本号
    public static int DB_VERSION = 40;
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
    //日志表
    public static final String TAB_DIARY = "diary";

    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createTableStudent(db);
        createTableTeacher(db);
        createTableUser(db);
        createTableCollege(db);
        createTableCourse(db);
        createTableEvaluate(db);
        createTableScore(db);
        createTableDiary(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TAB_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_COLLEGE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_COURSE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_EVALUATE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_SCORE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_DIARY);
//        db.execSQL("DROP TABLE IF EXISTS "+TAB_MAJOR);
        onCreate(db);
    }

    //创建学生表
    public void createTableStudent(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_STUDENT +
                "(id integer primary key autoincrement, " +
                "stu_name varchar(60), " +
                "stu_number varchar(60), " +
                "age integer," +
                "sex integer, " +
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
                "age varchar(60)," +
                "sex varchar(60), " +
                "college_name varchar(60), " +
                "time varchar(60))");
    }

    //创建用户表
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_USER +
                "(id integer primary key autoincrement, " +
                "user_name varchar(60), " +
                "user_number varchar(60), " +
                "user_role varchar(60), " +
                "user_email varchar(60), " +
                "age integer, " +
                "sex integer, " +
                "college_name varchar(60), " +
                "user_pwd varchar(60))");
    }

    //创建学院表
    public void createTableCollege(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_COLLEGE +
                "(id integer primary key autoincrement, " +
                "college_name varchar(60))");
    }

    //创建课程表
    public void createTableCourse(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_COURSE +
                "(id integer primary key autoincrement, " +
                "course_name varchar(60), " +
                "course_credit varchar(60), " +
                "course_hour varchar(60), " +
                "course_time varchar(60), " +
                "ach_point varchar(60), " +
                "place varchar(60), " +
                "college_name varchar(60),"+
                "tch_name varchar(60))");
    }

    //创建评分表
    public void createTableEvaluate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_EVALUATE +
                "(id integer primary key autoincrement, " +
                "user_number varchar(60), " +
                "tch_name varchar(60), " +
                "course_name varchar(60), " +
                "eva_score varchar(60), " +
                "eva_time varchar(60), " +
                "content varchar(60))");
    }

    //创建得分表
    public void createTableScore(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_SCORE +
                "(id integer primary key autoincrement, " +
                "tch_name varchar(60), " +
                "course_name varchar(60), " +
                "score varchar(60), " +
                "stu_number varchar(60), " +
                "year varchar(60))");
    }

    //创建日志表
    public void createTableDiary(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_DIARY +
                "(id integer primary key autoincrement, " +
                "user_number varchar(60), " +
                "time varchar(60), " +
                "title varchar(60), " +
                "content varchar(60))");
    }
    //------------------------------初始化数据--------------------------------------------------//
    public void initData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Map<String,String> teachers_map = new HashMap<>();
        //导入老师数据
        for (int i=0;i<DataSourse.TEACHER.length;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("tch_name",DataSourse.TEACHER[i]);
            contentValues.put("tch_number","2000"+i);
            contentValues.put("age",new Random().nextInt(40));
            contentValues.put("sex",new Random().nextInt(1));
            String college_name =
                    DataSourse.COLLEGE[new Random().nextInt(DataSourse.COLLEGE.length-1)];
            contentValues.put("college_name",college_name);
            contentValues.put("time",System.currentTimeMillis());
            db.insert(TAB_TEACHER,null,contentValues);
            teachers_map.put(DataSourse.TEACHER[i],college_name);
            //确保每个学生数据都有导师
            if (i<DataSourse.STUDENT.length){
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("stu_name",DataSourse.STUDENT[i]);
                contentValues1.put("stu_number","1000"+i);
                contentValues1.put("age",new Random().nextInt(30));
                contentValues1.put("sex",new Random().nextInt(1));
                contentValues1.put("college_name",college_name);
                contentValues1.put("marjor_name",DataSourse.MAJOR[new Random().nextInt(DataSourse.MAJOR.length-1)]);
                contentValues1.put("time",System.currentTimeMillis());
                db.insert(TAB_STUDENT,null,contentValues1);


            }
        }
        //导入学院数据
        for (int i=0;i<DataSourse.COLLEGE.length;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("college_name",DataSourse.COLLEGE[i]);
            db.insert(TAB_COLLEGE,null,contentValues);
        }

        //导入课程数据
        for (int i =0;i<DataSourse.COURSE.length;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("course_name",DataSourse.COURSE[i]);
            contentValues.put("course_credit",new Random().nextInt(10));
            contentValues.put("course_hour",new Random().nextInt(20));
            contentValues.put("course_time",DataSourse.TIMES[new Random().nextInt(DataSourse.TIMES.length-1)]);
            contentValues.put("ach_point",0.5f+"");
            contentValues.put("place",DataSourse.PLACES[new Random().nextInt(DataSourse.PLACES.length-1)]);
            //确保每个老师都有一个课程，方便演示
            String tch_name = DataSourse.TEACHER[new Random().nextInt(DataSourse.TEACHER.length-1)];
            if (i<DataSourse.TEACHER.length)
            {
                tch_name = DataSourse.TEACHER[i];
                if (i==0)
                {
                    //只给admin学生导入部分课程分数
                    ContentValues values = new ContentValues();
                    values.put("tch_name",tch_name);
                    values.put("course_name",contentValues.getAsString("course_name"));
                    values.put("score",new Random().nextInt(100));
                    values.put("stu_number","10000");
                    values.put("year",DataSourse.YEARS[new Random().nextInt(DataSourse.YEARS.length-1)]);
                    db.insert(TAB_SCORE,null,values);
                }

            }
            String college_name = teachers_map.get(tch_name);
            contentValues.put("tch_name",tch_name);
            contentValues.put("college_name",college_name);

            db.insert(TAB_COURSE,null,contentValues);


        }



        db.close();
    }
}
