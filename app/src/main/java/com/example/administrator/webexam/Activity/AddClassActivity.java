package com.example.administrator.webexam.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Presenter.TeacherPresenter.AddClasspresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.TeacherView.AddClassView;

public class AddClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        AddClassView addClassView = (AddClassView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);

        if (addClassView==null){
            addClassView = AddClassView.newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),addClassView,R.id.content_Frame);
        }

        ClassmateStatus classmateStatus = new ClassmateStatus();
        new AddClasspresenter(addClassView, classmateStatus);
    }
}
