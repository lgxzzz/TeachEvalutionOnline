package com.teaching.evaluation.test;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.RegisterActivity;
import com.teaching.evaluation.bean.College;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Evaluation;
import com.teaching.evaluation.bean.Student;
import com.teaching.evaluation.bean.Teacher;
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.data.SQLiteDbHelper;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.view.TreeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgx on 2019/4/30.
 */

public class DBTestAcitivity extends Activity {

    private Spinner mSpinner;
    private Button mInitDataBtn;
    private ListView mListview;
    private NodeAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_test);

        mInitDataBtn = (Button)findViewById(R.id.init_data);
        mInitDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager.getInstance(DBTestAcitivity.this).initData();
                refreshList("学生");
            }
        });

        mSpinner = (Spinner) findViewById(R.id.spinner_table);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("spinner select","onselect");
                TextView v = (TextView) view;
                String table = v.getText().toString();
                refreshList(table);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mListview = (ListView)findViewById(R.id.list_table);
    }

    public void refreshList(String table){
        mList.clear();
        DBManager dbManager = DBManager.getInstance(this);
        if (table.equals("学生")){
            List<Student> students;
            students = dbManager.queryStudent(null,null,null,null,null,null);
            for (int i= 0;i<students.size();i++){
                mList.add(students.get(i).toString());
            }
        }else if(table.equals("用户")){
            List<User> users;
            users = dbManager.queryUser(null,null,null,null,null,null);
            for (int i= 0;i<users.size();i++){
                mList.add(users.get(i).toString());
            }
        }else if(table.equals("老师")){
            List<Teacher> teachers;
            teachers = dbManager.queryTeacher(null,null,null,null,null,null);
            for (int i= 0;i<teachers.size();i++){
                mList.add(teachers.get(i).toString());
            }
        }else if(table.equals("学院")){
            List<College> colleges;
            colleges = dbManager.queryCollege(null,null,null,null,null,null);
            for (int i= 0;i<colleges.size();i++){
                mList.add(colleges.get(i).toString());
            }
        }else if(table.equals("课程")){
            List<Course> courses;
            courses = dbManager.queryCourse(null,null,null,null,null,"tch_name");
            for (int i= 0;i<courses.size();i++){
                mList.add(courses.get(i).toString());
            }
        }else if(table.equals("评价")){
            List<Evaluation> evaluations;
            evaluations = dbManager.queryEvaluations(null,null,null,null,null,"tch_name");
            for (int i= 0;i<evaluations.size();i++){
                mList.add(evaluations.get(i).toString());
            }
        }

        mAdapter = new NodeAdapter(this,mList);
        mListview.setAdapter(mAdapter);
    }

    class NodeAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mData = new ArrayList<>();
        private int mSelectIndex = -1;

        public NodeAdapter(Context mContext,List<String>data){
            this.mContext = mContext;
            this.mData = data;
        }

        public int getSelectIndex() {
            return mSelectIndex;
        }

        public void setSelectIndex(int mSelectIndex) {
            this.mSelectIndex = mSelectIndex;
        }

        @Override
        public int getCount() {
            return this.mData.size();
        }

        @Override
        public Object getItem(int i) {
            return this.mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            String data = this.mData.get(i);
            NodeAdapter.ViewHoler holer = null;
            if (view == null){
                holer = new NodeAdapter.ViewHoler();
                view = new TextView(this.mContext);
                holer.mText = (TextView) view;
                holer.mText.setTextSize(30);
                holer.mText.setTextColor(Color.BLACK);
                view.setTag(holer);
            }else{
                holer = (ViewHoler) view.getTag();
            }
            holer.mText.setText(data);
            return view;
        }

        class ViewHoler{
            TextView mText;
        }


    }
}
