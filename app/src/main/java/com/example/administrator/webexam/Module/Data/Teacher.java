package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/20.
 */

public class Teacher extends BmobObject {

    private String tphone;
    private String tpasswd;
    private University uid;
    private String teacherid;
    private String tname;
    private String tnickname;

    private boolean isAuthentication;
    public String getTnickname() {
        return tnickname;
    }

    public void setTnickname(String tnickname) {
        this.tnickname = tnickname;
    }


    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public String getTpasswd() {
        return tpasswd;
    }

    public void setTpasswd(String tpasswd) {
        this.tpasswd = tpasswd;
    }

    public University getUniversity() {
        return uid;
    }

    public void setUniversity(University uid) {
        this.uid = uid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public boolean isAuthentication() {
        return isAuthentication;
    }

    public void setAuthentication(boolean authentication) {
        isAuthentication = authentication;
    }






}
