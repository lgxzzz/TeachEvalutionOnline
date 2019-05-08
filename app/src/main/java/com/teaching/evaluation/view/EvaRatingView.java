package com.teaching.evaluation.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.teaching.evaluation.R;

/**
 * Created by lgx on 2019/5/5.
 */

public class EvaRatingView extends LinearLayout{

    ProgressBar mProgressBarFive;
    ProgressBar mProgressBarFour;
    ProgressBar mProgressBarThree;
    ProgressBar mProgressBarTwo;
    ProgressBar mProgressBarOne;

    int[] mRatingNumber = new int[]{0,0,0,0,0};

    public EvaRatingView(Context context) {
        super(context);
    }

    public EvaRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.rating_show,this,true);
        initView();
    }

    public EvaRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setParams(int count,int[] mRating){
       mRatingNumber = mRating;
       mProgressBarFive.setMax(count);
       mProgressBarFour.setMax(count);
       mProgressBarThree.setMax(count);
       mProgressBarTwo.setMax(count);
       mProgressBarOne.setMax(count);
       mProgressBarFive.setProgress(mRatingNumber[4]);
       mProgressBarFour.setProgress(mRatingNumber[3]);
       mProgressBarThree.setProgress(mRatingNumber[2]);
       mProgressBarTwo.setProgress(mRatingNumber[1]);
       mProgressBarOne.setProgress(mRatingNumber[0]);
    }

    public void initView(){
        mProgressBarFive = (ProgressBar)findViewById(R.id.progressbar_five);
        mProgressBarFour = (ProgressBar)findViewById(R.id.progressbar_four);
        mProgressBarThree = (ProgressBar)findViewById(R.id.progressbar_three);
        mProgressBarTwo = (ProgressBar)findViewById(R.id.progressbar_two);
        mProgressBarOne = (ProgressBar)findViewById(R.id.progressbar_one);
    }
}
