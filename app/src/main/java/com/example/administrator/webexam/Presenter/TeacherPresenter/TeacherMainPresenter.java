package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Contract.TeacherContract.TeacherMainContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IAdminStatus;
import com.example.administrator.webexam.Module.IClassmateStatus;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.ITeacherStatus;
import com.example.administrator.webexam.Module.IUniversityStatus;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TeacherMainPresenter implements TeacherMainContract.Presenter {

    @NonNull
    private final TeacherMainContract.IndexView indexView;
    @NonNull
    private final TeacherMainContract.ClassView classView;
    @NonNull
    private final TeacherMainContract.MeView meView;


    @NonNull
    private final ITeacherStatus iTeacherStatus;
    @NonNull
    private final IClassmateStatus iClassmateStatus;

    private final String ObjectId;
    private String NICKNAME="nickname";
    private String TYPE="type";
    private String ISLOGIN ="islogin";
    private String OBJECTID ="ObjectId";
    private static Teacher teacher2;
    private final IExaminStatus iExaminStatus;




    public TeacherMainPresenter(@NonNull TeacherMainContract.IndexView indexView, @NonNull TeacherMainContract.ClassView classView, @NonNull TeacherMainContract.MeView meView, @NonNull ITeacherStatus iTeacherStatus, @NonNull IClassmateStatus iClassmateStatus, String objectId, IExaminStatus iExaminStatus) {
        this.indexView = checkNotNull(indexView);
        this.classView = checkNotNull(classView);
        this.meView = checkNotNull(meView);

        this.iTeacherStatus = iTeacherStatus;
        this.iClassmateStatus = iClassmateStatus;
this.ObjectId = objectId;
        this.iExaminStatus = iExaminStatus;

        indexView.setPresenter(this);
        classView.setPresenter(this);
        meView.setPresenter(this);
    }

    @Override
    public void start() {


      getTeacherInfo();

    };;


    public void getTeacherInfo() {


iTeacherStatus.selectall(ObjectId, new ITeacherStatus.LoadAllMyInfoCallback() {
    @Override
    public void getallinfo(Teacher teacher) {


        teacher2=teacher;

    }

});

    }

    @Override
    public void startIndex() {
        getTeacherInfo();

    }

    @Override
    public void startClass(Context context) {

        iClassmateStatus.selectalllist(ObjectId, new IClassmateStatus.LoadallListCallBack() {
            @Override
            public void getlist(List<Classmate> classmates) {
                ArrayList c = (ArrayList) classmates;
                classView.initView(c);
            }
        });



    }

    @Override
    public void startMe(Context context) {


        setInfo(teacher2);

    }

    @Override
    public void loginout(Context context) {

        SharedPreferences userInfo = context.getSharedPreferences("user_login",0);

        userInfo.edit().putBoolean(ISLOGIN, false).commit();


    }

    @Override
    public void isAuto() {
       if (teacher2.isAuthentication()){

           classView.nextToAddClass();
       }else{
           classView.noAuto();
       }

    }

    @Override
    public void isAuto2() {
        if (teacher2.isAuthentication()){
            classView.nextToStudentMang();
        }else{
            classView.noAuto();
        }
    }
    @Override
    public void isAuto3() {
        Log.i("LLL","HHHH");
        Log.i("LLL",teacher2.getTname());
        if (teacher2.isAuthentication()) {

            indexView.nextToAddClass();
        } else {
            indexView.noAuto();
        }
    }

    @Override
    public void GetIdInfo() {
        meView.GetId(ObjectId);
    }

    @Override
    public void findExaminStatus() {



    }

    private void setInfo(Teacher teacher){
       meView.initView(teacher.getTnickname(),teacher.getTphone());

teacher2 = teacher;




    }



}
