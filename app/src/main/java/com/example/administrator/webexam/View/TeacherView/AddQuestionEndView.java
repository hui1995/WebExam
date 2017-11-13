package com.example.administrator.webexam.View.TeacherView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.webexam.Activity.MainActivity;
import com.example.administrator.webexam.Activity.ShowAllPageListActivity;
import com.example.administrator.webexam.Contract.TeacherContract.AddQuestionPagerContract;
import com.example.administrator.webexam.Presenter.TeacherPresenter.AddQuestionPagerPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.NoScrollGridView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQuestionEndView extends Fragment implements AddQuestionPagerContract.View {
    LocalBroadcastManager mLocalBroadcastManager;

    @Bind(R.id.tv_submit_result)
    TextView tvSubmitResult;
    int[] mIds = new int[50];
    public String SHOW_PAGER_ALL ="showpager";
    private String LOGIN_TYPE="login_type";

    private AddQuestionPagerContract.Presenter mPresenter;

    // TODO: Rename and change types and number of parameters
    public static AddQuestionEndView newInstance() {
        AddQuestionEndView fragment = new AddQuestionEndView();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_question_end_view, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new AddQuestionPagerPresenter(this,getActivity());



        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_submit_result)
    public void onViewClicked() {
        mPresenter.submitAdmin();


        //跳转到主页面
    }


    @Override
    public void setPresenter(AddQuestionPagerContract.Presenter presenter) {

    }

    @Override
    public void Toast() {

    }

    @Override
    public void gonext() {
        Intent intent = new Intent(getActivity(), ShowAllPageListActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt(SHOW_PAGER_ALL,1);
        intent.putExtras(bundle);
        startActivity(intent);
       // getActivity().finish();

    }
}
