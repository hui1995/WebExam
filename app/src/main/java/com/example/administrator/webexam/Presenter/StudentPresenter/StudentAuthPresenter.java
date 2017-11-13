package com.example.administrator.webexam.Presenter.StudentPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.administrator.webexam.Contract.StudentContract.StudentAuthContract;

import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Student;

import com.example.administrator.webexam.Module.IStudentStatus;




import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/9/29.
 */

public class StudentAuthPresenter implements StudentAuthContract.Presenter {

    @NonNull
    private final StudentAuthContract.View mView;

    @NonNull
    private final IStudentStatus iStudentStatus;
    private String OBJECTID ="ObjectId";





    public StudentAuthPresenter(@NonNull StudentAuthContract.View mView, @NonNull IStudentStatus iStudentStatus) {
        this.mView = checkNotNull(mView);
this.iStudentStatus = iStudentStatus;
        mView.setPresenter(this);
    }


    @Override
    public void start() {
      //  classBeens = new ArrayList<ClassBean>();



    }


    @Override
    public void Auth(Context context,String classid, String studentId, String studentname) {

        SharedPreferences userInfo = context.getSharedPreferences("user_login", 0);
        if (classid.isEmpty()||studentId.isEmpty()||studentname.isEmpty()){
            mView.Toast();

        }else{
            String  ObjectId= userInfo.getString(OBJECTID,"");


            Classmate classmate = new Classmate();
            classmate.setObjectId(classid);

            Student student = new Student();
            student.setCid(classmate);
            student.setSuid(studentId);
            student.setSname(studentname);
            student.setAutherntication(true);


            iStudentStatus.addStudentToClass(ObjectId,student);
        }




    }
}
