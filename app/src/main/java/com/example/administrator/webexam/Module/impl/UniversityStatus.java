package com.example.administrator.webexam.Module.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Module.Data.University;
import com.example.administrator.webexam.Module.IUniversityStatus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/9/26.
 */

public class UniversityStatus implements IUniversityStatus {
    @Override
    public void save(final University university, @NonNull final GetObjectIdCallback getObjectIdCallback) {



        BmobQuery<University> query = new BmobQuery<University>();
        query.addWhereEqualTo("uname",university.getUname());
        query.setLimit(1);
        query.findObjects(new FindListener<University>() {
            @Override
            public void done(List<University> list, BmobException e) {
                if (e ==null){
                    for (University university : list){
                        university.getObjectId();

                    }
                    if(list.get(0).getObjectId()==null){
                        university.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){

                                    getObjectIdCallback.getObject(s);
                                }else{
                                    getObjectIdCallback.getObject("fail");
                                }
                            }
                        });

                    }else{
                        getObjectIdCallback.getObject(list.get(0).getObjectId());

                    }



                }else {

                    //  checkNoPhoneCalback.getisphoneload(list);
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

        university.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){

                  getObjectIdCallback.getObject(s);
                }else{
                    getObjectIdCallback.getObject("fail");
                }
            }
        });
    }

    @Override
    public void findAll(final GetAllUniversity getAllUniversity) {
        BmobQuery<University> bmobQuery = new BmobQuery<University>();
        bmobQuery.addQueryKeys("objectId,uname");

        bmobQuery.findObjects(new FindListener<University>() {
            @Override
            public void done(List<University> universities, BmobException e) {
                if (e==null){
                    getAllUniversity.getAll(universities);
                }else{
                    universities =null;
                    getAllUniversity.getAll(universities);
                }
            }
        });
    }

    @Override
    public void findone(String uid, final GetUniversitu getUniversitu) {
      BmobQuery<University> bmobQuery = new BmobQuery<University>();
       bmobQuery.getObject(uid, new QueryListener<University>() {
           @Override
           public void done(University university, BmobException e) {
               if (e==null){
                   getUniversitu.getUniversity(university);
               }else{

               }
           }
       });
    }
}
