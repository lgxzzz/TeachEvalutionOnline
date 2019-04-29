package com.teaching.evaluation;

import android.app.Application;

import com.teaching.evaluation.manager.DBManager;

/**
 * Created by lgx on 2019/4/24.
 */

public class TeachingEvaApplication extends Application{

    DBManager mDBManager;

    @Override
    public void onCreate() {

        super.onCreate();

        mDBManager = new DBManager(this);
    }
}
