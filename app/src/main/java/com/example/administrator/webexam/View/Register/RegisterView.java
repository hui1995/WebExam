package com.example.administrator.webexam.View.Register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.webexam.Activity.LoginActivity;
import com.example.administrator.webexam.Contract.RegisterContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterView extends Fragment implements RegisterContract.RegView {


    @Bind(R.id.teacher_reg)
    Button teacherReg;
    @Bind(R.id.student_reg)
    Button studentReg;
    @Bind(R.id.tologin)
    TextView tologin;
    private RegisterContract.Presenter mPresenter;
    private OnButtonClick onButtonClick;
//private Button teacherReg,studentReg;
    public static RegisterView newsInstance() {


        return new RegisterView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_view, container, false);
       // teacherReg = view.findViewById(R.id.teacher_reg);
       // studentReg  =view.findViewById(R.id.student_reg);
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

    @OnClick({R.id.teacher_reg, R.id.student_reg, R.id.tologin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teacher_reg:

                onButtonClick.onClick(teacherReg);
                mPresenter.saveSignup_Type(true,getActivity());
                break;
            case R.id.student_reg:
                onButtonClick.onClick(studentReg);
                mPresenter.saveSignup_Type(false,getActivity());
                break;
            case R.id.tologin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }



    public OnButtonClick getOnButonClick(){

        return onButtonClick;
    }



    public void setOnButtonClick(OnButtonClick onButtonClick){
        this.onButtonClick = onButtonClick;
    }



    public interface OnButtonClick{
        public void onClick(View view);
    }

}
