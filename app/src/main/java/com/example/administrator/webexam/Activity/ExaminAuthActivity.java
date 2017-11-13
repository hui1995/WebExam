package com.example.administrator.webexam.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Presenter.TeacherPresenter.ExaminAuthPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.TeacherView.ExaminAuthView;

public class ExaminAuthActivity extends AppCompatActivity {

   private String ObjectId;
    private String OBJECTID ="ObjectId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examin_auth);


        ExaminAuthView examinAuthView = (ExaminAuthView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);

        if (examinAuthView==null){
            examinAuthView = new ExaminAuthView().newsInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),examinAuthView,R.id.content_Frame);


        }
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
        ObjectId= userInfo.getString(OBJECTID,"");
        ExaminStatus examinStatus = new ExaminStatus();
        ClassmateStatus status = new ClassmateStatus();
        new ExaminAuthPresenter(examinAuthView, examinStatus,status, ObjectId);







    }


}
