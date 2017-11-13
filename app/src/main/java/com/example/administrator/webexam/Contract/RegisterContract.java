package com.example.administrator.webexam.Contract;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

/**
 * Created by Administrator on 2017/9/21.
 */

public interface RegisterContract {



    interface RegView extends BaseView<Presenter>{


    }
    interface RegPhoneView extends BaseView<Presenter>{

        void getSignInfo();
        void  setCodefail();
        void nextgetcode(Boolean isTeacher);
        void toastphoneexit();
        void gonextindex();
    void  test(String phone,String phone2);

    }


    interface RegTeacherView extends BaseView<Presenter>{
        void ToastNoName();
        void  ToastNoUniversity();
        void ToasNoTeacherID();
        void signupgood();
        void signupfail();

    }
    interface RegStudentView extends BaseView<Presenter>{

        void signupgood();
        void signupfail();


    }

    interface Presenter extends BasePresenter{


        void saveSignup_Type(boolean isTeacher, Context context);
       boolean  checkPhone(String phone);
        boolean  checkPasswd(String passwd,String passwd2);
        void getcode(String phone,Context context);
        boolean isNoSignupPhone(String phone);
        boolean isNoEmptyNickname(String nickname);
        void checkSMS(Context context,String smscode);
        void signupStudent();
        void signupTeacher();
        boolean checkinfo(String name,String teacherid,String univerisity);
        void  selectphoneStudent();
        void  selectphoneTeacher();



    }


}
