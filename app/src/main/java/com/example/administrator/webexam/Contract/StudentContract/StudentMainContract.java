package com.example.administrator.webexam.Contract.StudentContract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

/**
 * Created by Administrator on 2017/9/26.
 */

public interface StudentMainContract {

    interface IndexView extends BaseView<Presenter>{
        void GetExamin(String eid,String ename);
        void goQuestionWrong(String sid);
    }

    interface ClassView extends BaseView<Presenter>{
        void toAuth();
        void ToastAuthing();
        void ToastAuthgood();


    }
    interface MeView extends BaseView<Presenter>{
        void initView(String nickname,String phone);

        void toAuth();
        void ToastAuthing();
        void ToastAuthgood();
        void GetId(String id);


    }



    interface Presenter extends BasePresenter{

        void  loginout(Context context);
        void startIndex();
        void startClass();
        void startMe(Context context);
        void isauth();
        void getStudentId();

        void isgoClass(Context context);
      void  getExaminIDANdName();
        void GetIdInfo();
    }
}
