package com.example.administrator.webexam.Presenter.TeacherPresenter;

import android.support.annotation.NonNull;

import com.example.administrator.webexam.Contract.TeacherContract.ExaminHistoryClassContract;

/**
 * Created by Administrator on 2017/10/17.
 */

public class ExaminHistoryClassPresenter implements ExaminHistoryClassContract.Presenter {



    @NonNull
    private final ExaminHistoryClassContract.View mView;

    public ExaminHistoryClassPresenter(@NonNull ExaminHistoryClassContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
