package com.example.administrator.webexam.Presenter.StudentPresenter;

import android.support.annotation.NonNull;
import android.util.Log;


import com.example.administrator.webexam.Contract.StudentContract.QuestionWrongContract;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.IExaminStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public class QuestionWrongPresenter implements QuestionWrongContract.Presenter {


    @NonNull
    private final QuestionWrongContract.View mView;

    @NonNull
    private final String sid;
    @NonNull
    private final boolean isCLass;
    @NonNull
    private final IAlreadyDoneSomeQuestionsStatus iAlreadyDoneSomeQuestionsStatus;
    @NonNull
    private final IExaminStatus iExaminStatus;

    private final String eid;

    ArrayList<AlreadyDoneQ> arrayList = new ArrayList<AlreadyDoneQ>();

    public QuestionWrongPresenter(@NonNull QuestionWrongContract.View mView, @NonNull String sid, boolean isClass,  @NonNull IAlreadyDoneSomeQuestionsStatus iAlreadyDoneSomeQuestionsStatus, @NonNull IExaminStatus iExaminStatus,String eid) {

        this.mView = mView;
        this.sid=sid;
        this.isCLass=isClass;
        this.eid=eid;
        this.iAlreadyDoneSomeQuestionsStatus = iAlreadyDoneSomeQuestionsStatus;

        this.iExaminStatus = iExaminStatus;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        iAlreadyDoneSomeQuestionsStatus.selectAll(sid, isCLass, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {
            @Override
            public void get(List<AlreadyDoneQ> list) {
ArrayList<AlreadyDoneQ> alreadyDoneQs = new ArrayList<AlreadyDoneQ>();
                if (isCLass){

                    for (int i=0;i<list.size();i++){




                        if (list.get(i).getSid().getCid().getEid().getObjectId()==eid){
                            AlreadyDoneQ alreadyDoneQ = new AlreadyDoneQ();

                            alreadyDoneQ=list.get(i);
                            alreadyDoneQs.add(alreadyDoneQ);


                        }
                    }

                    mView.initView(alreadyDoneQs,alreadyDoneQs.size());

                    }else {
                    mView.initView((ArrayList<AlreadyDoneQ>) list,list.size());
                }



            }
        });

/*        iAlreadyDoneSomeQuestionsStatus.selectAll(sid, isCLass, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {

            boolean isExaminEmty=true;
            boolean isExamining=false;
            @Override
            public void get(final List<AlreadyDoneQ> list) {
                arrayList= (ArrayList<AlreadyDoneQ>) list;


                final List<INfoBean> l = new ArrayList<INfoBean>();
                for (int i=0;i<list.size();i++){

                    final INfoBean iNfoBean = new INfoBean();
                    iNfoBean.setS2(i);



iExaminStatus.getExaminname(list.get(i).getEid(), new IExaminStatus.GetExaminname() {
    @Override
    public void getExaminname(ExaminQuestion examinQuestion) {

        //判断是否为空
        if (examinQuestion==null){
            isExaminEmty=true;

        }else{
            isExaminEmty=false;
            if (examinQuestion.isExamining()){
                isExamining=true;
            }else{
                isExamining=false;
            }

        }
    }
});


                    if (list.get(i).isRadio()){

                        iExaminStatus.getQuestionRadio(list.get(i).getRjid(), new IExaminStatus.GetQuestionRadio() {
                            @Override
                            public void get(final RadioQuestions radioQuestions) {
                                if (isExaminEmty) {

                                    return;

                                } else {
                                    if (isExamining){
return;
                                    }else{
                                        iNfoBean.setS1(radioQuestions.getQuestion());
                                        l.add(iNfoBean);


                                            mView.initView((ArrayList<INfoBean>) l, list.size());



                                    }


                                }
                            }
                        });


                    }else {


                        iExaminStatus.GetQuestionJudge(list.get(i).getRjid(), new IExaminStatus.GetQuestionJudge() {
                            @Override
                            public void get(final JudgementQuestions judgementQuestions) {

                                if (isExaminEmty) {

                                    return;

                                } else {
                                    if (isExamining) {
return;
                                    } else {
                                        iNfoBean.setS1(judgementQuestions.getQuestion());
                                        l.add(iNfoBean);



                                            mView.initView((ArrayList<INfoBean>) l, list.size());



                                    }
                                }




                            }
                        });
                    }



                    }




                }

        });*/



    }

    ///获取详细错题信息

    @Override
    public void getQuestionInfo(String objectid,boolean isRadio) {




        if (isRadio){
            iExaminStatus.getQuestionRadio(objectid, new IExaminStatus.GetQuestionRadio() {
                @Override
                public void get(RadioQuestions radioQuestions) {
                    mView.questionINfo(radioQuestions.getObjectId(),radioQuestions.getAnswer(),true);
                }
            });
        }else {
            iExaminStatus.GetQuestionJudge(objectid, new IExaminStatus.GetQuestionJudge() {
                @Override
                public void get(JudgementQuestions judgementQuestions) {
                    String answer;
                    if (judgementQuestions.getAnswer()){
                        answer="true";
                    }else{
                        answer="false";
                    }
                    mView.questionINfo(judgementQuestions.getObjectId(),answer,false);
                }
            });
        }






        }






}
