package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.ReportCardStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Presenter.TeacherPresenter.ShowStudentScorePresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.TeacherView.ShowStudentScoreView;

public class ShowStudentScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_score);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String cid = bundle.getString("cid");
        String eid = bundle.getString("eid");

        ShowStudentScoreView showStudentScoreView = (ShowStudentScoreView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);
        if (showStudentScoreView==null){
            showStudentScoreView= new ShowStudentScoreView().newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),showStudentScoreView,R.id.content_Frame);
        }

        ReportCardStatus reportCardStatus = new ReportCardStatus();
        StudentStatus iStudentStatus = new StudentStatus();

        new ShowStudentScorePresenter(showStudentScoreView, eid, cid, reportCardStatus, iStudentStatus);


    }
}
