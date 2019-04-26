package com.teaching.evaluation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.teaching.evaluation.constant.UserConstant;

public class RegisterActivity extends Activity {
    Button mRegStudent;
    Button mRegTeacher;

    int mCurrentRole = UserConstant.ROLE_DEFAULT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void initView(){
        mRegStudent = (Button) findViewById(R.id.register_stu);
        mRegTeacher = (Button) findViewById(R.id.register_tch);

        mRegStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });

        mRegTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
     }
}
