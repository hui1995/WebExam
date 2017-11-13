package com.example.administrator.webexam.Contract.TeacherContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

import com.example.administrator.webexam.Module.Data.ReportCard;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/17.
 */

public interface ShowStudentScoreContract {


    interface View extends BaseView<Presenter>{

        void initView(ArrayList<ReportCard> arrayList);

    }


    interface Presenter extends BasePresenter{


    }
}
