package com.teaching.evaluation.view;



import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.teaching.evaluation.R;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Evaluation;
import com.teaching.evaluation.bean.Score;
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.manager.LoginManager;

import java.util.ArrayList;
import java.util.List;


public class StudyDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;

    private SmartTable<Score> table;

    public StudyDialog(Context context, int layoutid, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.MyDialog);

        this.context = context;
        this.view = LayoutInflater.from(context).inflate(layoutid, null);
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);//这行一定要写在前面
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);
        initView();

    }

    public void initView() {
        table = (SmartTable<Score>) findViewById(R.id.table);

        User user = LoginManager.getInstance(getContext()).getUser();

        if (user.getRole().equals("老师")){
            //获取该老师的所有课程
            List<Course> courses = DBManager.getInstance(getContext()).queryCourse(
                    null,"tch_name =?",new String[]{user.getName()},null,null,null);
            List<Score> scores = new ArrayList<>();
            for (int i=0;i<courses.size();i++){
                Course course = courses.get(i);
                Score score = new Score();
                //获取该课程所有评价
                List<Evaluation> evaluations = DBManager.getInstance(getContext()).queryEvaluations(null,
                        "tch_name =? and course_name =?",new String[]{user.getName(),course.getName()},null,null,"id desc");
                Float allScore = 0.0f;
                for (int j=0;j<evaluations.size();j++){
                    String sco = evaluations.get(j).getEva_score();
                    Float sc = Float.parseFloat(sco);
                    allScore= allScore+sc;
                }
                if (allScore!=0.0f)
                {
                    score.setScore((allScore/evaluations.size())+"");
                }
                score.setCourse_name(course.getName());
                scores.add(score);
            }

            //老师对应的是综合得分
            Column<String> courseNameColumn = new Column<>("课程名称","course_name");
            Column<String> courseCreditColumn = new Column<>("得分","score");

            final TableData<Score> tableData = new TableData<>("综合分数",scores,courseNameColumn,courseCreditColumn);
            table.setTableData(tableData);
        }else{
            final List<Score> scores  = DBManager.getInstance(this.getContext()).queryScores(null,"stu_number =?",new String[]{user.getNumber()},null,null,null);

            Column<String> courseNameColumn = new Column<>("课程名称","course_name");
            Column<String> courseCreditColumn = new Column<>("得分","score");
            Column<String> courseTchNameColumn = new Column<>("教师","tch_name");
            Column<String> courseYearColumn = new Column<>("学年","year");

            final TableData<Score> tableData = new TableData<>("历年分数",scores,courseNameColumn,courseCreditColumn,courseTchNameColumn,courseYearColumn);
            table.setTableData(tableData);
        }
    }

}