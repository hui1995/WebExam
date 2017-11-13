package com.example.administrator.webexam.View.TeacherView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.AddQuestionPagerActivity;
import com.example.administrator.webexam.Activity.ShowAllPageListActivity;
import com.example.administrator.webexam.Contract.TeacherContract.TeacherMainContract;
import com.example.administrator.webexam.Contract.TeacherContract.TeacherMainContract.IndexView;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexTeacherView extends Fragment implements IndexView {
    @Bind(R.id.add_paper)
    Button addPaper;
    @Bind(R.id.question_bank)
    Button questionBank;
    @Bind(R.id.my_question_bank)
    Button myQuestionBank;
    private TeacherMainContract.Presenter mPresenter;
    public String SHOW_PAGER_ALL ="showpager";

    public static IndexTeacherView newsInstance() {
        // Required empty public constructor
        return new IndexTeacherView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_index_teacher_view, container, false);
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
        //  mPresenter.getTeacherInfo(getActivity());
//        mPresenter.start();
        mPresenter.startIndex();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.add_paper, R.id.question_bank, R.id.my_question_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_paper:
          mPresenter.isAuto3();
                break;
            case R.id.question_bank:
                showallpager();
                break;
            case R.id.my_question_bank:
                showmypager();
                break;
        }
    }
private void setAddPaper(){

}
    private void showmypager(){
        mPresenter.findExaminStatus();

        Intent intent = new Intent(getActivity(), ShowAllPageListActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt(SHOW_PAGER_ALL,1);
        intent.putExtras(bundle);
        startActivity(intent);
       // getActivity().finish();



    }

    private void showallpager(){


        Intent intent = new Intent(getActivity(), ShowAllPageListActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt(SHOW_PAGER_ALL,0);
        intent.putExtras(bundle);
        startActivity(intent);
       // getActivity().finish();
    }
    @Override
    public void noAuto() {
        Toast.makeText(getActivity(), "教师认证中，无法使用该功能", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextToAddClass() {
        Intent intent = new Intent(getActivity(),AddQuestionPagerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("EID","");
        intent.putExtras(bundle);
        startActivity(intent);
      //  getActivity().finish();


    }

}
