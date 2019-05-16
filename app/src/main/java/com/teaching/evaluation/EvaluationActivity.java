package com.teaching.evaluation;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.teaching.evaluation.adapter.EvaAdapter;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Evaluation;
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.manager.LoginManager;
import com.teaching.evaluation.view.BaseActivity;
import com.teaching.evaluation.view.EditEvaluaDialog;
import com.teaching.evaluation.view.EvaRatingView;

import java.util.List;

public class EvaluationActivity extends BaseActivity {

    Button mEvaEditBtn;
    EditEvaluaDialog mEditEvaDialog;

    TextView mEvaCourseName;
    TextView mEvaTchName;
    TextView mEvaScore;
    TextView mEvaCount;

    EvaRatingView mEvaRating;

    String mCourseName;
    String mTchName;
    String mCollegeName;

    ListView mEvaListView;

    EvaAdapter mAdapter;

    int[] mEvaScores = new int[]{0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);

        mCourseName = getIntent().getStringExtra("course_name");
        mTchName = getIntent().getStringExtra("tch_name");
        mCollegeName = getIntent().getStringExtra("college_name");

        initView();
        initData();
    }

    public void initView(){
        mEditEvaDialog = new EditEvaluaDialog(this,R.layout.edit_evaluation,true,true);
        mEvaEditBtn = (Button)findViewById(R.id.eva_edit_btn);
        mEvaEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = LoginManager.getInstance(getBaseContext()).getUser();
                if (!user.getCollege_name().equals(mCollegeName)){
                    Toast.makeText(getBaseContext(),"不在该院校，无法评价！",Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isAviable = DBManager.getInstance(getBaseContext()).isEvaAviable(mCourseName,mTchName);
                if (isAviable){
                    mEditEvaDialog.setParams(mCourseName,mTchName);
                    mEditEvaDialog.show();
                }else{
                    Toast.makeText(getBaseContext(),"已经评论过了！",Toast.LENGTH_LONG).show();
                }

            }
        });

        mEvaCourseName = (TextView)findViewById(R.id.eva_course_name);
        mEvaTchName = (TextView)findViewById(R.id.eva_tch_name);
        mEvaScore = (TextView)findViewById(R.id.eva_score);
        mEvaCount = (TextView)findViewById(R.id.eva_people_count);

        mEvaTchName.setText(mTchName);
        mEvaCourseName.setText(mCourseName);

        mEvaListView = (ListView) findViewById(R.id.eva_list);

        mEditEvaDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                initData();
            }
        });

        mEvaRating = (EvaRatingView)findViewById(R.id.eva_rating);
    }


    public void initData(){
        List<Evaluation> evaluations = DBManager.getInstance(this).queryEvaluations(null,
                "tch_name =? and course_name =?",new String[]{mTchName,mCourseName},null,null,"id desc");
        if (evaluations==null){
            return;
        }
        mAdapter = new EvaAdapter(this,evaluations);
        mEvaListView.setAdapter(mAdapter);

        mEvaCount.setText("评价次数"+evaluations.size());

        Float allScore = 0.0f;
        for (int i=0;i<evaluations.size();i++){
            String score = evaluations.get(i).getEva_score();
            if (score.equals("4.5")||score.equals("5.0"))
            {
                mEvaScores[4]=++mEvaScores[4];
            }else if(score.equals("3.5")||score.equals("4.0"))
            {
                mEvaScores[3]=++mEvaScores[3];
            }else if(score.equals("2.5")||score.equals("3.0"))
            {
                mEvaScores[2]=++mEvaScores[2];
            }else if(score.equals("1.5")||score.equals("2.0"))
            {
                mEvaScores[1]=++mEvaScores[1];
            }else if(score.equals("0.0")||score.equals("1.0"))
            {
                mEvaScores[0]=++mEvaScores[0];
            }
            Float sc = Float.parseFloat(score);
            allScore= allScore+sc;
        }
        if (allScore!=0.0f)
        {
            double sroce = allScore/evaluations.size();
            java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.0");
            String str = myformat.format(sroce);

            mEvaScore.setText(str);
        }
        mEvaRating.setParams(evaluations.size(),mEvaScores);

    }
}
