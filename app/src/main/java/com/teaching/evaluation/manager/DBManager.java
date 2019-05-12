package com.teaching.evaluation.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teaching.evaluation.bean.College;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Diary;
import com.teaching.evaluation.bean.Evaluation;
import com.teaching.evaluation.bean.Score;
import com.teaching.evaluation.bean.Student;
import com.teaching.evaluation.bean.Teacher;
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.constant.ErrorCode;
import com.teaching.evaluation.data.SQLiteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    Context mContext;

    private static DBManager mInstance = null;

    SQLiteDbHelper mDbHelper;

    public DBManager(Context context){

        this.mContext = context;
        mDbHelper = new SQLiteDbHelper(context);
        mInstance = this;
    }

    public static DBManager getInstance(Context context) {
        if (mInstance == null){
            mInstance = new DBManager(context);
        }
        return mInstance;
    }

    //初始化数据
    public void initData(){
        mDbHelper.initData();
    }

    //查询所有学生
    public List<Student> queryStudent(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_STUDENT,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("stu_name"));
            String number = cursor.getString(cursor.getColumnIndex("stu_number"));
            int age = cursor.getInt(cursor.getColumnIndex("stu_age"));
            String college_name = cursor.getString(cursor.getColumnIndex("college_name"));
            Student student = new Student();
            student.setName(name);
            student.setNumber(number);
            student.setAge(age);
            student.setCollege_name(college_name);
            students.add(student);
        }
        db.close();
        return students;
    }

    //所有用户
    public List<User> queryUser(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_USER,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("user_name"));
            String number = cursor.getString(cursor.getColumnIndex("user_number"));
            String pwd = cursor.getString(cursor.getColumnIndex("user_pwd"));
            String role = cursor.getString(cursor.getColumnIndex("user_role"));
            User user = new User();
            user.setName(name);
            user.setNumber(number);
            user.setPwd(pwd);
            user.setRole(role);
            users.add(user);
        }
        db.close();
        return users;
    }

    //所有老师
    public List<Teacher> queryTeacher(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<Teacher> teachers = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_TEACHER,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("tch_name"));
            String number = cursor.getString(cursor.getColumnIndex("tch_number"));
            String college = cursor.getString(cursor.getColumnIndex("college_name"));
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setNumber(number);
            teacher.setCollege(college);
            teachers.add(teacher);
        }
        db.close();
        return teachers;
    }

    //所有学院
    public List<College> queryCollege(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<College> colleges = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_COLLEGE,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("college_name"));
            College college = new College();
            college.setName(name);
            colleges.add(college);
        }
        db.close();
        return colleges;
    }

    //所有课程
    public List<Course> queryCourse(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<Course> courses = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_COURSE,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String course_name = cursor.getString(cursor.getColumnIndex("course_name"));
            String course_hour = cursor.getString(cursor.getColumnIndex("course_hour"));
            String course_credit = cursor.getString(cursor.getColumnIndex("course_credit"));
            String ach_point = cursor.getString(cursor.getColumnIndex("ach_point"));
            String place = cursor.getString(cursor.getColumnIndex("place"));
            String tch_number = cursor.getString(cursor.getColumnIndex("tch_name"));
            String time = cursor.getString(cursor.getColumnIndex("course_time"));
            Course course = new Course();
            course.setName(course_name);
            course.setHour(course_hour);
            course.setAch_point(ach_point);
            course.setPlace(place);
            course.setTch_name(tch_number);
            course.setCredit(course_credit);
            course.setTime(time);
            courses.add(course);
        }
        db.close();
        return courses;
    }

    //所有评价
    public List<Evaluation> queryEvaluations(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<Evaluation> evaluations = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_EVALUATE,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String user_number = cursor.getString(cursor.getColumnIndex("user_number"));
            String course_name = cursor.getString(cursor.getColumnIndex("course_name"));
            String tch_name = cursor.getString(cursor.getColumnIndex("tch_name"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String course_time = cursor.getString(cursor.getColumnIndex("eva_time"));
            String eva_score = cursor.getString(cursor.getColumnIndex("eva_score"));
            Evaluation evaluation = new Evaluation();
            evaluation.setContent(content);
            evaluation.setCourse_name(course_name);
            evaluation.setUser_number(user_number);
            evaluation.setTch_name(tch_name);
            evaluation.setCourse_time(course_time);
            evaluation.setEva_score(eva_score);
            evaluations.add(evaluation);
        }
        db.close();
        return evaluations;
    }

    //所有得分表
    public List<Score> queryScores(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<Score> Scores = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_SCORE,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String course_name = cursor.getString(cursor.getColumnIndex("course_name"));
            String tch_name = cursor.getString(cursor.getColumnIndex("tch_name"));
            String stu_number = cursor.getString(cursor.getColumnIndex("stu_number"));
            String year = cursor.getString(cursor.getColumnIndex("year"));
            String scor = cursor.getString(cursor.getColumnIndex("score"));
            Score score = new Score();
            score.setCourse_name(course_name);
            score.setTch_name(tch_name);
            score.setStu_number(stu_number);
            score.setYear(year);
            score.setScore(scor);
            Scores.add(score);
        }
        db.close();
        return Scores;
    }

    //所有日志
    public List<Diary> queryDiarys(String[] columns, String selection, String[]  selectionArgs, String groupBy, String having, String  orderBy){
        List<Diary> diaries = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(SQLiteDbHelper.TAB_DIARY,columns,selection,selectionArgs,groupBy,having,orderBy);
        while (cursor.moveToNext()){
            String user_number = cursor.getString(cursor.getColumnIndex("user_number"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String id = cursor.getString(cursor.getColumnIndex("id"));

            Diary diary = new Diary();
            diary.setUser_number(user_number);
            diary.setTime(time);
            diary.setTitle(title);
            diary.setContent(content);
            diary.setId(id);
            diaries.add(diary);
        }
        db.close();
        return diaries;
    }

    //增加日志
    public long insertDiary(ContentValues values){
        try{
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_DIARY,null,values);
            return code;
        }catch (Exception e){

        }
        return -1;
    }
    //修改日志
    public int  editDiary(String diaryID,ContentValues values){
        try{
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            int code = db.update(SQLiteDbHelper.TAB_DIARY,values,"id =?",new String[]{diaryID});
            return code;
        }catch (Exception e){

        }
        return -1;
    }
    //删除日志
    public int  deleteDiary(String diaryID){
        try{
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            int code = db.delete(SQLiteDbHelper.TAB_DIARY,"id =?",new String[]{diaryID});
            return code;
        }catch (Exception e){

        }
        return -1;
    }

    //添加评论记录
    public void addEvalution(Evaluation evaluation){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course_name",evaluation.getCourse_name());
        contentValues.put("user_number",evaluation.getUser_number());
        contentValues.put("tch_name",evaluation.getTch_name());
        contentValues.put("content",evaluation.getContent());
        contentValues.put("eva_score",evaluation.getEva_score());
        contentValues.put("eva_time",evaluation.getCourse_time());
        long x = db.insert(SQLiteDbHelper.TAB_EVALUATE,null,contentValues);
    }

    public void execSQL(String sql){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public void addUser(User user,DBManagerListener listener){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        //先判断是否已经注册过用户
        Cursor cursor = db.rawQuery("select * from user where user_number =?",new String[]{user.getNumber()});
        if (cursor!=null&&cursor.moveToFirst()){
            listener.onFail(ErrorCode.ERROR_ALREADY_REGISTERED);
            return;
        };
        String sql="";
        //未注册查询是否在学生表和教师表中
        if (user.getRole().equals("学生")){
            sql = "select * from student where stu_number =? and stu_name =?";
        }else{
            sql = "select * from teacher where tch_number =? and tch_name =?";
        }
        Cursor cursor1 = db.rawQuery(sql,new String[]{user.getNumber(),user.getName()});
        if (cursor1==null||!cursor1.moveToFirst()){
            listener.onFail(ErrorCode.ERROR_NOT_EXIST_PERSON);
            return;
        }
        //插入用户表中
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name",user.getName());
        contentValues.put("user_pwd",user.getPwd());
        contentValues.put("user_role",user.getRole());
        contentValues.put("user_number",user.getNumber());
        db.insert(SQLiteDbHelper.TAB_USER,null,contentValues);
        db.close();
        listener.onSuccess(user);
    }

    //登陆
    public void doLogin(User user,DBManagerListener listener){
        try{
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from user where user_name =? and user_pwd=?",new String[]{user.getName(),user.getPwd()});
            if (cursor.moveToFirst()){
                String user_number = cursor.getString(cursor.getColumnIndex("user_number"));
                String user_role = cursor.getString(cursor.getColumnIndex("user_role"));
                user.setRole(user_role);
                user.setNumber(user_number);
                listener.onSuccess(user);
            }else{
                listener.onFail(ErrorCode.ERROR_SEARCH);
            }
            db.close();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        listener.onFail(ErrorCode.ERROR_SEARCH);
    }

    public int  editCourse(String tch_name,ContentValues values){
        try{
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            int code = db.update(SQLiteDbHelper.TAB_COURSE,values,"tch_name =?",new String[]{tch_name});
            return code;
        }catch (Exception e){

        }
        return -1;
    }

    public long  insertCourse(ContentValues values){
        try{
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_COURSE,null,values);
            return code;
        }catch (Exception e){

        }
        return -1;
    }
    public interface DBManagerListener{
        public void onSuccess(User user);
        public void onFail(int error);
    }
}
