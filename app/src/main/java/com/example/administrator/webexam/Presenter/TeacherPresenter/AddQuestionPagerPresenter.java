package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.webexam.Activity.AddClassActivity;
import com.example.administrator.webexam.Activity.AddQuestionPagerActivity;
import com.example.administrator.webexam.Contract.TeacherContract.AddQuestionPagerContract;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/9.
 */

public class AddQuestionPagerPresenter implements AddQuestionPagerContract.Presenter {


    private final ArrayList<RadioQuestions> arrayList = new ArrayList<RadioQuestions>();
    private final AddQuestionPagerContract.View mView;
    private final IExaminStatus iExaminStatus;
    private String examid;
    private Context context;
    private String ObjectId;
    private String OBJECTID ="ObjectId";
    private String LOGIN_TYPE="login_type";


    public AddQuestionPagerPresenter(AddQuestionPagerContract.View mView,Context context) {
        this.mView = mView;
        this.context =context;
        iExaminStatus= new ExaminStatus();
    }

    @Override
    public void start() {

    }



    //保存选择题
    @Override
    public void save(String question, String option1, String option2, String option3, String option4, String answer, String ansyler) {

final RadioQuestions radioQuestions = new RadioQuestions();
        if (question.isEmpty()||option1.isEmpty()||option2.isEmpty()||option3.isEmpty()||option4.isEmpty()||answer==null){

            mView.Toast();
        }else{

            if (answer==null){
              answer="A";
            }

            radioQuestions.setAnalytic(ansyler);
            radioQuestions.setAnswer(answer);
            radioQuestions.setQuestion(question);
            radioQuestions.setOption1(option1);
            radioQuestions.setOption2(option2);
            radioQuestions.setOption3(option3);
            radioQuestions.setOption4(option4);


            iExaminStatus.selectLastId(new IExaminStatus.GetEidCallBack() {
                @Override
                public void getEid(String eid) {

                    ExaminQuestion examinQuestion = new ExaminQuestion();
                    if (eid==null){

                        examinQuestion.setObjectId(AddQuestionPagerActivity.eid);

                        radioQuestions.setEid(examinQuestion);
                     examid=AddQuestionPagerActivity.eid;
                    }else {

                        examinQuestion.setObjectId(eid);
                        radioQuestions.setEid(examinQuestion);
                        examid=eid;
                    }




                }


            });


            iExaminStatus.saveRadioQuestions(radioQuestions);
            mView.gonext();


        }
    }
//保存判断题
    @Override
    public void save(final String question, final String answer, final String ansyler) {
        final JudgementQuestions judgementQuestions = new JudgementQuestions();
     Boolean isTrue=true;
        if (question.isEmpty()||answer==null){


            mView.Toast();
        }else{
            if (answer.equals("false")){
                isTrue= false;
            }else{
                isTrue=true;

            }
            judgementQuestions.setQuestion(question);
            judgementQuestions.setAnalytic(ansyler);
            judgementQuestions.setAnswer(isTrue);




            iExaminStatus.selectLastId(new IExaminStatus.GetEidCallBack() {
                @Override
                public void getEid(String eid) {
                    ExaminQuestion examinQuestion = new ExaminQuestion();
                    if (eid==null){
examinQuestion.setObjectId(AddQuestionPagerActivity.eid);
                        judgementQuestions.setEid(examinQuestion);
                        examid =AddQuestionPagerActivity.eid;
                    }else{
                        examinQuestion.setObjectId(eid);
                        judgementQuestions.setEid(examinQuestion);
                        examid = eid;
                    }
                  //  Log.i("KKK","Examid"+eid+"GET"+AddQuestionPagerActivity.eid);






                }


            });
            iExaminStatus.saveJudgementQuestions(judgementQuestions);
            mView.gonext();
        }
    }
//保存试卷信息
    @Override
    public void savetitle(String title) {

        if (title.isEmpty()){

            mView.Toast();
        }else{
            SharedPreferences userInfo = context.getSharedPreferences("user_login", 0);
            ObjectId= userInfo.getString(OBJECTID,"");

            ExaminQuestion examinQuestion = new ExaminQuestion();
            examinQuestion.setExaminname(title);
            Teacher teacher = new Teacher();
            teacher.setObjectId(ObjectId);
            examinQuestion.setTid(teacher);
            examinQuestion.setFinish(false);
            examinQuestion.setPublic(false);
            examinQuestion.setExamining(false);
            iExaminStatus.saveExamininfo(examinQuestion);

            mView.gonext();
        }
    }

    @Override
    public void submitAdmin() {
        ExaminQuestion examinQuestion = new ExaminQuestion();
        examinQuestion.setFinish(true);
        iExaminStatus.updateExaminstgate(examid,examinQuestion);

        mView.gonext();

    }

}
