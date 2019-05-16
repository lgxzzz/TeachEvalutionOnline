package com.teaching.evaluation.view;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.adapter.NodeAdapter;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Teacher;
import com.teaching.evaluation.manager.DBManager;

import java.util.ArrayList;
import java.util.List;


public class TreeListDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;

    private ListView mCourseList;
    private ListView mTeacherList;

    private NodeAdapter mCourseAdapter;
    private NodeAdapter mTeacherAdapter;

    private List<String> mCourseData = new ArrayList<>();
    private List<String> mTeacherData = new ArrayList<>();

    public TreeListDialog(Context context,int layoutid, boolean isCancelable,boolean isBackCancelable) {
        super(context, R.style.MyDialog);

        this.context = context;
        this.view = LayoutInflater.from(context).inflate(layoutid,null);
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;

        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);//这行一定要写在前面
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

    }

    public void initView(){
        mTeacherList = (ListView)this.view.findViewById(R.id.teacher_listview);
        mCourseList = (ListView)this.view.findViewById(R.id.course_listview);

    }

    public void updateTeacherListView(String college){
        mTeacherData.clear();
        List<Teacher> teachers = DBManager.getInstance(this.context).queryTeacher(null,"college_name =?",new String[]{college},null,null,null);
        for (int i=0;i<teachers.size();i++){
            Teacher teacher = teachers.get(i);
            mTeacherData.add(teacher.getName());
        }
        mTeacherAdapter = new NodeAdapter(this.getContext(),mTeacherData);
        mTeacherList.setAdapter(mTeacherAdapter);
        mTeacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前adapter，判断是否点击同一个item
                NodeAdapter adapter = (NodeAdapter) adapterView.getAdapter();
                if (adapter.getSelectIndex() == i){
                    return;
                }else{
                    adapter.setSelectIndex(i);
                    adapter.notifyDataSetChanged();
                    //获取二级数据
                    TextView tv = (TextView)  view.findViewById(R.id.node_name);
                    updateCourseListView(tv.getText().toString());
                }
            }
        });
    }

    public void updateCourseListView(String tch_name){
        mCourseData.clear();
        final List<Course> courses = DBManager.getInstance(this.context).queryCourse(null,"tch_name =?",new String[]{tch_name},null,null,null);
        for (int i=0;i<courses.size();i++){
            Course course = courses.get(i);
            mCourseData.add(course.getName());
        }

        mCourseAdapter = new NodeAdapter(this.getContext(),mCourseData);
        mCourseList.setAdapter(mCourseAdapter);
        mCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前adapter，判断是否点击同一个item
                NodeAdapter adapter = (NodeAdapter) adapterView.getAdapter();
                if (adapter.getSelectIndex() == i){
                    return;
                }else{
                    adapter.setSelectIndex(i);
                    adapter.notifyDataSetChanged();

                    Course course = courses.get(i);
                    Intent mIntent = new Intent();
                    mIntent.putExtra("course_name",course.getName());
                    mIntent.putExtra("tch_name",course.getTch_name());
                    mIntent.putExtra("college_name",course.getCollege_name());
                    mIntent.setClassName("com.teaching.evaluation","com.teaching.evaluation.EvaluationActivity");
                    TreeListDialog.this.context.startActivity(mIntent);
                }
            }
        });
    }

    public void clearCourseList(){
        mCourseData.clear();
        mCourseAdapter = new NodeAdapter(this.getContext(),mCourseData);
        mCourseList.setAdapter(mCourseAdapter);
    }

    public void setParam(String college){
        updateTeacherListView(college);
        clearCourseList();
    }

}

