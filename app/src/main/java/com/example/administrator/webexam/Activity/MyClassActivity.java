package com.example.administrator.webexam.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Module.impl.ReportCardStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.Presenter.StudentPresenter.MyClassPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.StudentView.MyClassView;

public class MyClassActivity extends AppCompatActivity {
    private String OBJECTID ="ObjectId";
    private String LOGIN_TYPE="login_type";
    private String ObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
        ObjectId= userInfo.getString(OBJECTID,"");

        MyClassView myClassView = (MyClassView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);

        if (myClassView==null){

            myClassView= new MyClassView().newsInsantace();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),myClassView,R.id.content_Frame);

        }

        StudentStatus studentStatus = new StudentStatus();
        ClassmateStatus classmateStatus = new ClassmateStatus();
        TeacherStatus teacherStatus = new TeacherStatus();
        ExaminStatus examinStatus = new ExaminStatus();
        ReportCardStatus reportCardStatus = new ReportCardStatus();

        new MyClassPresenter(myClassView,ObjectId, studentStatus, classmateStatus, teacherStatus,examinStatus, reportCardStatus);


    }
}
