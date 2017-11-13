package com.example.administrator.webexam.Contract.StudentContract;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

/**
 * Created by Administrator on 2017/9/29.
 */

public interface StudentAuthContract {
    interface  View extends BaseView<Presenter>{
        void Toast();


    }


    interface Presenter extends BasePresenter{
void Auth(Context context, String classid, String studentId, String studentname);


    }


}
