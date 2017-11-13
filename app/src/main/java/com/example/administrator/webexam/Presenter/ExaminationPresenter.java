package com.example.administrator.webexam.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Contract.ExaminationContract;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.IReportCardStatus;
import com.example.administrator.webexam.Module.impl.AlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.impl.ReportCardStatus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/10/14.
 */

public class ExaminationPresenter implements ExaminationContract.Presenter {
private static  int count;

    private IReportCardStatus iReportCardStatus;
    private IAlreadyDoneSomeQuestionsStatus iAlreadyDoneSomeQuestionsStatus;
   private static    List<BmobObject> list2;
    public ExaminationPresenter() {
        this.iReportCardStatus = new ReportCardStatus();
        iAlreadyDoneSomeQuestionsStatus= new AlreadyDoneSomeQuestionsStatus();
        list2 = new ArrayList<BmobObject>();
    }

    @Override
    public void start() {

    }

    @Override
    public void checkAnswer(ArrayList<String> arrayList,int type,Context context) {


        if (type==4){

            for (int i=0;i<10;i++){


                if (i<ExaminationActivity.list1.size()){
                    if (arrayList.get(i).equals(ExaminationActivity.list1.get(i).getAnswer())) {
                        count = count + 2;


                        deleteReport(ExaminationActivity.ObjectId, ExaminationActivity.list1.get(i).getObjectId(),true);


                    }

                }else{
                    String info;
                    int co =ExaminationActivity.list1.size()+1;
                    if (ExaminationActivity.list2.get(10-co).getAnswer()){
                        info ="true";
                    }else{
                        info="false";
                    }


                    if (arrayList.get(i).equals(info)){
                        count = count + 2;


                        deleteReport(ExaminationActivity.ObjectId, ExaminationActivity.list2.get(10-co).getObjectId(),false);


                    }

                }
            }

        }else{



            for (int i=0;i<arrayList.size();i++){


                if (i<arrayList.size()/2){


                    if (arrayList.get(i).equals(ExaminationActivity.list1.get(i).getAnswer())){
                        count=count+2;


                    }else{

                        if (arrayList.get(i)!="null") {

                            if (type == 3) {
                                saveQuestionWrong(ExaminationActivity.examinid, ExaminationActivity.ObjectId,
                                        true, ExaminationActivity.list1.get(i).getObjectId(), arrayList.get(i), true);
                            }else
                            {
                                saveQuestionWrong(ExaminationActivity.examinid, ExaminationActivity.ObjectId,
                                        false, ExaminationActivity.list1.get(i).getObjectId(), arrayList.get(i), true);
                            }
                        }

                    }


                }else {
                    String info;




                    if (ExaminationActivity.list2.get(i-(arrayList.size()/2)).getAnswer()){
                        info ="true";
                    }else{
                        info="false";
                    }


                    if (arrayList.get(i).equals(info)){
                        count=count+2;


                    }else{
                        if (arrayList.get(i)!="null") {
                            if (type == 3) {
                                saveQuestionWrong(ExaminationActivity.examinid, ExaminationActivity.ObjectId,
                                        true, ExaminationActivity.list2.get(i - arrayList.size() / 2).getObjectId(), arrayList.get(i), false);
                            }else
                            {
                                saveQuestionWrong(ExaminationActivity.examinid, ExaminationActivity.ObjectId,
                                        false, ExaminationActivity.list2.get(i - arrayList.size() / 2).getObjectId(), arrayList.get(i), false);

                            }
                        }

                    }


                }
            }
        }


if (type==3||type==2){
    saveReportCard(count,context,type);
}




    }

    @Override
    public int countscord() {
        return count;
    }

    private void saveQuestionWrong(String eid,String sid,boolean isClassExamin,String Rjid,String youranswer,boolean isRadio){
        final AlreadyDoneQ alreadyDoneQ = new AlreadyDoneQ();
        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setObjectId(eid);
        Student student=new Student();
        student.setObjectId(sid);
        alreadyDoneQ.setEid(examinQuestion);
        alreadyDoneQ.setSid(student);

        alreadyDoneQ.setClassExamin(isClassExamin);

        if (isRadio){
            RadioQuestions radioQuestions= new RadioQuestions();
            radioQuestions.setObjectId(Rjid);
            alreadyDoneQ.setRid(radioQuestions);
            JudgementQuestions judgementQuestions= new JudgementQuestions();
            judgementQuestions.setObjectId("");
            alreadyDoneQ.setJid(judgementQuestions);
        }else {
            JudgementQuestions judgementQuestions= new JudgementQuestions();
            judgementQuestions.setObjectId(Rjid);
            alreadyDoneQ.setJid(judgementQuestions);
            RadioQuestions radioQuestions= new RadioQuestions();
            radioQuestions.setObjectId("");
            alreadyDoneQ.setRid(radioQuestions);

        }
        alreadyDoneQ.setYouranswer(youranswer);
        alreadyDoneQ.setRadio(isRadio);


        if (isRadio){
            iAlreadyDoneSomeQuestionsStatus.selectRId(sid, Rjid, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {
                @Override
                public void get(List<AlreadyDoneQ> list) {
                    if (list.size()==0){
                        iAlreadyDoneSomeQuestionsStatus.save(alreadyDoneQ);
                    }else{

                        iAlreadyDoneSomeQuestionsStatus.update(list.get(0).getObjectId(), alreadyDoneQ);
                    }
                }
            });
        }else{
            iAlreadyDoneSomeQuestionsStatus.selectJId(sid, Rjid, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {
                @Override
                public void get(List<AlreadyDoneQ> list) {
                    if (list.size()==0){
                        iAlreadyDoneSomeQuestionsStatus.save(alreadyDoneQ);
                    }else{

                        iAlreadyDoneSomeQuestionsStatus.update(list.get(0).getObjectId(), alreadyDoneQ);
                    }
                }
            });

        }



    };

    private void deleteReport(String sid,String rjid,boolean isRadio) {

        Log.i("IIII",sid+rjid);

        if (isRadio) {

            iAlreadyDoneSomeQuestionsStatus.selectRId(sid, rjid, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {
                @Override
                public void get(List<AlreadyDoneQ> list) {
                    if (list.size() == 0) {

                    } else {
                        iAlreadyDoneSomeQuestionsStatus.deleteQuestionWrong(list.get(0).getObjectId());

                    }
                }
            });
        }else{
            iAlreadyDoneSomeQuestionsStatus.selectJId(sid, rjid, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {
                @Override
                public void get(List<AlreadyDoneQ> list) {
                    if (list.size() == 0) {

                    } else {
                        iAlreadyDoneSomeQuestionsStatus.deleteQuestionWrong(list.get(0).getObjectId());

                    }
                }
            });

        }
    }


    private void saveReportCard(int i, Context context,int type){




        ReportCard reportCard = new ReportCard();
        ExaminQuestion examinQuestion=new ExaminQuestion();
        examinQuestion.setObjectId(ExaminationActivity.examinid);
        Student student= new Student();
        student.setObjectId(ExaminationActivity.ObjectId);
        reportCard.setEid(examinQuestion);
        reportCard.setSid(student);


        if (type==3){
            reportCard.setClassExamin(true);



        }else {
            reportCard.setClassExamin(false);
        }
        reportCard.setScore(i);
        SharedPreferences userInfo = context.getSharedPreferences("user_login", 0);
        String cid = userInfo.getString("cid","");

        Classmate classmate= new Classmate();
        classmate.setObjectId(cid);
        reportCard.setCid(classmate);

        iReportCardStatus.saveReportCard(reportCard);




    }


}
