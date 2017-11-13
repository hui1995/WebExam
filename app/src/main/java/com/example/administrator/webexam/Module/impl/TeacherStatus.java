package com.example.administrator.webexam.Module.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.ITeacherStatus;

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

public class TeacherStatus implements ITeacherStatus {
    @Override
    public void save(Teacher teacher) {


        teacher.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e ==null){
                    //返回一个注册成功的属性


                }else {
                    //返回一个注册失败的属性
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });


    }


    @Override
    public void selectphone(@NonNull String phone, @NonNull final CheckNoPhoneCallback checkNoPhoneCalback) {
        BmobQuery<Teacher> query = new BmobQuery<Teacher>();
        query.addWhereEqualTo("tphone",phone);
        query.setLimit(1);
        query.findObjects(new FindListener<Teacher>() {
            @Override
            public void done(List<Teacher> list, BmobException e) {
                if (e ==null){
                    for (Teacher teacher : list){
                       teacher.getTphone();

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
        BmobQuery<Teacher> query = new BmobQuery<Teacher>();
        query.addWhereEqualTo("tphone",phone);
        query.setLimit(1);
        query.findObjects(new FindListener<Teacher>() {
            @Override
            public void done(List<Teacher> list, BmobException e) {
                if (e ==null){
                    for (Teacher teacher : list){
                        teacher.getTphone();
                        teacher.getTpasswd();
                        teacher.getObjectId();
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
        BmobQuery<Teacher> bmobQuery = new BmobQuery<Teacher>();
        bmobQuery.getObject(ObjectId, new QueryListener<Teacher>() {
            @Override
            public void done(Teacher teacher, BmobException e) {
                if (e==null){
               loadAllMyInfoCallback.getallinfo(teacher);
                }else {

                }
            }
        });
    }

    @Override
    public void GetTidAll(final GetTidCallBack getTidCallBack) {
        BmobQuery<Teacher> query = new BmobQuery<Teacher>();


        query.addQueryKeys("objectId,uid");
        query.findObjects(new FindListener<Teacher>() {
            @Override
            public void done(List<Teacher> list, BmobException e) {
                if (e==null){
                    for (Teacher teacher :list){
                        teacher.getObjectId();
                    }
                    getTidCallBack.getTid(list);
                }else{
                    list=null;
                    getTidCallBack.getTid(list);
                }
            }
        });

    }
//获取大学
    @Override
    public void GetTeacherNo(boolean isAuthentication, final GetTidCallBack callBack) {

        BmobQuery<Teacher> query = new BmobQuery<Teacher>();
        query.addWhereEqualTo("isAuthentication",false);
        query.setLimit(50);
        query.include("uid");
        query.findObjects(new FindListener<Teacher>() {
            @Override
            public void done(List<Teacher> list, BmobException e) {
                if (e==null){
                    for (Teacher teacher:list){
                        teacher.getTname();
                        teacher.getUniversity();
                        teacher.getTeacherid();
                    }
                    callBack.getTid(list);
                }else{
                    list=null;
                    callBack.getTid(list);
                }
            }
        });
    }

    @Override
    public void deleteTeacher(String tid) {
        Teacher teacher = new Teacher();
        teacher.setObjectId(tid);
        teacher.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
               if (e==null){

               }else{

               }
            }
        });
    }

    @Override
    public void updateTeacher(String tid, boolean isAuth) {
Teacher teacher = new Teacher();
        teacher.setAuthentication(isAuth);
        teacher.update(tid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else {

                }
            }
        });
    }

    @Override
    public void updatePasswd(String id, String passwd) {
        Teacher teacher = new Teacher();
        teacher.setTpasswd(passwd);
        teacher.update(id, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                }else {

                }
            }
        });
    }


}
