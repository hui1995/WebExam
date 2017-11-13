package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.impl.AlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Presenter.StudentPresenter.QuestionWrongPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.StudentView.QuestionWrongView;

public class QuestionWrongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_wrong);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String sid = bundle.getString("sid");
        String examinid= bundle.getString("examinid");
        boolean isClass = bundle.getBoolean("isClass");


        QuestionWrongView questionWrongView = (QuestionWrongView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);


        if (questionWrongView==null){
            questionWrongView = new QuestionWrongView().newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),questionWrongView,R.id.content_Frame);
        }


        AlreadyDoneSomeQuestionsStatus alreadyDoneSomeQuestionsStatus = new AlreadyDoneSomeQuestionsStatus();
        ExaminStatus examinStatus = new ExaminStatus();

        new QuestionWrongPresenter(questionWrongView,sid,isClass,alreadyDoneSomeQuestionsStatus,examinStatus,examinid);



    }
}
