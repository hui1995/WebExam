package com.example.administrator.webexam.Contract.TeacherContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.RadioQuestions;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface AddQuestionPagerContract {

interface View extends BaseView<Presenter>{
    void Toast();
    void gonext();

}


interface Presenter extends BasePresenter{

void save(String question,String option1,String option2,String option3,String option4,String answer,String ansyler);

    void save(String question, String answer,String ansyler);
    void savetitle(String title);
    void submitAdmin();

}
}

