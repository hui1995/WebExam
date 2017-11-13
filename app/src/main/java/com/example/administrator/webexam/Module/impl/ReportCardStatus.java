package com.example.administrator.webexam.Module.impl;

import android.util.Log;
import android.widget.Toast;

import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IReportCardStatus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ReportCardStatus implements IReportCardStatus {

    @Override
    public void saveReportCard(ReportCard reportCard) {

        reportCard.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){

                }else {

                }
            }
        });

    }

    @Override
    public void haddoexamin(String sid, String eid, final HadExaminCallBack hadExaminCallBack) {
        BmobQuery<ReportCard> query1 = new BmobQuery<ReportCard>();

        ExaminQuestion examinQuestion =new ExaminQuestion();
        examinQuestion.setObjectId(eid);
        query1.addWhereEqualTo("eid",examinQuestion);
        BmobQuery<ReportCard> query2 = new BmobQuery<ReportCard>();


       Student student = new Student();
        student.setObjectId(sid);

        query2.addWhereEqualTo("sid",student);
        List<BmobQuery<ReportCard>> queries = new ArrayList<BmobQuery<ReportCard>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<ReportCard> query = new BmobQuery<ReportCard>();
        query.and(queries);
        query.order("-updatedAt");
        query.include("sid");
        query.setLimit(1);
        query.findObjects(new FindListener<ReportCard>() {
            @Override
            public void done(List<ReportCard> list, BmobException e) {
                if (e==null){

                    if (list.size()==0){
                        hadExaminCallBack.isDo(false);
                    }
                hadExaminCallBack.isDo(true);
                }else {
                    hadExaminCallBack.isDo(true);

                }
            }
        });

    }

    @Override
    public void getReportList(String sid, boolean isClass, final HadReportLast callback) {



        BmobQuery<ReportCard> query1 = new BmobQuery<ReportCard>();

        Student student = new Student();
        student.setObjectId(sid);
        query1.addWhereEqualTo("sid",student);
        BmobQuery<ReportCard> query2 = new BmobQuery<ReportCard>();
        query2.addWhereEqualTo("isClassExamin",isClass);
        List<BmobQuery<ReportCard>> queries = new ArrayList<BmobQuery<ReportCard>>();
queries.add(query1);
        queries.add(query2);
        BmobQuery<ReportCard> query = new BmobQuery<ReportCard>();
        query.and(queries);
        query.order("-updatedAt");
        query.include("eid");
        query.setLimit(100);
        query.findObjects(new FindListener<ReportCard>() {
            @Override
            public void done(List<ReportCard> list, BmobException e) {
                if (e==null){
                    callback.get(list);
                }else {
                    list=null;
                    callback.get(list);

                }
            }
        });


    }

    @Override
    public void haddoexamin(String eid, HadReportLast callBack) {

    }

    @Override
    public void haddoexamin(String eid, String cid, final HadReportLast callBack) {
        Log.i("KK",eid+"GGG"+cid+"GG");

        BmobQuery<ReportCard> query1 = new BmobQuery<ReportCard>();

        ExaminQuestion examinQuestion =new ExaminQuestion();
        examinQuestion.setObjectId(eid);
        query1.addWhereEqualTo("eid",examinQuestion);
        BmobQuery<ReportCard> query2 = new BmobQuery<ReportCard>();


        Classmate classmate = new Classmate();
        classmate.setObjectId(cid);

        query2.addWhereEqualTo("cid",classmate);
        List<BmobQuery<ReportCard>> queries = new ArrayList<BmobQuery<ReportCard>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<ReportCard> query = new BmobQuery<ReportCard>();
        query.and(queries);
        query.order("-updatedAt");
        query.include("sid");
        query.setLimit(100);
        query.findObjects(new FindListener<ReportCard>() {
            @Override
            public void done(List<ReportCard> list, BmobException e) {
                if (e==null){
                    callBack.get(list);
                    Log.i("KK",list.size()+"GG");
                }else {
                    list=null;
                    callBack.get(list);

                }
            }
        });


    }
}
