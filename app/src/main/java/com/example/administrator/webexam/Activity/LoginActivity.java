package com.example.administrator.webexam.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.AdminStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.Presenter.LoginPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




       LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_Frame);


        if (loginFragment== null){

        loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    loginFragment,R.id.content_Frame);
        }

        StudentStatus studentStatus = new StudentStatus();
        TeacherStatus teacherStatus = new TeacherStatus();
        AdminStatus adminStatus = new AdminStatus();



       new LoginPresenter(loginFragment, studentStatus, teacherStatus, adminStatus);
    }
}
