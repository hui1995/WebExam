package com.example.administrator.webexam.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.Data.University;
import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.Module.impl.UniversityStatus;
import com.example.administrator.webexam.Presenter.StudentPresenter.StudentAuthPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.StudentView.StudentAuthView;

public class StudentAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_auth);

        StudentAuthView studentAuthView = (StudentAuthView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);
        if (studentAuthView==null){
            studentAuthView = StudentAuthView.newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),studentAuthView,R.id.content_Frame);


        }

        StudentStatus studentStatus = new StudentStatus();
        new StudentAuthPresenter(studentAuthView, studentStatus);



    }
}
