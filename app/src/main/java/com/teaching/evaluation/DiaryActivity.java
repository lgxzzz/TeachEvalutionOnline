package com.teaching.evaluation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.teaching.evaluation.adapter.DiaryAdapter;
import com.teaching.evaluation.bean.Diary;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.view.OperateDiaryDialog;
import com.teaching.evaluation.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiaryActivity extends Activity{

    private SlideRecyclerView recycler_view_list;
    private DiaryAdapter mDiaryAapter;
    private List<Diary> mDiarys = new ArrayList<>();
    private Button mEditBtn;
    private OperateDiaryDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        initView();
    }


    public void initView(){
        mDiarys = DBManager.getInstance(this).queryDiarys(null,null,null,null,null,null);
        recycler_view_list = (SlideRecyclerView) findViewById(R.id.diary_list);
        mEditBtn = (Button) findViewById(R.id.diary_edit_btn);
        recycler_view_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDiaryAapter = new DiaryAdapter(this,mDiarys);
        recycler_view_list.setAdapter(mDiaryAapter);
        dialog = new OperateDiaryDialog(DiaryActivity.this,"insert",
                R.layout.operate_diary,true,true);

        dialog.setOnOpereateFinishListener(new OperateDiaryDialog.OnOpereateFinishListener() {
            @Override
            public void onFinish() {
                initView();
            }
        });

        mDiaryAapter.setOnItemClickListener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.Adapter adapter, View v, int position) {
                dialog.setParams(mDiarys.get(position).getId());
                dialog.setOpt("edit");
                dialog.initView();
                dialog.show();
            }
        });

        mDiaryAapter.setOnDeleteClickListener(new DiaryAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {

                DBManager.getInstance(DiaryActivity.this).deleteDiary(mDiarys.get(position).getId());
                mDiarys.remove(position);
                mDiaryAapter.notifyDataSetChanged();
                recycler_view_list.closeMenu();
            }
        });
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setOpt("insert");
                dialog.initView();
                dialog.show();
            }
        });
    }
}

