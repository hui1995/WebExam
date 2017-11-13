package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Module.impl.ReportCardStatus;
import com.example.administrator.webexam.Presenter.StudentPresenter.HistoryScorePresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.StudentView.HistoryScoreView;

public class HistoryScoreActivity extends AppCompatActivity {
    private String ObjectId;
    private String OBJECTID ="ObjectId";
    private String LOGIN_TYPE="login_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_score);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        Boolean isClass = b.getBoolean("isClass");
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
        ObjectId= userInfo.getString(OBJECTID,"");

        HistoryScoreView historyScoreView = (HistoryScoreView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);


if(historyScoreView==null){
    historyScoreView= new HistoryScoreView().newsInstance(isClass);
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),historyScoreView,R.id.content_Frame);
}

ReportCardStatus reportCardStatus = new ReportCardStatus();
        ExaminStatus examinStatus = new ExaminStatus();
new HistoryScorePresenter(historyScoreView,isClass, reportCardStatus,examinStatus,ObjectId);
    }
}
