package com.teaching.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.teaching.evaluation.constant.UserConstant;

public class ChooseRoleActivity extends Activity {
    Button mRegStudent;
    Button mRegTeacher;

    int mCurrentRole = UserConstant.ROLE_DEFAULT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_role);
    }

    public void initView(){
        mRegStudent = (Button) findViewById(R.id.register_stu);
        mRegTeacher = (Button) findViewById(R.id.register_tch);

        mRegStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mIntent = new Intent();
                mIntent.setClassName("com.teaching.evaluation","com.teaching.evaluation.RegisterActivity");
                mIntent.putExtra("role",UserConstant.ROLE_STUDENT);
                startActivity(mIntent);
            }
        });

        mRegTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent();
                mIntent.setClassName("com.teaching.evaluation","com.teaching.evaluation.RegisterActivity");
                mIntent.putExtra("role",UserConstant.ROLE_TEACHER);
                startActivity(mIntent);
            }
        });
    }

}
