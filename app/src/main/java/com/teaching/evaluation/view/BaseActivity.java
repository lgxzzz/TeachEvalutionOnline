package com.teaching.evaluation.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teaching.evaluation.TeachingEvaApplication;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TeachingEvaApplication.getInstance().addActivity(this);
    }

}
