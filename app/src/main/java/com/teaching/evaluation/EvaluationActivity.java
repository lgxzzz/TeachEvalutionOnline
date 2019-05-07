package com.teaching.evaluation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Evaluation;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.manager.LoginManager;
import com.teaching.evaluation.view.EditEvaluaDialog;
import com.teaching.evaluation.view.EvaRatingView;

import java.util.List;

public class EvaluationActivity extends Activity {

    Button mEvaEditBtn;
    EditEvaluaDialog mEditEvaDialog;

    TextView mEvaCourseName;
    TextView mEvaTchName;
    TextView mEvaScore;
    TextView mEvaCount;

    EvaRatingView mEvaRating;

    String mCourseName;
    String mTchName;

    ListView mEvaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);

        mCourseName = getIntent().getStringExtra("course_name");
        mTchName = getIntent().getStringExtra("tch_name");

        initView();
        initData();
    }

    public void initView(){
        mEditEvaDialog = new EditEvaluaDialog(this,R.layout.edit_evaluation,true,true);
        mEvaEditBtn = (Button)findViewById(R.id.eva_edit_btn);
        mEvaEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditEvaDialog.setParams(mCourseName,mTchName);
                mEditEvaDialog.show();
            }
        });

        mEvaCourseName = (TextView)findViewById(R.id.eva_course_name);
        mEvaTchName = (TextView)findViewById(R.id.eva_tch_name);
        mEvaScore = (TextView)findViewById(R.id.eva_score);
        mEvaCount = (TextView)findViewById(R.id.eva_content);

        mEvaTchName.setText(mTchName);
        mEvaCourseName.setText(mCourseName);

        mEvaListView = (ListView) findViewById(R.id.eva_list);
    }


    public void initData(){
        List<Evaluation> evaluations = DBManager.getInstance(this).queryEvaluations(null,"",new String[]{},null,null,null);
    }
}
