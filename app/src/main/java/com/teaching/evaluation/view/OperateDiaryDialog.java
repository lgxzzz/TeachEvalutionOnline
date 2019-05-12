package com.teaching.evaluation.view;



import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.bean.Course;
import com.teaching.evaluation.bean.Diary;
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.manager.LoginManager;

import java.util.ArrayList;
import java.util.List;


public class OperateDiaryDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;

    private EditText mEditTitle;
    private EditText mEditTime;
    private EditText mEditContent;
    private TextView mDescribeTv;

    private String mTime;
    private String mTitle;
    private String mContent;
    private String mDescribe;

    private String opt="insert";
    private Button mSureBtn;
    private Button mCancelBtn;
    private String mDiaryID;
    private ImageView mImg;

    private OnOpereateFinishListener listener;

    List<Diary> diaries = new ArrayList<>();

    public OperateDiaryDialog(Context context, String opt, int layoutid, boolean isCancelable, boolean isBackCancelable){
        super(context, R.style.MyDialog);
        this.opt = opt;
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(layoutid, null);
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;

        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);//这行一定要写在前面
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);


    }

    public void initView()
    {
        mImg = (ImageView) this.view.findViewById(R.id.my_img);
        mDescribeTv = (TextView) this.view.findViewById(R.id.my_describe);
        mEditTitle = (EditText) this.view.findViewById(R.id.diary_title);
        mEditContent = (EditText) this.view.findViewById(R.id.diary_content);
        mEditTime = (EditText) this.view.findViewById(R.id.diary_time);

        mSureBtn = (Button) this.view.findViewById(R.id.insert_course_btn);
        mCancelBtn = (Button) this.view.findViewById(R.id.cancel_btn);

        mEditTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTitle = editable.toString();
            }
        });

        mEditTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTime = editable.toString();
            }
        });

        mEditContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mContent = editable.toString();
            }
        });

        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (opt.equals("insert"))
                {
                    insertCourse();
                }else{
                    editCourse();
                }
                if (listener!=null){
                    listener.onFinish();
                }
                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        if (opt.equals("insert"))
        {
            initInsertView();
        }else{
            initEditView();
        }

    }

    public void initInsertView(){
        mEditTitle.setText("");
        mEditTime.setText("");
        mEditContent.setText("");
        mImg.setBackgroundResource(R.drawable.insert_course);
        mDescribeTv.setText("编辑日志");
        mSureBtn.setText("添加");
    }

    public void initEditView(){
        if (this.mDiaryID==null){
            return;
        }
        diaries = DBManager.getInstance(getContext()).queryDiarys(
                null,"id =?",new String[]{this.mDiaryID},null,null,null);

        mImg.setBackgroundResource(R.drawable.edit_course);
        mDescribeTv.setText("修改日志");
        mSureBtn.setText("修改");

        if (diaries.size()>0)
        {
            Diary diary = diaries.get(0);
            mEditTitle.setText(diary.getTitle());
            mEditTime.setText(diary.getTime());
            mEditContent.setText(diary.getContent());
        }

    }

    public void setOpt(String opt){
        this.opt = opt;
    }

    public void setParams(String id){
        this.mDiaryID = id;
    }

    public void setOnOpereateFinishListener(OnOpereateFinishListener listener){
        this.listener = listener;
    }

    public void insertCourse(){
        ContentValues values = new ContentValues();
        values.put("title",mTitle);
        values.put("time",mTime);
        values.put("content",mContent);
        DBManager.getInstance(getContext()).insertDiary(values);

    }

    public void editCourse(){
        ContentValues values = new ContentValues();
        values.put("title",mTitle);
        values.put("time",mTime);
        values.put("content",mContent);
        DBManager.getInstance(getContext()).editDiary(this.mDiaryID,values);
    }

    public interface OnOpereateFinishListener{
        public void onFinish();
    }
}