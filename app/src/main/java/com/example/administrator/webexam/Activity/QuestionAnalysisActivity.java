package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.impl.AlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Presenter.TeacherPresenter.QuestionAnalysisPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.TeacherView.QuestionAnalysisView;

public class QuestionAnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_analysis);
        Intent intent = getIntent();

        Bundle bundle =intent.getExtras();
        String eid =bundle.getString("eid");
        String cid =bundle.getString("cid");


        QuestionAnalysisView questionAnalysisView = (QuestionAnalysisView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);

        if (questionAnalysisView==null){

questionAnalysisView = new QuestionAnalysisView().newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),questionAnalysisView,R.id.content_Frame);

        }
        ExaminStatus iExaminStatus = new ExaminStatus();
        IAlreadyDoneSomeQuestionsStatus iAstatus = new AlreadyDoneSomeQuestionsStatus();
        IStudentStatus studentStatus = new StudentStatus();

        new QuestionAnalysisPresenter(questionAnalysisView, eid, iExaminStatus, iAstatus, studentStatus, cid);
    }
}
