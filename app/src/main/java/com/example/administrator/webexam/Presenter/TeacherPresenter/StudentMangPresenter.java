package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.administrator.webexam.Contract.StudentContract.StudentMainContract;
import com.example.administrator.webexam.Contract.TeacherContract.StudentMangContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IClassmateStatus;
import com.example.administrator.webexam.Module.IStudentStatus;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/9/28.
 */

public class StudentMangPresenter implements StudentMangContract.Presenter {



    @NonNull
    private final StudentMangContract.View mView;

    @NonNull
    private final IStudentStatus iStudentStatus;
    @NonNull
    private final String cid;

    public StudentMangPresenter(@NonNull StudentMangContract.View mView, @NonNull IStudentStatus iStudentStatus, @NonNull String cid) {
        this.mView =checkNotNull(mView);
        this.iStudentStatus = iStudentStatus;
        this.cid = cid;


        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initView();

    }

    @Override
    public void getStudentNOAuth(){

        final ArrayList<Student> arrayList = new ArrayList<Student>();

        iStudentStatus.selectCountStudentforClass(cid, new IStudentStatus.LoadCLassStudent() {
            @Override
            public void getLoadStudent(List<Student> list) {




                for (int i=0;i<list.size();i++){

                    if (!list.get(i).isAutherntication()){
                        Log.i("WWW",list.get(i).getSnickname());
                        arrayList.add(list.get(i));
                    }
                 //   Log.i("WWW",list.get(i).getSnickname());
                }
                    mView.addIteminfo(arrayList);
               // }


            }
        });




    }

    @Override
    public void updateAuthTrue(String sid) {

        iStudentStatus.updateTrueAuth(sid,true);



    }

    @Override
    public void updateAuthFales(String sid) {
        iStudentStatus.updateFalse(sid);
    }


    // 245485480d








}
