package com.example.administrator.webexam.Presenter.StudentPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Contract.StudentContract.MyClassContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IClassmateStatus;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.IReportCardStatus;
import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.ITeacherStatus;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14.
 */

public class MyClassPresenter implements MyClassContract.Presenter {
    @NonNull
    private final MyClassContract.View mView;
    private final String sid;

    @NonNull
    private final IStudentStatus iStudentStatus;
    @NonNull
    private final IClassmateStatus iClassmateStatus;
    @NonNull
    private final ITeacherStatus iTeacherStatus;
    @NonNull
    private final IExaminStatus iExaminStatus;
    @NonNull
    private final IReportCardStatus iReportCardStatus;
    private static String eid;
    private static String ename;
    private static boolean isDo =false;
    private boolean isExamining = false;
    private static String cid;

    public MyClassPresenter(@NonNull MyClassContract.View mView, String sid, @NonNull IStudentStatus iStudentStatus, @NonNull IClassmateStatus iClassmateStatus, @NonNull ITeacherStatus iTeacherStatus, @NonNull IExaminStatus iExaminStatus, @NonNull IReportCardStatus iReportCardStatus) {
        this.mView = mView;
        this.sid =sid;
        this.iStudentStatus = iStudentStatus;
        this.iClassmateStatus = iClassmateStatus;
        this.iTeacherStatus = iTeacherStatus;
        this.iExaminStatus =iExaminStatus;
        this.iReportCardStatus = iReportCardStatus;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
//获取学生班级相关信息；

iStudentStatus.selectAllMyClassInfo(sid, new IStudentStatus.LoadAllMyInfoCallback() {
    @Override
    public void getallinfo(Student student) {

       mView.setClassInfo(student);

        isExamining=student.getCid().getEid().isExamining();
        eid = student.getCid().getEid().getObjectId();
        ename=student.getCid().getEid().getExaminname();


    }
});

     /*
        iStudentStatus.selectall(sid, new IStudentStatus.LoadAllMyInfoCallback() {
            @Override
            public void getallinfo(Student student) {
                cid = student.getCid();



                iClassmateStatus.selectExaminnow(student.getCid(), new IClassmateStatus.LoadOneInfoCallBack() {
                    @Override
                    public void getClassInfo(Classmate classmate) {


                        mView.setClassInfo(classmate);
                        eid = classmate.getEid();

                        iTeacherStatus.selectall(classmate.getTid(), new ITeacherStatus.LoadAllMyInfoCallback() {
                            @Override
                            public void getallinfo(Teacher teacher) {
                                mView.setTeacherInfo(teacher.getTname());

                            }
                        });


                        iExaminStatus.getExaminname(classmate.getEid(), new IExaminStatus.GetExaminname() {
                            @Override
                            public void getExaminname(ExaminQuestion examinQuestion) {
                                mView.setExaminId(examinQuestion.getExaminname());
                               isExamining=examinQuestion.isExamining();
                            }
                        });

                    }
                });
            }
        });*/
iReportCardStatus.getReportList(sid, true, new IReportCardStatus.HadReportLast() {
    @Override
    public void get(List<ReportCard> reportCards) {


if (reportCards.size()==0){

}else{
    mView.setScore(reportCards.get(0).getScore(),100-reportCards.get(0).getScore());
}


    }
});


    }

    @Override
    public void getExaminid() {
        if (eid==""){
            mView.ToastNoExamin();

        }else{
            mView.GOExamining(eid,ename,isExamining);

        }

    }

    @Override
    public boolean checkHadExaminNow() {



iReportCardStatus.haddoexamin(sid, eid, new IReportCardStatus.HadExaminCallBack() {
    @Override
    public void isDo(boolean isdo) {
        isDo = isdo;
    }
});

        return isDo;
    }

    @Override
    public void getQuestionInfo() {
        mView.showQuestionWrong(sid);
    }

    @Override
    public void getExaminAnswer() {

        if (eid!=""){
            mView.ToastNoAnser();
        }else {
            mView.GOShowAnswer(eid,sid,ename);
        }

    }

    @Override
    public void saveCid(Context context,String cid) {
        SharedPreferences userInfo = context.getSharedPreferences("user_login",0);
        userInfo.edit().putString("cid",cid).commit();
        System.out.println("GGGGGGGGGGGGGGGGGG"+cid);


    }


}
