package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/20.
 */

public class Student extends BmobObject {



    private String sphone;
    private String spasswd;

    public String getSnickname() {
        return snickname;
    }

    public void setSnickname(String snickname) {
        this.snickname = snickname;
    }

    private String snickname;
    private String sname;
    private String suid;
    private Classmate cid;
    private boolean isAutherntication;

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSpasswd() {
        return spasswd;
    }

    public void setSpasswd(String spasswd) {
        this.spasswd = spasswd;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public Classmate getCid() {
        return cid;
    }

    public void setCid(Classmate cid) {
        this.cid = cid;
    }

    public boolean isAutherntication() {
        return isAutherntication;
    }

    public void setAutherntication(boolean autherntication) {
        isAutherntication = autherntication;
    }


}
