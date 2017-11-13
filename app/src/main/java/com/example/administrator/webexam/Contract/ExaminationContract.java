package com.example.administrator.webexam.Contract;

import android.content.Context;

import com.example.administrator.webexam.BasePresenter;
import com.example.administrator.webexam.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/14.
 */

public interface ExaminationContract {

    interface View extends BaseView<Presenter>{
        void gonext();

    }

    interface Presenter extends BasePresenter{

        void checkAnswer(ArrayList<String> arrayList, int type, Context context);
        int countscord();

    }
}
