package com.example.administrator.webexam.Contract.TeacherContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.Classmate;

/**
 * Created by Administrator on 2017/10/13.
 */

public interface ClassMainTeacherContract {

    interface View extends BaseView<Presenter>{

        void setAllStudent(int i);
        void setTrueStudent(int i);
        void setInfoForClass(Classmate classmate);
        void setNowExamin(String s,String eid);
        void setInfoExamin(int count,int high,int low,int ave);

        void goQustionAnanlsis(String eid,String cid);
        void goGrankScore(String eid,String cid);



    }


    interface Presenter extends BasePresenter{
            void updateExaming(String eid);


        void getQustionInfoTo();
        void getGrankScoreTo();



    }
}
