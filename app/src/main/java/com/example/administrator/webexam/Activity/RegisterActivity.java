package com.example.administrator.webexam.Activity;



import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.webexam.Adapter.ViewagerAdapter;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.Module.impl.UniversityStatus;
import com.example.administrator.webexam.Presenter.RegisterPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.View.Register.RegisterPhone;
import com.example.administrator.webexam.View.Register.RegisterView;
import com.example.administrator.webexam.View.Register.StudentRegisterView;
import com.example.administrator.webexam.View.Register.TeacherRegisterView;

import java.util.jar.Manifest;

public class RegisterActivity extends AppCompatActivity {
    private RegisterPhone registerPhone;
    private RegisterView registerView;
    private TeacherRegisterView teacherRegisterView;
    private StudentRegisterView studentRegisterView;



    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        viewPager = (ViewPager) findViewById(R.id.viewpager);

initView();


        registerView.setOnButtonClick(new RegisterView.OnButtonClick() {
            @Override
            public void onClick(View view) {





viewPager.setCurrentItem(1);
            }
        });
registerPhone.setOnButtonClick(new RegisterPhone.OnButtonClick() {
    @Override
    public void onClick(View view, boolean isTeacher) {
        if (isTeacher){
            viewPager.setCurrentItem(2);
        }else{
            viewPager.setCurrentItem(3);

        }
    }
});

    }



    private void initView(){

        registerView = RegisterView.newsInstance();
        registerPhone =RegisterPhone.newsInstance();

        teacherRegisterView= TeacherRegisterView.newsInsante();
        studentRegisterView = StudentRegisterView.newsInsante();
        setupViewPager(viewPager);
        setPresenter();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

    }

    StudentStatus studentStatus = new StudentStatus();
    TeacherStatus teacherStatus = new TeacherStatus();
    UniversityStatus universityStatus = new UniversityStatus();
//将fragment传入Presnter
    private void setPresenter(){

        new RegisterPresenter(registerView,registerPhone,teacherRegisterView,studentRegisterView,studentStatus, teacherStatus, universityStatus);
    }
//将framgnet放入viewager
    private void setupViewPager(ViewPager viewPager){

        ViewagerAdapter adapter = new ViewagerAdapter(getSupportFragmentManager());
        adapter.addFragment(registerView);
        adapter.addFragment(registerPhone);
        adapter.addFragment(teacherRegisterView);
        adapter.addFragment(studentRegisterView);
        viewPager.setAdapter(adapter);
    }
}
