package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/22.
 */

public class Classmate extends BmobObject {


    private String pname;
    private String cgrade;
    private String cname;
    private ExaminQuestion eid;

    private Teacher tid;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCgrade() {
        return cgrade;
    }

    public void setCgrade(String cgrade) {
        this.cgrade = cgrade;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }



    public ExaminQuestion getEid() {
        return eid;
    }

    public void setEid(ExaminQuestion eid) {
        this.eid = eid;
    }

    public Teacher getTid() {
        return tid;
    }

    public void setTid(Teacher tid) {
        this.tid = tid;
    }
}
