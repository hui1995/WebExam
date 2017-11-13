package com.example.administrator.webexam.Contract.TeacherContract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

import com.example.administrator.webexam.Module.Data.Student;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface StudentMangContract {


    interface View extends BaseView<Presenter>{
    void    initView();
        void addIteminfo(ArrayList<Student> students);


    }

    interface Presenter extends BasePresenter{


     void   getStudentNOAuth();
        void updateAuthTrue(String sid);
        void updateAuthFales(String sid);
    }
}
