package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/10/15.
 */

public class ReportCard extends BmobObject {



    private Student sid;
    private ExaminQuestion eid;
    private int  score;
    private Classmate cid;
    private boolean isClassExamin;

    public Student getSid() {
        return sid;
    }

    public void setSid(Student sid) {
        this.sid = sid;
    }

    public void setEid(ExaminQuestion eid) {
        this.eid = eid;
    }

    public ExaminQuestion getEid() {
        return eid;
    }

    public Classmate getCid() {
        return cid;
    }

    public void setCid(Classmate cid) {
        this.cid = cid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isClassExamin() {
        return isClassExamin;
    }

    public void setClassExamin(boolean classExamin) {
        isClassExamin = classExamin;
    }
}
