package com.example.administrator.webexam.Module;

import com.example.administrator.webexam.Module.Data.Classmate;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public interface IClassmateStatus {
interface  LoadallListCallBack{
    void getlist(List<Classmate> classmates);

}

interface LoadOneInfoCallBack{
    void getClassInfo(Classmate classmate);
}







    void add(Classmate classmate);
    void selectalllist(String tid,LoadallListCallBack loadallListCallBack);



/*    void selectalllist2(LoadallListCallBack loadallListCallBack);*/

void updateclassforexamin(String eid,String cid);
    void selectExaminnow(String cid,LoadOneInfoCallBack callBack);

    void selectClass(String sid,LoadallListCallBack callBack);

void selectClassStatus(String eid,LoadallListCallBack callBack);




}
