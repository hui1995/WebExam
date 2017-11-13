package com.example.administrator.webexam.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.administrator.webexam.Contract.LoginContract;
import com.example.administrator.webexam.Module.Data.Admin;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IAdminStatus;
import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.ITeacherStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/9/20.
 */

public class LoginPresenter implements LoginContract.Presenter {



    @NonNull
    private final LoginContract.View mView;

    @NonNull
    private final IStudentStatus iStudentStatus;
    @NonNull
    private final ITeacherStatus iTeacherStatus;
    @NonNull
    private final IAdminStatus iAdminStatus;
    @NonNull
    private String ObjectId;
    private String NICKNAME="nickname";
    private String TYPE="type";
    private String ISLOGIN ="islogin";
    private String OBJECTID ="ObjectId";


    public LoginPresenter(@NonNull LoginContract.View mView, @NonNull IStudentStatus iStudentStatus, @NonNull ITeacherStatus iTeacherStatus, @NonNull IAdminStatus iAdminStatus) {

        this.mView = checkNotNull(mView);
        this.iStudentStatus = iStudentStatus;
        this.iTeacherStatus = iTeacherStatus;
        this.iAdminStatus = iAdminStatus;
        mView.setPresenter(this);

    }

    @Override
    public void start() {


    }


    @Override
    public void getLogininfo(Context context,String phone, String passwd) {

checkInStudent(context,phone,passwd);





    }

    @Override
    public void checkInStudent(final Context context, final String phone, final String passwd) {

        iStudentStatus.getphoneandpwd(phone, new IStudentStatus.LoadPhonepwdCallback() {
            @Override
            public void onPhonepwdload(List<Student> students) {
                if (students.isEmpty()){

                    checkInTeacher(context,phone,passwd);

                }else{
                    if (students.get(0).getSpasswd().equals(passwd)){
                        savelogining(context,phone,"student",students.get(0).getObjectId(),true);
                        ObjectId=students.get(0).getObjectId();
                        System.out.println("------------"+students.get(0).getObjectId()+"--------------"+ObjectId);
                        mView.goStudentIndex();
                    }else{
                        mView.ToastPasswdfail();
                    }
                }
            }
        });

    }

    @Override
    public void checkInTeacher(final Context context, final String phone, final String passwd) {

        iTeacherStatus.getphoneandpwd(phone, new ITeacherStatus.LoadPhonepwdCallback() {
            @Override
            public void onPhonepwdload(List<Teacher> teachers) {
                if (teachers.isEmpty()){
                    checkInAdmin(context,phone,passwd);
                }else{
                    if (teachers.get(0).getTpasswd().equals(passwd)){
                        savelogining(context,phone,"teacher",teachers.get(0).getObjectId(),true);
                        ObjectId= teachers.get(0).getObjectId();
                        System.out.println("------------"+teachers.get(0).getObjectId()+"--------------"+ObjectId);
                        mView.goTeacherIndex();
                    }else{

                        mView.ToastPasswdfail();
                    }
                }
            }
        });

    }

    @Override
    public void checkInAdmin(final Context context, final String phone, final String passwd) {
        iAdminStatus.getphoneandpwd(phone, new IAdminStatus.LoadPhonepwdCallback() {
            @Override
            public void onPhonepwdload(List<Admin> admins) {
                if (admins.isEmpty()){
                    mView.ToastNoUser();

                }else{

                    if (admins.get(0).getRpasswd().equals(passwd)){

                        savelogining(context,phone,"admin",admins.get(0).getObjectId(),true);
                        ObjectId= admins.get(0).getObjectId();

                     mView.goAdminIndex();
                    }else{
                        mView.ToastPasswdfail();

                    }
                }
            }
        });

    }

    private void savelogining(Context context,String phone,String type, String objectId,boolean islogin){

        SharedPreferences userInfo = context.getSharedPreferences("user_login",0);
        userInfo.edit().putString(NICKNAME, phone).commit();
        userInfo.edit().putString(TYPE,type).commit();

        userInfo.edit().putString(OBJECTID,objectId).commit();
        userInfo.edit().putBoolean(ISLOGIN, islogin).commit();
    }
}
