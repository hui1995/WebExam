package com.example.administrator.webexam.Module.Data;

/**
 * Created by Administrator on 2017/10/9.
 */

public class StudentGrades {

    private String sid;
    private String eid;
    private boolean isMyClassExam;

    private String grade;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public boolean isMyClassExam() {
        return isMyClassExam;
    }

    public void setMyClassExam(boolean myClassExam) {
        isMyClassExam = myClassExam;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
