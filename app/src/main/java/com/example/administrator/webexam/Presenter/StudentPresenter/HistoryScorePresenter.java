package com.example.administrator.webexam.Presenter.StudentPresenter;

import android.support.annotation.NonNull;
import android.util.Log;


import com.example.administrator.webexam.Contract.StudentContract.HistoryScoreContract;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.IReportCardStatus;
import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.impl.ReportCardStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class HistoryScorePresenter implements HistoryScoreContract.Presenter {

  @NonNull
  private final  HistoryScoreContract.View mView;
    private final boolean isClass;
    @NonNull
    private final IReportCardStatus iReportCardStatus;
    @NonNull
    private final IExaminStatus iExaminStatus;

    private static String sid;

    public HistoryScorePresenter(@NonNull HistoryScoreContract.View view, boolean isClass, @NonNull IReportCardStatus iReportCardStatus, @NonNull IExaminStatus iExaminStatus,String sid) {
        this.mView = view;
        this.isClass=isClass;
        this.iReportCardStatus = iReportCardStatus;
        this.iExaminStatus = iExaminStatus;
        this.sid = sid;
     mView.setPresenter(this);
    }

    @Override
    public void start() {
//获取学生成绩列表信息
        iReportCardStatus.getReportList(sid, isClass, new IReportCardStatus.HadReportLast() {
            @Override
            public void get(List<ReportCard> reportCards) {

                if (reportCards.size()!=0){

                    Log.i("TAG",reportCards.size()+"jj");
mView.initView((ArrayList<ReportCard>) reportCards);

                }
            }
        });

/*      iReportCardStatus.getReportList(sid, isClass, new IReportCardStatus.HadReportLast() {

            @Override
            public void get(final List<ReportCard> reportCards) {
               final List<INfoBean> arrayList = new ArrayList<INfoBean>();

if (reportCards.size()!=0) {
    for (int j = 0; j < reportCards.size(); j++) {
        final INfoBean iNfoBean = new INfoBean();



        iNfoBean.setS2(reportCards.get(j).getScore());



        iExaminStatus.getExaminname(reportCards.get(j).getEid(), new IExaminStatus.GetExaminname() {
            @Override
            public void getExaminname(ExaminQuestion examinQuestion) {
boolean isInitView=false;
                iNfoBean.setS1(examinQuestion.getExaminname());
                arrayList.add(iNfoBean);






                    mView.initView((ArrayList<INfoBean>) arrayList);



            }
        });
    }
}


            }
        });*/


    }
}
