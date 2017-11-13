package com.example.administrator.webexam.Module;

import android.support.annotation.NonNull;

import com.example.administrator.webexam.Module.Data.Admin;
import com.example.administrator.webexam.Module.Data.Teacher;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface IAdminStatus {

    interface LoadPhonepwdCallback{
        void onPhonepwdload(List<Admin> admins);

    }
    interface  LoadAllMyInfoCallback{
        void getallinfo(Admin admin);
    }

    void getphoneandpwd(@NonNull String phone, @NonNull IAdminStatus.LoadPhonepwdCallback loadPhonepwdCallback);
    void selectall(@NonNull String ObjectId,@NonNull LoadAllMyInfoCallback loadAllMyInfoCallback);
}
