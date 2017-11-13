package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Presenter.TeacherPresenter.StudentMangPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.TeacherView.ClassTeacherView;
import com.example.administrator.webexam.View.TeacherView.StudentMangView;

public class StudentMangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mang);
        StudentMangView studentMangView = (StudentMangView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();

        String cid = bundle.getString(ClassTeacherView.CLASS_ID);

        if (studentMangView==null){
            studentMangView = StudentMangView.newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),studentMangView,R.id.content_Frame);
        }

        StudentStatus studentStatus = new StudentStatus();
        ClassmateStatus classmateStatus = new ClassmateStatus();
        new StudentMangPresenter(studentMangView, studentStatus, cid);
    }
}
