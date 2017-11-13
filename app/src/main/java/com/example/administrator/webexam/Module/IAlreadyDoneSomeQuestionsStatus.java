package com.example.administrator.webexam.Module;

import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */

public interface IAlreadyDoneSomeQuestionsStatus {

    interface GetWQuesitonCalBack {

        void get(List<AlreadyDoneQ> list);
    }


    interface GetCount {
        void get(int count);
    }

    void save(AlreadyDoneQ alreadyDoneQ);

    void update(String objetId, AlreadyDoneQ alreadyDoneQ);

    void selectRId(String sid, String rjid, GetWQuesitonCalBack calBack);
    void selectJId(String sid, String rjid, GetWQuesitonCalBack calBack);
    void selectAll(String sid, String rjid, boolean isRadio, GetWQuesitonCalBack calBack);

    void selectAll(String sid, boolean isClass, GetWQuesitonCalBack calBack);

    void selectTen(String sid, boolean isClass, GetWQuesitonCalBack calBack);

    void deleteQuestionWrong(String wid);

    void countWrong(String rjid, GetCount getCount);
    void  selectAllWrongForExamining(String eid,boolean isClass,GetWQuesitonCalBack calBack);

}

