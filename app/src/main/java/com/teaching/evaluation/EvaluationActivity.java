package com.teaching.evaluation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.teaching.evaluation.manager.LoginManager;
import com.teaching.evaluation.view.EditEvaluaDialog;

public class EvaluationActivity extends Activity {

    Button mEvaEditBtn;
    EditEvaluaDialog mEditEvaDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);
        initView();
    }

    public void initView(){
        mEditEvaDialog = new EditEvaluaDialog(this,R.layout.edit_evaluation,true,true);
        mEvaEditBtn = (Button)findViewById(R.id.eva_edit_btn);
        mEvaEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditEvaDialog.show();
            }
        });
    }
}
