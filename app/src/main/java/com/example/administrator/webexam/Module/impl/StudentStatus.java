package com.example.administrator.webexam.Module.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IStudentStatus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/9/22.
 */

public class StudentStatus implements IStudentStatus {
    @Override
    public void save(Student student) {




        student.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    System.out.println("ssssssssssssssss");
                }else {
                    System.out.println("gggggggggggg");
                }
            }
        });


    }

    @Override
    public void selectphone(@NonNull String phone, @NonNull final CheckNoPhoneCalback checkNoPhoneCalback) {


        BmobQuery<Student> query = new BmobQuery<Student>();
        query.addWhereEqualTo("sphone",phone);
        query.setLimit(1);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e ==null){
                    for (Student student : list){
                        student.getSphone();

                    }

                  checkNoPhoneCalback.getisphoneload(list);

                }else {

                  //  checkNoPhoneCalback.getisphoneload(list);
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

    }

    @Override
    public void getphoneandpwd(@NonNull String phone, @NonNull final LoadPhonepwdCallback loadPhonepwdCallback) {
        BmobQuery<Student> query = new BmobQuery<Student>();
        query.addWhereEqualTo("sphone",phone);
        query.setLimit(1);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e ==null){
                   for (Student student : list){
                       student.getSphone();
                       student.getSpasswd();
                       student.getObjectId();
                    }
                    loadPhonepwdCallback.onPhonepwdload(list);

                }else {

                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });


    }

    @Override
    public void selectall(@NonNull String ObjectId, @NonNull final LoadAllMyInfoCallback loadAllMyInfoCallback) {
        BmobQuery<Student> bmobQuery = new BmobQuery<Student>();
        bmobQuery.getObject(ObjectId, new QueryListener<Student>() {
            @Override
            public void done(Student student, BmobException e) {
                if (e==null){
                    loadAllMyInfoCallback.getallinfo(student);
                }else {
                    student=null;
                    loadAllMyInfoCallback.getallinfo(student);

                }
            }
        });
    }

    @Override
    public void selsetallStudentNo(@NonNull String ObjectId, @NonNull boolean isAuto, @NonNull final LoadAllStudentNo loadAllStudentNo) {

        BmobQuery<Student> b1 = new BmobQuery<Student>();
        Classmate classmate = new Classmate();
        classmate.setObjectId(ObjectId);

        b1.addWhereEqualTo("cid",classmate);
        BmobQuery<Student> b2 = new BmobQuery<Student>();
        b2.addWhereEqualTo("isAutherntication",isAuto);
        List<BmobQuery<Student>> bmobQueries = new ArrayList<BmobQuery<Student>>();
        bmobQueries.add(b1);
        bmobQueries.add(b2);

        BmobQuery<Student> query = new BmobQuery<Student>();
        query.and(bmobQueries);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e==null){
                  loadAllStudentNo.getAllStudentNo(list);
                }else{
                    list=null;
                    loadAllStudentNo.getAllStudentNo(list);

                }
            }
        });





    }

    @Override
    public void addStudentToClass(String objectId,Student student) {

        student.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else{

                }
            }
        });

    }

    @Override
    public void selectCountStudentforClass(String cid, final LoadCLassStudent callbak) {

        BmobQuery<Student> query = new BmobQuery<Student>();

        query.setLimit(100);
        Classmate classmate = new Classmate();
        classmate.setObjectId(cid);
        query.addWhereEqualTo("cid",classmate);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e==null){

                    for (Student student :list){
                        student.getSphone();
                        student.getSuid();
                        student.getSname();
                        student.isAutherntication();


                    }
                    callbak.getLoadStudent(list);

                }else{
                    list=null;
                    callbak.getLoadStudent(list);

                }
            }
        });
    }

    @Override
    public void updateTrueAuth(String sid,Boolean b) {
       Student student = new Student();
        student.setAutherntication(b);
        student.update(sid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else{

                }
            }
        });
    }

    @Override
    public void updateFalse(String sid) {
        Student student = new Student();


      Classmate classmate = new Classmate();
        classmate.setObjectId("");
       student.setCid(classmate);
        student.setSname("");
        student.setSuid("");
        student.update(sid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else{

                }
            }
        });

    }

    @Override
    public void updatePasswd(String id,String passwd) {
        Student student = new Student();
        student.setSpasswd(passwd);
        student.update(id, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else{

                }
            }
        });
    }

    @Override
    public void selectAllMyClassInfo(String sid, final LoadAllMyInfoCallback callBack) {

        BmobQuery<Student>  query =new BmobQuery<Student>();
        query.addWhereEqualTo("objectId",sid);
        query.order("-updatedAt");
        query.include("cid.eid,cid.tid");
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e==null){
                    callBack.getallinfo(list.get(0));

                }else {
               Student student = new Student();
                    callBack.getallinfo(student);


                }
            }
        });
    }


}
