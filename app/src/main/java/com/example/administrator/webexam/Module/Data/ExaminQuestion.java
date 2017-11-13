package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/10/9.
 */

public class ExaminQuestion extends BmobObject {

    private String examinname;
    private Teacher tid;
    private boolean isPublic;
    private boolean isFinish;

    private boolean isExamining;

    public String getExaminname() {
        return examinname;
    }

    public void setExaminname(String examinname) {
        this.examinname = examinname;
    }



    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isExamining() {
        return isExamining;
    }

    public void setExamining(boolean examining) {
        isExamining = examining;
    }

    public Teacher getTid() {
        return tid;
    }

    public void setTid(Teacher tid) {
        this.tid = tid;
    }
}
