package com.teaching.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.jdbc.JdbcMgr;
import com.teaching.evaluation.manager.LoginManager;

public class LoginActivity extends Activity implements View.OnClickListener {

    EditText mEditTextName;//输入的名称
    EditText mEditTextPwd;//输入的密码

    CheckBox mCheckBoxRemindPwd;//是否记住密码

    TextView mTextForgetPwd;//忘记密码

    Button mButtonLogin; //登陆
    Button mButtonRegister;//注册

    LoginManager mLoginManager;

    String mUserName = null;
    String mPwd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mLoginManager = LoginManager.getInstance();

        initView();

    }

    //初始化控件，绑定点击事件
    public void initView(){
        mEditTextName = (EditText)findViewById(R.id.name);
        mEditTextPwd  = (EditText)findViewById(R.id.pwd);
        mCheckBoxRemindPwd = (CheckBox) findViewById(R.id.remind_pwd);
        mTextForgetPwd = (TextView) findViewById(R.id.forget_pwd);
        mButtonLogin = (Button) findViewById(R.id.login);
        mButtonRegister = (Button) findViewById(R.id.register);

        mTextForgetPwd.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);

        mEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                 mUserName = editable.toString();
            }
        });

        mEditTextPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    mPwd = editable.toString();
            }
        });
    }

    public void doLogin(){
        if (mUserName ==null || mUserName.length()==0){
            Toast.makeText(LoginActivity.this,"用户名不能未空",Toast.LENGTH_SHORT).show();
            return;
        }

        if (mPwd ==null|| mPwd.length()==0){
            Toast.makeText(LoginActivity.this,"密码不能未空",Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setName(mUserName);
        user.setPwd(mPwd);
        mLoginManager.doLogin(user);
    }

    public void register(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.forget_pwd:
                break;
            case R.id.login:
                doLogin();

//                Intent mIntent = new Intent();
//                mIntent.setClassName("com.teaching.evaluation","com.teaching.evaluation.MainActivity");
//                startActivity(mIntent);
                break;
            case R.id.register:
                register();
                break;
        }
    }



}
