package com.teaching.evaluation.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.adapter.NodeAdapter;
import com.teaching.evaluation.bean.College;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Teacher;
import com.teaching.evaluation.manager.DBManager;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeListView extends LinearLayout {

    private ListView mCollegeList;


    private NodeAdapter mCollegeAdapter;


    private List<String> mCollegeData = new ArrayList<>();

    private TreeListDialog mTreeListDialog;

    DBManager dbManager;

    public TreeListView(Context context) {
        super(context);
    }

    public TreeListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tree_listview,this,true);
        initDefaultData();
        initView();
    }

    public TreeListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(){
        mTreeListDialog = new TreeListDialog(this.getContext(),R.layout.tree_dialog,true,true);

        mCollegeList = (ListView)findViewById(R.id.college_listview);

        mCollegeAdapter = new NodeAdapter(this.getContext(),mCollegeData);
        mCollegeList.setAdapter(mCollegeAdapter);
        mCollegeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前adapter，判断是否点击同一个item
                NodeAdapter adapter = (NodeAdapter) adapterView.getAdapter();
                    adapter.setSelectIndex(i);
                    adapter.notifyDataSetChanged();

                    //获取二级数据
                    TextView tv = (TextView)  view.findViewById(R.id.node_name);;
                    mTreeListDialog.setParam(tv.getText().toString());
                    mTreeListDialog.show();

            }
        });
    }

    public void initDefaultData(){
        dbManager = DBManager.getInstance(this.getContext());

        List <College> colleges = dbManager.queryCollege(null,null,null,null,null,null);
        for (int i=0;i<colleges.size();i++){
            College college = colleges.get(i);
            mCollegeData.add(college.getName());
        }
    }

}
