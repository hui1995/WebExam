package com.example.administrator.webexam.Contract.TeacherContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.ReportCard;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public interface QuestionAnalysisContract {

    interface View extends BaseView<Presenter>{



        void initView(ArrayList<AlreadyDoneQ> list,int count);
    }


    interface Presenter extends BasePresenter{


    }
}
