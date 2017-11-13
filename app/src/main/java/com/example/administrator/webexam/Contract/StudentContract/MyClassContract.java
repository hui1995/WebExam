package com.example.administrator.webexam.Contract.StudentContract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Student;

/**
 * Created by Administrator on 2017/10/14.
 */

public interface MyClassContract {


    interface View extends BaseView<Presenter>{

        void setClassInfo(Student student);




        void ToastNoExamin();
        void GOExamining(String eid,String ename,boolean isExamining);
        void setScore(int i,int i2);
        void showQuestionWrong(String sid);

        void ToastNoAnser();
        void GOShowAnswer(String eid,String sid,String ename);
    }

    interface Presenter extends BasePresenter{


        void getExaminid();

boolean checkHadExaminNow();

        void getQuestionInfo();
        void getExaminAnswer();
        void saveCid(Context context,String cid);
        }



}
