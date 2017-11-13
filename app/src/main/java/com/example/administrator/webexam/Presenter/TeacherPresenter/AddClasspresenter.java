package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.administrator.webexam.Contract.TeacherContract.AddClassContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IClassmateStatus;

/**
 * Created by Administrator on 2017/9/28.
 */

public class AddClasspresenter implements AddClassContract.Presenter {

    private String OBJECTID ="ObjectId";
    @NonNull
    private final AddClassContract.View mView;
    @NonNull
    private final IClassmateStatus iClassmateStatus;

    public AddClasspresenter(@NonNull AddClassContract.View mView, @NonNull IClassmateStatus iClassmateStatus) {
        this.mView = mView;
        this.iClassmateStatus = iClassmateStatus;
        mView.setPresenter(this);

    }


    @Override
    public void start() {

    }

    @Override
    public void saveclass(Context context,String classname, String grade, String profession) {
        Classmate classmate = new Classmate();
        classmate.setCgrade(grade);
        classmate.setPname(profession);
        classmate.setCname(classname);
        SharedPreferences userInfo = context.getSharedPreferences("user_login", 0);

        Teacher teacher = new Teacher();
        teacher.setObjectId(userInfo.getString(OBJECTID,""));
        classmate.setTid(teacher);
        classmate.setEid(null);
        iClassmateStatus.add(classmate);


    }
}
