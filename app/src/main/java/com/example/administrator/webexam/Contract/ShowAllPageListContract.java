package com.example.administrator.webexam.Contract;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12.
 */

public interface ShowAllPageListContract {

    interface View extends BaseView<Presenter>{


        void showList(ArrayList<ExaminQuestion> arrayList);
    }

    interface Presenter extends BasePresenter{

        void updateExamin(String eid);

    }
}
