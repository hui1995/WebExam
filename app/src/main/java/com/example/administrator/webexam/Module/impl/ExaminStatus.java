package com.example.administrator.webexam.Module.impl;

import android.content.ContentValues;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArraySet;
import android.util.Log;

import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.IExaminStatus;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/10/11.
 */

public class ExaminStatus implements IExaminStatus{
    private static String eid=null;




    @Override
    public void saveExamininfo(ExaminQuestion examinQuestion) {
        examinQuestion.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    eid =s;
                }else {

                }
            }
        });




    }

    @Override
    public void saveJudgementQuestions(JudgementQuestions judgementQuestions) {

        judgementQuestions.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){

                }else {

                }
            }
        });

    }

    @Override
    public void saveRadioQuestions(RadioQuestions radioQuestions) {
        radioQuestions.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){



                }else {

                }
            }
        });


    }




    @Override
    public void selectLastId(GetEidCallBack callBack) {
        callBack.getEid(eid);
    }

    @Override
    public void updateExaminstgate(String eid, ExaminQuestion examinQuestion) {
        examinQuestion.update(eid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else{

                }
            }
        });
    }

    @Override
    public void selectExam(boolean isPublic, final GetAllExaminCallBack getAllExaminCallBack) {
        BmobQuery<ExaminQuestion> query= new BmobQuery<ExaminQuestion>();
        query.addWhereEqualTo("isPublic",isPublic);

        query.setLimit(100);
        query.findObjects(new FindListener<ExaminQuestion>() {
            @Override
            public void done(List<ExaminQuestion> list, BmobException e) {
                if (e==null){
                    for (ExaminQuestion examinQuestion :list){
                        examinQuestion.getTid();
                        examinQuestion.getExaminname();
                        examinQuestion.isFinish();
                        examinQuestion.isPublic();
                        examinQuestion.isExamining();
                    }
                    getAllExaminCallBack.getAll(list);

                }else{
                    list=null;
                    getAllExaminCallBack.getAll(list);

                }

            }


        });

    }

    @Override
    public void selectExam(String tid, final GetAllExaminCallBack getAllExaminCallBack) {
        BmobQuery<ExaminQuestion> bmobQuery = new BmobQuery<ExaminQuestion>();

        bmobQuery.addWhereEqualTo("tid",tid);
        bmobQuery.setLimit(100);
        bmobQuery.findObjects(new FindListener<ExaminQuestion>() {
            @Override
            public void done(List<ExaminQuestion> list, BmobException e) {
                if (e==null){
                    for (ExaminQuestion examinQuestion :list){
                        examinQuestion.getExaminname();
                        examinQuestion.isPublic();
                        examinQuestion.isFinish();
                        examinQuestion.getTid();
                        examinQuestion.getObjectId();
                        examinQuestion.isExamining();
                    }
                    getAllExaminCallBack.getAll(list);

                }else{
                    list=null;
                    getAllExaminCallBack.getAll(list);
                }
            }
        });
    }

    @Override
    public void countRadioGet(String objectid, final GetCountCallBack countCallBack) {

        BmobQuery<RadioQuestions> rquery = new BmobQuery<RadioQuestions>();
        rquery.addWhereEqualTo("eid",objectid);
        rquery.count(RadioQuestions.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e==null){
                    countCallBack.getCount(integer);


                }else{
                    countCallBack.getCount(0);

                }
            }
        });
    }

    @Override
    public void countJudmGet(String objectid, final GetCountCallBack countCallBack) {


        BmobQuery<JudgementQuestions> query = new BmobQuery<JudgementQuestions>();
        query.addWhereEqualTo("eid",objectid);
        query.count(JudgementQuestions.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e==null){
                    countCallBack.getCount(integer);
                }else{
                    countCallBack.getCount(0);
                }
            }
        });
    }

    @Override
    public void GetRadioAll(String objectid, final GetAllQuestionCallBack callback) {
        BmobQuery<RadioQuestions> query = new BmobQuery<RadioQuestions>();
ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setObjectId(objectid);
        query.addWhereEqualTo("eid",examinQuestion);
        query.findObjects(new FindListener<RadioQuestions>() {
            @Override
            public void done(List<RadioQuestions> list, BmobException e) {
                if (e==null){
                    for (RadioQuestions radioQuestions :list){
                        radioQuestions.getObjectId();
                        radioQuestions.getQuestion();
                        radioQuestions.getOption1();
                        radioQuestions.getOption2();
                        radioQuestions.getOption3();
                        radioQuestions.getOption4();
                        radioQuestions.getAnalytic();
                        radioQuestions.getAnalytic();

                    }
                    callback.getAllquestion(list);
                }else{
                    list=null;
                    callback.getAllquestion(list);

                }
            }
        });
    }

    @Override
    public void getJudmeAll(String objectid, final GetAllSelectCallBack callBack) {


        BmobQuery<JudgementQuestions> query = new BmobQuery<JudgementQuestions>();

        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setObjectId(objectid);
        query.addWhereEqualTo("eid",examinQuestion);
        query.findObjects(new FindListener<JudgementQuestions>() {
            @Override
            public void done(List<JudgementQuestions> list, BmobException e) {
                if (e==null){
                    for (JudgementQuestions judgementQuestions :list){
                        judgementQuestions.getObjectId();
                        judgementQuestions.getQuestion();
                        judgementQuestions.getAnalytic();
                        judgementQuestions.getAnswer();

                    }

                    callBack.getAllQuestion(list);
                }else {
                    list=null;
                    callBack.getAllQuestion(list);
                }
            }
        });

    }

    @Override
    public void getExaminname(String eid, GetExaminname examinname) {

    }

    @Override
    public void getQuestionRadio(String objectid, final GetQuestionRadio callback) {


     BmobQuery<RadioQuestions> query = new BmobQuery<RadioQuestions>();
        query.getObject(objectid, new QueryListener<RadioQuestions>() {
            @Override
            public void done(RadioQuestions radioQuestions, BmobException e) {
                if (e==null){
                    callback.get(radioQuestions);
                }
            }
        });

    }

    @Override
    public void GetQuestionJudge(String objectid, final GetQuestionJudge callback) {


        BmobQuery<JudgementQuestions> query = new BmobQuery<JudgementQuestions>();
        query.getObject(objectid, new QueryListener<JudgementQuestions>() {
            @Override
            public void done(JudgementQuestions judgementQuestions, BmobException e) {
                if (e==null){
                    callback.get(judgementQuestions);

                }
            }
        });

    }

    @Override
    public void selectExamin(String[] args, final GetAllExaminCallBack callBack) {
BmobQuery<ExaminQuestion> query = new BmobQuery<ExaminQuestion>();
        query.addWhereEqualTo("ObjectId", Arrays.asList(args));
        query.findObjects(new FindListener<ExaminQuestion>() {
            @Override
            public void done(List<ExaminQuestion> list, BmobException e) {
                if (e==null){
                    callBack.getAll(list);
                }else{
                    list=null;
                    callBack.getAll(list);
                }
            }
        });

    }








    /*@Override
    public void selectAll(final GetExaminAllInfo callback) {
        selectAll(new GetExaminAllInfo() {
            @Override
            public void GetAll(ExaminQuestion examinQuestion, ArrayList<RadioQuestions> radioQuestionses, ArrayList<JudgementQuestions> judgementQuestionses) {
                callback.GetAll(examinQuestion,radioQuestionses,judgementQuestionses);
            }
        });
    }*/
}
