package com.example.administrator.webexam.Contract.TeacherContract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public interface TeacherMainContract {

    interface IndexView extends BaseView<Presenter> {
        void  noAuto();
        void nextToAddClass();
    }

    interface ClassView extends BaseView<Presenter>{
        void initView(ArrayList<Classmate> list);

        void  noAuto();
        void nextToAddClass();
        void nextToStudentMang();

    }
    interface MeView extends BaseView<Presenter>{


        void initView(String nickname,String phone);
        void GetId(String id);
    }



    interface Presenter extends BasePresenter {



      //  void getTeacherInfo(Context context);

        void startIndex();
        void startClass(Context context);
        void startMe(Context context);
        void  loginout(Context context);
        void isAuto();
        void isAuto2();
        void isAuto3();
        void GetIdInfo();
        void findExaminStatus();


    }
}
