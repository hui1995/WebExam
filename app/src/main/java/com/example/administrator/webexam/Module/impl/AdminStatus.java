package com.example.administrator.webexam.Module.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Module.Data.Admin;
import com.example.administrator.webexam.Module.IAdminStatus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2017/9/25.
 */

public class AdminStatus implements IAdminStatus {
    @Override
    public void getphoneandpwd(@NonNull String phone, @NonNull final LoadPhonepwdCallback loadPhonepwdCallback) {


        BmobQuery<Admin> query = new BmobQuery<Admin>();
        query.addWhereEqualTo("rphone",phone);
        query.setLimit(1);
        query.findObjects(new FindListener<Admin>() {
            @Override
            public void done(List<Admin> list, BmobException e) {
                if (e ==null){
                    for (Admin admin : list){
                        admin.getRphone();
                        admin.getRpasswd();
                        admin.getObjectId();

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
        BmobQuery<Admin> bmobQuery = new BmobQuery<Admin>();
        bmobQuery.getObject(ObjectId, new QueryListener<Admin>() {
            @Override
            public void done(Admin admin, BmobException e) {
                if (e==null){
                    loadAllMyInfoCallback.getallinfo(admin);
                }else {

                }
            }
        });
    }
}
