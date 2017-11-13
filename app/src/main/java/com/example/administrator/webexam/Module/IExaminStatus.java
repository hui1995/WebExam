package com.example.administrator.webexam.Module;

import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */

public interface IExaminStatus {


    interface GetEidCallBack{
        void getEid(String eid);
    }
    interface GetAllExaminCallBack{
        void getAll(List<ExaminQuestion> examinQuestions);

    }
    interface GetCountCallBack{

        void getCount(int count);
    }

    interface GetAllQuestionCallBack{

        void getAllquestion(List<RadioQuestions> list);
    }
    interface GetAllSelectCallBack{
        void getAllQuestion(List<JudgementQuestions> list);
    }
    interface GetExaminname{

        void getExaminname(ExaminQuestion examinQuestion);
    }

    interface GetQuestionRadio{

        void get(RadioQuestions radioQuestions);
    }

    interface GetQuestionJudge{

        void get(JudgementQuestions judgementQuestions);

    }



    void saveExamininfo(ExaminQuestion examinQuestion);





    void saveJudgementQuestions(JudgementQuestions judgementQuestions);

    void saveRadioQuestions(RadioQuestions radioQuestions);

 void selectLastId(final GetEidCallBack callBack);
    void updateExaminstgate(String eid,ExaminQuestion examinQuestion);

    void selectExam(boolean isPublic,GetAllExaminCallBack getAllExaminCallBack);
    void selectExam(String tid,GetAllExaminCallBack getAllExaminCallBack);

    void countRadioGet(String objectid,GetCountCallBack countCallBack);
    void countJudmGet(String objectid,GetCountCallBack countCallBack);

    void GetRadioAll(String objectid ,GetAllQuestionCallBack callback);
    void getJudmeAll(String objectid,GetAllSelectCallBack callBack);
    void getExaminname(String eid,GetExaminname examinname);

    void getQuestionRadio(String objectid ,GetQuestionRadio callback);
        void GetQuestionJudge(String objectid,GetQuestionJudge callback);

    void selectExamin(String[] args,GetAllExaminCallBack callBack);


}
