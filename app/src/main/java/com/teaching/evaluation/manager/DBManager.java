package com.teaching.evaluation.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teaching.evaluation.data.SQLiteDbHelper;

public class DBManager {

    Context mContext;

    SQLiteDbHelper mDbHelper;

    public DBManager(Context context){

        this.mContext = context;
        mDbHelper = new SQLiteDbHelper(context);
        mDbHelper.initData();
    }

    public void initData(){

    }

}
