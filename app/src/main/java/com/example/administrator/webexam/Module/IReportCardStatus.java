package com.example.administrator.webexam.Module;

import com.example.administrator.webexam.Module.Data.ReportCard;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */

public interface IReportCardStatus {

    interface HadExaminCallBack{

        void isDo(boolean isdo);
    }

    interface HadReportLast{

        void get(List<ReportCard> reportCards);
    }







    void saveReportCard(ReportCard reportCard);

    void haddoexamin(String sid,String eid, HadExaminCallBack hadExaminCallBack
    );

    void getReportList(String sid,boolean isClass,HadReportLast callback);

    void haddoexamin(String eid, HadReportLast callBack);
    void haddoexamin(String eid, String cid,HadReportLast callBack);
}
