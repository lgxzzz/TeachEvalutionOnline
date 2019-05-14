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
import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.manager.DBManager;
import com.teaching.evaluation.manager.LoginManager;

import java.util.ArrayList;
import java.util.List;


public class OpreateCourseDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;

    private EditText mEditCourseName;
    private EditText mEditScore;
    private EditText mEditHour;
    private EditText mEditTime;
    private EditText mEditAchPoint;
    private EditText mEditPlace;
    private TextView mDescribeTv;
    private Spinner mCourseSp;

    private String mTime;
    private String mHour;
    private String mName;
    private String mScore;
    private String mAchPoint;
    private String mPlace;
    private String mDescribe;

    private String opt="insert";
    private String tch_name;
    private Button mSureBtn;
    private Button mCancelBtn;

    private ImageView mImg;

    List<Course> courses = new ArrayList<>();
    Course course;

    public OpreateCourseDialog(Context context, String opt,int layoutid, boolean isCancelable, boolean isBackCancelable){
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
        mCourseSp = (Spinner) this.view.findViewById(R.id.spinner_course);
        mDescribeTv = (TextView) this.view.findViewById(R.id.my_describe);
        mEditCourseName = (EditText) this.view.findViewById(R.id.my_name);
        mEditScore = (EditText) this.view.findViewById(R.id.my_score);
        mEditHour = (EditText) this.view.findViewById(R.id.my_hour);
        mEditTime = (EditText) this.view.findViewById(R.id.my_time);
        mEditAchPoint = (EditText) this.view.findViewById(R.id.my_achpoint);
        mEditPlace = (EditText) this.view.findViewById(R.id.my_place);

        mSureBtn = (Button) this.view.findViewById(R.id.insert_course_btn);
        mCancelBtn = (Button) this.view.findViewById(R.id.cancel_btn);

        mEditCourseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mName = editable.toString();
            }
        });

        mEditScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mScore = editable.toString();
            }
        });

        mEditHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mHour = editable.toString();
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

        mEditAchPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mAchPoint = editable.toString();
            }
        });

        mEditPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPlace = editable.toString();
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

                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        User user = LoginManager.getInstance(getContext()).getUser();

        courses = DBManager.getInstance(getContext()).queryCourse(
                null,"tch_name =?",new String[]{user.getName()},null,null,null);

        if (opt.equals("insert"))
        {
            initInsertView();

        }else if(courses.size()==0){

            initInsertView();
            mDescribeTv.setText("未录入过课程，请添加！");
        }else{

            initEditView();
        }

    }

    public void initInsertView(){
        mImg.setBackgroundResource(R.drawable.insert_course);
        mDescribeTv.setText("请输入课程信息");
        mCourseSp.setVisibility(View.GONE);
        mSureBtn.setText("录入");
    }

    public void initEditView(){
        mImg.setBackgroundResource(R.drawable.edit_course);
        mDescribeTv.setText("请选择修改的课程");
        mCourseSp.setVisibility(View.VISIBLE);
        mSureBtn.setText("修改");

        String[] spinnerItems = new String[courses.size()];
        for (int i= 0;i<courses.size();i++){
            spinnerItems[i]=courses.get(i).getName();
        }
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                R.layout.simple_spinner_item, spinnerItems);
        //下拉的样式res
        spinnerAdapter.setDropDownViewResource(R.layout.simple_expandable_list_item_1);
        //绑定 Adapter到控件
        mCourseSp.setAdapter(spinnerAdapter);
        mCourseSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                tv.setTextColor(Color.BLACK);
                course = courses.get(i);
                mEditCourseName.setText(course.getName());
                mEditScore.setText(course.getCredit());
                mEditAchPoint.setText(course.getAch_point());
                mEditHour.setText(course.getHour());
                mEditPlace.setText(course.getPlace());
                mEditTime.setText(course.getTime());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setParams(String tch_name){
        this.tch_name = tch_name;
    }

    public void insertCourse(){
        ContentValues values = new ContentValues();
        values.put("course_name",mName);
        values.put("course_credit",mScore);
        values.put("course_hour",mHour);
        values.put("course_time",mTime);
        values.put("ach_point",mAchPoint);
        values.put("place",mPlace);
        values.put("tch_name",tch_name);
        DBManager.getInstance(getContext()).insertCourse(values);
    }

    public void editCourse(){
        ContentValues values = new ContentValues();
        values.put("course_name",mName);
        values.put("course_credit",mScore);
        values.put("course_hour",mHour);
        values.put("course_time",mTime);
        values.put("ach_point",mAchPoint);
        values.put("place",mPlace);
        values.put("tch_name",course.getTch_name());
        DBManager.getInstance(getContext()).editCourse(course.getTch_name(),course.getName(),values);
    }
}