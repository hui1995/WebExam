package com.example.administrator.webexam.Contract.StudentContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/17.
 */

public interface QuestionWrongContract {


    interface View extends BaseView<Presenter> {

        void initView(ArrayList<AlreadyDoneQ> arrayList, int count);

        void questionINfo(String object,String youranswer,boolean isRadio);

    }
    interface Presenter extends BasePresenter{

        void getQuestionInfo(String objectid,boolean isRadio);



    }
}
