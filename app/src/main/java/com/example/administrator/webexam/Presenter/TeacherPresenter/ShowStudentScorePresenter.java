package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.util.Log;


import com.example.administrator.webexam.Contract.TeacherContract.ShowStudentScoreContract;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IReportCardStatus;
import com.example.administrator.webexam.Module.IStudentStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public class ShowStudentScorePresenter implements ShowStudentScoreContract.Presenter{

    private final ShowStudentScoreContract.View mView;
    private final String eid;
    private final String cid;

    private final IReportCardStatus iReportCardStatus;
    private final IStudentStatus iStudentStatus;

    public ShowStudentScorePresenter(ShowStudentScoreContract.View mView, String eid, String cid, IReportCardStatus iReportCardStatus, IStudentStatus iStudentStatus) {
        this.mView = mView;
        this.eid = eid;
        this.cid = cid;
        this.iReportCardStatus = iReportCardStatus;
        this.iStudentStatus = iStudentStatus;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        iReportCardStatus.haddoexamin(eid, cid, new IReportCardStatus.HadReportLast() {

            @Override
            public void get(List<ReportCard> reportCards) {



                mView.initView((ArrayList<ReportCard>) reportCards);


            }
        });



    }
}
