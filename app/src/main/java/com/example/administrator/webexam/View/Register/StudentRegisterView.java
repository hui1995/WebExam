package com.example.administrator.webexam.View.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.LoginActivity;
import com.example.administrator.webexam.Activity.RegisterActivity;
import com.example.administrator.webexam.Contract.RegisterContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class StudentRegisterView extends Fragment implements RegisterContract.RegStudentView {


    @Bind(R.id.getsmscode)
    EditText getsmscode;
    @Bind(R.id.signupStudent)
    Button signupStudent;
    private RegisterContract.Presenter mPresenter;


    public static StudentRegisterView newsInsante() {


        return new StudentRegisterView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_register_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(@NonNull RegisterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    //注册按钮

    @OnClick(R.id.signupStudent)
    public void onViewClicked() {
        Toast.makeText(getActivity(),getsmscode.getText(),Toast.LENGTH_LONG).show();

//核对短信
        mPresenter.checkSMS(getActivity(),getsmscode.getText().toString());



    }
//注册成功跳转到登录页面
    @Override
    public void signupgood() {

        mPresenter.signupStudent();
        startActivity(new Intent(getActivity(), LoginActivity.class));

    }
//注册失败提示
    @Override
    public void signupfail() {
        Toast.makeText(getActivity(),"注册成功，请重新注册",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), RegisterActivity.class));
    }
}
