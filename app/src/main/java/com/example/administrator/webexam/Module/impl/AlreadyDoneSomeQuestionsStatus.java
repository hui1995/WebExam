package com.example.administrator.webexam.Module.impl;

import android.util.Log;

import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/10/15.
 */

public class AlreadyDoneSomeQuestionsStatus implements IAlreadyDoneSomeQuestionsStatus {

    @Override
    public void save(AlreadyDoneQ alreadyDoneQ) {

        alreadyDoneQ.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){

                }else {

                }
            }
        });

    }

    @Override
    public void update(String objetId, AlreadyDoneQ alreadyDoneQ) {

        alreadyDoneQ.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else {

                }
            }
        });

    }

    @Override
    public void selectRId(String sid, String rjid, final GetWQuesitonCalBack calBack) {
        BmobQuery<AlreadyDoneQ> query1 = new BmobQuery<AlreadyDoneQ>();

        Student student = new Student();
        student.setObjectId(sid);
        query1.addWhereEqualTo("sid",student);
        BmobQuery<AlreadyDoneQ> query2 = new BmobQuery<AlreadyDoneQ>();
        query2.addWhereEqualTo("rid",rjid);
        List<BmobQuery<AlreadyDoneQ>> queries = new ArrayList<BmobQuery<AlreadyDoneQ>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<AlreadyDoneQ> query = new BmobQuery<AlreadyDoneQ>();
        query.and(queries);


        query.findObjects(new FindListener<AlreadyDoneQ>() {
            @Override
            public void done(List<AlreadyDoneQ> list, BmobException e) {
                if (e==null){
                    calBack.get(list);

                }else {
                    list=null;
                    calBack.get(list);


                }
            }
        });
    }
    @Override
    public void selectJId(String sid, String rjid, final GetWQuesitonCalBack calBack) {
        BmobQuery<AlreadyDoneQ> query1 = new BmobQuery<AlreadyDoneQ>();

        Student student = new Student();
        student.setObjectId(sid);
        query1.addWhereEqualTo("sid",student);
        BmobQuery<AlreadyDoneQ> query2 = new BmobQuery<AlreadyDoneQ>();
        query2.addWhereEqualTo("jid",rjid);
        List<BmobQuery<AlreadyDoneQ>> queries = new ArrayList<BmobQuery<AlreadyDoneQ>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<AlreadyDoneQ> query = new BmobQuery<AlreadyDoneQ>();
        query.and(queries);


        query.findObjects(new FindListener<AlreadyDoneQ>() {
            @Override
            public void done(List<AlreadyDoneQ> list, BmobException e) {
                if (e==null){
                    calBack.get(list);

                }else {
                    list=null;
                    calBack.get(list);


                }
            }
        });
    }

    @Override
    public void selectAll(String sid, String rjid, boolean isRadio, GetWQuesitonCalBack calBack) {

    }

    @Override
    public void selectAll(String sid, boolean isClass, final GetWQuesitonCalBack calBack) {
        BmobQuery<AlreadyDoneQ> query1 = new BmobQuery<AlreadyDoneQ>();

        Student student = new Student();
        student.setObjectId(sid);
        query1.addWhereEqualTo("sid",student);
        BmobQuery<AlreadyDoneQ> query2 = new BmobQuery<AlreadyDoneQ>();
        query2.addWhereEqualTo("isClassExamin",isClass);
        List<BmobQuery<AlreadyDoneQ>> queries = new ArrayList<BmobQuery<AlreadyDoneQ>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<AlreadyDoneQ> query = new BmobQuery<AlreadyDoneQ>();
        query.and(queries);
        query.order("-updatedAt");
        query.include("eid,jid,rid,sid.cid");

        query.findObjects(new FindListener<AlreadyDoneQ>() {
            @Override
            public void done(List<AlreadyDoneQ> list, BmobException e) {
                if (e==null){
                    calBack.get(list);

                }else {
                    list=null;
                    calBack.get(list);


                }
            }
        });
    }

    @Override
    public void selectTen(String sid, boolean isClass, final GetWQuesitonCalBack calBack) {
        BmobQuery<AlreadyDoneQ> query1 = new BmobQuery<AlreadyDoneQ>();
        Log.i("OOO",sid+isClass+"");
        Student student = new Student();
        student.setObjectId(sid);
        query1.addWhereEqualTo("sid",student);
        BmobQuery<AlreadyDoneQ> query2 = new BmobQuery<AlreadyDoneQ>();
        query2.addWhereEqualTo("isClassExamin",isClass);
        List<BmobQuery<AlreadyDoneQ>> queries = new ArrayList<BmobQuery<AlreadyDoneQ>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<AlreadyDoneQ> query = new BmobQuery<AlreadyDoneQ>();
        query.and(queries);
        query.order("-updatedAt");
        query.include("eid,jid,rid");

        query.findObjects(new FindListener<AlreadyDoneQ>() {
            @Override
            public void done(List<AlreadyDoneQ> list, BmobException e) {
                if (e==null){
                    calBack.get(list);

                }else {
                    list=null;
                    calBack.get(list);


                }
            }
        });
    }

    @Override
    public void deleteQuestionWrong(String wid) {


        AlreadyDoneQ alreadyDoneQ  = new AlreadyDoneQ();
        alreadyDoneQ.setObjectId(wid);
        alreadyDoneQ.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else {

                }
            }
        });

    }

    @Override
    public void countWrong(String rjid, GetCount getCount) {

    }

    @Override
    public void selectAllWrongForExamining(String eid, boolean isClass, final GetWQuesitonCalBack calBack) {
        BmobQuery<AlreadyDoneQ> query1 = new BmobQuery<AlreadyDoneQ>();

        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setObjectId(eid);
        query1.addWhereEqualTo("eid",new BmobPointer(examinQuestion));
        BmobQuery<AlreadyDoneQ> query2 = new BmobQuery<AlreadyDoneQ>();
        query2.addWhereEqualTo("isClassExamin",isClass);
        List<BmobQuery<AlreadyDoneQ>> queries = new ArrayList<BmobQuery<AlreadyDoneQ>>();
        queries.add(query1);
        queries.add(query2);
        BmobQuery<AlreadyDoneQ> query = new BmobQuery<AlreadyDoneQ>();
        query.and(queries);
        query.order("-updatedAt");
        query.include("eid,jid,rid,sid.cid");

        query.findObjects(new FindListener<AlreadyDoneQ>() {
            @Override
            public void done(List<AlreadyDoneQ> list, BmobException e) {
                if (e==null){
                    calBack.get(list);

                }else {
                    list=null;
                    calBack.get(list);


                }
            }
        });
    }


}
