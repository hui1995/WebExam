package com.example.administrator.webexam.Contract.TeacherContract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface AddClassContract {

    interface View extends BaseView<Presenter>{

    }

    interface  Presenter extends BasePresenter{



        void saveclass(Context context,String classname, String grade, String profession);
    }
}
