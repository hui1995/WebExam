package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.example.administrator.webexam.Contract.TeacherContract.ClassMainTeacherContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IClassmateStatus;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.IReportCardStatus;
import com.example.administrator.webexam.Module.IStudentStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class ClassMainTeacherPresenter implements ClassMainTeacherContract.Presenter {


    @NonNull
    private final ClassMainTeacherContract.View mView;

    @NonNull
    private final IStudentStatus iStudentStatus;

    @NonNull
    private final IClassmateStatus iClassmateStatus;
    @NonNull
    private final String cid;
    @NonNull
    private final IExaminStatus iExaminStatus;
    @NonNull
    private final IReportCardStatus reportCardStatus;
    private int count=0;
    private final String sid;
    private   static   String eid;

    public ClassMainTeacherPresenter(@NonNull ClassMainTeacherContract.View mView, @NonNull IStudentStatus iStudentStatus, @NonNull IClassmateStatus iClassmateStatus, @NonNull String cid, @NonNull IExaminStatus iExaminStatus, @NonNull IReportCardStatus reportCardStatus, String sid) {
        this.mView = mView;
        this.iStudentStatus=iStudentStatus;
        this.iClassmateStatus = iClassmateStatus;
        this.cid=cid;
        this.iExaminStatus = iExaminStatus;
        this.reportCardStatus = reportCardStatus;
        this.sid = sid;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        iStudentStatus.selectCountStudentforClass(cid, new IStudentStatus.LoadCLassStudent() {
            @Override
            public void getLoadStudent(List<Student> list) {

                mView.setAllStudent(list.size());



                count = 0;

                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).isAutherntication()) {
                        count++;
                    } else {
                        count = count;
                    }
                }


                mView.setTrueStudent(count);


            }
        });

        iClassmateStatus.selectExaminnow(cid, new IClassmateStatus.LoadOneInfoCallBack() {


        String string = "暂无考试";
        @Override
        public void getClassInfo ( final Classmate classmate){
            mView.setInfoForClass(classmate);
eid = classmate.getEid().getObjectId();

            reportCardStatus.haddoexamin( eid,cid, new IReportCardStatus.HadReportLast() {
                @Override
                public void get(List<ReportCard> reportCards) {



                    if (reportCards.size()!=0){

                        int co =0;
                        for (int i=0;i<reportCards.size();i++){
                            co=co+reportCards.get(i).getScore();
                        }
                        int f =co/reportCards.size();
                        mView.setInfoExamin(reportCards.size(),reportCards.get(reportCards.size()-1).getScore(),reportCards.get(0).getScore(),f);


                    }else{
                        mView.setInfoExamin(0,0,0,0);
                    }

                }
            });
            if (classmate.getEid() == null) {
                mView.setNowExamin(string, "");
            } else {
                mView.setNowExamin(classmate.getEid().getExaminname(), eid);
            }
        }
        });




    }

    @Override
    public void updateExaming(String cid) {

iClassmateStatus.updateclassforexamin("",cid);
        iClassmateStatus.selectClassStatus(eid, new IClassmateStatus.LoadallListCallBack() {
            @Override
            public void getlist(List<Classmate> classmates) {
                if (classmates.size()>0){

                }else{
                    ExaminQuestion examinQuestion = new ExaminQuestion();
                    examinQuestion.setExamining(false);
                  iExaminStatus.updateExaminstgate(eid,examinQuestion);
                }
            }
        });
    }

    @Override
    public void getQustionInfoTo() {


        mView.goQustionAnanlsis(eid,cid);
    }

    @Override
    public void getGrankScoreTo() {
        mView.goGrankScore(eid,cid);

    }
}
