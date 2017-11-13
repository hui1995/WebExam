package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class AlreadyDoneQ extends BmobObject {


    private Student sid;
    private ExaminQuestion eid;
    private RadioQuestions rid;
    private boolean isRadio;
    private String youranswer;
    private boolean isClassExamin;
    private JudgementQuestions jid;

    public Student getSid() {
        return sid;
    }

    public void setSid(Student sid) {
        this.sid = sid;
    }

    public ExaminQuestion getEid() {
        return eid;
    }

    public void setEid(ExaminQuestion eid) {
        this.eid = eid;
    }

    public RadioQuestions getRid() {
        return rid;
    }

    public void setRid(RadioQuestions rid) {
        this.rid = rid;
    }

    public JudgementQuestions getJid() {
        return jid;
    }

    public void setJid(JudgementQuestions jid) {
        this.jid = jid;
    }

    public boolean isRadio() {
        return isRadio;
    }

    public void setRadio(boolean radio) {
        isRadio = radio;
    }

    public String getYouranswer() {
        return youranswer;
    }

    public void setYouranswer(String youranswer) {
        this.youranswer = youranswer;
    }

    public boolean isClassExamin() {
        return isClassExamin;
    }

    public void setClassExamin(boolean classExamin) {
        isClassExamin = classExamin;
    }
}
