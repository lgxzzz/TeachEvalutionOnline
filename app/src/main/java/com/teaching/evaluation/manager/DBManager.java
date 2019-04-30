package com.teaching.evaluation.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teaching.evaluation.data.SQLiteDbHelper;

import java.util.List;

public class DBManager {

    Context mContext;

    private static DBManager mInstance = null;

    SQLiteDbHelper mDbHelper;

    public DBManager(Context context){

        this.mContext = context;
        mDbHelper = new SQLiteDbHelper(context);
        mInstance = this;
    }

    public static DBManager getInstance(Context context) {
        if (mInstance == null){
            mInstance = new DBManager(context);
        }
        return mInstance;
    }

    public Cursor query(String table,String[] columns,String selection,String[]  selectionArgs,String groupBy,String having,String  orderBy){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
        db.close();
        return cursor;
    }

    public void execSQL(String sql){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
}
