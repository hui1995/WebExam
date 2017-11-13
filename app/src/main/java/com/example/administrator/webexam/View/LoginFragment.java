package com.example.administrator.webexam.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.AdminActivity;
import com.example.administrator.webexam.Activity.MainActivity;
import com.example.administrator.webexam.Activity.RegisterActivity;

import com.example.administrator.webexam.Contract.LoginContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.passwd)
    EditText passwd;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.signup)
    TextView signup;
    private LoginContract.Presenter mPresenter;
    private String LOGIN_TYPE="login_type";


    public static LoginFragment newInstance() {

        return new LoginFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_login, container, false);


        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
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


    @Override
    public void setMessage() {

    }

    @Override
    public void ToastNoUser() {
        Toast.makeText(getActivity(),"该用户不存在",Toast.LENGTH_LONG).show();
    }

    @Override
    public void ToastPasswdfail() {
Toast.makeText(getActivity(),"密码错误，请核对密码",Toast.LENGTH_LONG).show();
    }

    @Override
    public void goStudentIndex() {
      Intent intent = new Intent(getActivity(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_TYPE,"student");
        intent.putExtras(bundle);


        startActivity(intent);
        getActivity().finish();
      //  Toast.makeText(getActivity(),"student",Toast.LENGTH_LONG).show();
    }

    @Override
    public void goTeacherIndex() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_TYPE,"teacher");
        intent.putExtras(bundle);


        startActivity(intent);
        getActivity().finish();
     //   Toast.makeText(getActivity(),"teacher",Toast.LENGTH_LONG).show();
    }

    @Override
    public void goAdminIndex() {
        Intent intent = new Intent(getActivity(), AdminActivity.class);



        startActivity(intent);
        getActivity().finish();
       // Toast.makeText(getActivity(),"admin",Toast.LENGTH_LONG).show();
    }


    @OnClick({R.id.login, R.id.signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:

                mPresenter.getLogininfo(getActivity(),phone.getText().toString(),passwd.getText().toString());
                break;
            case R.id.signup:
                startActivity(new Intent(getActivity(), RegisterActivity.class));


                break;
        }
    }
}
