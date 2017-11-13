package com.example.administrator.webexam.Module;

import android.support.annotation.NonNull;

import com.example.administrator.webexam.Module.Data.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public interface IStudentStatus {


interface LoadPhonepwdCallback{
    void onPhonepwdload(List<Student> students);

}
    interface  LoadAllMyInfoCallback{
        void getallinfo(Student student);
    }
interface CheckNoPhoneCalback{
    void getisphoneload(@NonNull List<Student> students);
}

interface LoadAllStudentNo {

    void getAllStudentNo(@NonNull List<Student> students);
}
interface LoadCLassStudent{

    void getLoadStudent(List<Student> list);
}




    void save(Student student);

    void selectphone(@NonNull String phone,@NonNull CheckNoPhoneCalback checkNoPhoneCalback );




    void getphoneandpwd(@NonNull String phone, @NonNull LoadPhonepwdCallback loadPhonepwdCallback);
    void selectall(@NonNull String ObjectId,@NonNull LoadAllMyInfoCallback loadAllMyInfoCallback);

    void selsetallStudentNo(@NonNull String ObjectId,@NonNull boolean isAuto,@NonNull LoadAllStudentNo loadAllStudentNo);

    void addStudentToClass(String objectId,Student student);

    void selectCountStudentforClass(String cid,LoadCLassStudent callbak);

void updateTrueAuth(String sid,Boolean b);
    void updateFalse(String sid);

    void updatePasswd(String id,String passwd);

    void  selectAllMyClassInfo(String sid, LoadAllMyInfoCallback callBack);




}

