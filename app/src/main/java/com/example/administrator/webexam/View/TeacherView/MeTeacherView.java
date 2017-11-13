package com.example.administrator.webexam.View.TeacherView;


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

import com.example.administrator.webexam.Activity.LoginActivity;
import com.example.administrator.webexam.Activity.UpdatePasswdActivity;
import com.example.administrator.webexam.Contract.TeacherContract.TeacherMainContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeTeacherView extends Fragment implements TeacherMainContract.MeView {

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.myNickName)
    TextView myNickName;


    @Bind(R.id.changepasswd)
    Button changepasswd;
    @Bind(R.id.loginout)
    Button loginout;
    @Bind(R.id.phone)
    TextView input_phone;
    private TeacherMainContract.Presenter mPresenter;

    public static MeTeacherView newsInstance() {
        // Required empty public constructor
        return new MeTeacherView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_teacher_view, container, false);
     //   mPresenter.getTeacherInfo(getActivity());
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(TeacherMainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startMe(getActivity());

    }

    @Override
    public void initView(String nickname, String phone) {
      //  Toast.makeText(getActivity(),nickname,Toast.LENGTH_LONG).show();
myNickName.setText(nickname);
    input_phone.setText(phone);
    }

    @Override
    public void GetId(String id) {
        Intent intent =new Intent(getActivity(), UpdatePasswdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putInt("type",0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);


    }

    @OnClick({ R.id.changepasswd, R.id.loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.changepasswd:
                mPresenter.GetIdInfo();
                break;
            case R.id.loginout:
                mPresenter.loginout(getActivity());
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
