package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.util.Log;

import com.example.administrator.webexam.Contract.TeacherContract.QuestionAnalysisContract;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.IReportCardStatus;
import com.example.administrator.webexam.Module.IStudentStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public class QuestionAnalysisPresenter implements QuestionAnalysisContract.Presenter {

    private final QuestionAnalysisContract.View mView;
    private final String eid;
   boolean isgo=false;
    boolean isgo2=false;
    private final IExaminStatus iExaminStatus;
    private final IAlreadyDoneSomeQuestionsStatus iAstatus;
    private final IStudentStatus iStudentStatus;
    private final String cid;

    public QuestionAnalysisPresenter(QuestionAnalysisContract.View mView, String eid, IExaminStatus iExaminStatus, IAlreadyDoneSomeQuestionsStatus iAstatus, IStudentStatus iStudentStatus, String cid) {
        this.mView = mView;
        this.eid = eid;

        this.iExaminStatus = iExaminStatus;
        this.iAstatus = iAstatus;
        this.iStudentStatus = iStudentStatus;
        this.cid = cid;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        final ArrayList<AlreadyDoneQ> arrayList = new ArrayList<AlreadyDoneQ>();
iStudentStatus.selectCountStudentforClass(cid, new IStudentStatus.LoadCLassStudent() {
    @Override
    public void getLoadStudent(List<Student> list) {
        final int count = list.size();
        iAstatus.selectAllWrongForExamining(eid, true, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {



            @Override
            public void get(List<AlreadyDoneQ> list) {
                Log.i("TYY",list.size()+eid);
                for (int i=0;i<list.size();i++){

                    if (list.get(i).getSid().getCid().getObjectId().equals(cid)){
                        mView.initView((ArrayList<AlreadyDoneQ>) list, count);
                    }


                }
            }
        });
    }
});



       /* iExaminStatus.getJudmeAll(eid, new IExaminStatus.GetAllSelectCallBack() {

            @Override
            public void getAllQuestion(List<JudgementQuestions> list) {
                for (int i=0;i<list.size();i++){





                    iAstatus.countWrong(list.get(i).getObjectId(), new IAlreadyDoneSomeQuestionsStatus.GetCount() {
                        @Override
                        public void get(int count) {


                            if (isgo||isgo2){
                                mView.initView(arrayList);
                            }
                        }
                    });

                }

            }
        });
        iExaminStatus.GetRadioAll(eid, new IExaminStatus.GetAllQuestionCallBack() {
            @Override
            public void getAllquestion(List<RadioQuestions> list) {
                for (int i=0;i<list.size();i++){
                    final INfoBean iNfoBean = new INfoBean();
                    if (i==list.size()-1){
                        isgo2=true;

                    }

                    iNfoBean.setS1(list.get(i).getQuestion());

                    iAstatus.countWrong(list.get(i).getObjectId(), new IAlreadyDoneSomeQuestionsStatus.GetCount() {
                        @Override
                        public void get(int count) {
                            iNfoBean.setS2(100-2*count);
                            arrayList.add(iNfoBean);
                            if (isgo||isgo2){
                                mView.initView(arrayList);
                            }
                        }
                    });
                }
            }
        });*/




    }
}
