package com.example.administrator.webexam.View.Register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.webexam.Contract.RegisterContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
/*
*
*
*
* */




public class RegisterPhone extends Fragment implements RegisterContract.RegPhoneView {


    @Bind(R.id.input_phone)
    EditText inputPhone;
    @Bind(R.id.input_name)
    EditText inputName;
    @Bind(R.id.input_passwd)
    EditText inputPasswd;
    @Bind(R.id.check_passwd)
    EditText checkPasswd;
    @Bind(R.id.get_code)
    Button getCode;
    private OnButtonClick onButtonClick;
    private RegisterContract.Presenter mPresenter;


    private static final String SIGNUP_TYPE = "signup_type";

    public static RegisterPhone newsInstance() {
        RegisterPhone registerPhone = new RegisterPhone();
/*        Bundle args = new Bundle();
        args.putBoolean(SIGNUP_TYPE, isTeacher);
        registerPhone.setArguments(args);*/

        return registerPhone;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_register_phone, container, false);
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
//       mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getSignInfo() {
        String phone = inputPhone.getText().toString();
        String passwd = inputPasswd.getText().toString();
        String passwd2 = checkPasswd.getText().toString();
        String nickname = inputName.getText().toString();

        if (mPresenter.checkPhone(phone)) {
            if (mPresenter.isNoEmptyNickname(nickname)) {

                if (mPresenter.checkPasswd(passwd, passwd2)) {

                    if (mPresenter.isNoSignupPhone(phone)) {
                        mPresenter.selectphoneStudent();
                        // mPresenter.getcode(phone,getActivity());

                    } else {
                        Toast.makeText(getActivity(), "该账号已注册", Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(getActivity(), "两次密码输入不一致", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getActivity(), "昵称不能为空", Toast.LENGTH_LONG).show();
            }


        } else {
            Toast.makeText(getActivity(), "手机号格式不正确", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setCodefail() {
        Toast.makeText(getActivity(), "获取代码失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void nextgetcode(Boolean isTeacher) {
        onButtonClick.onClick(getCode, isTeacher);
    }

    @Override
    public void toastphoneexit() {
        Toast.makeText(getActivity(), "该手机已注册", Toast.LENGTH_LONG).show();
    }

    @Override
    public void gonextindex() {

        mPresenter.getcode(inputPhone.getText().toString(), getActivity());
    }

    @Override
    public void test(String phone, String phone2) {
        Toast.makeText(getActivity(), phone + "||" + phone2, Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.get_code)
    public void onViewClicked() {
        getSignInfo();
    }


    public OnButtonClick getOnButonClick() {

        return onButtonClick;
    }


    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }


    public interface OnButtonClick {
        public void onClick(View view, boolean isTeacher);
    }
}
