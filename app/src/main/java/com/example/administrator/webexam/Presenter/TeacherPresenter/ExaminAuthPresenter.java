package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Contract.TeacherContract.ExaminAuthContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.IClassmateStatus;
import com.example.administrator.webexam.Module.IExaminStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/13.
 */

public class ExaminAuthPresenter implements ExaminAuthContract.Presenter {

    @NonNull
    private final ExaminAuthContract.View mView;

    @NonNull
    private final IExaminStatus iExaminStatus;

    @NonNull
    private final IClassmateStatus iClassmateStatus;
    @NonNull
    private final String tid;
    private static List<Classmate> list= new ArrayList<Classmate>();
    private static String geteid;


    public ExaminAuthPresenter(@NonNull ExaminAuthContract.View mView, @NonNull IExaminStatus iExaminStatus, @NonNull IClassmateStatus iClassmateStatus, @NonNull String eid) {
        this.mView = mView;
        this.iExaminStatus = iExaminStatus;
        this.iClassmateStatus = iClassmateStatus;
        this.tid = eid;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        iExaminStatus.selectExam(tid, new IExaminStatus.GetAllExaminCallBack() {
                    @Override
                    public void getAll(List<ExaminQuestion> examinQuestions) {
                        ArrayList<ExaminQuestion> arrayList = new ArrayList<ExaminQuestion>();
                        for (int i = 0; i < examinQuestions.size(); i++) {
                            if (examinQuestions.get(i).isFinish() && !examinQuestions.get(i).isPublic()) {
                                arrayList.add(examinQuestions.get(i));
                            }

                        }
                        mView.init(arrayList);
                    }
                }
        );
    }


    @Override
    public void getMyClassinfo(String s) {
        geteid=s;

        iClassmateStatus.selectalllist(tid, new IClassmateStatus.LoadallListCallBack() {
            @Override
            public void getlist(List<Classmate> classmates) {

                list=classmates;

                getStringclass(classmates);


            }
        });


    }

    private void getStringclass(List<Classmate> classmates){

        String[] classall = new String[classmates.size()];
        boolean[] bool = new boolean[classmates.size()];

        for (int i=0;i<classmates.size();i++){

            classall[i]= classmates.get(i).getCgrade()+"级"+classmates.get(i).getPname()+classmates.get(i).getCname();
            Log.i("LL",classall[i]);
            bool[i]=false;



        }
        mView.ShowClassinfo(classall,bool);





    }

    @Override
    public void setExaminForClass(String s,String eid) {


        //String[] strings = new String[applearSpan(s,",")];
        String[] strings =s.split(",");
        for (int i=0;i<strings.length;i++){

        }


        saveExaminForClass(strings,eid);





    }
    void saveExaminForClass(String[] strings,String eid){


        int[] num = new int[strings.length];
        for (int i=0;i<strings.length;i++){


            num[i] = Integer.parseInt(strings[i]);
            list.get(num[i]).getObjectId();

          iClassmateStatus.updateclassforexamin(geteid,list.get(num[i]).getObjectId());

            ExaminQuestion examinQuestion = new ExaminQuestion();
            examinQuestion.setExamining(true);
            examinQuestion.setFinish(true);
            iExaminStatus.updateExaminstgate(eid,examinQuestion);


        }








    }





}
