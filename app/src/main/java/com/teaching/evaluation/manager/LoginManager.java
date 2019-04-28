package com.teaching.evaluation.manager;

import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.jdbc.JdbcMgr;

/**
 * Created by lgx on 2019/4/24.
 *
 */

public class LoginManager {

    private static final LoginManager gManager = new LoginManager();

    private User mLoginUser;

    private LoginListenter mListenter;

    private JdbcMgr mJdbcMgr;


    private LoginManager() {
        mJdbcMgr = new JdbcMgr();
    }

    public static LoginManager getInstance() {
        return gManager;
    }

    //测试数据库
    public void testJDBC(){
        mJdbcMgr.getConnection();
        //查询所有老师
        mJdbcMgr.closeConnection();
    }

    public void setListenter(LoginListenter listenter){
        this.mListenter = listenter;
    }

    //登录
    public void doLogin(User user){
        if(user!=null){
            this.mLoginUser = user;
        }
        mListenter.onSuccess();
    }

    //登出
    public void logout(){
        mLoginUser = null;
    }

    public interface LoginListenter{
        public void onSuccess();
        public void onError(int error);
    }
}
