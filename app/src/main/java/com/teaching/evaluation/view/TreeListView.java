package com.teaching.evaluation.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.teaching.evaluation.R;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeListView extends LinearLayout {

    private ListView mCollegeList;
    private ListView mCourseList;
    private ListView mTeacherList;

    private NodeAdapter mCollegeAdapter;
    private NodeAdapter mCourseAdapter;
    private NodeAdapter mTeacherAdapter;

    private List<String> mCollegeData = new ArrayList<>();
    private List<String> mCourseData = new ArrayList<>();
    private List<String> mTeacherData = new ArrayList<>();

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

        mCollegeList = (ListView)findViewById(R.id.college_listview);
        mTeacherList = (ListView)findViewById(R.id.teacher_listview);
        mCourseList = (ListView)findViewById(R.id.course_listview);

        mCollegeAdapter = new NodeAdapter(this.getContext(),mCollegeData);
        mCollegeList.setAdapter(mCollegeAdapter);
        mCollegeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前adapter，判断是否点击同一个item
                NodeAdapter adapter = (NodeAdapter) adapterView.getAdapter();
                if (adapter.getSelectIndex() == i){
                    return;
                }else{
                    adapter.setSelectIndex(i);
                    adapter.notifyDataSetChanged();

                    //获取二级数据

                    updateTeacherListView();
                }

            }
        });
    }

    public void updateTeacherListView(){
        mTeacherAdapter = new NodeAdapter(this.getContext(),mTeacherData);
        mTeacherList.setAdapter(mTeacherAdapter);
        mTeacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前adapter，判断是否点击同一个item
                NodeAdapter adapter = (NodeAdapter) adapterView.getAdapter();
                if (adapter.getSelectIndex() == i){
                    return;
                }else{
                    adapter.setSelectIndex(i);
                    adapter.notifyDataSetChanged();

                    //获取二级数据

                    updateCourseListView();
                }
            }
        });
    }

    public void updateCourseListView(){
        mCourseAdapter = new NodeAdapter(this.getContext(),mCourseData);
        mCourseList.setAdapter(mCourseAdapter);
        mCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前adapter，判断是否点击同一个item
                NodeAdapter adapter = (NodeAdapter) adapterView.getAdapter();
                if (adapter.getSelectIndex() == i){
                    return;
                }else{
                    adapter.setSelectIndex(i);
                    adapter.notifyDataSetChanged();

                    showPopuView();
                }
            }
        });
    }

    public void showPopuView(){

    }

    public void initDefaultData(){
        mCollegeData.add("体育学院");
        mCollegeData.add("软件学院");
        mCollegeData.add("外国语");
        mCollegeData.add("政法学院");

        mTeacherData.add("张老师");
        mTeacherData.add("黄老师");
        mTeacherData.add("赵老师");

        mCourseData.add("语文");
        mCourseData.add("数学");
        mCourseData.add("英语");
        mCourseData.add("体育");
    }

    class NodeAdapter extends BaseAdapter{
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
            ViewHoler holer = null;
            if (view == null){
                holer = new ViewHoler();
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
