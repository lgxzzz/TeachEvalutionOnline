package com.teaching.evaluation.test;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.data.SQLiteDbHelper;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.view.TreeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgx on 2019/4/30.
 */

public class DBTestAcitivity extends Activity {

    private Spinner mSpinner;
    private ListView mListview;
    private NodeAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_test);

        mSpinner = (Spinner) findViewById(R.id.spinner_table);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("spinner select","onselect");
                TextView v = (TextView) view;
                String table = v.getText().toString();
                refreshList(table);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mListview = (ListView)findViewById(R.id.list_table);
    }

    public void refreshList(String table){
        mList.clear();
        DBManager dbManager = DBManager.getInstance(this);
        Cursor cursor = null;
        if (table.equals("学生")){
            cursor = dbManager.query(SQLiteDbHelper.TAB_STUDENT,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getColumnName(cursor.getColumnIndex("stu_name"));
                mList.add(name);
            }
        }

        mAdapter = new NodeAdapter(this,mList);
        mListview.setAdapter(mAdapter);
    }

    class NodeAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mData = new ArrayList<>();
        private int mSelectIndex = -1;

        public NodeAdapter(Context mContext,List<String>data){
            this.mContext = mContext;
            this.mData = data;
        }

        public int getSelectIndex() {
            return mSelectIndex;
        }

        public void setSelectIndex(int mSelectIndex) {
            this.mSelectIndex = mSelectIndex;
        }

        @Override
        public int getCount() {
            return this.mData.size();
        }

        @Override
        public Object getItem(int i) {
            return this.mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            String data = this.mData.get(i);
            NodeAdapter.ViewHoler holer = null;
            if (view == null){
                holer = new NodeAdapter.ViewHoler();
                view = new TextView(this.mContext);
                holer.mText = (TextView) view;
                holer.mText.setTextSize(30);
                holer.mText.setTextColor(Color.BLACK);
                view.setTag(holer);
            }else{
                holer = (ViewHoler) view.getTag();
            }
            holer.mText.setText(data);
            return view;
        }

        class ViewHoler{
            TextView mText;
        }


    }
}
