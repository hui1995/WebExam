package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/10/9.
 */

public class JudgementQuestions extends BmobObject {


    private ExaminQuestion eid;
    private String question;

    private Boolean answer;
    private String analytic;

    public ExaminQuestion getEid() {
        return eid;
    }

    public void setEid(ExaminQuestion eid) {
        this.eid = eid;
    }

    public String getQuestion() {
        return question;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAnalytic() {
        return analytic;
    }

    public void setAnalytic(String analytic) {
        this.analytic = analytic;
    }
}
