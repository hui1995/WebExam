package com.example.administrator.webexam.Module;

import android.support.annotation.NonNull;

import com.example.administrator.webexam.Module.Data.Teacher;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public interface ITeacherStatus {

    interface LoadPhonepwdCallback{
        void onPhonepwdload(List<Teacher> teachers);

    }
    interface  LoadAllMyInfoCallback{
        void getallinfo(Teacher teacher);
    }
    interface CheckNoPhoneCallback{

        void getisphoneload(@NonNull List<Teacher> teacher);


    }
    interface  GetTidCallBack{

        void getTid(List<Teacher> list);
    }


    void save(Teacher teacher);
    void selectphone(@NonNull String phone, @NonNull CheckNoPhoneCallback checkNoPhoneCalback );
    void getphoneandpwd(@NonNull String phone, @NonNull LoadPhonepwdCallback loadPhonepwdCallback);

    void selectall(@NonNull String ObjectId,@NonNull LoadAllMyInfoCallback loadAllMyInfoCallback);

    void GetTidAll(GetTidCallBack getTidCallBack);
    void GetTeacherNo(boolean isAuthentication,GetTidCallBack callBack);
    void deleteTeacher(String tid);
    void updateTeacher(String tid,boolean isAuth);
    void updatePasswd(String id,String passwd);
}
