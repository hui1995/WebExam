package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Module.impl.ReportCardStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Presenter.TeacherPresenter.ClassMainTeacherPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.TeacherView.ClassMainTeacherView;
import com.example.administrator.webexam.View.TeacherView.ClassTeacherView;

public class ClassMainTeacherActivity extends AppCompatActivity {
    private String OBJECTID ="ObjectId";
    private String LOGIN_TYPE="login_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_teacher);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String classid =bundle.getString(ClassTeacherView.CLASS_ID,"");



        ClassMainTeacherView classMainTeacherView = (ClassMainTeacherView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);


        if (classMainTeacherView==null){
            classMainTeacherView = new ClassMainTeacherView().newsInstance(classid);


            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),classMainTeacherView,R.id.content_Frame);

        }

        StudentStatus studentStatus = new StudentStatus();
        ClassmateStatus status = new ClassmateStatus();
        ExaminStatus examinStatus = new ExaminStatus();
        ReportCardStatus reportCardStatus = new ReportCardStatus();
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
      String  ObjectId= userInfo.getString(OBJECTID,"");

        new ClassMainTeacherPresenter(classMainTeacherView,studentStatus, status, classid,examinStatus, reportCardStatus, ObjectId);
    }
}
