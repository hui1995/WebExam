package com.example.administrator.webexam.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Contract.ShowAllPageListContract;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.IExaminStatus;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShowAllPageListPresenter implements ShowAllPageListContract.Presenter{
    @NonNull
    private final ShowAllPageListContract.View mView;

    @NonNull
    private final IExaminStatus iExaminStatus;
    private final int i;
    private final String eid;

    public ShowAllPageListPresenter(@NonNull ShowAllPageListContract.View mView, @NonNull IExaminStatus iExaminStatus,int i,String eid) {
  this.mView = checkNotNull(mView);
        this.iExaminStatus = iExaminStatus;
        mView.setPresenter(this);
        this.i =i;
        this.eid =eid;

    }

    @Override
    public void start() {

        if (i==1){

            iExaminStatus.selectExam(eid, new IExaminStatus.GetAllExaminCallBack() {
                @Override
                public void getAll(List<ExaminQuestion> examinQuestions) {

                    mView.showList((ArrayList<ExaminQuestion>) examinQuestions);

                }
            });

        }else {

            iExaminStatus.selectExam(true, new IExaminStatus.GetAllExaminCallBack() {
                @Override
                public void getAll(List<ExaminQuestion> examinQuestions) {
                    mView.showList((ArrayList<ExaminQuestion>) examinQuestions);
                }
            });

        }



    }

    @Override
    public void updateExamin(String eid) {

        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setPublic(true);
        iExaminStatus.updateExaminstgate(eid,examinQuestion);
    }
}
