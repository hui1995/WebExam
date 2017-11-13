package com.example.administrator.webexam.Activity;


import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.webexam.Adapter.ViewPagerAdapter;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.impl.ClassmateStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.Presenter.StudentPresenter.StudentMainPresenter;
import com.example.administrator.webexam.Presenter.TeacherPresenter.TeacherMainPresenter;
import com.example.administrator.webexam.R;

import com.example.administrator.webexam.View.StudentView.ClassStudentView;
import com.example.administrator.webexam.View.StudentView.IndexStudentView;
import com.example.administrator.webexam.View.StudentView.MeStudentView;
import com.example.administrator.webexam.View.TeacherView.ClassTeacherView;
import com.example.administrator.webexam.View.TeacherView.IndexTeacherView;
import com.example.administrator.webexam.View.TeacherView.MeTeacherView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @Bind(R.id.viewpger)
    ViewPager viewpger;
    @Bind(R.id.navigation)
    BottomNavigationView navigation;
    @Bind(R.id.container)
    LinearLayout container;
    private MenuItem menuItem;
    private String OBJECTID ="ObjectId";
    private String LOGIN_TYPE="login_type";
    private IndexStudentView indexStudentView;
    private ClassStudentView classStudentView;
    private MeStudentView meStudentView;
    private IndexTeacherView indexTeacherView;
    private ClassTeacherView classTeacherView;
    private MeTeacherView meTeacherView;

    private String ObjectId;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //  mTextMessage.setText(R.string.title_home);
                    viewpger.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    //   mTextMessage.setText(R.string.title_dashboard);
                    viewpger.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    //    mTextMessage.setText(R.string.title_notifications);
                    viewpger.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
  ObjectId= userInfo.getString(OBJECTID,"");
        //  mTextMessage = (TextView) findViewById(R.id.message);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//      navigation.setItemTextColor(null);
        navigation.setItemIconTintList(null);
       int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.black_overlay),
                getResources().getColor(R.color.blue_btn)
        };
        ColorStateList cs1 = new ColorStateList(states,colors);
        navigation.setItemTextColor(cs1);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




      viewpger.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              if (menuItem!=null){
                  menuItem.setCheckable(true);
              }else{
                  navigation.getMenu().getItem(0).setCheckable(true);
              }

              menuItem = navigation.getMenu().getItem(position);
              menuItem.setCheckable(true);
          }



          @Override
          public void onPageSelected(int position) {

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }


      });

//viewpger.setOffscreenPageLimit(3);


        viewpger.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });



        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString(LOGIN_TYPE);

        if (type.equals("student")) {

            setStudentMain();

        } else if (type.equals("teacher")) {

            setTeacherMain();

        }

    }

    private void setStudentMain() {
indexStudentView  = IndexStudentView.newInstance();
        classStudentView = ClassStudentView.newInstance();
        meStudentView = MeStudentView.newInstance();
        setupViewPage(viewpger,indexStudentView,classStudentView,meStudentView);
        StudentStatus studentStatus = new StudentStatus();
        ExaminStatus examinStatus = new ExaminStatus();

        new StudentMainPresenter(indexStudentView,classStudentView,meStudentView, studentStatus, examinStatus, ObjectId);

    }


    private void setTeacherMain() {

        indexTeacherView = IndexTeacherView.newsInstance();
        classTeacherView = ClassTeacherView.newsInstance();
        meTeacherView = MeTeacherView.newsInstance();
        setupViewPage(viewpger,indexTeacherView,classTeacherView,meTeacherView);

        TeacherStatus teacherStatus = new TeacherStatus();
        ClassmateStatus classmateStatus = new ClassmateStatus();
        ExaminStatus examinQuestion = new ExaminStatus();

new TeacherMainPresenter(indexTeacherView,classTeacherView,meTeacherView, teacherStatus, classmateStatus,ObjectId, examinQuestion);
    }





    private void setupViewPage(ViewPager viewPager, Fragment fragment1,Fragment fragment2,Fragment fragment3){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(fragment1);
        adapter.addFragment(fragment2);
        adapter.addFragment(fragment3);

        viewPager.setAdapter(adapter);
    };





}
