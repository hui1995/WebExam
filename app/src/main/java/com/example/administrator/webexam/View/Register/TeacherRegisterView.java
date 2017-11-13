package com.example.administrator.webexam.View.Register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.LoginActivity;
import com.example.administrator.webexam.Contract.RegisterContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 *
 *
 * 注册跳转登录，未等待注册成功就跳转
 */
public class TeacherRegisterView extends Fragment implements RegisterContract.RegTeacherView {


    @Bind(R.id.input_smscode)
    EditText inputSmscode;
    @Bind(R.id.input_univercity)
    EditText inputUnivercity;
    @Bind(R.id.input_name)
    EditText inputName;
    @Bind(R.id.input_teacherid)
    EditText inputTeacherid;
    @Bind(R.id.singup_teacher)
    Button singupTeacher;
    private RegisterContract.Presenter mPresenter;

    public static TeacherRegisterView newsInsante() {


        return new TeacherRegisterView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_register_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
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

    @OnClick(R.id.singup_teacher)
    public void onViewClicked() {

     mPresenter.checkSMS(getActivity(),inputSmscode.getText().toString());
      /*      //
         if (mPresenter.checkinfo(inputName.getText().toString(),inputTeacherid.getText().toString(),inputUnivercity.getText().toString())){




         }else {
             Toast.makeText(getActivity(),"信息错误",Toast.LENGTH_LONG).show();
         }

        }else{


            Toast.makeText(getActivity(),"验证码错误，注册失败",Toast.LENGTH_LONG).show();
        }*/

      startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void ToastNoName() {
        Toast.makeText(getActivity(),"用户名未空",Toast.LENGTH_LONG).show();
    }

    @Override
    public void ToastNoUniversity() {
        Toast.makeText(getActivity(),"大学名为空",Toast.LENGTH_LONG).show();
    }

    @Override
    public void ToasNoTeacherID() {
        Toast.makeText(getActivity(),"教职工编号为空",Toast.LENGTH_LONG).show();

    }

    @Override
    public void signupgood() {
        if (mPresenter.checkinfo(inputName.getText().toString(),inputTeacherid.getText().toString(),inputUnivercity.getText().toString())){
mPresenter.signupTeacher();



        }else {
            Toast.makeText(getActivity(),"相关信息错误",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void signupfail() {

        Toast.makeText(getActivity(),"验证码错误，请重新验证",Toast.LENGTH_LONG).show();


    }
}
