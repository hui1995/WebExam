package com.example.administrator.webexam.View.StudentView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.MyClassActivity;
import com.example.administrator.webexam.Contract.StudentContract.MyClassContract;
import com.example.administrator.webexam.Contract.StudentContract.StudentMainContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassStudentView extends Fragment implements StudentMainContract.ClassView {


    @Bind(R.id.go_my_class)
    Button goMyClass;

    private StudentMainContract.Presenter mPresenter;


    public static ClassStudentView newInstance() {


        return new ClassStudentView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

 View view = inflater.inflate(R.layout.fragment_class_student_view, container, false);
    ButterKnife.bind(this, view);






        return view;
    }

    @Override
    public void setPresenter(StudentMainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startClass();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.go_my_class)
    public void onViewClicked() {
       mPresenter.isgoClass(getActivity());



    }

    @Override
    public void toAuth() {
        Toast.makeText(getActivity(),"请前往认证中心认证",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ToastAuthing() {
        Toast.makeText(getActivity(),"正在认证，请等待认证成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ToastAuthgood() {
        startActivity(new Intent(getActivity(),MyClassActivity.class));
      //  getActivity().finish();

    }
}
