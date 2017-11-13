package com.example.administrator.webexam.Contract.TeacherContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/13.
 */

public interface ExaminAuthContract {



    interface View extends BaseView<Presenter>{


        void init(ArrayList<ExaminQuestion> arrayList);
        void ShowClassinfo(String[] strings,boolean[] booleen);

    }


    interface Presenter extends BasePresenter{



        void getMyClassinfo(String s);
        void setExaminForClass(String s,String eid);




    }
}
