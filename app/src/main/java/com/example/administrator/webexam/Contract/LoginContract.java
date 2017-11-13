package com.example.administrator.webexam.Contract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface LoginContract {



    interface View extends BaseView<Presenter>{


        void setMessage();
        void ToastNoUser();
        void ToastPasswdfail();
        void goStudentIndex();
        void goTeacherIndex();
        void goAdminIndex();




    }



    interface Presenter extends BasePresenter{

void getLogininfo(Context context,String phone,String passwd);
        void checkInStudent(Context context, String phone, String passwd);
        void checkInTeacher(Context context,String phone,String passwd);
        void checkInAdmin(Context context,String phone,String passwd);

    }
}
