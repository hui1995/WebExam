package com.example.administrator.webexam.View.StudentView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.HistoryScoreActivity;
import com.example.administrator.webexam.Activity.LoginActivity;
import com.example.administrator.webexam.Activity.StudentAuthActivity;
import com.example.administrator.webexam.Activity.UpdatePasswdActivity;
import com.example.administrator.webexam.Contract.StudentContract.StudentMainContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class MeStudentView extends Fragment implements StudentMainContract.MeView {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.myNickName)
    TextView myNickName;
    @Bind(R.id.phone)
    TextView input_phone;
    @Bind(R.id.auth)
    Button auth;
    @Bind(R.id.mess)
    Button mess;
    @Bind(R.id.changepasswd)
    Button changepasswd;
    @Bind(R.id.loginout)
    Button loginout;
    private StudentMainContract.Presenter mPresenter;




    public static MeStudentView newInstance() {

        return new MeStudentView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

View  view = inflater.inflate(R.layout.fragment_me_student_view, container, false);
    ButterKnife.bind(this, view);




        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startMe(getActivity());
    }


    @Override
    public void setPresenter(StudentMainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.auth, R.id.mess, R.id.changepasswd, R.id.loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auth:

                mPresenter.isauth();
              //  startActivity(new Intent(getActivity(), StudentAuthActivity.class));
                break;
            case R.id.mess:

                Intent intent = new Intent(getActivity(), HistoryScoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isClass",false);
                intent.putExtras(bundle);
                startActivity(intent);
             //   getActivity().finish();
                break;
            case R.id.changepasswd:
                mPresenter.GetIdInfo();
                break;
            case R.id.loginout:

                mPresenter.loginout(getActivity());
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }

    @Override
    public void initView(String nickname, String phone) {


        myNickName.setText(nickname);
       input_phone.setText(phone);
    }

    @Override
    public void toAuth() {
        startActivity(new Intent(getActivity(), StudentAuthActivity.class));
    }

    @Override
    public void ToastAuthing() {
        Toast.makeText(getActivity(),"您正在认证,请等待认证结果",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ToastAuthgood() {
        Toast.makeText(getActivity(),"您已认证成功，请跳转班级界面操作",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GetId(String id) {
        Intent intent =new Intent(getActivity(), UpdatePasswdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putInt("type",1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
