package com.example.administrator.webexam.Module.Data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/20.
 */

public class Admin extends BmobObject{

    private String rphone;
    private String rpasswd;

    public String getRphone() {
        return rphone;
    }

    public void setRphone(String rphone) {
        this.rphone = rphone;
    }

    public String getRpasswd() {
        return rpasswd;
    }

    public void setRpasswd(String rpasswd) {
        this.rpasswd = rpasswd;
    }
}
