package com.teaching.evaluation.view;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.adapter.NodeAdapter;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Evaluation;
import com.teaching.evaluation.bean.Teacher;
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.manager.LoginManager;

import java.util.ArrayList;
import java.util.List;


public class EditEvaluaDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;

    private RatingBar mRatingBar;
    private Button mSureBtn;
    private Button mCancelBtn;
    private EditText mEditText;

    private String mContent;
    private String mTchName;
    private String mCourseName;
    private String mEvaScore;

    public EditEvaluaDialog(Context context, int layoutid, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.MyDialog);

        this.context = context;
        this.view = LayoutInflater.from(context).inflate(layoutid, null);
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

    public void initView() {
        mSureBtn = (Button) this.view.findViewById(R.id.eva_sure);
        mCancelBtn = (Button) this.view.findViewById(R.id.eva_cancel);
        mEditText = (EditText) this.view.findViewById(R.id.eva_content);
        mRatingBar = (RatingBar) this.view.findViewById(R.id.ratingBar);

        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= LoginManager.getInstance(context).getUser();
                mEvaScore = mRatingBar.getRating()+"";
                Evaluation evaluation = new Evaluation();
                evaluation.setTch_name(mTchName);
                evaluation.setCourse_name(mCourseName);
                evaluation.setUser_number(user.getNumber());
                evaluation.setEva_score(mEvaScore);
                evaluation.setContent(mContent);
                DBManager.getInstance(context).addEvalution(evaluation);
                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dismiss();
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mContent = editable.toString();
            }
        });

    }

    public void setParams(String mCourseName,String mTchName){
        this.mCourseName = mCourseName;
        this.mTchName = mTchName;
    }

}