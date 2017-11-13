package com.example.administrator.webexam.Presenter.AdminPresenter;

import android.support.annotation.NonNull;

import com.example.administrator.webexam.Contract.AdminContract.AdminMainContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/9/26.
 */

public class AdminMainPresenter implements AdminMainContract.Presenter {

     @NonNull
    private final AdminMainContract.IndexView indexView;
    @NonNull
    private final AdminMainContract.ClassView classView;
    @NonNull
    private final AdminMainContract.MeView meView;

    public AdminMainPresenter(@NonNull AdminMainContract.IndexView indexView, @NonNull AdminMainContract.ClassView classView, @NonNull AdminMainContract.MeView meView) {
        this.indexView = checkNotNull(indexView);
        this.classView = checkNotNull(classView);
        this.meView = checkNotNull(meView);
        indexView.setPresenter(this);
        classView.setPresenter(this);
        meView.setPresenter(this);
    }




    @Override
    public void start() {

    }
}
