package com.example.administrator.webexam.Module.impl;

import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IClassmateStatus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ClassmateStatus implements IClassmateStatus {
    @Override
    public void add(Classmate classmate) {
        classmate.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){

                }else{

                }
            }
        });
    }

    @Override
    public void selectalllist(String tid, final LoadallListCallBack loadallListCallBack) {

        BmobQuery<Classmate> query = new BmobQuery<Classmate>();
        query.addWhereEqualTo("tid",tid);
        query.setLimit(50);
        query.findObjects(new FindListener<Classmate>() {
            @Override
            public void done(List<Classmate> list, BmobException e) {
                if (e==null){
                    for (Classmate classmate :list){
                        classmate.getCgrade();
                        classmate.getPname();
                        classmate.getCname();
                        classmate.getObjectId();
                    }
                    loadallListCallBack.getlist(list);
                }else{
                    list =null;
                    loadallListCallBack.getlist(list);

                }
            }
        });
    }

    @Override
    public void updateclassforexamin(String eid,String cid) {
        Classmate classmate = new Classmate();
        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setObjectId(eid);
        classmate.setEid(examinQuestion);
       //
        // classmate.setEid(eid);
        classmate.update(cid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else{

                }
            }
        });
    }

    @Override
    public void selectExaminnow(String cid, final LoadOneInfoCallBack callBack) {


        BmobQuery<Classmate> bmobQuery = new BmobQuery<Classmate>();
     //   bmobQuery.include("eid");

        bmobQuery.addWhereEqualTo("objectId",cid);
        bmobQuery.include("eid");
        bmobQuery.findObjects(new FindListener<Classmate>() {
            @Override
            public void done(List<Classmate> list, BmobException e) {
              if (e==null){
                callBack.getClassInfo(list.get(0));
              }else {

              }
            }
        });
/*        bmobQuery.getObject(cid, new QueryListener<Classmate>() {
            @Override
            public void done(Classmate classmate, BmobException e) {
                if (e==null){
                    callBack.getClassInfo(classmate);

                }else {

                }
            }
        });*/

    }

    @Override
    public void selectClass(String sid, final LoadallListCallBack callBack) {
        BmobQuery<Classmate> bmobQuery = new BmobQuery<Classmate>();
        bmobQuery.addWhereEqualTo("sid",sid);
        bmobQuery.setLimit(10);
        bmobQuery.findObjects(new FindListener<Classmate>() {
            @Override
            public void done(List<Classmate> list, BmobException e) {
                if (e==null){

                    callBack.getlist(list);

                }else{
                    list=null;
                    callBack.getlist(list);

                }
            }
        });
    }

    @Override
    public void selectClassStatus( String eid, final LoadallListCallBack callBack) {


        BmobQuery<Classmate> query2 = new BmobQuery<Classmate>();
        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setObjectId(eid);
        query2.addWhereNotEqualTo("eid",examinQuestion);

        query2.findObjects(new FindListener<Classmate>() {
            @Override
            public void done(List<Classmate> list, BmobException e) {
                if (e==null){
                   callBack.getlist(list);
                }else {
                    list=null;
                    callBack.getlist(list);

                }
            }
        });

    }



   /* @Override
    public void selectalllist2(final LoadallListCallBack loadallListCallBack) {
        BmobQuery<Classmate> query = new BmobQuery<Classmate>();
        query.addQueryKeys("objectId,cname,pname,cgrade");
        query.setLimit(50);
        query.findObjects(new FindListener<Classmate>() {
            @Override
            public void done(List<Classmate> list, BmobException e) {
                if (e==null){

                    loadallListCallBack.getlist(list);
                }else{
                    list =null;
                    loadallListCallBack.getlist(list);

                }
            }
        });
    }*/


}
