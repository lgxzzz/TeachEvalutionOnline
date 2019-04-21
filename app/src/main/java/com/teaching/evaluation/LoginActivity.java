package com.teaching.evaluation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEditTextName;//输入的名称
    EditText mEditTextPwd;//输入的密码

    CheckBox mCheckBoxRemindPwd;//是否记住密码

    TextView mTextForgetPwd;//忘记密码

    Button mButtonLogin; //登陆
    Button mButtonRegister;//注册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();

    }

    //初始化控件，绑定点击事件
    public void initView(){
        mEditTextName = findViewById(R.id.name);
        mEditTextPwd  = findViewById(R.id.pwd);
        mCheckBoxRemindPwd = findViewById(R.id.remind_pwd);
        mTextForgetPwd = findViewById(R.id.forget_pwd);
        mButtonLogin = findViewById(R.id.login);
        mButtonRegister = findViewById(R.id.register);

        mTextForgetPwd.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.forget_pwd:
                break;
            case R.id.login:
                Intent mIntent = new Intent();
                mIntent.setClassName("com.teaching.evaluation","com.teaching.evaluation.MainActivity");
                startActivity(mIntent);
                break;
            case R.id.register:
                break;
        }
    }
}
