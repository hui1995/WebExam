package com.example.administrator.webexam.View.TeacherView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.webexam.Contract.TeacherContract.AddQuestionPagerContract;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Presenter.TeacherPresenter.AddQuestionPagerPresenter;
import com.example.administrator.webexam.R;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionIndexView extends Fragment implements AddQuestionPagerContract.View{

    LocalBroadcastManager mLocalBroadcastManager;
    @Bind(R.id.examin_name)
    EditText examinName;
    @Bind(R.id.add_paper)
    Button addPaper;
    private AddQuestionPagerContract.Presenter mPresenter;


    public AddQuestionIndexView newsInstance() {


        return new AddQuestionIndexView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_question_index_view, container, false);
        mPresenter = new AddQuestionPagerPresenter(this,getActivity());
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.add_paper)
    public void onViewClicked() {

       savetitle();


    }

    @Override
    public void setPresenter(AddQuestionPagerContract.Presenter presenter) {

    }
    private void savetitle(){
        mPresenter.savetitle(examinName.getText().toString());
    }

    @Override
    public void Toast() {
        Toast.makeText(getActivity(),"请填写试卷名称",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gonext() {
        Intent intent = new Intent("com.leyikao.jumptonext");
        mLocalBroadcastManager.sendBroadcast(intent);
    }
}
