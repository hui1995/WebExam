package com.example.administrator.webexam.Contract.StudentContract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

import com.example.administrator.webexam.Module.Data.ReportCard;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/16.
 */

public interface HistoryScoreContract {

    interface View extends BaseView<Presenter>{



      void  initView(ArrayList<ReportCard> iNfoBeen);
    }


    interface Presenter extends BasePresenter{

    }
}
